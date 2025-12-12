package ru.practicum.playlistmaker.UI.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import ru.practicum.playlistmaker.R
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import ru.practicum.playlistmaker.UI.theme.*
import ru.practicum.playlistmaker.presentation.SearchViewModel

@Composable
fun SearchTextField(
    viewModel: SearchViewModel,
    searchText: String,
    onSearchChange: (String) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchText,
        onValueChange = { onSearchChange(it) },
        modifier = Modifier
            .fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search_icon),
                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                contentDescription = null,
                modifier = Modifier.clickable {
                    viewModel.search(searchText)
                }
            )
        },
        trailingIcon = {
            if (searchText.isNotEmpty()) {
                IconButton(
                    onClick = { onSearchChange("") }
                ) {
                    Icon(
                        Icons.Default.Clear,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        contentDescription = null,
                    )
                }
            }
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            cursorColor = Blue,
            focusedTextColor = AlmostBlack,
            unfocusedTextColor = AlmostBlack,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            selectionColors = TextSelectionColors(
                handleColor = Color.Transparent,
                backgroundColor = Color.Transparent
            )
        ),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                viewModel.search(searchText)
                keyboardController?.hide()
            }
        )
    )
}