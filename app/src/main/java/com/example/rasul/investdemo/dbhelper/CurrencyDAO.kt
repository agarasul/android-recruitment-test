package com.example.rasul.investdemo.dbhelper

import com.example.rasul.investdemo.entity.Result

interface CurrencyDAO {

    fun getAllCurrencies(): List<Result>
    fun saveAllCurrencies(currencies: List<Result>):Boolean
}