package com.example.selflearning.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.selflearning.People
import com.example.selflearning.Rooms
import com.example.selflearning.network.RoomApi
import com.example.selflearning.repository.Repository
import com.example.selflearning.repository.RoomRepository
import com.example.selflearning.repository.utils.Event
import com.example.selflearning.repository.utils.Resource
import com.example.selflearning.utils.UIState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RoomViewModel(
    private val roomRepository: RoomRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(SupervisorJob() + ioDispatcher)
) : CoroutineScope by coroutineScope, ViewModel(){
    private val _roomdata : MutableLiveData<UIState> = MutableLiveData<UIState>()
    val roomdata : LiveData<UIState> get() = _roomdata

    private val _peopledata : MutableLiveData<UIState> = MutableLiveData<UIState>()
    val peopledata : LiveData<UIState> get() = _peopledata

   /* fun getRoomData(){
        viewModelScope.launch {
            val response=appRepository.getRoom()
            _roomdata.postValue(handleRoomResponse(response))
        }
    }

    fun getPeopleData(){
        viewModelScope.launch {
            val response=appRepository.getPeople()
            _peopledata.postValue(handlePeopleResponse(response))
        }
    }*/



    /*private fun handleRoomResponse(response: Response<Rooms>): Event<Resource<Rooms>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }

    private fun handlePeopleResponse(response: Response<People>): Event<Resource<People>>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Event(Resource.Success(resultResponse))
            }
        }
        return Event(Resource.Error(response.message()))
    }*/

    fun getRoomData() {
        _roomdata.postValue(UIState.LOADING())
        collectUsersList()
        launch {
            roomRepository.roomData()
        }
    }

    private fun collectUsersList() {
        launch {
            roomRepository.roomresponseFlow.collect { uiState ->
                when(uiState) {
                    is UIState.LOADING -> { _roomdata.postValue(uiState) }
                    is UIState.SUCCESS -> { _roomdata.postValue(uiState) }
                    is UIState.ERROR -> { _roomdata.postValue(uiState) }
                }
            }
        }
    }

    fun getPeopleData() {
        _peopledata.postValue(UIState.LOADING())
        collectTagsList()
        launch {
            roomRepository.peopleData()
        }
    }

    private fun collectTagsList() {
        launch {
            roomRepository.peopleresponseFlow.collect { uiState ->
                when(uiState) {
                    is UIState.LOADING -> { _peopledata.postValue(uiState) }
                    is UIState.SUCCESS -> { _peopledata.postValue(uiState) }
                    is UIState.ERROR -> { _peopledata.postValue(uiState) }
                }
            }
        }
    }

}
