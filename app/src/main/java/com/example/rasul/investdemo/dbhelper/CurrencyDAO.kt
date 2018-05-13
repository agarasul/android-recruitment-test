package com.example.rasul.investdemo.dbhelper

import com.example.rasul.investdemo.entity.Result

interface CurrencyDAO {

    fun getAllCurrencies(): ArrayList<Result>
    fun saveAllCurrencies(currencies: List<Result>): Boolean
}