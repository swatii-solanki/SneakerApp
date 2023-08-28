package com.crickbuzztest.utils

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import androidx.databinding.DataBindingUtil
import com.crickbuzztest.R
import com.crickbuzztest.databinding.ItemLoaderBinding

class MyLoader(context: Context) {

    private var dialog: Dialog

    fun show() {
        dialog.show()
    }

    fun dismiss() {
        if (dialog.isShowing) dialog.dismiss()
    }

    init {
        val inflater = LayoutInflater.from(context)
        val binding: ItemLoaderBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_loader, null, false)
        dialog = Dialog(context)
        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(binding.root)
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCancelable(false)
    }

}