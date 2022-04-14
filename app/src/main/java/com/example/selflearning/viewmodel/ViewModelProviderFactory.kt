package com.example.selflearning.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.selflearning.repository.Repository


class ViewModelProviderFactory(
    val app: Application,
    val appRepository: Repository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoomViewModel::class.java)) {
            return RoomViewModel(app, appRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}