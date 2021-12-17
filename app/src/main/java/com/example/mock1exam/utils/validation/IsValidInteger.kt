package com.example.mock1exam.utils.validation

fun isValidInteger(it: String): Boolean {
    return it.toIntOrNull() != null
}