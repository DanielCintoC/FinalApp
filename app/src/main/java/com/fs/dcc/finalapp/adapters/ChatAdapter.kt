package com.fs.dcc.finalapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fs.dcc.finalapp.R
import com.fs.dcc.finalapp.models.Message
import com.fs.dcc.finalapp.utils.CircleTransform
import com.fs.dcc.finalapp.utils.inflate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_chat_item_left.view.*
import kotlinx.android.synthetic.main.fragment_chat_item_right.view.*
import java.text.SimpleDateFormat

/**
 * Created by danielcintoconde on 04/12/18.
 */
class ChatAdapter(val items: List<Message>, val userIde: String): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val GLOBAL_MESSAGE = 1
    private val MY_MESSAGE = 2

    private val layoutRight = R.layout.fragment_chat_item_right
    private val layoutLeft = R.layout.fragment_chat_item_left

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            MY_MESSAGE -> ViewHolderRight(parent.inflate(layoutRight))
            else -> ViewHolderLeft(parent.inflate(layoutLeft))
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            MY_MESSAGE -> (holder as ViewHolderRight).bind(items[position])
            GLOBAL_MESSAGE -> (holder as ViewHolderLeft).bind(items[position])
        }
    }

    override fun getItemViewType(position: Int) = if (items[position].authorId == userIde) MY_MESSAGE else GLOBAL_MESSAGE


    class ViewHolderRight(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(message: Message) = with(itemView) {

            textViewMessageRight.text = message.message
            textViewTimeRight.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            if(message.profileImageURL.isNotEmpty()) {
                Picasso.get().load(R.drawable.ic_avatar).resize(100, 100)
                        .centerCrop().transform(CircleTransform()).into(imageViewProfileRight)
            } else {
                Picasso.get().load(message.profileImageURL).resize(100, 100)
                        .centerCrop().transform(CircleTransform()).into(imageViewProfileRight)
            }

        }

    }

    class ViewHolderLeft(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(message: Message) = with(itemView) {
            textViewMessageLeft.text = message.message
            textViewTimeLeft.text = SimpleDateFormat("hh:mm").format(message.sentAt)
            if(message.profileImageURL.isNotEmpty()) {
                Picasso.get().load(message.profileImageURL).resize(100, 100)
                        .centerCrop().transform(CircleTransform()).into(imageViewProfileLeft)
            } else {
                Picasso.get().load(R.drawable.ic_avatar).resize(100, 100)
                        .centerCrop().transform(CircleTransform()).into(imageViewProfileLeft)
            }

        }
    }

}