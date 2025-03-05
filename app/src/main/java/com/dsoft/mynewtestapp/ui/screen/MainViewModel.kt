package com.dsoft.mynewtestapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.usecase.DeleteItemUseCase
import com.dsoft.mynewtestapp.domain.usecase.GetItemFromDBUseCase
import com.dsoft.mynewtestapp.domain.usecase.UpdateItemAmountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemFromDBUseCase: GetItemFromDBUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val updateItemAmountUseCase: UpdateItemAmountUseCase
) : ViewModel() {

    private val _itemFlow: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val itemFlow: StateFlow<MainState> = _itemFlow.asStateFlow()

    private val _queryFlow: MutableStateFlow<String> = MutableStateFlow("")
    private val queryFlow: StateFlow<String> = _queryFlow.asStateFlow()

    init {
        fetchItemsFromDB()
    }

    fun sendIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.DeleteItem -> deleteItem(intent.item)
            is MainIntent.ChangeCountItem -> changeCountItem(intent.item, intent.count)
            is MainIntent.UpdateSearchQuery -> updateSearchQuery(intent.query)
        }
    }

    private fun fetchItemsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            getItemFromDBUseCase()
                .combine(queryFlow) { list, query ->
                    list.filter { item ->
                        item.name.lowercase().contains(query.lowercase(), ignoreCase = true)
                    }
                }
                .collectLatest { list -> _itemFlow.update { it.copy(itemList = list) } }
        }
    }

    private fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteItemUseCase(item)
        }
    }

    private fun updateSearchQuery(query: String) {
        _itemFlow.update { it.copy(searchQuery = query) }
        _queryFlow.update { query }
    }

    private fun changeCountItem(item: Item, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            updateItemAmountUseCase(item.id, count)
        }
    }
}