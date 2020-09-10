package com.nurlandroid.kotapp.feature.chat

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator

object Animations {

    fun animate(animator: Animator) {
        val animatorSet = AnimatorSet()
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.play(animator)
        animatorSet.start()
    }

    fun animateGroup(
            animatorList: List<Animator>,
            duration: Long = 1000,
            blockStart: () -> Unit = {},
            blockEnd: () -> Unit = {}
    ) {
        val animatorSet = AnimatorSet()
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.duration = duration
        animatorSet.playTogether(animatorList)

        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                blockStart.invoke()
            }

            override fun onAnimationEnd(animation: Animator?) {
                blockEnd.invoke()
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
        })

        animatorSet.start()
    }

    fun bounceInRight(
            target: View,
            duration: Long = 1000,
            blockStart: () -> Unit = {},
            blockEnd: () -> Unit = {}
    ): Animator {

        val animator = ObjectAnimator.ofFloat(target, "translationX", target.measuredWidth + target.width.toFloat(), -30f, 100f, 0f)
        animator.duration = duration

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
                blockStart.invoke()
            }

            override fun onAnimationEnd(animation: Animator?) {
                blockEnd.invoke()
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}

        })
        return animator
    }

    fun bounceInLeft(target: View): Animator {
        val animator = ObjectAnimator.ofFloat(target, "translationX", -target.width.toFloat(), 30f, -100f, 0f)
        animator.duration = 1000

        animator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {
                target.visibility = View.VISIBLE
            }
        })
        return animator
    }

    fun slideOutRight(target: View): Animator {
        val parent = target.parent as ViewGroup
        val distance = parent.width - target.left
        val animator = ObjectAnimator.ofFloat(target, "translationX", 0f, distance.toFloat())
        animator.duration = 400

        animator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(animation: Animator?) {
                target.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })
        return animator
    }

    fun slideOutLeft(target: View): Animator {
        val animator = ObjectAnimator.ofFloat(target, "translationX", 0f, -target.right.toFloat())
        animator.duration = 400

        animator.addListener(object : Animator.AnimatorListener {

            override fun onAnimationEnd(animation: Animator?) {
                target.visibility = View.INVISIBLE
            }

            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
        })
        return animator
    }

    fun bounceIn(
            target: View,
            duration: Long = 1000,
            blockStart: () -> Unit = {},
            blockEnd: () -> Unit = {}
    ) {
        animateGroup(
                listOf(
                        ObjectAnimator.ofFloat(target, "alpha", 0f, 1f, 1f, 1f),
                        ObjectAnimator.ofFloat(target, "scaleX", 0.3f, 1.05f, 0.9f, 1f),
                        ObjectAnimator.ofFloat(target, "scaleY", 0.3f, 1.05f, 0.9f, 1f)
                ),
                duration = duration,
                blockStart = blockStart,
                blockEnd = blockEnd
        )
    }
}