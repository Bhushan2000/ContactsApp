package com.example.contactsapp.retrofitCallbacks

import com.example.contactsapp.models.User

// The callback class which is passsed to the repo constructor to get the callback to the viewmodel.
interface UsersCallback {
    fun onSuccess(users: List<User>)
    fun onFailed(userError: UserError)
    fun onLoading(loading:Boolean)
}