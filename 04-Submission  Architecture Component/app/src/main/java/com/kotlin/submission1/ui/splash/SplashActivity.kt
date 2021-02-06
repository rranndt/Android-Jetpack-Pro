package com.kotlin.submission1.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.submission1.databinding.ActivitySplashBinding
import com.kotlin.submission1.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            finish()
        }, 2000)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}