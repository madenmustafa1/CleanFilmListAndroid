package com.maden.filmlist.presentation.film_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maden.filmlist.databinding.FilmListItemBinding
import com.maden.filmlist.domain.model.movie_list.MovieItemResponseModel

class FilmListAdapter(private val _clickListener: FilmListAdapterListener) :
    RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    private val items = mutableListOf<MovieItemResponseModel>()

    class ViewHolder(private val binding: FilmListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: MovieItemResponseModel, clickListener: FilmListAdapterListener) {
            binding.movieModel = movieItem
            binding.itemLayout.setOnClickListener {
                clickListener.onItemClick(movieItem)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmListItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val movieItem = items[position]
        viewHolder.bind(movieItem, _clickListener)
        if (position == itemCount - 4) {
            _clickListener.lastItem(true)
        }
    }

    override fun getItemCount(): Int = items.size

    fun addItems(newItems: List<MovieItemResponseModel>) {
        if (_search) {
            items.clear()
            _search = false
        }

        items.addAll(newItems)
        notifyDataSetChanged()
    }

    private var _search = false
    fun search(newItems: List<MovieItemResponseModel>) {
        _search = true

        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun clearAdapter() = items.clear()
}

interface FilmListAdapterListener {
    fun onItemClick(item: MovieItemResponseModel)
    fun lastItem(isLastItem: Boolean)
}
