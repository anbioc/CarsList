package com.sevenpeakssoftware.amirnaghavi.presentation

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.sevenpeakssoftware.amirnaghavi.databinding.ActivityNavigationBinding
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class NavigationActivity : DaggerAppCompatActivity() {

    lateinit var binding: ActivityNavigationBinding

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>
    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        makeFullScreen()
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.apply {
            hide()
        }
    }

}