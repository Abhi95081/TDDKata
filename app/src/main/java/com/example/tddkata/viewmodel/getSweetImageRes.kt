package com.example.tddkata.viewmodel

import com.example.tddkata.R

// -------------------- IMAGE MAPPER --------------------
fun getSweetImageRes(name: String): Int {
    return when (name.lowercase()) {
        "samosa" -> R.drawable.samosa
        "gulab jamun" -> R.drawable.gulabjamun
        "ladoo" -> R.drawable.ladoo
        else -> R.drawable.samosa
    }
}
