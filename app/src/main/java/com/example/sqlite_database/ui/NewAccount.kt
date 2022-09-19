package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.R
import com.example.sqlite_database.data.MyDataBase
import com.example.sqlite_database.data.Table
import com.example.sqlite_database.databinding.FragmentNewAccountBinding


class NewAccount : Fragment(R.layout.fragment_new_account) {
    private lateinit var binding: FragmentNewAccountBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewAccountBinding.bind(view)
        navController = Navigation.findNavController(view)

        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        val db = MyDataBase(requireContext(), "DB", null, 2)
        binding.Create.setOnClickListener()
        {
            val name = binding.name.text.toString()
            val password = binding.pass.text.toString()
            val table = Table(name, password)
            val res = db.add(table)
            if (res) {
                Toast.makeText(
                    requireContext(),
                    "You have been registered successfully",
                    Toast.LENGTH_LONG
                ).show()
                binding.name.text.clear()
                binding.pass.text.clear()
            } else
                Toast.makeText(requireContext(), "an error occurred", Toast.LENGTH_LONG).show()
        }
    }
}