package com.example.gethub.util

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.gethub.R

object ColorType {

    fun TextView?.setColor(context: Context, type: String?) {
        when (type) {
            "User" -> this?.apply {
                setTextColor(ContextCompat.getColor(context, R.color.gray))
                text = type
            }
            "Organization" -> this?.apply {
                setTextColor(ContextCompat.getColor(context, R.color.gray))
                text = type
            }
        }
    }
}