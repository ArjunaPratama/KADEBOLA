package com.arjuna.kadebola.Fragment


import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.arjuna.kadebola.Adapter.AdapterMatch
import com.arjuna.kadebola.Api.ApiRepository
import com.arjuna.kadebola.DetailActivity
import com.arjuna.kadebola.Model.EventsItem
import com.arjuna.kadebola.Model.mainview
import com.arjuna.kadebola.Presenter.MatchNextPresenter
import com.arjuna.kadebola.Presenter.MatchPrevPresenter

import com.arjuna.kadebola.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh

class PrevFragment : Fragment(), mainview {
    override fun showLoading() {
        Ppbar.visibility = View.VISIBLE
    }

    override fun showMatchList(data: List<EventsItem>) {
        Pswiperesfresh.isRefreshing = false
        eventnya.clear()
        eventnya.addAll(data)
        Padapter.notifyDataSetChanged()

    }

    override fun hideLoading() {
        Ppbar.visibility = View.INVISIBLE
    }

    private var eventnya: MutableList<EventsItem> = mutableListOf()
    private lateinit var Ppresenter: MatchPrevPresenter
    private lateinit var Padapter: AdapterMatch
    private lateinit var Matchnya: String
    private lateinit var Listnya: RecyclerView
    private lateinit var Ppbar: ProgressBar
    private lateinit var Pspinner: Spinner
    private lateinit var Pswiperesfresh: SwipeRefreshLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_next, container, false)
        val kegiatan = activity

        Padapter = AdapterMatch(context, eventnya) {
            startActivity(intentFor<DetailActivity>(
                    "ID_CLUBA" to it.idHomeTeam,
                    "ID_CLUBB" to it.idAwayTeam,
                    "ID_EVENT" to it.idEvent
            ))
        }
        Listnya = view.findViewById(R.id.rcListNext)
        Listnya.layoutManager = LinearLayoutManager(kegiatan)
        Listnya.adapter = Padapter

        Pspinner = view.spinnernya
        Pswiperesfresh = view.swipe
        Ppbar = view.pBar

        val spinner = resources.getStringArray(R.array.league)
        val spinnerid = resources.getStringArray(R.array.league_id)
        Pspinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinner)
        Pspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val getLeague = Pspinner.selectedItemPosition
                Matchnya = spinnerid[getLeague].toString()
                Ppresenter.getPrevList(Matchnya)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        Pswiperesfresh.onRefresh {
            Ppresenter.getPrevList(Matchnya)
        }
        Pswiperesfresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE)

        val request = ApiRepository()
        val gson = Gson()
        Ppresenter = MatchPrevPresenter(this, request, gson)
        return view
    }


}
