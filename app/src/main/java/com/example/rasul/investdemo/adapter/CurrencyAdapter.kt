package com.example.rasul.investdemo.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        holder.dateTxt.text = dateFormat(currencyList[position].date)

        if (currencyList[position].currencyState.trim().toLowerCase() == "up") {
            holder.buyPriceTxt.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            holder.salePriceTxt.setTextColor(ContextCompat.getColor(context, android.R.color.holo_green_dark))
            holder.currencyStateImg.setImageResource(R.drawable.ic_trending_up)
        } else {
            holder.currencyStateImg.setImageResource(R.drawable.ic_trending_down)
            holder.buyPriceTxt.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
            holder.salePriceTxt.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
        }
        holder.buyPriceTxt.text = currencyList[position].buyPrice
        holder.salePriceTxt.text = currencyList[position].sellPrice
        holder.spreadTxt.text = currencyList[position].spread.toString()


    }

//    private fun currencyPriceSpan(currencyText: String): SpannableString {
//
//        Log.d("AAAa ", currencyText)
//        val formattedText = SpannableString(currencyText);
//        formattedText.setSpan(CustomTypeFaceSpan(20, context), 0, currencyText.length - 1,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        formattedText.setSpan(CustomTypeFaceSpan(14, context), currencyText.length - 1, currencyText.length,
//                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return formattedText
//    }


    private fun dateFormat(dateText: String): String {
        val sdf = SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
        val newFormat = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.US)
        val date = sdf.parse(dateText)
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return newFormat.format(date)
    }

    inner class CurrencyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currencyNameTxt: TextView = itemView.findViewById(R.id.currencyNameTxt)
        val dateTxt: TextView = itemView.findViewById(R.id.dateTxt)
        val buyPriceTxt: TextView = itemView.findViewById(R.id.buyPriceTxt)
        val salePriceTxt: TextView = itemView.findViewById(R.id.salePriceTxt)
        val spreadTxt: TextView = itemView.findViewById(R.id.spreadTxt)
        val currencyStateImg: ImageView = itemView.findViewById(R.id.currencyStateImg)


    }
}