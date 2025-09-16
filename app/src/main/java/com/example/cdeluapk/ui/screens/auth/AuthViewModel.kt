package com.example.cdeluapk.ui.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cdeluapk.data.model.AuthResponse
import com.example.cdeluapk.data.model.User
import com.example.cdeluapk.data.repository.MainRepository
// import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
// import javax.inject.Inject

data class AuthUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

// @HiltViewModel
class AuthViewModel /* @Inject constructor(
    private val repository: MainRepository
) */ : ViewModel() {
    
    // Instancia temporal sin DI
    private val repository: MainRepository = MainRepository()

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email: String, password: String, onSuccess: (AuthResponse) -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.login(email, password)
            result.fold(
                onSuccess = { authResponse ->
                    _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
                    onSuccess(authResponse)
                },
                onFailure = { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Error de autenticación"
                    )
                }
            )
        }
    }

    fun register(name: String, email: String, password: String, passwordConfirmation: String, onSuccess: (User) -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.register(name, email, password, passwordConfirmation)
            result.fold(
                onSuccess = { user ->
                    _uiState.value = _uiState.value.copy(isLoading = false, isSuccess = true)
                    onSuccess(user)
                },
                onFailure = { exception ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message ?: "Error de registro"
                    )
                }
            )
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun resetState() {
        _uiState.value = AuthUiState()
    }
}