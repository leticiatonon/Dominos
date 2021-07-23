package com.tonon.dominos.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso
import com.tonon.dominos.R
import com.tonon.dominos.databinding.ItemDiscountBinding
import com.tonon.dominosapp.model.discount
import com.tonon.dominosapp.model.flavour
import kotlinx.android.synthetic.main.fragment_discount.*
import kotlinx.android.synthetic.main.fragment_food.*
import kotlinx.android.synthetic.main.item_flavours.*


class Discount : Fragment() {

    private lateinit var discountAdapter : DiscountAdapter

    companion object {
        fun newInstance(): Discount {
            val fragmentHome = Discount()
            val arg = Bundle()
            fragmentHome.arguments = arg
            return fragmentHome
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discount, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val foods: MutableList<discount> = getDiscount() as MutableList<discount>

        discountAdapter = DiscountAdapter(foods)
        recyclerview_discount.adapter = discountAdapter
        recyclerview_discount.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

    }

    private fun getDiscount(): List<discount>{
        return arrayListOf(
            discount(R.drawable.pizzagrande, "2 pizzas grande", "com 40% de desconto", 1),
            discount(R.drawable.pizzamedia, "2 pizzas média", "com 40% de desconto", 2),
            discount(R.drawable.brotinho, "Brotinho R$10,00", "em qualquer compra", 3),
            discount(R.drawable.cocaesanduba, "Combo Sanduba", "R$26,90 sanduíche \n" + "+ coca lata", 4),
            discount(R.drawable.grandeecoca, "1 pizzas grande \n" + "coca 2 litros", "R$65,90", 5),
            discount(R.drawable.duasmediasecoca, "2 pizzas média \n" + "coca 2 litros", "72,90", 6),
            discount(R.drawable.pepperock, "2 pizzas grande", "com 40% de desconto", 7),
            discount(R.drawable.pepperock, "2 pizzas grande", "com 40% de desconto", 8),
            discount(R.drawable.pepperock, "2 pizzas grande", "com 40% de desconto", 9),
            discount(R.drawable.pepperock, "2 pizzas grande", "com 40% de desconto", 10)

        ).toList()

    }

    class DiscountAdapter(private val discount: List<discount>): RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscountViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemDiscountBinding.inflate(layoutInflater, parent, false)
            return DiscountViewHolder(binding)
        }

        override fun onBindViewHolder(holder: DiscountViewHolder, position: Int) {
            holder.bind(discount[position])
        }

        override fun getItemCount(): Int {
           return discount.size
        }

        class DiscountViewHolder(private val binding: ItemDiscountBinding): RecyclerView.ViewHolder(binding.root){
            fun bind(discount: discount){
                Picasso.get().load(discount.image).into(binding.imgFood)
                binding.textNameFood.text = discount.name
                binding.textDescriptionFood.text = discount.description
            }


        }

    }




}