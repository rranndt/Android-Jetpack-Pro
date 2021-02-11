package com.kotlin.submission2.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.kotlin.submission2.R
import com.kotlin.submission2.databinding.ActivitySplashBinding
import com.kotlin.submission2.ui.home.HomeActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
//            finish()
//        }, 2000)

        lifecycleScope.launch {
            delay(2000L)
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}