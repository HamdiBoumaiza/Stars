package com.hb.stars.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hb.stars.databinding.ItemSpecieBinding
import com.hb.stars.domain.models.SpecieModel


class SpeciesAdapter : RecyclerView.Adapter<SpeciesAdapter.ViewHolder>() {

    private val list = ArrayList<SpecieModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemSpecieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(list[position])
    }

    fun addItems(specieModel: SpecieModel) {
        list.add(specieModel)
        notifyDataSetChanged()
    }

    override fun getItemCount() = list.size

    inner class ViewHolder(private val view: ItemSpecieBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bindTo(specie: SpecieModel) {
            with(view) {
                itemNameValue.text = specie.name
                itemLanguageValue.text = specie.language
            }
        }
    }
}