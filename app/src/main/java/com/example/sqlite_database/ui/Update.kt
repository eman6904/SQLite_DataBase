package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.R
import com.example.sqlite_database.data.MyDataBase
import com.example.sqlite_database.data.Table
import com.example.sqlite_database.databinding.FragmentPrintDataBinding
import com.example.sqlite_database.databinding.FragmentUpdateBinding


class Update : Fragment(R.layout.fragment_update) {
    private lateinit var binding: FragmentUpdateBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUpdateBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        binding.update.setOnClickListener()
        {
            val db = MyDataBase(requireContext(), "DB", null, 2)
            val id = binding.id.text.toString()!!.toInt()
            val name = binding.name.text.toString()
            val password = binding.pass.text.toString()
            val recordsNumber = db.recordsNumber()
            if (id > recordsNumber)
                binding.textId.text = "Not Found"
            val table = Table(name, password)
            val res = db.update(table, id)
            if (res)
                Toast.makeText(
                    requireContext(),
                    "the update has been completed successfully",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(requireContext(), "there iS an error", Toast.LENGTH_LONG).show()
            binding.id.setOnClickListener()
            { binding.textId.text = "" }
            binding.id.text.clear()
            binding.pass.text.clear()
            binding.name.text.clear()
        }
    }
}