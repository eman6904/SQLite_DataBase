package com.example.sqlite_database.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.R
import com.example.sqlite_database.databinding.FragmentFaceBinding


class FaceFragment : Fragment(R.layout.fragment_face) {
    private lateinit var binding: FragmentFaceBinding
    private lateinit var navController: NavController
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFaceBinding.bind(view)
        navController = Navigation.findNavController(view)

        //to hide action bar
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        binding.signIn.setOnClickListener()
        { navController.navigate(R.id.action_faceFragment_to_sign_In) }
        binding.newAccount.setOnClickListener()
        { navController.navigate(R.id.action_faceFragment_to_newAccount) }
        binding.PrintData.setOnClickListener()
        { navController.navigate(R.id.action_faceFragment_to_printData) }
        binding.update.setOnClickListener()
        { navController.navigate(R.id.action_faceFragment_to_update2) }
        binding.Delete.setOnClickListener()
        { navController.navigate(R.id.action_faceFragment_to_delete) }
        binding.AccountsNumber.setOnClickListener()
        {
            val bundle = bundleOf("Key" to 1)
            navController.navigate(R.id.action_faceFragment_to_welcome, bundle)
        }
    }
}