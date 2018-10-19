package com.arjuna.kadebola.Presenter

import com.arjuna.kadebola.Model.club.TeamsItem
import com.arjuna.kadebola.Model.event.Events


interface DetailMatchView {

    fun showLoading()

    fun showDetailClub(data1: List<TeamsItem>, data2: List<TeamsItem>)

    fun showDetailMatch(data: List<Events>)

    fun hideLoading()

}