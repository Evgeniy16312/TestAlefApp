package com.example.testalefapp.data

import retrofit2.http.GET

interface NetworkAPI {
    @GET("task-m-001/list.php")
    suspend fun getPictures(): List<String>
}