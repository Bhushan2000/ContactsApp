package com.example.contactsapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.contactsapp.R
import com.example.contactsapp.models.User

class ListAdapter(private val list: List<User>) :
    RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user: User = list[position]
        holder.tvUserName.text = user.name

        holder.tvUserPhone.text = user.phone

    }

    override fun getItemCount(): Int = list.size

    class UserViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

        val tvUserName: TextView = view!!.findViewById(R.id.list_name)
        val tvUserPhone: TextView = view!!.findViewById(R.id.list_phone)
    }
}

