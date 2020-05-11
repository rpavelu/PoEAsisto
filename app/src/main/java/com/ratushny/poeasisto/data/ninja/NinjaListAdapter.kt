package com.ratushny.poeasisto.data.ninja

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.data.ninja.currency.model.NinjaCurrency
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.ln
import kotlin.math.pow

class NinjaListAdapter :
    RecyclerView.Adapter<NinjaListAdapter.ViewHolder>() {

    private var ninjaCurrencyData: List<NinjaCurrency> = emptyList()
    lateinit var clickListener: ClickListener

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onClick(pos: Int, view: View)
    }

    fun addCurrencyList(currencyList: List<NinjaCurrency>) {
        ninjaCurrencyData = currencyList
        notifyDataSetChanged()
    }

    private fun getFormatedNumber(count: Double): String {
        if (count < 1000)
            return BigDecimal(count).setScale(1, RoundingMode.HALF_EVEN).toString()
        val exp = (ln(count) / ln(1000.0)).toInt()
        return String.format("%.1f %c", count / 1000.0.pow(exp.toDouble()), "kMGTPE"[exp - 1])
            .replace(" ", "")
            .replace(",", ".")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.ninja_currency_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ninjaCurrencyData.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ninjaCur: NinjaCurrency = ninjaCurrencyData[position]
        holder.currencyTextView.text = ninjaCur.currencyTypeName
        holder.currencyPriceTextView.text = getFormatedNumber(ninjaCur.currencyValue)
        if (ninjaCur.currencyChange > 0) {
            holder.currencyPriceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceUp))
            holder.currencyPriceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_up_black_24dp)
            holder.currencyPriceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceUp))
        } else if (ninjaCur.currencyChange < 0) {
            holder.currencyPriceChangeTextView.setTextColor(holder.itemView.context.getColor(R.color.priceDown))
            holder.currencyPriceChangeImageView.setImageResource(R.drawable.ic_arrow_drop_down_black_24dp)
            holder.currencyPriceChangeImageView.setColorFilter(holder.itemView.context.getColor(R.color.priceDown))
        }
        holder.currencyPriceChangeTextView.text = getFormatedNumber(ninjaCur.currencyChange) + "%"

        Glide.with(holder.itemView.context)
            .load(ninjaCur.currencyImage)
            .into(holder.currencyImageView)
    }

    inner class ViewHolder(
        itemView: View,
        val currencyImageView: ImageView = itemView.findViewById(R.id.currency_image_view),
        val currencyTextView: TextView = itemView.findViewById(R.id.currency_text_view),
        val currencyPriceTextView: TextView = itemView.findViewById(R.id.currency_price),
        val currencyPriceChangeTextView: TextView = itemView.findViewById(R.id.currency_change_text_view),
        val currencyPriceChangeImageView: ImageView = itemView.findViewById(R.id.currency_change_arrow_image_view)
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        override fun onClick(v: View) {
//            mClickListener.onClick(adapterPosition, v)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }
}