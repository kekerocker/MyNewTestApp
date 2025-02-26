package com.dsoft.mynewtestapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dsoft.mynewtestapp.R
import com.dsoft.mynewtestapp.ui.composables.ItemCard

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    Surface(modifier = modifier) {
        val state = viewModel.itemFlow.collectAsState()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                OutlinedTextField(
                    label = { Text(stringResource(id = R.string.search_items)) },
                    singleLine = true,
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            tint = colorResource(id = R.color.black),
                            contentDescription = "search icon"
                        )
                    },
                    trailingIcon = {
                        if (state.value.searchQuery.isNotBlank()) {
                            IconButton(
                                onClick = { viewModel.sendIntent(MainIntent.UpdateSearchQuery("")) },
                                content = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_close),
                                        tint = colorResource(id = R.color.black),
                                        contentDescription = "close"
                                    )
                                }
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    value = state.value.searchQuery,
                    onValueChange = { viewModel.sendIntent(MainIntent.UpdateSearchQuery(it)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }
            items(
                count = state.value.itemList.count(),
                key = { state.value.itemList[it].id }) { index ->
                val currentItem = state.value.itemList[index]
                ItemCard(
                    modifier = Modifier.animateItem(),
                    item = currentItem,
                    onDeleteEvent = { item ->
                        viewModel.sendIntent(MainIntent.DeleteItem(item))
                    },
                    onCountChangeEvent = { item, count ->
                        viewModel.sendIntent(MainIntent.ChangeCountItem(item, count))
                    }
                )
            }
        }

    }
}