package com.android.diworkshop.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.android.diworkshop.R
import com.android.diworkshop.data.model.Home

class HomeRVAdapter(home: List<Home>?,context: Context) : RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

    private var homeList: ArrayList<Home>

    private lateinit var context: Context

    init {
        this.homeList = home as ArrayList<Home>
        this.context = context
        println("The elements size ${homeList.size}")

        homeList.forEach {
            // just printing this..
            println("The elements ${it.id} are $it")
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_feed,
            parent, false
        )
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.plot(homeList[position])
    }

    override fun getItemCount(): Int {
        return homeList.size
    }

    fun updatePostList(postList: List<Home>) {
        this.homeList = postList as ArrayList<Home>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // you can use data binding or view model to manage this item view

        private var title = itemView.findViewById<TextView>(R.id.post_title)
        private var body = itemView.findViewById<ImageView>(R.id.post_body)

        @SuppressLint("SetTextI18n")
        fun plot(home: Home) {

            title.text = home.authorName

            body.load(Uri.parse(home.url)) {
                crossfade(true)
            }
        }
    }
}
