package com.example.sqlite_database

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.sqlite_database.databinding.FragmentFaceBinding
import com.example.sqlite_database.databinding.FragmentSignInBinding


class Sign_In : Fragment(R.layout.fragment_sign_in) {
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
          val db=MyDataBase(requireContext(),"firstdatabase",null,1)
          val name=binding.name.text.toString()
          val password=binding.pass.text.toString()
         var list=ArrayList<table>()
          list=db.search_in_table("name",name)
          var c=0
          if(list.size==0) {
              c++
              binding.text1.text = "Invalid Name"
          }
          list.clear()
          list=db.search_in_table("password",password)
          if(list.size==0) {
              c++
              binding.text2.text = "Invalid password"
          }
          if(c==0)
              navController.navigate(R.id.action_sign_In_to_welcome)
      }
    }
}