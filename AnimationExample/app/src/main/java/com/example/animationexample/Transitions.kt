package com.example.animationexample

import android.os.Parcel
import android.os.Parcelable
internal const val animationTitle = "ANIMATION_TITLE"
internal const val animationType = "ANIMATION_TYPE"
class Constants{
    enum class TransitionType{
        EXPLODE_KOTLIN, EXPLODE_XML, FADE_KOTLIN, FADE_XML, SLIDE_KOTLIN, SLIDE_XML
    }
    enum class  TransitionTitle{
        ExplodeKotlin, ExplodeXml, FadeKotlin, FadeXml, SlideKotlin, SlideXml
    }
}