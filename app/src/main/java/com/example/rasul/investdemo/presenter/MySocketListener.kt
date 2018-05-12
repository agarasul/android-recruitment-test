package az.hellodigital.shofero.service

import android.content.Context
import android.util.Log
import com.example.rasul.investdemo.entity.Result
import com.example.rasul.investdemo.view.IView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONArray


/**
 * Created by rasul on 04.02.2018.
 */

class MySocketListener(private var context: Context, private var mainView: IView.MainView) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
    }

    override fun onMessage(webSocket: WebSocket?, response: String?) {
        super.onMessage(webSocket, response)

        if (response!!.startsWith("42")) {
            val jsonArray = JSONArray(response.substring(2))
            val responseList: ArrayList<Result> = Gson().fromJson(jsonArray.getJSONObject(1).getJSONArray("result").toString(), object : TypeToken<ArrayList<Result>>() {}.type)
            mainView.onSuccess(responseList)
        }
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)
        Log.d("AAAA", " ON CLOSED " + "code " + code.toString() + " reason" + reason)
    }


    override fun onFailure(webSocket: WebSocket?, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d("AAAA", "CONNECTION IS LOST")
        t.printStackTrace()
        mainView.onError(t)
    }
}
