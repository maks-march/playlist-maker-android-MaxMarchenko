package ru.practicum.playlistmaker.UI.composables.screens

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
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.ItemsList
import ru.practicum.playlistmaker.UI.composables.Screen
import ru.practicum.playlistmaker.UI.composables.ThemeSwitch
import ru.practicum.playlistmaker.data.ButtonElement
import ru.practicum.playlistmaker.presentation.SettingsContextManager

@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    settingsContextManager: SettingsContextManager
) {
    val settingButtons = listOf(
        ButtonElement(
            icon = { it -> ThemeSwitch() },
            nameId = R.string.theme_button
        ),
        ButtonElement(
            iconId = R.drawable.share_icon,
            nameId = R.string.share_button,
            task = { settingsContextManager.shareApp() }
        ),
        ButtonElement(
            iconId = R.drawable.support_icon,
            nameId = R.string.support_button,
            task = { settingsContextManager.callSupport() }
        ),
        ButtonElement(
            iconId = R.drawable.arrow_icon,
            nameId = R.string.agreement_button,
            task = { settingsContextManager.openUserAgreement() }
        )
    )

    Screen(
        stringResource(R.string.settings),
        onNavigateBack = onNavigateBack
    ) {
        ItemsList(
            settingButtons,
            onClick = { item -> item.task() },
        ) { item ->
            SettingButton(item)
        }
    }
}

@Composable
fun SettingButton(item: ButtonElement) {
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
        item.icon(item);
    }
}