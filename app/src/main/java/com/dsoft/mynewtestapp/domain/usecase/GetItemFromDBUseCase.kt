package com.dsoft.mynewtestapp.domain.usecase

import com.dsoft.mynewtestapp.domain.model.Item
import com.dsoft.mynewtestapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetItemFromDBUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(query: String): Flow<List<Item>> {
        return repository.getAllItems(query)
    }
}