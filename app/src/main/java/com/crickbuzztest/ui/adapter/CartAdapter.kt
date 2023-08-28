package com.crickbuzztest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.crickbuzztest.R
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.databinding.ItemCartBinding

class CartAdapter(private val listener: CartClickListener) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var list: List<Cart> = emptyList()

    class CartViewHolder(val context: Context,val cartBinding: ItemCartBinding) :
        RecyclerView.ViewHolder(cartBinding.root) {

        fun bindData(data: Cart) {
            cartBinding.tvName.text = data.name
            cartBinding.tvPrice.text = "$${data.retailPrice}"
            Glide.with(context)
                .load(data.imageUrl)
                .override(200, 200)
                .into(cartBinding.ivSneaker)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding: ItemCartBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_cart,
            parent,
            false
        )
        return CartViewHolder(parent.context,binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val data = list[position]
        holder.bindData(data)
        holder.cartBinding.ivCross.setOnClickListener {
            listener.onClick(data)
        }
    }

    override fun getItemCount() = list.size

    fun setCartData(list: List<Cart>) {
        this.list = list
        notifyDataSetChanged()
    }
}

interface CartClickListener {
    fun onClick(cart: Cart)
}