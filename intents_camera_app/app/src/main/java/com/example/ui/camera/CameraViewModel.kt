package com.example.ui.camera

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CameraViewModel : ViewModel() {
    private val _capturedImage = MutableStateFlow<Bitmap?>(null)

    val capturedImage = _capturedImage.asStateFlow()

    fun onImageCaptured(bitmap: Bitmap?) {
        if (bitmap != null) {
            _capturedImage.value = bitmap
        }
    }
}