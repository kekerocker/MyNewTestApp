package com.dsoft.mynewtestapp.domain.usecase

import com.dsoft.mynewtestapp.domain.repository.Repository
import javax.inject.Inject

class StoreItemsInDBUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke() {
        repository.insertItems()
    }
}