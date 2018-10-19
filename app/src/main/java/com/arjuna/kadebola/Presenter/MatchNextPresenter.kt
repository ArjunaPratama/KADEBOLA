package com.arjuna.kadebola.Presenter

import com.arjuna.kadebola.Api.ApiRepository
import com.arjuna.kadebola.Api.SportDBApi
import com.arjuna.kadebola.Model.EventResponse
import com.arjuna.kadebola.Model.EventsItem
import com.arjuna.kadebola.Model.mainview
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchNextPresenter(private val view: mainview,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getNextList(league: String){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(SportDBApi.getNextMatch(league)),
                    EventResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showMatchList(data.events as List<EventsItem>)

            }
        }
    }
}