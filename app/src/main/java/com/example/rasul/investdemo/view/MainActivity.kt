package com.example.rasul.investdemo.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.rasul.investdemo.MyUtils
import com.example.rasul.investdemo.R
import com.example.rasul.investdemo.adapter.CurrencyAdapter
import com.example.rasul.investdemo.dbhelper.CurrencyDAO
import com.example.rasul.investdemo.dbhelper.SQLiteHelper
import com.example.rasul.investdemo.entity.Result
import com.example.rasul.investdemo.service.WebSocketBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IView.MainView, View.OnClickListener {

    var isCached = false
    var isFirst = true
    lateinit var sqLiteHelper: CurrencyDAO
    lateinit var adapter: CurrencyAdapter
    lateinit var webSocketBuilder: WebSocketBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        sqLiteHelper = SQLiteHelper(this)
        webSocketBuilder = WebSocketBuilder()
        val isNetworkAvailable = MyUtils.isNetworkAvailable(this)
        if (isNetworkAvailable) webSocketBuilder.startConnection(this, this)
        else {
            isCached = true
            refreshBtn.visibility = View.VISIBLE
            progressBar.visibility = View.INVISIBLE
            Toast.makeText(this, "You're not connected to the internet now. So cached data will shown", Toast.LENGTH_LONG).show()
            onSuccess(sqLiteHelper.getAllCurrencies())
        }
    }


    private fun initViews() {
        refreshBtn.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE
        progressBar.isIndeterminate = true
        refreshBtn.setOnClickListener(this)
    }


    override fun onSuccess(response: ArrayList<Result>) {
        sqLiteHelper.saveAllCurrencies(response)

        runOnUiThread {
            if (response.isEmpty()) {
                noDataView.visibility = View.VISIBLE
                messageTxt.text = "You've no cached data. Please check your connection to load new data"
                progressBar.visibility = View.INVISIBLE
                refreshBtn.visibility = View.VISIBLE
            } else {
                noDataView.visibility = View.INVISIBLE
                if (isCached) {
                    progressBar.visibility = View.INVISIBLE
                    refreshBtn.visibility = View.VISIBLE
                } else {
                    isCached = false
                    progressBar.visibility = View.VISIBLE
                    refreshBtn.visibility = View.INVISIBLE
                }
                if (isFirst) {
                    adapter = CurrencyAdapter(this, response)
                    isFirst = false
                    currencyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    currencyList.adapter = adapter
                } else {
                    adapter.currencyList = response
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    override fun onError(exception: Throwable) {
        runOnUiThread {
            if (adapter.currencyList.isNotEmpty()) {
                isCached = true
                progressBar.visibility = View.INVISIBLE
                refreshBtn.visibility = View.VISIBLE
            } else {
                noDataView.visibility = View.VISIBLE
                messageTxt.text = "Please check your connection to load new data"
                progressBar.visibility = View.VISIBLE
                refreshBtn.visibility = View.INVISIBLE
            }

        }

    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.refreshBtn -> {
                if (MyUtils.isNetworkAvailable(this)) {
                    webSocketBuilder.startConnection(this, this)
                    isCached = false
                    refreshBtn.visibility = View.INVISIBLE
                    progressBar.visibility = View.VISIBLE
                } else Toast.makeText(this, "You're not connected to the internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
