package com.example.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat

class MyEditText : AppCompatEditText, View.OnTouchListener {

    internal lateinit var mClearButtonImage : Drawable

    constructor(context : Context) : super(context){
        Init()
    }

    constructor(context : Context, attrs : AttributeSet) : super(context, attrs){
        Init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        Init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        hint = "Masukan nama anda"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun Init(){
        mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
        setOnTouchListener(this)

        addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        if(compoundDrawables[2] != null){
            val clearButtonStart : Float
            val clearButtonEnd : Float
            var isClearButtonClicked = false
            when(layoutDirection){
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when{
                        event?.x!! < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    if (event != null) {
                        when{
                            event.x > clearButtonStart -> isClearButtonClicked = true
                        }
                    }
                }
            }
            when{
                isClearButtonClicked -> if (event != null) {
                    when{
                        event.action == MotionEvent.ACTION_DOWN -> {
                            mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
                            showClearButton()
                            return true
                        }
                        event.action == MotionEvent.ACTION_UP -> {
                            mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
                            when{
                                text != null -> text?.clear()
                            }
                            hideClearButton()
                            return true
                        }
                        else -> return false
                    }
                }
                else -> return false
            }
        }
        return false
    }

    private fun showClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, mClearButtonImage, null)
    }

    private fun hideClearButton(){
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null,null,null)
    }


}