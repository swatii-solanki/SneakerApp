package com.crickbuzztest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crickbuzztest.R
import com.crickbuzztest.data.model.Sneaker
import com.crickbuzztest.databinding.ItemSneakerBinding

class SneakerAdapter(private val listener: SneakerClickListener) :
    RecyclerView.Adapter<SneakerAdapter.SneakerViewHolder>() {

    private var list: List<Sneaker> = emptyList()

    class SneakerViewHolder(
        private val context: Context,
        val sneakerBinding: ItemSneakerBinding,
    ) :
        RecyclerView.ViewHolder(sneakerBinding.root) {

        fun bindData(data: Sneaker) {
            sneakerBinding.tvName.text = data.name
            sneakerBinding.tvPrice.text = "$${data.retailPrice}"
            Glide.with(context)
                .load(data.imageUrl)
                .override(200, 200)
                .into(sneakerBinding.ivSneaker)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SneakerViewHolder {
        val binding: ItemSneakerBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_sneaker,
            parent,
            false
        )
        return SneakerViewHolder(parent.context, binding)
    }

    override fun onBindViewHolder(holder: SneakerViewHolder, position: Int) {
        val data = list[position]
        holder.bindData(data)

        holder.sneakerBinding.ivAdd.setOnClickListener {
            listener.onClick(data)
        }
        holder.itemView.setOnClickListener {
            listener.navigateToDetail(data)
        }
    }

    override fun getItemCount() = list.size

    fun setSneakerData(list: List<Sneaker>) {
        this.list = list
        notifyDataSetChanged()
    }
}

interface SneakerClickListener {
    fun onClick(sneaker: Sneaker)
    fun navigateToDetail(sneaker: Sneaker)
}