package com.nurlandroid.kotapp.feature.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nurlandroid.kotapp.R
import kotlinx.android.synthetic.main.fragment_chat.*
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent.setEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener


class ChatFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var messageAdapter: MessageListAdapter
    private var isCollapsed: Boolean = false

    private val messageList = mutableListOf<Message>(
            Message(message = "Hello", sender = User("Nikita", profileUrl = ""), animation = R.raw.kapu29),
            Message(message = "Hi", sender = User("Me", profileUrl = ""), animation = R.raw.kote3),
            Message(message = "How are you?", sender = User("Nikita", profileUrl = ""), animation = R.raw.kapu26),
            Message(message = "Angry", sender = User("Me", profileUrl = ""), animation = R.raw.kote2),
            Message(message = "Blblalbla", sender = User("Me", profileUrl = ""), animation = R.raw.kote1)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setKeyboardListener()
        messageAdapter = MessageListAdapter(requireContext(), messageList)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        messageRecyclerView.apply {
            adapter = messageAdapter
            layoutManager = linearLayoutManager
        }

        sendButton.setOnClickListener {
            val typedText = chatEditText.text.toString()
            if (typedText.isNotEmpty()) {
                val myMessage = Message(typedText, sender = User("Me", profileUrl = ""), animation = 0)

                when (typedText) {
                    "Sleep" -> {
                        myAnimation.setAnimation(R.raw.kote14)
                        myAnimation.tag = R.raw.kote14
                        myAnimation.playAnimation()

                        myMessage.animation = R.raw.kote14
                    }
                    "Fight" -> {
                        animateExit()
                        myMessage.animation = R.raw.kapu11
                    }
                    else -> {
                        myMessage.animation = R.raw.kote1
                    }
                }

                messageList.add(myMessage)
                messageList.add(Message("$typedText its fine =)", sender = User("Nikita", profileUrl = ""), animation = R.raw.kapu29))

                messageAdapter.setNewMessage(messageList)
                chatEditText.text.clear()
                scrollToBottom()
            }
        }

        chatEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                //changeAnimationOnTyping(s)
            }
        })

        animateEnter()
    }

    private fun changeAnimationOnTyping(s: CharSequence) {
        if (s.isEmpty()) {
            myAnimation.setAnimation(R.raw.kote1)
            myAnimation.tag = R.raw.kote1
            myAnimation.playAnimation()
        } else if (myAnimation.tag != R.raw.kote8) {
            myAnimation.setAnimation(R.raw.kote8)
            myAnimation.tag = R.raw.kote8
            myAnimation.playAnimation()
        }
    }

    private fun setKeyboardListener() {
        setEventListener(
                requireActivity(),
                viewLifecycleOwner,
                object : KeyboardVisibilityEventListener {
                    override fun onVisibilityChanged(isOpen: Boolean) {
                        isCollapsed = !isOpen
                        animateBar()
                        scrollToBottom()
                    }
                })
    }

    private fun animateEnter() {
        Animations.animateGroup(
                listOf(
                        Animations.bounceInRight(myAnimation, blockStart = { myAnimation.visibility = View.VISIBLE }),
                        Animations.bounceInLeft(senderAnimation)
                )
        )
    }

    private fun animateExit() {
        Animations.animate(Animations.slideOutRight(myAnimation))
        Animations.animate(Animations.slideOutLeft(senderAnimation))

        Animations.bounceIn(
                target = commonAnimation,
                duration = 1000,
                blockStart = {
                    KeyboardUtils.hideKeyboard(requireActivity())
                    commonAnimation.visibility = View.VISIBLE
                    commonAnimation.setAnimation(R.raw.kapu11)
                    commonAnimation.tag = R.raw.kapu11
                    commonAnimation.playAnimation()
                })
    }

    private fun animateBar() {
        if (isCollapsed) {
            inflate()
        } else {
            collapse()
        }
    }

    private fun collapse() {
        val params: ViewGroup.LayoutParams = myContainer.layoutParams
        params.height = myContainer.height / 2
        myContainer.requestLayout()

        val params1: ViewGroup.LayoutParams = senderAnimation.layoutParams
        params1.width = senderAnimation.width / 2
        senderAnimation.requestLayout()

        val params2: ViewGroup.LayoutParams = myAnimation.layoutParams
        params2.width = myAnimation.width / 2
        myAnimation.requestLayout()

        isCollapsed = true
    }

    private fun inflate() {
        val params: ViewGroup.LayoutParams = myContainer.layoutParams
        params.height = myContainer.height * 2
        myContainer.requestLayout()

        val params1: ViewGroup.LayoutParams = senderAnimation.layoutParams
        params1.width = senderAnimation.width * 2
        senderAnimation.requestLayout()

        val params2: ViewGroup.LayoutParams = myAnimation.layoutParams
        params2.width = myAnimation.width * 2
        myAnimation.requestLayout()

        isCollapsed = false
    }

    private fun scrollToBottom() {
        if (messageList.isNotEmpty()) {
            messageRecyclerView.smoothScrollToPosition(messageList.size - 1)
        }
    }
}