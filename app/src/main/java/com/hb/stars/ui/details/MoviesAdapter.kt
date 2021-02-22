package com.hb.stars.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hb.stars.databinding.ItemMovieBinding
import com.hb.stars.domain.models.MovieModel
import com.hb.stars.utils.DEFAULT_MAX_LINES_MOVIE


class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    private val list = ArrayList<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    fun addItems(movieModel: MovieModel) {
        list.add(movieModel)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val view: ItemMovieBinding) :
        RecyclerView.ViewHolder(view.root) {

        init {
            view.root.setOnClickListener {
                val expanded: Boolean = list[adapterPosition].isExpanded
                list[adapterPosition].isExpanded = !expanded
                notifyItemChanged(adapterPosition)
            }
        }

        fun bindTo(movie: MovieModel) {
            with(view) {
                itemTitle.text = movie.title
                itemDescription.text = movie.description
                if (movie.isExpanded) itemDescription.maxLines = Integer.MAX_VALUE
                else itemDescription.maxLines = DEFAULT_MAX_LINES_MOVIE

            }
        }
    }
}