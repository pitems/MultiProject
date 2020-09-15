package com.pitems.livedata.UI.UserDemoRoom.fragments.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pitems.livedata.R
import com.pitems.livedata.data.viewmodel.DemoRoom.UserViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*


class ListFragment : Fragment() {
    private lateinit var mUserViewModel: UserViewModel
    lateinit var adapter:ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       val view = inflater.inflate(R.layout.fragment_list, container, false)
        //RecyclerView on our layout
        adapter = ListAdapter()
        val recyclerView = view.recyclerview
        recyclerView.adapter=adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //UserViewModel
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mUserViewModel.readAllData.observe(viewLifecycleOwner, Observer {
                //user comes from the observable read all data which is a List<User>
                //this way we can tell the adapter to apply the function notifyDatachanged when the observable has changed
                //when adding or removing something
               user-> adapter.setData(user)
        })
        view.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_addFragment)

        }
        return  view
    }



}