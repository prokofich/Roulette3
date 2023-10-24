package com.example.roulette.presenter.presenters

import android.os.Looper
import com.example.roulette.model.ResponseGet
import com.example.roulette.presenter.presenterInterface.PresenterRulesInterface
import com.example.roulette.presenter.viewInterface.ViewRulesInterface
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import android.os.Handler
import com.example.roulette.model.constant.URL_JSON_FILE

class PresenterRules(private val view: ViewRulesInterface):PresenterRulesInterface {

    //отправка запроса+получение ответа в виде текста правил
    override fun loadTextRules() {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(URL_JSON_FILE)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val responseFromServer = Gson().fromJson(responseBody,ResponseGet::class.java)
                Handler(Looper.getMainLooper()).post {
                    view.showTextRules(responseFromServer.text)
                }
            }
        })
    }
}