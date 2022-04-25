package com.example.selflearning.di

import com.example.selflearning.network.RoomApi
import com.example.selflearning.repository.RoomRepository
import com.example.selflearning.repository.RoomRepositoryImpl
import com.example.selflearning.viewmodel.RoomViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    // provides the room repository implementation
    fun roomRepository(roomApi: RoomApi): RoomRepository = RoomRepositoryImpl(roomApi)

    // provide Gson object
    fun provideGson() = GsonBuilder().create()

    // provide logging interceptor
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // provide okhttp client
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    // providing the retrofit builder
    fun roomApi(okHttpClient: OkHttpClient, gson: Gson): RoomApi =
        retrofit2.Retrofit.Builder()
            .baseUrl(RoomApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(RoomApi::class.java)

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { roomApi(get(), get()) }
    single { roomRepository(get()) }
}

val viewModelModule = module {
    viewModel {
        RoomViewModel(get())
    }
}