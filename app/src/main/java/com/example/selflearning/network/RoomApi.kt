package com.example.selflearning.network

import com.example.selflearning.People
import com.example.selflearning.Rooms
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RoomApi {
    @GET(
        ROOM_DATA
    )
    suspend fun roomData():Response<Rooms>

    @GET(
        PEOPLE_DATA
    )
    suspend fun peopleData():Response<People>


    companion object{
        const val IMAGE_URL="https://randomuser.me/api/portraits/"
        const val BASE_URL = "https://61d6afbe35f71e0017c2e74b.mockapi.io/api/v1/"
        const val ROOM_DATA = "rooms"
        const val PEOPLE_DATA = "people"

    }
}