package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.R
import com.example.sqlite_database.data.MyDataBase
import com.example.sqlite_database.data.Table
import com.example.sqlite_database.databinding.FragmentSignInBinding


class SignIn : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        navController = Navigation.findNavController(view)

        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        binding.signIn.setOnClickListener()
        {
            val db = MyDataBase(requireContext(), "DB", null, 2)
            val name = binding.name.text.toString()
            val password = binding.pass.text.toString()
            binding.name.setOnClickListener()
            { binding.text1.text = "" }
            binding.pass.setOnClickListener()
            { binding.text2.text = "" }
            var list = ArrayList<Table>()
            list = db.searchInTable("name", name)
            var nameValid = true
            var passwordValid = true
            if (list.size == 0) {
                nameValid = false
                binding.text1.text = "Invalid Name"
                binding.name.text.clear()
            } else
                binding.text1.text = ""
            list.clear()
            list = db.searchInTable("password", password)
            if (list.size == 0) {
                passwordValid = false
                binding.text2.text = "Invalid password"
                binding.pass.text.clear()
            } else
                binding.text2.text = ""
            if (nameValid && passwordValid) {
                val bundle = bundleOf("Key" to 0)
                navController.navigate(R.id.action_sign_In_to_welcome, bundle)
            }
        }
    }
}