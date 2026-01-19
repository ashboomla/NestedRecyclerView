package com.example.nestedrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nestedrecyclerview.data.CategoryRow

class CategoryAdapter(
    private val onLoadMore: () -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    private val scrollStateMap = mutableMapOf<String, Int>()

    private val items = mutableListOf<CategoryRow>()

    companion object {
        val sharedPool = RecyclerView.RecycledViewPool()
    }

    fun submitData(newItems: List<CategoryRow>) {
        val start = items.size
        items.addAll(newItems)
        notifyItemRangeInserted(start, newItems.size)
    }

    inner class CategoryVH(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.tvCategoryTitle)
        val rvMovies: RecyclerView = view.findViewById(R.id.rvMovies)
        val movieAdapter = MovieAdapter()

        init {
            rvMovies.apply {
                layoutManager = LinearLayoutManager(
                    view.context,
                    RecyclerView.HORIZONTAL,
                    false
                )
                adapter = movieAdapter
                setRecycledViewPool(sharedPool)
                setHasFixedSize(true)
                isNestedScrollingEnabled = false
            }

            rvMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val lm = recyclerView.layoutManager as LinearLayoutManager
                    val pos = lm.findFirstVisibleItemPosition()
                    val category = items[layoutPosition]
                    if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                        scrollStateMap[category.id] = pos
                    }
                }
            })

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return CategoryVH(view)
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val category = items[position]
        holder.title.text = category.title
        holder.movieAdapter.submitList(category.movies)
        var lm = holder.rvMovies.layoutManager as LinearLayoutManager
        holder.rvMovies.post {
            lm.scrollToPositionWithOffset(scrollStateMap[category.id] ?: 0, 0)
        }

        if(position == items.size - 3) {
            onLoadMore()
        }
    }

    override fun getItemCount(): Int = items.size
}