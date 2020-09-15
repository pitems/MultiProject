package com.pitems.livedata.UI.LiveData

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.pitems.livedata.R
import com.pitems.livedata.data.viewmodel.LiveData.LiveDataViewModel
import kotlinx.android.synthetic.main.live_data.*

class LiveData : Fragment(){
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.live_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        val viewModel= ViewModelProvider(this).get(LiveDataViewModel::class.java)

        //This will observe the viewModel function which has the value of the mutabledata
        viewModel.seconds().observe(viewLifecycleOwner, Observer {
            number_txt.text=it.toString()
        })
        viewModel.finished.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Finished", Toast.LENGTH_SHORT).show()
            }
        })


        start_btn.setOnClickListener {
            if(number_input.text.isEmpty() || number_input.text.length < 4){
                Toast.makeText(context, "Invalid Number", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.timerValue.value = number_input.text.toString().toLong()
                viewModel.startTimer()
            }

        }

        stop_btn.setOnClickListener {
            viewModel.stopTimer()
        }
    }




}