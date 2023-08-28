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
import androidx.recyclerview.widget.GridLayoutManager
import com.crickbuzztest.R
import com.crickbuzztest.data.model.Sneaker
import com.crickbuzztest.databinding.FragmentHomeBinding
import com.crickbuzztest.ui.adapter.SneakerAdapter
import com.crickbuzztest.ui.adapter.SneakerClickListener
import com.crickbuzztest.ui.viewmodel.HomeViewModel
import com.crickbuzztest.utils.MyLoader
import com.crickbuzztest.utils.Resource
import com.crickbuzztest.utils.showErrorMsg
import com.crickbuzztest.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), SneakerClickListener {

    private val viewmodel: HomeViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var sneakerAdapter: SneakerAdapter
    private lateinit var loader: MyLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initLoader()
        setUpSneakerRecyclerView()
        fetchSneakerData()
    }

    private fun initLoader() {
        loader = MyLoader(requireContext())
    }

    private fun setUpSneakerRecyclerView() {
        sneakerAdapter = SneakerAdapter(this)
        binding.rvSneakerList.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = sneakerAdapter
        }
    }

    private fun fetchSneakerData() {
        lifecycleScope.launch {
            viewmodel.sneakersState.collectLatest {
                when (it) {
                    is Resource.Loading -> loader.show()

                    is Resource.Success -> {
                        loader.dismiss()
                        sneakerAdapter.setSneakerData(it.value)
                    }

                    is Resource.Failure -> {
                        loader.dismiss()
                        requireContext().showErrorMsg()
                    }
                }
            }
        }
    }

    override fun onClick(sneaker: Sneaker) {
        viewmodel.insertIntoCart(sneaker)
        requireContext().showToast("Added into cart..")
    }

    override fun navigateToDetail(sneaker: Sneaker) {
        val bundle = Bundle()
        bundle.putParcelable("sneaker", sneaker)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}