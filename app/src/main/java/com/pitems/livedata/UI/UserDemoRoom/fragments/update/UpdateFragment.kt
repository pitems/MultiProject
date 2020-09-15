package com.pitems.livedata.UI.UserDemoRoom.fragments.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pitems.livedata.R
import com.pitems.livedata.data.model.User
import com.pitems.livedata.data.viewmodel.DemoRoom.UserViewModel
import com.pitems.livedata.utils.inputCheck
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
    //When we created our argument on nav_graph the class UpdateFragmentArgs was automatically generated
    //this way when we press the item on the list and we send a parcel of the selected object args will receive this object inside here
    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mUserViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_person_name.setText(args.currentUser.name)
        view.update_person_lastname.setText(args.currentUser.lastname)
        view.update_person_age.setText(args.currentUser.age.toString())


        view.update_btn.setOnClickListener {
            updateItem()
        }
        //Add Menu
        setHasOptionsMenu(true)

        //Btn delete
        view.delete_btn.setOnClickListener{deleteUser()}
        return view
    }

    private fun updateItem(){
        val firstName = update_person_name.text.toString()
        val lastName = update_person_lastname.text.toString()
        val age = Integer.parseInt(update_person_age.text.toString())

        if(inputCheck(firsName = firstName,lastName = lastName,age = update_person_age.text)){
            //Create User Object
            val updatedUser= User(args.currentUser.id,firstName,lastName,age)
            //Update Current User
            mUserViewModel.updateUser(updatedUser)
            //Navigate Back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
           inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            mUserViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(), "Successfully removerd ${args.currentUser.name}", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No"){_,_ ->}
        builder.setTitle("Delete ${args.currentUser.name}?")
        builder.setMessage("Are you sure you want to delete ${args.currentUser.name}")
        builder.create().show()

    }
}