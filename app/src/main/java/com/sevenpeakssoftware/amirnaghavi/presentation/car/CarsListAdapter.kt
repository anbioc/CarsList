package com.sevenpeakssoftware.amirnaghavi.presentation.car

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sevenpeakssoftware.amirnaghavi.databinding.ItemCarBinding
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity

class CarsListAdapter() :
    RecyclerView.Adapter<CarItemViewHolder>() {

    var itemList: List<CarEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarItemViewHolder =
        CarItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: CarItemViewHolder, position: Int) =
        holder.bind(itemList[position])

    override fun getItemCount(): Int = itemList.size

}

class CarItemViewHolder(
    private val binding: ItemCarBinding,
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(
            parent: ViewGroup,
        ): CarItemViewHolder = CarItemViewHolder(
            ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun bind(car: CarEntity) {
        Glide.with(binding.root.context).load(car.image).into(binding.itemCarImage)
        binding.itemCarTitleTv.text = car.title
        binding.itemCarDescriptionTv.text = car.ingress
        binding.itemCarDateTv.text = car.dateTime
    }
}