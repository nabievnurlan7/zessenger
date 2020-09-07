package com.nurlandroid.kotapp.feature.chat

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.text.InputType
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

object KeyboardUtils {

    val CALENDAR = KeyboardUtilsEnum.CALENDAR.typeName
    val SELECT = KeyboardUtilsEnum.SELECT.typeName
    val DICTIONARY = KeyboardUtilsEnum.DICTIONARY.typeName
    val AMOUNT = KeyboardUtilsEnum.AMOUNT.typeName
    val NUMERICAL = KeyboardUtilsEnum.NUMERICAL.typeName
    val STRING = KeyboardUtilsEnum.STRING.typeName
    val RULER = KeyboardUtilsEnum.RULER.typeName

    enum class KeyboardUtilsEnum(val typeName: String, val code: Int) { //ни в коем случае не менять порядок - в attrs используется нумерованный вариант
        STRING("string", 0),
        NUMERICAL("numerical", 1),
        AMOUNT("amount", 2),
        DICTIONARY("dictionary", 3),
        SELECT("select", 4),
        CALENDAR("calendar", 5),
        RULER("ruler", 6)
    }


    fun getInputType(type: String?): Int? {
        return when (type) {
            AMOUNT -> InputType.TYPE_CLASS_PHONE  //InputType.TYPE_NUMBER_FLAG_DECIMAL  //todo для суммы доработай на самом поле, чтоб срабатывало форматирование после ухода и возвращалось к простому числу при фокусе
            NUMERICAL -> InputType.TYPE_CLASS_PHONE
            SELECT -> -1
            CALENDAR -> -2
            DICTIONARY -> -3
            RULER -> -4
            else -> InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        }
    }

    fun getInputType(type: Int?): Int? {
        return when (type) {
            KeyboardUtilsEnum.AMOUNT.ordinal -> InputType.TYPE_CLASS_PHONE  //InputType.TYPE_NUMBER_FLAG_DECIMAL  //todo для суммы доработай на самом поле, чтоб срабатывало форматирование после ухода и возвращалось к простому числу при фокусе
            KeyboardUtilsEnum.NUMERICAL.ordinal -> InputType.TYPE_CLASS_PHONE
            KeyboardUtilsEnum.SELECT.ordinal -> -1
            KeyboardUtilsEnum.CALENDAR.ordinal -> -2
            KeyboardUtilsEnum.DICTIONARY.ordinal -> -3
            KeyboardUtilsEnum.RULER.ordinal -> -4
            else -> InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        }
    }

    fun hideKeyboard(activity: Activity) {
        try {
            if (activity.currentFocus != null) {
                val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                        activity.currentFocus!!.windowToken, 0
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hideKeyboard(view: View, context: Context) {
        try {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun showKeyboard(view: View, context: Context) {
        try {
            if (view.requestFocus()) {
                val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun isKeyboardOpened(activity: Activity): Boolean {
        val rect = Rect()
        val activityRoot = (activity.findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
        val visibleThreshold = dp2px(activity.resources, 100)
        activityRoot.getWindowVisibleDisplayFrame(rect)

        val heightDiff = activityRoot.rootView.height - rect.height()
        return heightDiff > visibleThreshold
    }

    private fun dp2px(resources: Resources, dp: Int): Float = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), resources.displayMetrics)

}