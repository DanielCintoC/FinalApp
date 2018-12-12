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
import kotlinx.android.synthetic.main.dialog_rate.view.*
import java.util.*

class RateDialog: DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = activity!!.layoutInflater.inflate(R.layout.dialog_rate, null)

        return AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.dialog_title))
                .setView(view)
                .setPositiveButton(getString(R.string.dialog_ok)) { _, _ ->
                    val textRate = view.editTextRateFeedback.text.toString()
                    val imageURL = FirebaseAuth.getInstance().currentUser!!.photoUrl?.toString() ?: run {""}
                    val rate = Rate(textRate, view.ratingBarFeedBack.rating, Date(), imageURL)
                    RxBus.publish(NewRateEvent(rate))
                }
                .setNegativeButton(getString(R.string.dialog_cancel)) { _, _ ->
                    activity!!.toast("Pressed CANCEL")
                }
                .create()

    }

}