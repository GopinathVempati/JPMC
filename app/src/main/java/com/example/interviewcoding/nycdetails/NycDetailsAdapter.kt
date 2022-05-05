package com.example.interviewcoding.nycdetails

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewcoding.data.NycDetails
import com.example.interviewcoding.data.NycResponse
import com.example.interviewcoding.databinding.ItemNyclistBinding

class NycDetailsAdapter : RecyclerView.Adapter<NycDetailsAdapter.NycViewHolder>() {

    var nycDetails: List<NycDetails> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NycViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemNyclistBinding = ItemNyclistBinding.inflate(inflater, parent, false)
        return NycViewHolder(itemNyclistBinding)
    }

    override fun onBindViewHolder(holder: NycViewHolder, position: Int) {
        val nycItem = nycDetails[position]
        holder.bind(nycItem)
    }

    override fun getItemCount() = nycDetails.size

    fun updateData(nycDetails: List<NycDetails>) {
        this.nycDetails = nycDetails
        notifyDataSetChanged()
    }

    class NycViewHolder(val itemNyclistBinding: ItemNyclistBinding) :
        RecyclerView.ViewHolder(itemNyclistBinding.root) {
        fun bind(nycItem: NycDetails) {
            itemNyclistBinding.schoolNameTv.text = "School Name : ".plus(nycItem.schoolName)
            val readAvg = nycItem.satCriticalReadingAvgScore?.toInt() ?: 0
            val mathAvg = nycItem.satMathAvgScore?.toInt() ?: 0
            val writeAvg = nycItem.satWritingAvgScore?.toInt() ?: 0
            val average = (readAvg + mathAvg + writeAvg) / 3
            itemNyclistBinding.schoolName2Tv.text = "Total Avarage of School : ".plus(average)
        }

    }
}