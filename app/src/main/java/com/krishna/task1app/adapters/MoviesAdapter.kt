package com.krishna.task1app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.krishna.task1app.R
import com.krishna.task1app.models.MoviesModelItem


class MoviesAdapter(arrayList: ArrayList<MoviesModelItem>, mContext: Context) :
    ListAdapter<MoviesModelItem, MoviesAdapter.MyViewHolder>(Movies_COMPARATOR) {
    var moviesArrayList: ArrayList<MoviesModelItem>
    var cxt: Context

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var heroImage: ImageView
        var movieName: TextView

        init {
            heroImage = itemView.findViewById(R.id.hero_image)
            movieName = itemView.findViewById(R.id.movie_name)
        }
    }

    init {
        cxt = mContext
        moviesArrayList = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.movies_list_items, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: MoviesModelItem = moviesArrayList[position]
        Glide.with(holder.heroImage.context).load(currentItem.stream_icon).into(holder.heroImage)
        holder.movieName.text = currentItem.name
    }

    override fun getItemCount(): Int {
        return moviesArrayList.size
    }

    companion object {
        private val Movies_COMPARATOR = object : DiffUtil.ItemCallback<MoviesModelItem>() {
            //assuming slugName is unique property
            override fun areItemsTheSame(
                oldItem: MoviesModelItem,
                newItem: MoviesModelItem
            ): Boolean =
                oldItem.stream_id == newItem.stream_id

            override fun areContentsTheSame(
                oldItem: MoviesModelItem,
                newItem: MoviesModelItem
            ): Boolean =
                oldItem == newItem

        }
    }
}