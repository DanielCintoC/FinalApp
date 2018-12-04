package com.fs.dcc.finalapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.fs.dcc.finalapp.R
import com.fs.dcc.finalapp.adapters.ChatAdapter
import com.fs.dcc.finalapp.models.Message
import com.fs.dcc.finalapp.utils.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import java.util.*
import java.util.EventListener
import kotlin.collections.HashMap

class ChatFragment : Fragment() {

    private lateinit var _view: View

    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private lateinit var currentUser: FirebaseUser

    private var store: FirebaseFirestore = FirebaseFirestore.getInstance()
    private lateinit var chatDBReference: CollectionReference

    private lateinit var adapter: ChatAdapter
    private var messageList: ArrayList<Message> = ArrayList()

    private var chatSubscription: ListenerRegistration? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _view = inflater.inflate(R.layout.fragment_chat, container, false)
        setUpChatDB()
        setUpCurrentUser()
        setUpRecyclerView()
        setUpChatButton()
        subscribeToChatMessages()
        return _view
    }

    private fun setUpChatDB() {
        //Va a buscar la colección chat y si no existe la crea, en Firebase
        chatDBReference = store.collection("chat")
    }

    private fun setUpCurrentUser() {
        currentUser = mAuth.currentUser!!
    }

    private fun setUpRecyclerView() {

        val layoutManager = LinearLayoutManager(context)
        adapter = ChatAdapter(messageList, currentUser.uid)
        _view.recyclerView.setHasFixedSize(true)
        _view.recyclerView.layoutManager = layoutManager
        _view.recyclerView.itemAnimator = DefaultItemAnimator()
        _view.recyclerView.adapter = adapter

    }

    private fun setUpChatButton() {

        _view.buttonSend.setOnClickListener {

            val messageText = editTextMessage.text.toString()
            if (messageText.isNotEmpty()) {
                val message = Message(currentUser.uid, messageText,
                        currentUser.photoUrl.toString(), Date())
                //Guardaremos msj en firebase
                saveMessage(message)
                _view.editTextMessage.setText("")
            }

        }

    }

    // Guardar mensaje en servicio de Firebase
    private fun saveMessage(message: Message) {

        val newMessage = HashMap<String, Any>()
        newMessage["authorId"] = message.authorId
        newMessage["message"] = message.message
        newMessage["profileImageURL"] = message.profileImageURL
        newMessage["sentAt"] = message.sentAt
        chatDBReference.add(newMessage)
                .addOnCompleteListener {
                    activity!!.toast("¡Message added!")
                }
                .addOnFailureListener {
                    activity!!.toast("¡Something was wrong, please try again!")
                }

    }

    private fun subscribeToChatMessages() {

        chatSubscription = chatDBReference.orderBy("sentAt", Query.Direction.DESCENDING) //.limit(100)
                .addSnapshotListener(object : EventListener, com.google.firebase.firestore.EventListener<QuerySnapshot> {

                    override fun onEvent(snapshot: QuerySnapshot?, exception: FirebaseFirestoreException?) {
                        exception?.let {
                            // Si esto no en nulo
                            activity!!.toast("Exception: $exception")
                            return
                        }

                        snapshot?.let {
                            messageList.clear()
                            val messages = it.toObjects(Message::class.java)
                            messageList.addAll(messages.asReversed())
                            adapter.notifyDataSetChanged()
                            _view.recyclerView.smoothScrollToPosition(messageList.size)
                        }

                    }

                })

    }

    override fun onDestroy() {
        chatSubscription?.remove()
        super.onDestroy()
    }

}
