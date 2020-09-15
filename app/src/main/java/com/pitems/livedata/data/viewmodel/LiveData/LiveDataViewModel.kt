package com.pitems.livedata.data.viewmodel.LiveData

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataViewModel :ViewModel(){
    private lateinit var   timer : CountDownTimer
    var timerValue= MutableLiveData<Long>()
    //Mutable Live data unlike Livedata can be called every second and its capable of changing constantly without problem
    private val _seconds = MutableLiveData<Int>()
    var finished = MutableLiveData<Boolean>()

    //We cannot access the mutable live data so we will use a Static LiveData so we have no way of changing the MutableLiveData Inside
    fun seconds(): LiveData<Int>{
        return _seconds
    }

    fun startTimer(){
        timer = object: CountDownTimer(timerValue.value!!.toLong(),1000){
            override fun onTick(p0: Long) {
                //This will send data to the mutablelive data each time the time has changed
                val timeLeft = p0/1000
                _seconds.value= timeLeft.toInt()
            }

            override fun onFinish() {
                _seconds.value = 0
                finished.value=true
            }
        }.start()
    }

    fun stopTimer(){
        timer.cancel()
        _seconds.value=0
    }
}