package com.example.rasul.investdemo.view

import com.example.rasul.investdemo.entity.Result
import com.example.rasul.investdemo.entity.SocketResponse
import org.json.JSONArray
import java.io.IOException

interface IView {

    interface MainView {
        fun onSuccess(response: ArrayList<Result>)
        fun onError(exception: Throwable)
    }
}