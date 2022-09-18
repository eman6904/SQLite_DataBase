package com.example.sqlite_database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.databinding.FragmentFaceBinding
import com.example.sqlite_database.databinding.FragmentNewAccountBinding


class NewAccount : Fragment(R.layout.fragment_new_account) {
    private lateinit var binding: FragmentNewAccountBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =FragmentNewAccountBinding.bind(view)
        navController = Navigation.findNavController(view)

        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        val db=MyDataBase(requireContext(),"firstdatabase",null,1)
        binding.Create.setOnClickListener()
        {
            val name=binding.name.text.toString()
            val password=binding.pass.text.toString()
            val table=table(name,password)
            val res=db.add(table)
            if(res) {
                Toast.makeText(
                    requireContext(),
                    "You have been registered successfully",
                    Toast.LENGTH_LONG
                ).show()
                binding.name.text.clear()
                binding.pass.text.clear()
            }
            else
                Toast.makeText(requireContext(),"an error occurred",Toast.LENGTH_LONG).show()
        }
    }
}