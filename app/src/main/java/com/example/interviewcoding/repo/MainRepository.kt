package com.example.interviewcoding.repo

import com.example.interviewcoding.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService){
    fun getNYCList() = retrofitService.getNYCList()
    fun getNYCDetails() = retrofitService.getNYCDetails()
}