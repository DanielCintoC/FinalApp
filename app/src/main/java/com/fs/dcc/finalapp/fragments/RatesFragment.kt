package com.fs.dcc.finalapp.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fs.dcc.finalapp.R
import com.fs.dcc.finalapp.adapters.RatesAdapter
import com.fs.dcc.finalapp.dialogues.RateDialog
import com.fs.dcc.finalapp.models.Rate
import kotlinx.android.synthetic.main.fragment_rates.view.*

class RatesFragment : Fragment() {

    private lateinit var _view: View
    private lateinit var adapter: RatesAdapter
    private val rates: ArrayList<Rate> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_rates, container, false)
        setUpRecyclerView()
        setUpFAB()
        return _view
    }

    private fun setUpRecyclerView() {

        val layoutManager = LinearLayoutManager(context)
        adapter = RatesAdapter(rates)
        _view.recyclerView.setHasFixedSize(true)
        _view.recyclerView.layoutManager = layoutManager
        _view.recyclerView.itemAnimator = DefaultItemAnimator()
        _view.recyclerView.adapter = adapter

    }

    private fun setUpFAB() {
        _view.fabRating.setOnClickListener { RateDialog().show(fragmentManager, "")}
    }

}
