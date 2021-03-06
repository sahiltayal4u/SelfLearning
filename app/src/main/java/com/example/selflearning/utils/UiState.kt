package com.example.selflearning.utils


    sealed class UIState {
        class SUCCESS(val success: Any): UIState()
        class LOADING(val isLoading: Boolean = true) : UIState()
        class ERROR(val error: Throwable): UIState()
    }