package com.sfjava.dkquakes.ui

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:background")
fun setBackground(view: View, toggle: Boolean) {
    if(toggle) {
        // FIXME: define this highlight color in a colors resource
        view.setBackgroundColor(Color.argb(100, 200, 30, 0))
    } else {
        view.setBackgroundColor(Color.TRANSPARENT)
    }
}
