package ru.practicum.playlistmaker.UI.composables.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.practicum.playlistmaker.R
import ru.practicum.playlistmaker.UI.composables.Screen
import ru.practicum.playlistmaker.UI.composables.TrackListItem
import ru.practicum.playlistmaker.UI.theme.AlmostBlack
import ru.practicum.playlistmaker.UI.theme.Blue
import ru.practicum.playlistmaker.domain.models.SearchState
import ru.practicum.playlistmaker.presentation.SearchViewModel

//@Composable
//fun SearchScreen(
//    onNavigateBack: () -> Unit
//) {
//    Screen(
//        title = stringResource(R.string.search),
//        onNavigateBack = onNavigateBack
//    ) {
//        SearchTextField()
//    }
//}

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onNavigateBack: () -> Unit
) {
    val screenState by viewModel.searchScreenState.collectAsState()
    var text by remember { mutableStateOf("") }
    Screen(
        "Поиск",
        false,
        onNavigateBack
    ) {
        SearchTextField(
            viewModel,
            text
        ) {
            text = it
        }
        when (screenState) {
            is SearchState.Initial -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Введите строку для поиска")
                }
            }

            is SearchState.Searching -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is SearchState.Success -> {
                val tracks = (screenState as SearchState.Success).foundList
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(tracks.size) { index ->
                        TrackListItem(track = tracks[index])
                        HorizontalDivider(thickness = 0.5.dp)
                    }
                }
            }

            is SearchState.Fail -> {
                val error = (screenState as SearchState.Fail).error
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Ошибка: $error", color = Color.Red)
                }
            }
        }
    }
}

@Composable
fun SearchTextField(
    viewModel: SearchViewModel,
    searchText: String,
    onSearchChange: (String) -> Unit
) {

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
        singleLine = true
    )
}