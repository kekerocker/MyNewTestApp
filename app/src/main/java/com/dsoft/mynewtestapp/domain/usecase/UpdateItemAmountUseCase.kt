package com.dsoft.mynewtestapp.domain.usecase

import com.dsoft.mynewtestapp.domain.repository.Repository
import javax.inject.Inject

class UpdateItemAmountUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(id: Int, count: Int) {
        repository.updateItemAmount(id, count)
    }
}