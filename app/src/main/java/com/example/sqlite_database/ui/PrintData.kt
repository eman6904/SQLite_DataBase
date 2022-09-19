package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite_database.R
import com.example.sqlite_database.data.MyDataBase
import com.example.sqlite_database.data.Table
import com.example.sqlite_database.data.TableAdapter
import com.example.sqlite_database.databinding.FragmentFaceBinding
import com.example.sqlite_database.databinding.FragmentPrintDataBinding

class PrintData : Fragment(R.layout.fragment_print_data) {
    private lateinit var binding: FragmentPrintDataBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPrintDataBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        val db = MyDataBase(requireContext(), "DB", null, 2)
        var list = ArrayList<Table>()
        list = db.printTable()
        val adapter = TableAdapter(list)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = adapter
    }
}