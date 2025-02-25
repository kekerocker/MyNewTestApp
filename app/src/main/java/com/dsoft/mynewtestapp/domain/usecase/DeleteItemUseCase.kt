package com.dsoft.mynewtestapp.domain.usecase

import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.repository.Repository
import javax.inject.Inject

class DeleteItemUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(item: Item) {
        repository.deleteItem(item)
    }
}