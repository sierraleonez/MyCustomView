package com.example.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat

class MyButton : AppCompatButton {

    private var enabledBackground : Drawable? = null
    private var disabledBackground : Drawable? = null
    private var txtColor : Int = 0

    constructor(context : Context) : super(context) {
        Init()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context, attrs){
        Init()
    }

    constructor(context : Context, attrs : AttributeSet, defStyleAtr : Int) : super(context, attrs, defStyleAtr){
        Init()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        background = when{
            isEnabled -> enabledBackground
            else -> disabledBackground
        }

        setTextColor(txtColor)
        textSize = 12f
        gravity = Gravity.CENTER
        text = when{
            isEnabled -> "Submit"
            else -> "isi dulu"
        }
    }

    private fun Init(){
        txtColor = ContextCompat.getColor(context, android.R.color.background_light)
        disabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button)
        enabledBackground = ContextCompat.getDrawable(context, R.drawable.bg_button_disable)
    }
}