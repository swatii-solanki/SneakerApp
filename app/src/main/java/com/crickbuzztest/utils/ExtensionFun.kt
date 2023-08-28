package com.crickbuzztest.utils

import android.content.Context
import android.widget.Toast
import com.crickbuzztest.R

fun Context.showErrorMsg(errorMsg: String? = null) {
    if (!errorMsg.isNullOrEmpty())
        showToast(errorMsg)
    else
        showToast(getString(R.string.something_went_wrong))
}

fun Context.showToast(
    msg: String,
) {
    val toast = Toast.makeText(this, msg, Toast.LENGTH_SHORT)
    toast.show()
}
