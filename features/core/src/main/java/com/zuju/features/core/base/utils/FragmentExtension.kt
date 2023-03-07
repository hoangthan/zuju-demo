package com.zuju.features.core.base.utils

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.showToast(content: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), content, duration).show()
}

fun Fragment.showToast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), stringRes, duration).show()
}
