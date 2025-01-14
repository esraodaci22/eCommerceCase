package com.example.ecommercecase.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun String.toCurrencyFormat(currencySymbol: String = "₺"): String {
    return "$this $currencySymbol"
}
fun ImageView.loadImage(url: String?, scaleType: ImageView.ScaleType = ImageView.ScaleType.CENTER_CROP) {
    this.scaleType = scaleType
    if (!url.isNullOrEmpty()) {
        Glide.with(this.context)
            .load(url)
            .apply(RequestOptions().centerCrop())
            .into(this)
    }
}