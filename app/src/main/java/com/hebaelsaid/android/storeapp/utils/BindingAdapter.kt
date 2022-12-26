package com.hebaelsaid.android.storeapp.utils

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition


@BindingAdapter("android:bindImgUrl", "android:bindProgressItem")
fun setGlideImageUrl(
    image: ImageView,
    url: String?,
    progressBar: ProgressBar?,
) {
    image.visibility = View.VISIBLE
    /*Glide.with(image.context)
        .load(url)
       // .diskCacheStrategy(DiskCacheStrategy.NONE)
      //  .override(image.width,image.height)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                image.visibility = View.GONE
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                progressBar?.visibility = View.GONE
                return false
            }
        }).into(image)*/

    Glide.with(image.context)
        .asBitmap()
        .load(url)
        .into(object : SimpleTarget<Bitmap?>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                progressBar?.visibility = View.GONE
                image.requestLayout()
                image.setImageBitmap(resource)
            }
        })
}
