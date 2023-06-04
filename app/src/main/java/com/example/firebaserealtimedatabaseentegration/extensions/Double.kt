package com.example.firebasedbentegration.extensions

import java.math.RoundingMode
import java.text.DecimalFormat

fun Double.addTL(): String {
    return "$this TL"
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)
