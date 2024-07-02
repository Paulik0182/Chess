package com.nayya.chess.utils.image

import android.graphics.drawable.Drawable

interface ImageLoader<T> {

    fun loadInto(url: String, container: T)
    fun loadInto(resource: Drawable, container: T)
    fun loadInto(resource: Int, container: T)
}