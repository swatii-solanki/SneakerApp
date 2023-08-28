package com.crickbuzztest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crickbuzztest.R
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.databinding.FragmentCartBinding
import com.crickbuzztest.ui.adapter.CartAdapter
import com.crickbuzztest.ui.adapter.CartClickListener
import com.crickbuzztest.ui.viewmodel.CartViewModel
import com.crickbuzztest.utils.MyLoader
import com.crickbuzztest.utils.Resource
import com.crickbuzztest.utils.showErrorMsg
import com.crickbuzztest.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment(), CartClickListener {

    private val viewmodel: CartViewModel by viewModels()

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private lateinit var loader: MyLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initLoader()
        clickListener()
        setUpCartRecyclerView()
        fetchCartData()
        calculatePrice()
    }

    private fun clickListener() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }

    private fun initLoader() {
        loader = MyLoader(requireContext())
    }

    private fun setUpCartRecyclerView() {
        cartAdapter = CartAdapter(this)
        binding.rvCartList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cartAdapter
        }
    }

    private fun fetchCartData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.cartState.collectLatest {
                when (it) {
                    is Resource.Loading -> loader.show()

                    is Resource.Success -> {
                        loader.dismiss()
                        cartAdapter.setCartData(it.value)
                    }

                    is Resource.Failure -> {
                        loader.dismiss()
                        requireContext().showErrorMsg()
                    }
                }
            }
        }
    }

    private fun calculatePrice() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewmodel.cartPrice.collectLatest {
                if (it == null) {
                    binding.tvTotalValue.text = "$0"
                } else {
                    binding.tvTotalValue.text = "$$it"
                }
            }
        }
    }

    override fun onClick(cart: Cart) {
        viewmodel.removeItemFromCart(cart)
        requireContext().showToast("Removed from cart..")
    }
}