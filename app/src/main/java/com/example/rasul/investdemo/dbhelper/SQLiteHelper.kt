package com.example.rasul.investdemo.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.rasul.investdemo.entity.Result
import java.io.IOException

class SQLiteHelper(internal var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION), CurrencyDAO {
    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "tbms.db"
        private val CURRENCY_TABLE = "Currencies"
        private val tableQuery = "CREATE TABLE " + CURRENCY_TABLE +
                "    id             INTEGER      PRIMARY KEY AUTOINCREMENT\n" +
                "                                UNIQUE\n" +
                "                                NOT NULL,\n" +
                "    currency_name  VARCHAR (50) NOT NULL,\n" +
                "    currency_state VARCHAR (50) NOT NULL,\n" +
                "    buyPrice       VARCHAR (50) NOT NULL,\n" +
                "    sellPrice      VARCHAR (50) NOT NULL,\n" +
                "    date           VARCHAR (50) NOT NULL,\n" +
                "    spread         INTEGER (3) NOT NULL\n" +
                ");\n"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(tableQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    override fun getAllCurrencies(): List<Result> {
        return emptyList()
    }

    override fun saveAllCurrencies(currencies: List<Result>): Boolean {
        val db = this.writableDatabase
        db.execSQL("delete from $CURRENCY_TABLE");
        try {
            for (currency in currencies) {
                val values = ContentValues()
                values.put("currency_name", currency.currencyName)
                values.put("currency_state", currency.currencyState)
                values.put("buyPrice", currency.buyPrice)
                values.put("sellPrice", currency.sellPrice)
                values.put("date", currency.date)
                values.put("spread", currency.spread)
                db.insert(CURRENCY_TABLE, null, values)
            }
            db.close()
            return true
        } catch (exception: IOException) {
            return false
        }
    }
}