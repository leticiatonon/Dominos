package com.tonon.dominos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tonon.dominos.R
import com.tonon.dominos.databinding.ItemIngredientsBinding
import com.tonon.dominosapp.model.ingredient
import kotlinx.android.synthetic.main.activity_detail_food.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_food.*

class DetailFood : AppCompatActivity() {

    private lateinit var ingredientAdapter : DetailFood.IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_food)

        val ingredient: MutableList<ingredient> = getIngredients() as MutableList<ingredient>
        ingredientAdapter = IngredientAdapter(ingredient)
        recycleView_ingredients.adapter = ingredientAdapter
       recycleView_ingredients.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL, false)



    }

    class IngredientAdapter(private val ingredient: List<ingredient>): RecyclerView.Adapter<IngredientAdapter.IngredientHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemIngredientsBinding.inflate(layoutInflater, parent, false)
            return IngredientHolder(binding)
        }

        override fun onBindViewHolder(holder: IngredientHolder, position: Int) {
            holder.bind(ingredient[position])
        }

        override fun getItemCount(): Int {
            return ingredient.size
        }

        class IngredientHolder(private val binding: ItemIngredientsBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(ingredient: ingredient){
                Picasso.get().load(ingredient.image).into(binding.imageIngredient)
                binding.textIngredient.text  = ingredient.name
                binding.textPrice.text = ingredient.price
            }
        }
    }

    private fun getIngredients(): ArrayList<ingredient> {
        return arrayListOf(
            ingredient(R.drawable.garlic,"Alho", "R$2,00", 1),
            ingredient(R.drawable.mushroom,"Champignon", "R$2,90", 2),
            ingredient(R.drawable.basil,"Manjericão", "R$1,70", 3),
            ingredient(R.drawable.egg,"Codorna", "R$1,00", 4),
            ingredient(R.drawable.capsicum,"Pimentão", "R$1,50", 5)
        )
    }
}