package com.example.firebaserealtimedatabaseentegration.extensions

fun String?.safeGet(): String {
    return if (this.isNullOrEmpty()) "" else this
}