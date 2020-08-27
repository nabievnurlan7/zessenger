package com.nurlandroid.kotapp.feature.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurlandroid.kotapp.R
import kotlinx.android.synthetic.main.fragment_chat.*

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var messageAdapter: MessageListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val messageList = mutableListOf(
                Message("Hello", sender = User("Nikita", profileUrl = "")),
                Message("Hi", sender = User("Me", profileUrl = "")),
                Message("How are you?", sender = User("Nikita", profileUrl = "")),
                Message("Blblalbla", sender = User("Me", profileUrl = "")),
                Message("Blblalbla", sender = User("Me", profileUrl = "")))

        messageAdapter = MessageListAdapter(requireContext(), messageList)

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        reyclerview_message_list.apply {
            adapter = messageAdapter
            layoutManager = linearLayoutManager
        }

        button_chatbox_send.setOnClickListener {
            val typedText = edittext_chatbox.text.toString()

            messageList.add(Message(typedText, sender = User("Me", profileUrl = "")))
            messageList.add(Message("$typedText its fine =)", sender = User("Nikita", profileUrl = "")))

            messageAdapter.setNewMessage(messageList)
            edittext_chatbox.text.clear()
            reyclerview_message_list.smoothScrollToPosition(messageList.size - 1)
        }
    }
}