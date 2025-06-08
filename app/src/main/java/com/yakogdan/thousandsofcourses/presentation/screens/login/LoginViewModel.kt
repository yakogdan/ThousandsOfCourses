package com.yakogdan.thousandsofcourses.presentation.screens.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")

    fun onEmailChanged(text: String) {
        email.value = text
    }

    fun onPasswordChanged(text: String) {
        password.value = text
    }

    val isEmailPasswordValid: StateFlow<Boolean> = combine(email, password) { email, password ->
        isEmailValid(email) && password.isNotBlank()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    private fun isEmailValid(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches() && !email.any { it in 'А'..'я' }
    }
}