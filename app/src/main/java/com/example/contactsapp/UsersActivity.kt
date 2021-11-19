package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.adapter.ListAdapter
import com.example.contactsapp.models.User
import com.example.contactsapp.retrofitCallbacks.UserError
import com.example.contactsapp.viewModel.UsersViewModel

class UsersActivity : AppCompatActivity(), View.OnClickListener
{

    private lateinit var mListRecyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var btnLoad: Button
    private lateinit var tvErrorMessage: TextView

    private lateinit var viewModel: UsersViewModel
    private var mAdapter: ListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)

        findViews()
        showErrorMessage(null)
        setListeners()
    }
    private fun findViews() {
        mListRecyclerView = findViewById(R.id.userListRecyclerView)
        btnLoad = findViewById(R.id.btnLoad)
        progressBar = findViewById(R.id.progress)
        tvErrorMessage = findViewById(R.id.tvErrorMessage)
        btnLoad.setOnClickListener(this)
    }

    private fun setListeners() {
        viewModel = ViewModelProvider(this).get(UsersViewModel::class.java)
        viewModel.userList.observe(this, Observer(onUsersListReceived()))
        viewModel.userError.observe(this, Observer(onUserError()))
        viewModel.userLoading.observe(this, Observer(onLoading()))
        viewModel.getUser()
    }
    private fun onUsersListReceived() = { users: List<User> ->
        setList(users)
    }

    private fun onLoading() = { loading: Boolean ->
        showLoading(loading)
    }

    private fun onUserError() = { userError: UserError ->
        showErrorMessage(userError.message)
    }

    private fun showErrorMessage(message: String?) {
        tvErrorMessage.visibility = if (null != message) View.VISIBLE else View.GONE
        tvErrorMessage.text = message
    }

    private fun showLoading(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setList(users: List<User>) {
        if (null != mAdapter) {
            mAdapter?.notifyDataSetChanged()
            return
        }
        mAdapter = ListAdapter(users)
        mListRecyclerView.adapter = mAdapter
        mListRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        if (v!!.id == R.id.btnLoad) {
            showErrorMessage(null)
            viewModel.getUser()
        }
    }
}