package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.playlistmaker.composables.ButtonsList
import com.example.playlistmaker.composables.Screen
import com.example.playlistmaker.composables.ThemeSwitch
import com.example.playlistmaker.data.SettingButton
import com.example.playlistmaker.ui.theme.IsDarkTheme
import com.example.playlistmaker.ui.theme.PlaylistMakerTheme
import com.example.playlistmaker.ui.theme.ThemeManager

class SettingsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val currentTheme by ThemeManager.currentTheme.collectAsState()

            PlaylistMakerTheme(
                IsDarkTheme(currentTheme)
            ) {
                Screen(
                    stringResource(R.string.settings)
                ) {
                    ButtonsList(
                        settingButtons,
                        onClick = { item -> item.task() },
                    ) { item ->
                        SettingButton(item)
                    }
                }
            }
        }
    }
    private val settingButtons = setOf(
        SettingButton(
            icon = { it -> ThemeSwitch() },
            nameId = R.string.theme_button
        ),
        SettingButton(
            iconId = R.drawable.share_icon,
            nameId = R.string.share_button,
            task = { shareApp() }
        ),
        SettingButton(
            iconId = R.drawable.support_icon,
            nameId = R.string.support_button,
            task = { callSupport() }
        ),
        SettingButton(
            iconId = R.drawable.arrow_icon,
            nameId = R.string.agreement_button,
            task = { openUserAgreement() }
        )
    )

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
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .padding(10.dp)
                    .weight(2f)
            )
            item.Icon()
        }
    }

    private fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, getString(R.string. share_message))
        }
        try {
            startActivity(Intent.createChooser(shareIntent, "Поделиться"))
        } catch (e: Exception) {
            Toast.makeText(this@SettingsActivity, "Мессенджер не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun callSupport() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = "mailto:".toUri()
            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.developer_email)))
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
            putExtra(Intent.EXTRA_TEXT, getString(R.string.email_body))
        }

        try {
            startActivity(Intent.createChooser(emailIntent, "Выберите почтовый клиент"))
        } catch (e: Exception) {
            Toast.makeText(this@SettingsActivity, "Почтовый клиент не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun openUserAgreement() {
        val agreementIntent = Intent(Intent.ACTION_VIEW).apply {
            data = getString(R.string.agreement_url).toUri()
        }

        try {
            startActivity(Intent.createChooser(agreementIntent, "Выберите браузер"))
        } catch (e: Exception) {
            Toast.makeText(this@SettingsActivity, "Браузер не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }
}