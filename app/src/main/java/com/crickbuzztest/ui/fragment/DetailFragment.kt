package com.crickbuzztest.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.crickbuzztest.R
import com.crickbuzztest.data.model.Sneaker
import com.crickbuzztest.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        clickListener()
        val sneaker = requireArguments().getParcelable<Sneaker>("sneaker")
        binding.tvHeading.text = sneaker?.name
        binding.tvTotalValue.text = "$${sneaker?.retailPrice}"
        Glide.with(requireContext())
            .load(sneaker?.imageUrl)
            .override(200, 200)
            .into(binding.ivSneaker)
    }

    private fun clickListener() {
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
    }
}