package com.example.a2021_androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a2021_androidproject.API.ResAPI
import com.example.a2021_androidproject.databinding.ActivityMainBinding
import com.example.a2021_androidproject.model.ResDTO
import com.example.a2021_androidproject.model.Restaurant
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val restList:List<Restaurant> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ResAdapter
    private lateinit var ResService : ResAPI


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initResRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidguzo.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        ResService = retrofit.create(ResAPI::class.java)
        val jsonObject = JSONObject()


        ResService.getResName(
            getString(R.string.resAPIKey),
            10,
            1
        )
            .enqueue(object: Callback<ResDTO> {
                //성공.
                override fun onResponse(call: Call<ResDTO>, response: Response<ResDTO>) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT Sucess")
                        return
                    }
                    Log.e(TAG, "Sucess")
                    Log.e(TAG, response.toString())

                    response.body()?.let {
                        it.restaurants.forEach{ restaurant ->
                            restList.toMutableList().add(restaurant)
                        }
                        adapter.submitList(it.restaurants)

                    }

                }

                //실패처리.
                override fun onFailure(call: Call<ResDTO>, t: Throwable) {
                    Log.e(TAG, t.toString() )
                    Log.e(TAG, "실패" )
                }

            })

        binding.searchEditText.setOnKeyListener{v,keyCode, event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN){
                Log.e(TAG,"검색버튼 누름")
                search(binding.searchEditText.text.toString())
                return@setOnKeyListener true
            }

            return@setOnKeyListener false

        }
    }

    private fun search(keyword :String){
        ResService.searchRes(keyword)
            .enqueue( object : Callback<ResDTO> {
                //성공.
                override fun onResponse(call: Call<ResDTO>, response: Response<ResDTO>) {
                    if (response.isSuccessful.not()) {
                        Log.e(TAG, "NOT Sucess")
                        return
                    }
                    adapter.submitList(response.body()?.restaurants.orEmpty())
                    Log.d(TAG,"sucess asdf")
                }

                override fun onFailure(call: Call<ResDTO>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }


    private fun initResRecyclerView(){
        adapter = ResAdapter()
        binding.resRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.resRecyclerview.adapter = adapter
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}