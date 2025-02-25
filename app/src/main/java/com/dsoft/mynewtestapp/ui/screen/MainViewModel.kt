package com.dsoft.mynewtestapp.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.usecase.ChangeCountUseCase
import com.dsoft.mynewtestapp.domain.usecase.DeleteItemUseCase
import com.dsoft.mynewtestapp.domain.usecase.GetItemFromDBUseCase
import com.dsoft.mynewtestapp.domain.usecase.StoreItemsInDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getItemFromDBUseCase: GetItemFromDBUseCase,
    private val storeItemsInDBUseCase: StoreItemsInDBUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val changeCountUseCase: ChangeCountUseCase
) : ViewModel() {

    private val _itemFlow: MutableStateFlow<MainState> = MutableStateFlow(MainState())
    val itemFlow: StateFlow<MainState> = _itemFlow.asStateFlow()

    private val searchQuery get() = itemFlow.value.searchQuery

    init {
        populateScreen()
    }

    private fun populateScreen() {
        fetchItemsFromDB()
        insertItemsToDB()
    }

    fun sendIntent(intent: MainIntent) {
        when(intent) {
            is MainIntent.DeleteItem -> deleteItem(intent.item)
            is MainIntent.ChangeCountItem -> changeCountItem(intent.item, intent.count)
            is MainIntent.UpdateSearchQuery -> updateSearchQuery(intent.query)
        }
    }

    private fun insertItemsToDB() {
        viewModelScope.launch(Dispatchers.IO) { storeItemsInDBUseCase() }
    }

    private fun fetchItemsFromDB() {
        viewModelScope.launch(Dispatchers.IO) {
            getItemFromDBUseCase(searchQuery).collectLatest { list ->
                _itemFlow.update { it.copy(itemList = list) }
            }
        }
    }

    private fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteItemUseCase(item)
        }
    }

    private fun updateSearchQuery(query: String) {
        _itemFlow.update { it.copy(searchQuery = query) }
        fetchItemsFromDB()
    }

    private fun changeCountItem(item: Item, count: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            changeCountUseCase(item, count)
        }
    }
}