package com.example.simpleapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerview: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manager= LinearLayoutManager(this)
        getAllData()

    }

    fun getAllData(){
        Api.retrofitService.getAllData().enqueue(object: retrofit2.Callback<List<Property>>{

            override fun onResponse(call: retrofit2.Call<List<Property>>,
                response: retrofit2.Response<List<Property>>
            ) {
                if(response.isSuccessful){
                    recyclerview = findViewById<RecyclerView>(R.id.recycler_view).apply{
                        myAdapter = MyAdapter(response.body()!!)
                        layoutManager = manager
                        adapter = myAdapter
                    }
                }
            }

            override fun onFailure(call: retrofit2.Call<List<Property>>, t: Throwable) {
                t.printStackTrace()
            }


        })
    }
}
