package com.example.sqlite_database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
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
        {navController.navigate(R.id.action_faceFragment_to_sign_In)}
        binding.newAccount.setOnClickListener()
        {navController.navigate(R.id.action_faceFragment_to_newAccount)}
    }
}