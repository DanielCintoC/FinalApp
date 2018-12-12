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
import com.fs.dcc.finalapp.models.NewRateEvent
import com.fs.dcc.finalapp.models.Rate
import com.fs.dcc.finalapp.utils.RxBus
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_rates.view.*

class RatesFragment : Fragment() {

    private lateinit var _view: View
    private lateinit var adapter: RatesAdapter
    private val rates: ArrayList<Rate> = ArrayList()

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser
    private val store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var ratesDBRef: CollectionReference
    private  var ratesSubscriptor: ListenerRegistration? = null
    private lateinit var rateBusListener: Disposable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _view = inflater.inflate(R.layout.fragment_rates, container, false)

        setUpRatesDB()
        setUpCurrentUser()

        setUpRecyclerView()
        setUpFAB()

        subscribeToNewRatings()
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

    private fun setUpRatesDB() {
        ratesDBRef = store.collection("rates")
    }

    private fun setUpCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

    private fun saveRate(rate: Rate) {}

    private fun subscribeToRatings() {}

    private fun subscribeToNewRatings() {
        RxBus.listen(NewRateEvent::class.java).subscribe({
            saveRate(it.rate)
        })
    }

}
