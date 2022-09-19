package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.R
import com.example.sqlite_database.data.MyDataBase
import com.example.sqlite_database.databinding.FragmentWelcomeBinding


class Welcome : Fragment(R.layout.fragment_welcome) {
    private lateinit var binding: FragmentWelcomeBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWelcomeBinding.bind(view)
        navController = Navigation.findNavController(view)

        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        val db = MyDataBase(requireContext(), "DB", null, 2)
        val value = arguments?.getInt("Key")
        if (value == 1) {
            val recordsNumber = db.recordsNumber()
            binding.wl.text = "Accounts Number = $recordsNumber"
        } else
            binding.wl.text = "Welcome With You 😊"
    }
}