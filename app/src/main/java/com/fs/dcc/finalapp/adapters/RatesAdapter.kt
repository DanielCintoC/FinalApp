package com.fs.dcc.finalapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fs.dcc.finalapp.R
import com.fs.dcc.finalapp.models.Rate
import com.fs.dcc.finalapp.utils.CircleTransform
import com.fs.dcc.finalapp.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_rates_item.view.*
import java.text.SimpleDateFormat

/**
 * Created by danielcintoconde on 11/12/18.
 */
class RatesAdapter(private val items: List<Rate>) : RecyclerView.Adapter<RatesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.inflate(R.layout.fragment_rates))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(rate: Rate) = with(itemView) {
            textViewRate.text = rate.text
            textViewStar.text = "${rate.rate}"
            textViewCalendar.text = SimpleDateFormat("dd MMM, yyyy").format(rate.createdAt)
            Picasso.get().load(rate.profileImageURL).resize(100, 100)
                    .centerCrop()
                    .transform(CircleTransform())
                    .into(imageViewProfile)
        }

    }

}