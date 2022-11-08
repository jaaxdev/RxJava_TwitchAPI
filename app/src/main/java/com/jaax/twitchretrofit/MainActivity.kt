package com.jaax.twitchretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jaax.twitchretrofit.model.TopGame
import com.jaax.twitchretrofit.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        getResponse()
    }

    private fun getResponse() {

        val call = RetrofitClient.getService().getTopGames()
        call.enqueue(object : Callback<TopGame> {
            override fun onResponse(call: Call<TopGame>, response: Response<TopGame>) {
                if(response.isSuccessful) {
                    Log.i("twitch: ", response.body()!!.data[0].toString())
                } else {
                    Log.i("twitch: ", "No success")
                }
            }

            override fun onFailure(call: Call<TopGame>, t: Throwable) {
                Log.i("twitch: ", "Error")
            }

        })
    }
}