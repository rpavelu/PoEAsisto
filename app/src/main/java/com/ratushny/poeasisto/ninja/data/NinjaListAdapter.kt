package com.ratushny.poeasisto.ninja.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.ninja.data.model.NinjaListItem
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*
import kotlin.math.ln
import kotlin.math.pow

class NinjaListAdapter :
    RecyclerView.Adapter<NinjaListAdapter.ViewHolder>(),
    Filterable {

    private var ninjaItemData: List<NinjaListItem> = emptyList()
    private var filteredNinjaItemData: List<NinjaListItem> = emptyList()

    lateinit var clickListener: ClickListener

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, view: View)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                ninjaItemData = if (charSearch.isEmpty()) {
                    filteredNinjaItemData
                } else {
                    val resultList: MutableList<NinjaListItem> = ArrayList()
                    for (row in filteredNinjaItemData) {
                        if (row.typeName.lowercase().contains(charSearch.lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = ninjaItemData
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ninjaItemData = results?.values as List<NinjaListItem>
                notifyDataSetChanged()
            }
        }
    }

    fun addItemList(itemList: List<NinjaListItem>) {
        ninjaItemData = itemList
        filteredNinjaItemData = itemList

        notifyDataSetChanged()
    }

    private fun formatNumber(count: Float): String {
        if (count < 1000)
            return BigDecimal(count.toDouble()).setScale(1, RoundingMode.HALF_EVEN).toString()
        val exp = (ln(count) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
            .replace(" ", "")
            .replace(",", ".")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.ninja_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ninjaItemData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ninjaCur: NinjaListItem = ninjaItemData[position]
        holder.nameTextView.text = ninjaCur.typeName
        holder.priceTextView.text = formatNumber(ninjaCur.value)
        if (ninjaCur.change > 0) {
            holder.priceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceUp))
            holder.priceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
            holder.priceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceUp))
        } else if (ninjaCur.change < 0) {
            holder.priceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceDown))
            holder.priceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
            holder.priceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceDown))
        }
        holder.priceChangeTextView.text = formatNumber(ninjaCur.change) + "%"

        Glide.with(holder.itemView.context)
            .load(ninjaCur.image)
            .into(holder.iconImageView)
    }

    inner class ViewHolder(
        itemView: View,
        val iconImageView: ImageView = itemView.findViewById(R.id.icon),
        val nameTextView: TextView = itemView.findViewById(R.id.name),
        val priceTextView: TextView = itemView.findViewById(R.id.price),
        val priceChangeTextView: TextView = itemView.findViewById(R.id.price_change),
        val priceChangeImageView: ImageView = itemView.findViewById(R.id.price_change_arrow)
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
//            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}