package com.example.interviewcoding

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewcoding.data.NycResponse
import com.example.interviewcoding.databinding.ItemNyclistBinding
import com.example.interviewcoding.nycdetails.NycDetailsActivity

class MainAdapter : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    var nyclist: List<NycResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val itemNyclistBinding = ItemNyclistBinding.inflate(inflater, parent, false)
        return MainViewHolder(itemNyclistBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val nycItem = nyclist[position]
        holder.bind(nycItem)
    }

    override fun getItemCount() = nyclist.size

    fun updateData(nyclist: List<NycResponse>) {
        this.nyclist = nyclist
        notifyDataSetChanged()
    }

    class MainViewHolder(val itemNyclistBinding: ItemNyclistBinding) :
        RecyclerView.ViewHolder(itemNyclistBinding.root) {
        fun bind(nycItem: NycResponse) {
            itemNyclistBinding.schoolNameTv.text = "School Name : ".plus(nycItem.schoolName)
            itemNyclistBinding.schoolName2Tv.text = "Email Address : ".plus(nycItem.schoolEmail)
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(itemView.context, NycDetailsActivity::class.java).putExtra(
                        "schoolId",
                        nycItem.dbn
                    )
                )
            }
        }

    }
}