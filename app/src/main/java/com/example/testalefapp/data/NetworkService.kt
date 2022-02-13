package com.example.testalefapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService{
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://dev-tasks.alef.im/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getData(): NetworkAPI = retrofit.create(NetworkAPI::class.java)

    companion object{
        val instance: NetworkService = NetworkService()
    }
}