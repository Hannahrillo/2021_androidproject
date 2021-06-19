package com.example.a2021_androidproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initResRecyclerView()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://androidguzo.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val ResService = retrofit.create(ResAPI::class.java)
        val jsonObject = JSONObject()


        ResService.getResName(
            "PcyPpWSaq3yG3%2BInouSgio9lR1uLPQhVJ4PcIEkgxbgoV%2FAVW7%2F1oKrJl%2BMjSigZdGr60meGPllUGeAOb3U0mA%3D%3D",
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
                        Log.d(TAG, it.toString())
                        it.restaurants.forEach{ restaurant ->
                            Log.d(TAG, restaurant.toString() )
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
    }

    fun initResRecyclerView(){
        adapter = ResAdapter()
        binding.resRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.resRecyclerview.adapter = adapter
    }

    companion object{
        private const val TAG = "MainActivity"
    }
}