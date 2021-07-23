package com.tonon.dominos.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tonon.dominos.DetailFood
import com.tonon.dominos.R
import com.tonon.dominos.databinding.ItemCategoryBinding
import com.tonon.dominos.databinding.ItemFlavoursBinding
import com.tonon.dominosapp.model.category
import com.tonon.dominosapp.model.flavour
import com.tonon.dominosapp.model.ingredient
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.fragment_food.view.*
import kotlinx.android.synthetic.main.item_discount.view.*
import kotlinx.android.synthetic.main.item_flavours.*
import kotlinx.android.synthetic.main.item_flavours.view.*
import java.util.*


class Food : Fragment() {

    private lateinit var flavourAdapter : FlavourAdapter
    private lateinit var categoryAdapter: CategoryAdapter



    companion object {
        fun newInstance(): Food {
            val fragmentHome = Food()
            val arg = Bundle()
            fragmentHome.arguments = arg
            return fragmentHome
        }

        var EXTRA_FOOD_DETAILS = "extra_food_details"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_food, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val foods: MutableList<flavour> = getPizzaFlavours() as MutableList<flavour>


        flavourAdapter = FlavourAdapter(foods)
        recycleView_foods.adapter = flavourAdapter
        recycleView_foods.layoutManager = LinearLayoutManager(context)

        flavourAdapter.setOnClickListener(object : FlavourAdapter.OnClickListener{
            override fun onClick(position: Int, model: flavour) {
                val intent = Intent(context, DetailFood::class.java)
                startActivity(intent)
            }


        })



        val category: MutableList<category> = getCategory() as MutableList<category>

        categoryAdapter = CategoryAdapter(category)
        recycleView_categories.adapter = categoryAdapter
        recycleView_categories.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)




    }





    private fun getPizzaFlavours(): List<flavour>{
        return arrayListOf(
            flavour(R.drawable.pepperock, "Pepperock", "R$ 57,90", 1),
            flavour(R.drawable.mrtexas, "Frango com cream cheese", "R$ 54,90", 2),
            flavour(R.drawable.toscana, "Toscana", "R$ 51,90", 3),
            flavour(R.drawable.america, "América", "R$51,90", 4),
            flavour(R.drawable.margherita, "Margherita", "R$47,90", 5),
            flavour(R.drawable.cornandbacon, "Corn & Bacon", "R$54,90", 6),
            flavour(R.drawable.portuguesa, "Portuguesa", "R$47,90", 7),
            flavour(R.drawable.quatroqueijos, "4 Queijos", "R$51,90", 8),
            flavour(R.drawable.bufala, "Búfala La Bianca", "R$54,90", 9),
            flavour(R.drawable.calabresa, "Calabresa", "47,90", 10),
            flavour(R.drawable.extravaganzza, "Extravaganzza", "R$57,90", 11)
        ).toList()

    }



    class FlavourAdapter(private val flavour: List<flavour>): RecyclerView.Adapter<FlavourAdapter.FlavourViewHolder>() {

        private var onClickListener: OnClickListener?= null


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlavourViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemFlavoursBinding.inflate(layoutInflater, parent, false)
            return  FlavourViewHolder(binding)

        }

        override fun onBindViewHolder(holder: FlavourViewHolder, position: Int) {

            val model = flavour[position]

            holder.bind(flavour[position])

            holder.itemView.setOnClickListener {
                if (onClickListener != null){
                    onClickListener!!.onClick(position,model)
                }
            }
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

        fun setOnClickListener(onClickListener: OnClickListener){
            this.onClickListener = onClickListener
        }

        interface OnClickListener {
            fun onClick(position: Int, model: flavour)
        }


    }

    class CategoryAdapter(private val category: List<category>): RecyclerView.Adapter<CategoryAdapter.CategoryHolder>(){

        class CategoryHolder(private val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(category: category){
                binding.textCategory.text  = category.name
            }

        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemCategoryBinding.inflate(layoutInflater, parent, false)
            return CategoryHolder(binding)
        }

        override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
            holder.bind(category[position])
        }

        override fun getItemCount(): Int {
            return category.size
        }


    }


    private fun getCategory(): List<category>{
        return arrayListOf(
                category("Pizza"),
                category("Sanduba"),
                category("Sobremesa"),
                category("Acompanhamento"),
                category("Calzone"),
                category("Bebida")
        ).toList()

    }

}

