package com.SShayar.greenglobe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.SShayar.greenglobe.Models.User
import com.SShayar.greenglobe.R
import com.SShayar.greenglobe.databinding.FollowRvBinding
import com.bumptech.glide.Glide

class FollowAdapter (var context:Context,var followList: ArrayList<User>):RecyclerView.Adapter<FollowAdapter.ViewHolder>(){
    inner class ViewHolder(var binding: FollowRvBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding=FollowRvBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return followList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(followList.get(position).image).placeholder(R.drawable.user).into(holder.binding.profileImage)
        holder.binding.name.text=followList.get(position).name
    }
}