package com.maden.filmlist.common

import android.view.View
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.databinding.BindingAdapter
import com.maden.filmlist.R
import com.maden.filmlist.domain.use_cases.remote.ImageUseCase

@BindingAdapter("app:downloadImageWithUrl")
fun downloadImageWithUrl(view: View, url: String?) {
    val imageView = view as? ImageView ?: return
    imageView.downloadImageWithUrl(url = url)
}

@BindingAdapter("app:isFavoriteCheck")
fun isFavoriteCheck(view: View, boolean: Boolean) {
    val imageView = view as? ImageView ?: return
    if (boolean) imageView.setImageResource(R.drawable.star)
}


fun ImageView.downloadImageWithUrl(url: String?) {
    if (url.isNullOrEmpty()) {
        setNoPicturesImage(this)
        return
    }

    ImageUseCase.execute(
        url = url,
        bitmap = { bitmap ->
            bitmap?.let {
                this.setImageBitmap(bitmap)
            } ?: run {
                setNoPicturesImage(this)
            }
        },
        exception = {
            setNoPicturesImage(this)
        }
    )
}

private fun setNoPicturesImage(imageView: ImageView) {
    imageView.setImageDrawable(
        AppCompatResources.getDrawable(
            imageView.context,
            R.drawable.no_pictures
        )
    )
}