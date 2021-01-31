package com.kotlin.academy.ui.detail

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.kotlin.academy.R
import com.kotlin.academy.databinding.ActivityDetailCourseBinding

class DetailCourseActivity : AppCompatActivity() {

    private var _binding: ActivityDetailCourseBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val EXTRA_COURSE = "extra_course"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        binding.detailContent
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}