package com.example.interviewcoding.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewcoding.data.NycDetails
import com.example.interviewcoding.data.NycResponse
import com.example.interviewcoding.repo.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val nycListResponse = MutableLiveData<List<NycResponse>>()
    val nycDetailsResponse = MutableLiveData<List<NycDetails>>()
    val errorMessage = MutableLiveData<String>()

    fun getNYCList() {
        val response = repository.getNYCList()

        response.enqueue(object : Callback<List<NycResponse>>{
            override fun onResponse(
                call: Call<List<NycResponse>>,
                response: Response<List<NycResponse>>
            ) {
                if(response.isSuccessful){
                    nycListResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<NycResponse>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
    fun getNyCDetails() {
        val response = repository.getNYCDetails()

        response.enqueue(object : Callback<List<NycDetails>>{
            override fun onResponse(
                call: Call<List<NycDetails>>,
                response: Response<List<NycDetails>>
            ) {
                if(response.isSuccessful){
                    nycDetailsResponse.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<NycDetails>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}