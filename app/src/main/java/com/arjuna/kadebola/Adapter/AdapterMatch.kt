package com.arjuna.kadebola.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arjuna.kadebola.Model.EventsItem
import com.arjuna.kadebola.R
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class AdapterMatch(private val context: Context?,
        private val myMatchList: MutableList<EventsItem>,
                   private val listener: (EventsItem) -> Unit): RecyclerView.Adapter<AdapterMatch.MatchViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMatch.MatchViewHolder {
       val view = LayoutInflater.from(parent.context)
               .inflate(R.layout.list_item, parent,false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int =
            myMatchList.size

    override fun onBindViewHolder(holder: AdapterMatch.MatchViewHolder, position: Int) {
        holder.bindItem(myMatchList[position], listener)
    }
    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var view: View = view
        fun bindItem(myMatchList: EventsItem, listener: (EventsItem) -> Unit){
            view.txtteam1.text = myMatchList.strHomeTeam.toString()
            view.txtteam2.text = myMatchList.strAwayTeam.toString()

            var tanggalpertandingan = myMatchList.dateEvent
            var locale = Locale("ID")
            var formatwaktu = SimpleDateFormat("yyyy-M-d", locale)
            val tanggale: Date

            try {
                tanggale = formatwaktu.parse(tanggalpertandingan)
                formatwaktu = SimpleDateFormat("E, d MMM yyyy", locale)
                tanggalpertandingan = formatwaktu.format(tanggale)
            }catch (e: ParseException){
                e.printStackTrace()
            }
            view.txtjadwal.text = tanggalpertandingan
            view.txtevent.text = myMatchList.idEvent


            if (myMatchList.intAwayScore != null){
                view.txthasil.text = myMatchList.intHomeScore.toString() + " vs "+myMatchList.intAwayScore
            }else{
                view.txthasil.text = "VS"
            }
            itemView.setOnClickListener { listener(myMatchList) }

        }

    }
}