package com.sevenpeakssoftware.amirnaghavi.presentation

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import dagger.android.support.DaggerAppCompatActivity

class NavigationActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeFullScreen()

    }

    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        supportActionBar?.apply {
            hide()
        }
    }
}