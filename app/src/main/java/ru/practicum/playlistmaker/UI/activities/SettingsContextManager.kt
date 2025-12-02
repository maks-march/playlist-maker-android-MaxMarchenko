package ru.practicum.playlistmaker.UI.activities

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import ru.practicum.playlistmaker.R

class SettingsContextManager(
    val context: Context
) {
    fun shareApp() {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            setType("text/plain")
            putExtra(Intent.EXTRA_TEXT, context.getString(R.string. share_message))
        }
        try {
            context.startActivity(Intent.createChooser(shareIntent, "Поделиться"))
        } catch (e: Exception) {
            Toast.makeText(context, "Мессенджер не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun callSupport() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            setData("mailto:".toUri())
            putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.developer_email)))
            putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.email_subject))
            putExtra(Intent.EXTRA_TEXT, context.getString(R.string.email_body))
        }

        try {
            context.startActivity(Intent.createChooser(emailIntent, "Выберите почтовый клиент"))
        } catch (e: Exception) {
            Toast.makeText(context, "Почтовый клиент не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun openUserAgreement() {
        val agreementIntent = Intent(Intent.ACTION_VIEW).apply {
            setData(context.getString(R.string.agreement_url).toUri())
        }

        try {
            context.startActivity(Intent.createChooser(agreementIntent, "Выберите браузер"))
        } catch (e: Exception) {
            Toast.makeText(context, "Браузер не найден" + e.message, Toast.LENGTH_SHORT).show()
        }
    }
}
