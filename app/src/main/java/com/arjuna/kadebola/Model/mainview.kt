package com.arjuna.kadebola.Model

interface mainview {
    fun showLoading()

    fun showMatchList(data: List<EventsItem>)

    fun hideLoading()
}