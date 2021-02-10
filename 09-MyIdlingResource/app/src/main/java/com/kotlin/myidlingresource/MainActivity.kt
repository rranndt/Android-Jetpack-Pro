package com.kotlin.myidlingresource

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.myidlingresource.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            delay1()
            delay2()
        }
    }

    private fun delay1() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.textView.text = getString(R.string.delay1)
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                // Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement()
            }
        }, 2000)
    }

    private fun delay2() {
        EspressoIdlingResource.increment()
        Handler(Looper.getMainLooper()).postDelayed({
            binding.textView.text = getString(R.string.delay2)
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                // Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement()
            }
        }, 3000)
    }
}