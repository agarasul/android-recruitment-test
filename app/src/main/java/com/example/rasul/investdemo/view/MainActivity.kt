package com.example.rasul.investdemo.view

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.rasul.investdemo.adapter.CurrencyAdapter
import com.example.rasul.investdemo.R
import com.example.rasul.investdemo.dbhelper.CurrencyDAO
import com.example.rasul.investdemo.dbhelper.SQLiteHelper
import com.example.rasul.investdemo.entity.Result
import com.example.rasul.investdemo.service.WebSocketBuilder
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), IView.MainView, View.OnClickListener {

    lateinit var sqLiteHelper: CurrencyDAO
    lateinit var webSocketBuilder: WebSocketBuilder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        sqLiteHelper = SQLiteHelper(this)
        webSocketBuilder = WebSocketBuilder()
        webSocketBuilder.startConnection(this, this)
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
            val adapter = CurrencyAdapter(this, response)
            currencyList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            currencyList.adapter = adapter
        }
    }

    override fun onError(exception: Throwable) {
        runOnUiThread {
            progressBar.visibility = View.INVISIBLE
            refreshBtn.visibility = View.VISIBLE
        }

    }


    override fun onClick(p0: View) {
        when (p0.id) {
            R.id.refreshBtn -> {
                webSocketBuilder.startConnection(this, this)
                refreshBtn.visibility = View.INVISIBLE
                progressBar.visibility = View.VISIBLE
            }
        }
    }

}
