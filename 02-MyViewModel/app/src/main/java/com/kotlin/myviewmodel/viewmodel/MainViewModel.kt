package com.kotlin.myviewmodel.viewmodel

import androidx.lifecycle.ViewModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel : ViewModel() {

    // Membuat variable penampung untuk dipertahankan datanya
    var result = 0

    // Metode calculate untuk menghitung volume
    fun calculate(width: String, height: String, length: String) {
        // Mengubah hasil result dari hasil perkalian lebar, tinggi, dan panjang
        result = width.toInt() * height.toInt() * length.toInt()
    }
}