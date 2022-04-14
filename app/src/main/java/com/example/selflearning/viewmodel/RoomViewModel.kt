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
import com.example.selflearning.repository.utils.Event
import com.example.selflearning.repository.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class RoomViewModel(
    app: Application,
    private val appRepository: Repository
): ViewModel() {
    private val _roomdata : MutableLiveData<Event<Resource<Rooms>>> = MutableLiveData<Event<Resource<Rooms>>>()
    val roomdata : LiveData<Event<Resource<Rooms>>> get() = _roomdata

    private val _peopledata : MutableLiveData<Event<Resource<People>>> = MutableLiveData<Event<Resource<People>>>()
    val peopledata : LiveData<Event<Resource<People>>> get() = _peopledata

    fun getRoomData(){
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
    }

    private fun handleRoomResponse(response: Response<Rooms>): Event<Resource<Rooms>>? {
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
    }
}
