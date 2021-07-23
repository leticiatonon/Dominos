package com.tonon.dominos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tonon.dominos.databinding.ItemFlavoursBinding
import com.tonon.dominosapp.model.flavour


class FlavourAdapter(private val flavour: List<flavour>): RecyclerView.Adapter<FlavourAdapter.FlavourViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavourViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFlavoursBinding.inflate(layoutInflater, parent, false)
        return  FlavourViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FlavourViewHolder, position: Int) {
        holder.bind(flavour[position])
    }

    override fun getItemCount(): Int {
        return flavour.size
    }

    class FlavourViewHolder(private val binding: ItemFlavoursBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(flavour: flavour){
            Picasso.get().load(flavour.image).into(binding.foodImage)
            binding.foodName.text = flavour.name
            binding.foodPrice.text = flavour.price
        }
    }

}
