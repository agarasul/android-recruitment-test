package com.example.rasul.investdemo.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rasul.investdemo.R
import com.example.rasul.investdemo.entity.Result
import java.text.SimpleDateFormat
import java.util.*

class CurrencyAdapter(internal var context: Context, internal var currencyList: ArrayList<Result>) : RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.cart_item, parent, false)
        return CurrencyHolder(view)
    }

    override fun getItemCount(): Int = currencyList.size

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {
        holder.currencyNameTxt.text = currencyList[position].currencyName
        val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        holder.dateTxt.text = sdf.format(currencyList[position].date)

        if (currencyList[position].currencyState == "up") {
            holder.buyPriceTxt.setTextColor(context.getColor(android.R.color.holo_green_dark))
            holder.buyPriceTxt.setTextColor(context.getColor(android.R.color.holo_green_dark))
        } else {
            holder.buyPriceTxt.setTextColor(context.resources.getColor(android.R.color.holo_red_dark))
            holder.buyPriceTxt.setTextColor(context.resources.getColor(android.R.color.holo_red_dark))
        }

        holder.buyPriceTxt.text = currencyList[position].buyPrice
        holder.salePriceTxt.text = currencyList[position].sellPrice
        holder.spreadTxt.text = currencyList[position].spread.toString()


    }


    inner class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyNameTxt: TextView = itemView.findViewById(R.id.currencyNameTxt)
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        val buyPriceTxt: TextView = itemView.findViewById(R.id.buyPriceTxt)
        val salePriceTxt: TextView = itemView.findViewById(R.id.salePriceTxt)
        val spreadTxt: TextView = itemView.findViewById(R.id.spreadTxt)

    }
}