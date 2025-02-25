package com.dsoft.mynewtestapp.data.util

import android.content.SharedPreferences
import javax.inject.Inject

private const val KEY_INITIATED = "initiated"
class PrefsWrapper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    fun setInitiated() {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_INITIATED, true)
        editor.apply()
    }

    fun isInitiated(): Boolean {
        return sharedPreferences.getBoolean(KEY_INITIATED, false)
    }

}