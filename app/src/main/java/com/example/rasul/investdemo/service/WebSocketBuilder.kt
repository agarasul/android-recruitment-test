package com.example.rasul.investdemo.service

import android.content.Context
import az.hellodigital.shofero.service.MySocketListener
import com.example.rasul.investdemo.view.IView
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket

class WebSocketBuilder {

    var client: OkHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .build()

    private lateinit var webSocket: WebSocket
    val SOCKET_CONNECTION = "wss://q.investaz.net:3000/socket.io/?EIO=3&transport=websocket"

    fun startConnection(context: Context, mainView: IView.MainView) {
        val request = Request.Builder().url(SOCKET_CONNECTION)
                .build()
        val listener = MySocketListener(context, mainView)
        webSocket = client.newWebSocket(request, listener)
    }
}