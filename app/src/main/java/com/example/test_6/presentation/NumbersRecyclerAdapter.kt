package com.example.test_6.presentation

import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_6.databinding.NumberLayoutBinding

class NumbersRecyclerAdapter(private val numberList: List<Numbers>) :
    RecyclerView.Adapter<NumbersRecyclerAdapter.NumbersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return NumbersViewHolder(NumberLayoutBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return numberList.size
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        val number = numberList[position]
        holder.bind(number)
    }

    var onItemClick: ((Numbers) -> Unit)? = null

    inner class NumbersViewHolder(private val binding: NumberLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(number: Numbers) {
            with(binding) {
                if (number.numbers != null) {
                    tvButton.text = "${number.numbers}"
                } else {
                    tvButton.text = number.icon
                }
                tvButton.setOnClickListener {
                    onItemClick?.invoke(number)
                    d("number", "${number.numbers}")
                }
            }
        }
    }

}