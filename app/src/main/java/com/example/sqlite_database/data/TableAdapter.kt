package com.example.sqlite_database.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite_database.databinding.RecordmodelBinding

class TableAdapter(private val list: ArrayList<Table>) :
    RecyclerView.Adapter<TableAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: RecordmodelBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var name = binding.name
        var password = binding.password
        var enterName = binding.entername
        var enterPassword = binding.enterpassword
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecordmodelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.name.text = "Name: "
        holder.password.text = "Password: "
        holder.enterName.text = item.name
        holder.enterPassword.text = item.password
    }

    override fun getItemCount(): Int {
        return list.size
    }
}