package com.example.pokedex.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pokedex.R

abstract class BaseActivity(private val color: Int = 0): AppCompatActivity() {

    companion object {
        const val WHITE = 0
        const val GRAY = 1
        const val BLACK = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val color = when(color) {
            WHITE -> R.color.white
            GRAY -> R.color.gray
            BLACK -> {
                window?.statusBarColor = ContextCompat.getColor(this, R.color.black)
                window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
                return
            }
            else -> R.color.white
        }

        window?.statusBarColor = ContextCompat.getColor(this, color)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}