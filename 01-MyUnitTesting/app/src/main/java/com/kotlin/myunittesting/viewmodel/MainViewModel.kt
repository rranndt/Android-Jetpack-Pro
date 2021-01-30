package com.kotlin.myunittesting.viewmodel

import com.kotlin.myunittesting.model.CuboidModel

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel(private val cuboidModel: CuboidModel) {

    fun getCircumference() = cuboidModel.getCircumference()

    fun getSurfaceArea() = cuboidModel.getSurfaceArea()

    fun getVolume() = cuboidModel.getVolume()

    fun save(w: Double, l: Double, h: Double) {
        cuboidModel.save(w, l, h)
    }

}