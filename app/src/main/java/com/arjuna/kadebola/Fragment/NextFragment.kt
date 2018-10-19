package com.arjuna.kadebola.Fragment


import android.graphics.Color
import android.os.Bundle
import android.support.constraint.R.id.parent
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
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

import com.arjuna.kadebola.R
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next.view.*
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.intentFor
import org.jetbrains.anko.support.v4.onRefresh
import java.text.FieldPosition


class NextFragment : Fragment(), mainview {

    private var eventnya: MutableList<EventsItem> = mutableListOf()
    private lateinit var Npresenter: MatchNextPresenter
    private lateinit var Nadapter: AdapterMatch
    private lateinit var Matchnya: String
    private lateinit var Listnya: RecyclerView
    private lateinit var Npbar: ProgressBar
    private lateinit var Nspinner: Spinner
    private lateinit var Nswiperesfresh: SwipeRefreshLayout

    companion object {
        fun newInstance(): NextFragment {
            var fragmentNext = NextFragment()
            var pendapat = Bundle()
            fragmentNext.arguments = pendapat
            return fragmentNext
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_next, container, false)
        val kegiatan = activity

        Nadapter = AdapterMatch(context, eventnya) {
            startActivity(intentFor<DetailActivity>(
                    "ID_CLUBA" to it.idHomeTeam,
                    "ID_CLUBB" to it.idAwayTeam,
                    "ID_EVENT" to it.idEvent
            ))
        }
        Listnya = view.findViewById(R.id.rcListNext)
        Listnya.layoutManager = LinearLayoutManager(kegiatan)
        Listnya.adapter = Nadapter

        Nspinner = view.spinnernya
        Nswiperesfresh = view.swipe
        Npbar = view.pBar

        val spinner = resources.getStringArray(R.array.league)
        val spinnerid = resources.getStringArray(R.array.league_id)
        Nspinner.adapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinner)
        Nspinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val getLeague = Nspinner.selectedItemPosition
                Matchnya = spinnerid[getLeague].toString()
                Npresenter.getNextList(Matchnya)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        Nswiperesfresh.onRefresh {
            Npresenter.getNextList(Matchnya)
        }
        Nswiperesfresh.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE)

        val request = ApiRepository()
        val gson = Gson()
        Npresenter = MatchNextPresenter(this, request, gson)
        return view
    }

    override fun showLoading() {
        Npbar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        Npbar.visibility = View.INVISIBLE
    }

    override fun showMatchList(data: List<EventsItem>) {
        Nswiperesfresh.isRefreshing = false
        eventnya.clear()
        eventnya.addAll(data)
        Nadapter.notifyDataSetChanged()
    }


}
