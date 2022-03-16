package com.ratushny.poeasisto.ninja.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.ninja.data.model.NinjaListItem
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ln
import kotlin.math.pow

class NinjaListAdapter :
    RecyclerView.Adapter<NinjaListAdapter.ViewHolder>() {

    private var ninjaCurrencyData: List<NinjaListItem> = emptyList()
    lateinit var clickListener: ClickListener

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, view: View)
    }

    fun addCurrencyList(currencyList: List<NinjaListItem>) {
        ninjaCurrencyData = currencyList
        notifyDataSetChanged()
    }

    private fun getFormatedNumber(count: Float): String {
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

    override fun getItemCount() = ninjaCurrencyData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ninjaCur: NinjaListItem = ninjaCurrencyData[position]
        holder.itemTextView.text = ninjaCur.typeName
        holder.currencyPriceTextView.text = getFormatedNumber(ninjaCur.value)
        if (ninjaCur.change > 0) {
            holder.currencyPriceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceUp))
            holder.currencyPriceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
            holder.currencyPriceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceUp))
        } else if (ninjaCur.change < 0) {
            holder.currencyPriceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceDown))
            holder.currencyPriceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
            holder.currencyPriceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceDown))
        }
        holder.currencyPriceChangeTextView.text = getFormatedNumber(ninjaCur.change) + "%"

        Glide.with(holder.itemView.context)
            .load(ninjaCur.image)
            .into(holder.currencyImageView)
    }

    inner class ViewHolder(
        itemView: View,
        val currencyImageView: ImageView = itemView.findViewById(R.id.item_icon_imageview),
        val itemTextView: TextView = itemView.findViewById(R.id.item_name_textview),
        val currencyPriceTextView: TextView = itemView.findViewById(R.id.item_price),
        val currencyPriceChangeTextView: TextView = itemView.findViewById(R.id.item_change_textview),
        val currencyPriceChangeImageView: ImageView = itemView.findViewById(R.id.item_change_arrow_imageview)
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
//            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}