package com.dsoft.mynewtestapp.domain.usecase

import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.repository.Repository
import javax.inject.Inject

class ChangeCountUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(item: Item, count: Int) {
        repository.insertItem(item.copy(amount = count))
    }
}