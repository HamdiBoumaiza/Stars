package com.hb.stars.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hb.stars.databinding.ItemCharacterBinding
import com.hb.stars.domain.models.CharacterModel


class CharactersAdapter(
    private val action: (CharacterModel) -> Unit
) :
    ListAdapter<CharacterModel, CharactersAdapter.ViewHolder>(CharactersDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }


    inner class ViewHolder(private val view: ItemCharacterBinding) :
        RecyclerView.ViewHolder(view.root) {

        init {
            view.root.setOnClickListener { action(getItem(adapterPosition)) }
        }

        fun bindTo(character: CharacterModel) {
            view.itemTitle.text = character.name
        }
    }
}

class CharactersDiffCallback : DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}