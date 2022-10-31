package com.krishna.task1app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.krishna.task1app.R
import com.krishna.task1app.models.CategoriesModelItem
import com.krishna.task1app.models.MoviesModelItem
import com.krishna.task1app.util.OnClickItem

class CategoriesAdapter(
    categoryArrayList: ArrayList<CategoriesModelItem>,
    private val moviesArrayList: ArrayList<MoviesModelItem>,
    private val onClickItem: OnClickItem,
    context: Context
) :
    RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>() {
    private val parentModelArrayList: ArrayList<CategoriesModelItem>
    var cxt: Context

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var category: TextView
        var more: TextView
        var childRecyclerView: RecyclerView

        init {
            category = itemView.findViewById(R.id.Movie_category)
            more = itemView.findViewById(R.id.more)
            childRecyclerView = itemView.findViewById(R.id.moviesRv)
        }
    }

    init {
        parentModelArrayList = categoryArrayList
        cxt = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_layout_items, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return parentModelArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem: CategoriesModelItem = parentModelArrayList[position]
        val layoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(cxt, LinearLayoutManager.HORIZONTAL, false)
        holder.childRecyclerView.layoutManager = layoutManager
        holder.childRecyclerView.setHasFixedSize(true)
        holder.category.text = currentItem.category_name
        val arrayList: ArrayList<MoviesModelItem> = ArrayList()
        for (i in 0 until moviesArrayList.size) {
            if (parentModelArrayList[position].category_id == moviesArrayList[i].category_id) {
                arrayList.add(moviesArrayList[i])
            }
        }
        holder.more.setOnClickListener {
            onClickItem.onClickOfMore(currentItem.category_id)
        }
        val childRecyclerViewAdapter =
            MoviesAdapter(arrayList, holder.childRecyclerView.context)
        holder.childRecyclerView.adapter = childRecyclerViewAdapter
    }
}