package com.nurlandroid.kotapp.feature.chat

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Point
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Display
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
            Message(message = "Blblalbla", sender = User("Me", profileUrl = ""), animation = R.raw.kote2),
            Message(message = "Blblalbla", sender = User("Me", profileUrl = ""), animation = R.raw.kote1)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setKeyboardListener()
        messageAdapter = MessageListAdapter(requireContext(), messageList)
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        reyclerview_message_list.apply {
            adapter = messageAdapter
            layoutManager = linearLayoutManager
        }

        button_chatbox_send.setOnClickListener {

            val typedText = edittext_chatbox.text.toString()
            val myMessage = Message(typedText, sender = User("Me", profileUrl = ""), animation = 0)

            if (typedText == "sleep") {
                myAnimation.setAnimation(R.raw.kote14)
                myAnimation.tag = R.raw.kote14
                myAnimation.playAnimation()

                myMessage.animation = R.raw.kote14
            } else {
                myMessage.animation = R.raw.kote1
            }

            messageList.add(myMessage)
            messageList.add(Message("$typedText its fine =)", sender = User("Nikita", profileUrl = ""), animation = R.raw.kapu29))

            messageAdapter.setNewMessage(messageList)
            edittext_chatbox.text.clear()
            scrollToBottom()


        }

        edittext_chatbox.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                changeAnimationOnTyping(s)
            }
        })
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

    private fun animate() {
        val display: Display = requireActivity().windowManager.defaultDisplay
        val point = Point()
        display.getSize(point)
        val width: Int = point.x // screen width

        val halfW = width / 2.0f

        val animatorSet = AnimatorSet()
        val lftToRgt = ObjectAnimator.ofFloat(senderAnimation, "translationX", 0f, halfW)
                .setDuration(700)

        val scaleDown = ObjectAnimator.ofPropertyValuesHolder(myContainer,
                PropertyValuesHolder.ofFloat("scaleX", 0.5f),
                PropertyValuesHolder.ofFloat("scaleY", 0.5f))
        scaleDown.duration = 1000

        animatorSet.play(scaleDown)
//        animatorSet.start()

        animateBar()
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
            reyclerview_message_list.smoothScrollToPosition(messageList.size - 1)
        }
    }
}