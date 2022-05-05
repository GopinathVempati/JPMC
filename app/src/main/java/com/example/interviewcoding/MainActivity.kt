package com.example.interviewcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.interviewcoding.databinding.ActivityMainBinding
import com.example.interviewcoding.databinding.ItemNyclistBinding
import com.example.interviewcoding.repo.MainRepository
import com.example.interviewcoding.viewmodel.MainViewModel
import com.example.interviewcoding.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository((retrofitService)))).get(
                MainViewModel::class.java
            )

        binding.nycListRv.adapter = adapter

        viewModel.nycListResponse.observe(this) {
            adapter.updateData(it)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
        }
        viewModel.getNYCList()
    }
}