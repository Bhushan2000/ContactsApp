package com.example.contactsapp.repo

import com.example.contactsapp.api.RetrofitUtil
import com.example.contactsapp.api.UsersInterface
import com.example.contactsapp.models.User
import com.example.contactsapp.retrofitCallbacks.UserError
import com.example.contactsapp.retrofitCallbacks.UsersCallback
import com.example.contactsapp.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// Class which calls the webservice and get the data,
// then it returns the data back to the ViewModel with the help of the interface
// we send in the constructor.

class UsersRepo(callback: UsersCallback):Callback<List<User>> {

    private val userCallback: UsersCallback = callback

    private val userError = UserError(Constants.UNKNOWN_ERROR_CODE, Constants.UNKNOWN_ERROR)

    fun getUsers(){
        userCallback.onLoading(true)
        val request = RetrofitUtil.buildService(UsersInterface::class.java)
        val call = request.getUsers()
        call.enqueue(this)
    }

    override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
        userCallback.onLoading(false)
        if (response.body() == null){
            userCallback.onFailed(userError)
            return
        }
        if (response.isSuccessful){
            userCallback.onSuccess(response.body()!!)
            return
        }
        userCallback.onFailed(userError)
    }

    override fun onFailure(call: Call<List<User>>, t: Throwable) {

        val userError = UserError(Constants.USER_LOAD_FAILURE, t.message.toString())
        userCallback.onFailed(userError)
        userCallback.onLoading(false)
    }


}