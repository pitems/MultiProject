package com.pitems.livedata.UI.UserDemoRoom.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pitems.livedata.R
import com.pitems.livedata.data.viewmodel.DemoRoom.UserViewModel
import com.pitems.livedata.data.model.User
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*


class AddFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener{
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase(){
        val firstName= person_name.text.toString()
        val lastName = person_lastname.text.toString()
        val age = person_age.text
        if(inputCheck(firstName,lastName,age)){
            //Create User Object
            val user = User(
                0,
                firstName,
                lastName,
                Integer.parseInt(age.toString())
            )
            //Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT).show()
            //Navigate Back
            //findNavController().popBackStack(R.id.listFragment,false)
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private  fun inputCheck(firsName:String, lastName:String, age:Editable):Boolean{
        return !(TextUtils.isEmpty(firsName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}