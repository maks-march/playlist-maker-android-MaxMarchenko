package data

import android.content.Intent

data class NavigationButton(
    val iconId: Int,
    val nameId: Int,
    val link: () -> Intent? = { null }
)
