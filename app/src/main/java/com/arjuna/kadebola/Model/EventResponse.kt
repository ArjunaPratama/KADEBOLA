package com.arjuna.kadebola.Model


import com.google.gson.annotations.SerializedName


data class EventResponse(

	@field:SerializedName("events")
	val events: List<EventsItem?>? = null
)