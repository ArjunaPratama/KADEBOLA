package com.arjuna.kadebola.Model.club


import com.google.gson.annotations.SerializedName


data class ClubResponse(

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null
)