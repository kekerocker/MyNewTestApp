package com.dsoft.mynewtestapp.data.mapper

import com.dsoft.mynewtestapp.data.model.ItemDBO
import com.dsoft.mynewtestapp.domain.model.Item
import java.time.Instant
import java.time.LocalDateTime
import java.util.TimeZone

typealias Mapper<T, R> = (T) -> R

val itemToDBO: Mapper<Item, ItemDBO> = { item ->
    ItemDBO(
        id = item.id,
        name = item.name,
        time = item.date.nano.toLong(),
        tags = item.tags,
        amount = item.amount
    )
}

val itemDBOtoDomain: Mapper<ItemDBO, Item> = { dbo ->
    Item(
        id = dbo.id,
        name = dbo.name,
        date = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(dbo.time),
            TimeZone.getDefault().toZoneId()
        ),
        tags = dbo.tags,
        amount = dbo.amount
    )
}