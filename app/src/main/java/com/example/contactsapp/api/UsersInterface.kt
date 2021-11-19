package com.example.contactsapp.api

import com.example.contactsapp.models.User
import retrofit2.Call
import retrofit2.http.GET

// This class is for the UserWebService which is needed by Retrofit.
interface UsersInterface {
    @GET("users")
    fun getUsers():Call<List<User>>
}