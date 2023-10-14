package com.example.dogbreedapp.utils

import java.util.regex.Pattern

object Validations {

    fun String.isValidUsername() = this.isNotBlank()

    fun String.isValidFullName() = this.isNotBlank()

    fun String.isValidPassword(): Boolean {
        val passwordPattern = "^(?=.*[A-Z])(?=.*\\d)(?=.*\\W).{8,}$"

        val pattern = Pattern.compile(passwordPattern)

        val matcher = pattern.matcher(this)

        return matcher.find() && this.isNotBlank()
    }

}