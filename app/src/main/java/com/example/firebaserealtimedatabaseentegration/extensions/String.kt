package com.example.firebaserealtimedatabaseentegration.extensions

fun String?.safeGet(): String {
    return if (this.isNullOrEmpty()) "" else this
}

fun String.addTL(): String {
    return "$this TL"
}