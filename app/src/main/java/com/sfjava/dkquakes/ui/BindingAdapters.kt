package com.sfjava.dkquakes.ui

import android.widget.TextView

import androidx.databinding.BindingAdapter
import java.text.DateFormat
import java.util.*

@BindingAdapter("android:text")
fun setText(view: TextView, date: Date?) {
    if (date != null) {
        val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        val localizedDate: String = df.format(date)
        view.text = localizedDate
    }
}

@BindingAdapter("android:text")
fun setText(view: TextView, value: Double?) {
    if (value != null) {
        view.text = value.toString()
    }
}

@BindingAdapter("android:date")
fun setDate(view: TextView, date: Date?) {
    if (date != null) {
        val df: DateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
        val localizedDate: String = df.format(date)
        view.text = localizedDate
    }
}
