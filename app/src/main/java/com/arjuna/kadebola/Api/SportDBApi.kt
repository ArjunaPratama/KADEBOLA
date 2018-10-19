package com.arjuna.kadebola.Api

object SportDBApi {

    fun getNextMatch(league: String?): String {
        //https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=4328
        return "https://www.thesportsdb.com/api/v1/json/1/eventsnextleague.php?id=$league"

    }

    fun getPrevMatch(league: String?): String{
        return "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=$league"
    }

    fun getDetailClub(league: String?): String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id=$league"
    }

    fun getDetailMatch(league: String?): String{
        return "https://www.thesportsdb.com/api/v1/json/1/lookupevent.php?id=$league"
    }
}

