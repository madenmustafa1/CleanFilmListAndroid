package com.maden.filmlist.data.service.image

import android.graphics.Bitmap
import com.maden.easy_bitmap.EasyBitmap

class DownloadImage {

    private val _easyBitmap = EasyBitmap()

    fun withUrl(
        url: String,
        exception: (exception: Exception) -> Unit = { },
        headers: Map<String, String>? = null,
        bitmap: (bitmap: Bitmap?) -> Unit
    ) {
        _easyBitmap.downloadImage(
            url = url,
            headers = headers,
            exception = exception,
            bitmap = bitmap
        )
    }
}