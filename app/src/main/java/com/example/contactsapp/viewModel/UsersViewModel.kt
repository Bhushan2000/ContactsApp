package com.example.contactsapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.contactsapp.retrofitCallbacks.UserError
import com.example.contactsapp.retrofitCallbacks.UsersCallback
import com.example.contactsapp.models.User
import com.example.contactsapp.repo.UsersRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// UsersViewModel has three MutableLiveData variables
// which will be observed by the Activity/Fragment.
class UsersViewModel(application: Application) : AndroidViewModel(application), UsersCallback {

    //  userList will be notified when the service is returned with a proper user list.
    //  userError will be notified when there is some error from the service.
    //  userLoading will be notified when the service starts loading and ends loading.

    var userList: MutableLiveData<List<User>> = MutableLiveData()
    var userError: MutableLiveData<UserError> = MutableLiveData()
    var userLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getUser() {
        if (userLoading.value == true) {
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            UsersRepo(this@UsersViewModel).getUsers()
        }
    }


    override fun onSuccess(users: List<User>) {
        userList.postValue(users)
    }

    override fun onFailed(error: UserError) {
        userError.postValue(error)
    }

    override fun onLoading(loading: Boolean) {
       userLoading.postValue(loading)
    }


}