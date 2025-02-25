package com.dsoft.mynewtestapp.ui.screen

import com.dsoft.mynewtestapp.domain.model.Item

data class MainState(
    val searchQuery: String = "",
    val itemList: List<Item> = emptyList()
)