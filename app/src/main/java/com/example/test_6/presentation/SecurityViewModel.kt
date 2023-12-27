package com.example.test_6.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_6.common.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SecurityViewModel : ViewModel() {
     val numberButtons = mutableListOf<Numbers>(
        Numbers("1"),
        Numbers("2"),
        Numbers("3"),
        Numbers("4"),
        Numbers("5"),
        Numbers("6"),
        Numbers("7"),
        Numbers("8"),
        Numbers("9"),
        Numbers(null, "p"),
        Numbers("0"),
        Numbers(null, "d")
    )
    private val _buttonFlow = MutableStateFlow<List<Numbers>>(numberButtons)
    val buttonFlow: SharedFlow<List<Numbers>> = _buttonFlow.asStateFlow()


    private val password: String = "0934"
    private val _passwordCheck = MutableStateFlow<Resource<String>>(Resource.Loading(isLoading = false))
    val passwordCheck: SharedFlow<Resource<String>> = _passwordCheck.asStateFlow()

    fun checkPassword(userInput: String) {
        _passwordCheck.value = Resource.Loading(isLoading = true)
        viewModelScope.launch {
            if (userInput != password) {
                _passwordCheck.value = Resource.Fail(errorMessage = "Password is incorrect")
            } else {
                _passwordCheck.value = Resource.Success(successMessage = "Password is incorrect")
            }
        }
        _passwordCheck.value = Resource.Loading(isLoading = false)
    }

}
