package com.example.contactsapp.api

import com.example.contactsapp.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// This class will create the Retrofit builder object for the service calls.
class RetrofitUtil {
    companion object {
        private val client = OkHttpClient.Builder().build()
        private val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        fun <T> buildService(service: Class<T>): T {

            return retrofit.create(service)
        }
    }
}