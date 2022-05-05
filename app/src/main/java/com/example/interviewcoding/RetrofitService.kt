package com.example.interviewcoding

import com.example.interviewcoding.data.NycDetails
import com.example.interviewcoding.data.NycResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET


interface RetrofitService {

    @GET("s3k6-pzi2.json")
    fun getNYCList(): Call<List<NycResponse>>

    @GET("f9bf-2cp4.json")
    fun getNYCDetails(): Call<List<NycDetails>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {

                val retrofit =
                    Retrofit.Builder().baseUrl("https://data.cityofnewyork.us/resource/")
                        .addConverterFactory(GsonConverterFactory.create()).build()

                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}
