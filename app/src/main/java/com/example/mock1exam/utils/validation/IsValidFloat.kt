package com.example.mock1exam.utils.validation

fun isValidFloat(it: String): Boolean {
    return it.toFloatOrNull() != null
}