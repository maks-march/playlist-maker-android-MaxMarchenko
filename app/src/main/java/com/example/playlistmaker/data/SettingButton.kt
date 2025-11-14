package com.example.playlistmaker.data

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.playlistmaker.R
import com.example.playlistmaker.composables.ButtonIcon

class SettingButton(
    val iconId: Int = R.drawable.arrow_icon,
    val icon: @Composable (SettingButton) -> Unit = { it -> ButtonIcon(it) },
    val nameId: Int,
    val task: () -> Unit = {})
{
    @Composable
    fun Icon() {
        icon(this)
    }
}
