package com.practicum.playlistmaker.UI.composables.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.UI.composables.ButtonsList
import com.practicum.playlistmaker.UI.composables.Screen
import com.practicum.playlistmaker.UI.composables.ThemeSwitch
import com.practicum.playlistmaker.data.SettingButton
import com.practicum.playlistmaker.data.SettingsContextManager

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    settingsContextManager: SettingsContextManager
) {
    val settingButtons = setOf(
        SettingButton(
            icon = { it -> ThemeSwitch() },
            nameId = R.string.theme_button
        ),
        SettingButton(
            iconId = R.drawable.share_icon,
            nameId = R.string.share_button,
            task = { settingsContextManager.shareApp() }
        ),
        SettingButton(
            iconId = R.drawable.support_icon,
            nameId = R.string.support_button,
            task = { settingsContextManager.callSupport() }
        ),
        SettingButton(
            iconId = R.drawable.arrow_icon,
            nameId = R.string.agreement_button,
            task = { settingsContextManager.openUserAgreement() }
        )
    )

    Screen(
        stringResource(R.string.settings),
        onNavigateBack = onNavigateBack
    ) {
        ButtonsList(
            settingButtons,
            onClick = { item -> item.task() },
        ) { item ->
            SettingButton(item)
        }
    }
}

@Composable
fun SettingButton(item: SettingButton) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp, 10.dp)
        ,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text (
            stringResource(item.nameId),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .padding(10.dp)
                .weight(2f)
        )
        item.Icon()
    }
}