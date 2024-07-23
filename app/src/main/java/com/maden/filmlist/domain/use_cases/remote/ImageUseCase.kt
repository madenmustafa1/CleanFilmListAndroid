package com.maden.filmlist.domain.use_cases.remote

import android.graphics.Bitmap
import com.maden.filmlist.data.remote.service.image.DownloadImage

object ImageUseCase {
    private val downloadImage = DownloadImage()

    fun execute(
        url: String,
        exception: (exception: Exception) -> Unit = { },
        headers: Map<String, String>? = null,
        bitmap: (bitmap: Bitmap?) -> Unit
    ) {
        downloadImage.withUrl(
            url = url,
            headers = headers,
            exception = exception,
            bitmap = bitmap
        )
    }
}