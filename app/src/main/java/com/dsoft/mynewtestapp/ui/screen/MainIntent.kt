package com.dsoft.mynewtestapp.ui.screen

import com.dsoft.mynewtestapp.domain.model.Item

sealed interface MainIntent {
    data class DeleteItem(val item: Item): MainIntent
    data class ChangeCountItem(val item: Item, val count: Int): MainIntent
    data class UpdateSearchQuery(val query: String): MainIntent
}