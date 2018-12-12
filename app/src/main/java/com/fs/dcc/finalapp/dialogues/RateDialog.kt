package com.fs.dcc.finalapp.dialogues

import android.support.v7.app.AlertDialog
import android.app.Dialog
import android.support.v4.app.DialogFragment
import android.os.Bundle
import com.fs.dcc.finalapp.R
import com.fs.dcc.finalapp.models.NewRateEvent
import com.fs.dcc.finalapp.models.Rate
import com.fs.dcc.finalapp.utils.RxBus
import com.fs.dcc.finalapp.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dialog_rate.view.*
import java.util.*

class RateDialog: DialogFragment(){

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        setUpCurrentUser()
        val view = activity!!.layoutInflater.inflate(R.layout.dialog_rate, null)

        return AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.dialog_title))
                .setView(view)
                .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                    val textRate = view.editTextRateFeedback.text.toString()
                    if (textRate.isNotEmpty()) {
                        val imageURL = currentUser.photoUrl?.toString() ?: run {""}
                        val rate = Rate(currentUser.uid,textRate, view.ratingBarFeedBack.rating, Date(), imageURL)
                        RxBus.publish(NewRateEvent(rate))
                    } else {
                        activity!!.toast(getString(R.string.dialog_message_not_empty_text))
                    }

                }
                .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                    activity!!.toast("Pressed CANCEL")
                }
                .create()

    }

    private fun setUpCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

}