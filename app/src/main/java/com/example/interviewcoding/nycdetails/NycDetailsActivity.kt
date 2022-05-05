package com.example.interviewcoding.nycdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.interviewcoding.MainAdapter
import com.example.interviewcoding.R
import com.example.interviewcoding.RetrofitService
import com.example.interviewcoding.databinding.ActivityMainBinding
import com.example.interviewcoding.databinding.ActivityNycDetailsBinding
import com.example.interviewcoding.repo.MainRepository
import com.example.interviewcoding.viewmodel.MainViewModel
import com.example.interviewcoding.viewmodel.MainViewModelFactory

class NycDetailsActivity : AppCompatActivity() {
    private var dbn: String = ""
    lateinit var binding: ActivityNycDetailsBinding
    lateinit var viewModel: MainViewModel
    var adapter = NycDetailsAdapter()
    val retrofitService = RetrofitService.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nyc_details)
        dbn = intent.getStringExtra("schoolId").toString()
        binding = ActivityNycDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(MainRepository((retrofitService)))).get(
                MainViewModel::class.java
            )

        binding.nycDetailsRv.adapter = adapter
        viewModel.errorMessage.observe(this) {
            Toast.makeText(this@NycDetailsActivity, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.nycDetailsResponse.observe(this) {
//            val list = it.filter {
//                it.dbn.equals(dbn)
//            }
            adapter.updateData(it)
        }
        viewModel.getNyCDetails()
    }
}