package com.example.selflearning.repository

import com.example.selflearning.network.RoomApi
import com.example.selflearning.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface RoomRepository {
    val roomresponseFlow: StateFlow<UIState>
    val peopleresponseFlow: StateFlow<UIState>
    suspend fun roomData()
    suspend fun peopleData()
}

class RoomRepositoryImpl(
    private val roomApi: RoomApi
) : RoomRepository {

    private val _roomresponseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val roomresponseFlow: StateFlow<UIState>
        get() = _roomresponseFlow

    private val _peopleresponseFlow: MutableStateFlow<UIState> = MutableStateFlow(UIState.LOADING())

    override val peopleresponseFlow: StateFlow<UIState>
        get() = _peopleresponseFlow

    override suspend fun roomData() {
        try {
            val response = roomApi.roomData()

            if (response.isSuccessful) {
                response.body()?.let {
                    _roomresponseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _roomresponseFlow.value = UIState.ERROR(IllegalStateException("User details are coming as null!"))
                }
            } else {
                _roomresponseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _roomresponseFlow.value = UIState.ERROR(e)
        }
    }

    override suspend fun peopleData() {
        try {
            val response = roomApi.peopleData()

            if (response.isSuccessful) {
                response.body()?.let {
                    _peopleresponseFlow.value = UIState.SUCCESS(it)
                } ?: run {
                    _peopleresponseFlow.value = UIState.ERROR(IllegalStateException("Tag details are coming as null!"))
                }

            } else {
                _peopleresponseFlow.value = UIState.ERROR(Exception(response.errorBody()?.string()))
            }
        } catch (e: Exception) {
            _peopleresponseFlow.value = UIState.ERROR(e)
        }
    }


}
