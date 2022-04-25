package com.example.selflearning.repository

import com.example.selflearning.network.RoomApi
import com.example.selflearning.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class Repository {

    suspend fun getPeople() = com.example.selflearning.network.Retrofit.TestApi.peopleData()

    suspend fun getRoom() =  com.example.selflearning.network.Retrofit.TestApi.roomData()
}