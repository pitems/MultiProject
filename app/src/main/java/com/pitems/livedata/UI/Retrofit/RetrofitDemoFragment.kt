package com.pitems.livedata.UI.Retrofit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pitems.livedata.R
import com.pitems.livedata.UI.adapters.MyAdapter
import com.pitems.livedata.data.model.Post
import com.pitems.livedata.data.repository.PostRepository
import com.pitems.livedata.data.viewmodel.DemoRoom.RetrofitDemoViewModel
import com.pitems.livedata.data.viewmodel.DemoRoom.RetrofitDemoViewModelFactory
import kotlinx.android.synthetic.main.fragment_list.*

import kotlinx.android.synthetic.main.fragment_retrofit_demo.view.*


class RetrofitDemoFragment : Fragment() {
    private lateinit var viewModel: RetrofitDemoViewModel
    private val myAdapter by lazy{MyAdapter()}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_retrofit_demo, container, false)
         fun setupRecyclerView(){
           view.recyclerview.adapter=myAdapter
             view.recyclerview.layoutManager = LinearLayoutManager(context)


        }

        setupRecyclerView()
        val repository = PostRepository()
        val viewModelFactory = RetrofitDemoViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(RetrofitDemoViewModel::class.java)

        viewModel.getCustomPostQuery(2,"id","desc")
        val myPost=Post(1,1,"Le Title","Le body")
        viewModel.pushPostEncoded(1,1,"Le Title","Le body")
        viewModel.myResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("POST RESPONSE", response.body().toString())
                Log.d("CODE", response.code().toString())
                Log.d("MESSAGE", response.message())
                Log.d("HEADERS", response.headers().toString())
                Toast.makeText(context, "Post Sent", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.myResponseCustom.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { myAdapter.setData(it) }
            } else {
                Toast.makeText(context, response.code(), Toast.LENGTH_SHORT).show()
            }

        })




        //TODO make the actions in here add data to the adapter
        //HashMap with Options
        val options: HashMap<String,String> = HashMap()
        options["_sort"] = "id"
        options["_order"]="desc"
        //Btn action
        view.btn_post.setOnClickListener {
            val number = view.number_edit.text.toString()
            viewModel.getCustomPostQuery(userId = Integer.parseInt(number),"id","desc")
            viewModel.myResponseCustomMap.observe(viewLifecycleOwner, Observer { response ->
                //TODO Fix error if there is no internet present, a check module should be added to do that work
                if (response.isSuccessful) {
                    response.body()?.forEach {
                        Log.d("UserId", it.userId.toString())
                        Log.d("Id", it.id.toString())
                        Log.d("Title", it.title)
                        Log.d("Body", it.body)
                        Log.d("End", "-------------------------------")
                    }
                } else {

                }


            })
        }


        // Inflate the layout for this fragment
        return view


    }


}