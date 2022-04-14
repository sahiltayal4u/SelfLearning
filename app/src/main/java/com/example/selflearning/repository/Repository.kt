package com.example.selflearning.repository


class Repository {

    suspend fun getPeople() = com.example.selflearning.network.Retrofit.TestApi.peopleData()

    suspend fun getRoom() =  com.example.selflearning.network.Retrofit.TestApi.roomData()
}