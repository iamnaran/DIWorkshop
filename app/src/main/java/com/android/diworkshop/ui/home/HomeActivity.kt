package com.android.diworkshop.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.android.diworkshop.R
import com.android.diworkshop.data.model.Home
import com.android.diworkshop.di.component.AppComponent
import dagger.android.AndroidInjection
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject lateinit var appComponent: AppComponent
    @Inject lateinit var homeViewModelFactory: HomeViewModelFactory
    @Inject lateinit var homeViewModel: HomeViewModel

    private var adapter = HomeRVAdapter(ArrayList(),this)
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        progressBar = findViewById(R.id.progressBar)
        prepareRecyclerView()

        homeViewModel = ViewModelProviders.of(this, homeViewModelFactory).get(
            HomeViewModel::class.java
        )
        
        doViewModelWork()
    }

    private fun prepareRecyclerView() {

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }

    }

    private fun doViewModelWork() {
        doObserverWork()
        loadData()
    }

    private fun doObserverWork() {

        progressBar.visibility = View.VISIBLE

        homeViewModel.onResult().observe(this,
            Observer<List<Home>> {
                if (it != null) {

                    print(it)
                    Log.d("Holy Shit, Success", it.toString())

                    recyclerView.adapter = adapter
                    adapter.updatePostList(it)

                }
            })

        homeViewModel.onLoader().observe(this, Observer<Boolean> {
            if (it == false) progressBar.visibility = View.GONE
        })

        homeViewModel.onError().observe(this, Observer {

            Log.e("Holy Shit, Error", it.toString())
        })

    }


    private fun loadData() {
        homeViewModel.getFeeds()
    }


    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.disposeElements()
    }


    class HomeRVAdapter(home: List<Home>?) : RecyclerView.Adapter<HomeRVAdapter.ViewHolder>() {

        private var homeList: ArrayList<Home>

        init {
            this.homeList = home as ArrayList<Home>

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
                title.text = home.authorName + " - " + adapterPosition + home.url
//                Glide.with(applicationContext).load(home.url).into(body)

                body.load(home.url)
            }
        }
    }


}
