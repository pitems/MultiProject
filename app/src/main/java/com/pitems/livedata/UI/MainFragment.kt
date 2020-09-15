package com.pitems.livedata.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pitems.livedata.R
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener {
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btn_liveData.setOnClickListener(this)
        btn_room.setOnClickListener(this)
        btn_retrofit.setOnClickListener(this)
    }



    override fun onClick(v:View){
        when(v.id){
            R.id.btn_liveData -> navController.navigate(R.id.action_mainFragment_to_liveData2)
            R.id.btn_room -> navController.navigate(R.id.action_mainFragment_to_listFragment)
            R.id.btn_retrofit->navController.navigate(R.id.action_mainFragment_to_retrofitDemo)

        }
    }
}