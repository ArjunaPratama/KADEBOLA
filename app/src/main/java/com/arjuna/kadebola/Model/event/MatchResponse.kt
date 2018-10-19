package com.arjuna.kadebola.Model.event


import com.google.gson.annotations.SerializedName


data class MatchResponse(

	@field:SerializedName("events")
	val events: List<Events?>? = null
)