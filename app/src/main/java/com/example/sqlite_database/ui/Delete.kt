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
import com.example.sqlite_database.databinding.FragmentDeleteBinding
import com.example.sqlite_database.databinding.FragmentUpdateBinding

class Delete : Fragment(R.layout.fragment_delete) {
    private lateinit var binding: FragmentDeleteBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDeleteBinding.bind(view)
        navController = Navigation.findNavController(view)
        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        binding.delete.setOnClickListener()
        {
            val db = MyDataBase(requireContext(), "DB", null, 2)
            val id = binding.id.text.toString()
            val recordsNumber = db.recordsNumber()
            if (id!!.toInt() > recordsNumber)
                binding.textId.text = "Not Found"
            val res = db.delete(id)
            if (res)
                Toast.makeText(
                    requireContext(),
                    "It has been deleted successfully",
                    Toast.LENGTH_LONG
                ).show()
            else
                Toast.makeText(requireContext(), "there is an error", Toast.LENGTH_LONG).show()
            binding.id.setOnClickListener()
            { binding.textId.text = "" }
            binding.id.text.clear()
        }
    }

}