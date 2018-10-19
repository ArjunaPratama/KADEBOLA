package com.arjuna.kadebola.Presenter

import com.arjuna.kadebola.Api.ApiRepository
import com.arjuna.kadebola.Api.SportDBApi
import com.arjuna.kadebola.DetailActivity
import com.arjuna.kadebola.Model.club.ClubResponse
import com.arjuna.kadebola.Model.club.TeamsItem
import com.arjuna.kadebola.Model.event.Events
import com.arjuna.kadebola.Model.event.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val view: DetailMatchView) {
    fun getDetailClub(team1: String, team2: String){
        view.showLoading()
        doAsync {
            val data1 = gson
                    .fromJson(apiRepository
                            .doRequest(SportDBApi.getDetailClub(team1)),
                            ClubResponse::class.java
                    )
            val data2 = gson
                    .fromJson(apiRepository
                            .doRequest(SportDBApi.getDetailClub(team2)),
                            ClubResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showDetailClub(data1.teams as List<TeamsItem>, data2.teams as List<TeamsItem>)
            }
        }
    }

    fun getDetailMatch(league: String?){
        view.showLoading()
        doAsync {
            val data = gson
                    .fromJson(apiRepository
                            .doRequest(SportDBApi
                                    .getDetailMatch(league)),
                            MatchResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showDetailMatch(data.events as List<Events>)
            }
        }
    }
}