package com.dsoft.mynewtestapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item")
class ItemDBO(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val time: Long,
    val tags: List<String>,
    val amount: Int
) {

    /**
     * в обычной ситуации я бы так не делал.
     */
    companion object {
        val list by lazy {
            listOf(
                ItemDBO(
                    name = "iPhone 13",
                    time = 1633046400000,
                    tags = listOf("Телефон", "Новый", "Распродажа"),
                    amount = 15
                ),
                ItemDBO(
                    name = "Samsung Galaxy S21",
                    time = 1633132800000,
                    tags = listOf("Телефон", "Хит"),
                    amount = 30
                ),
                ItemDBO(
                    name = "PlayStation 5",
                    time = 1633219200000,
                    tags = listOf("Игровая приставка", "Акция", "Распродажа"),
                    amount = 7
                ),
                ItemDBO(
                    name = "LG OLED TV",
                    time = 1633305600000,
                    tags = listOf("Телевизор", "Эксклюзив", "Ограниченный"),
                    amount = 22
                ),
                ItemDBO(
                    name = "Apple Watch Series 7",
                    time = 1633392000000,
                    tags = listOf("Часы", "Новый", "Рекомендуем"),
                    amount = 0
                ),
                ItemDBO(
                    name = "Xiaomi Mi 11",
                    time = 1633478400000,
                    tags = listOf("Телефон", "Скидка", "Распродажа"),
                    amount = 5
                ),
                ItemDBO(
                    name = "MacBook Air M1",
                    time = 1633564800000,
                    tags = listOf("Ноутбук", "Тренд"),
                    amount = 12
                ),
                ItemDBO(
                    name = "Amazon Kindle Paperwhite",
                    time = 1633651200000,
                    tags = listOf("Электронная книга", "Последний шанс", "Ограниченный"),
                    amount = 18
                ),
                ItemDBO(
                    name = "Fitbit Charge 5",
                    time = 1633737600000,
                    tags = emptyList(),
                    amount = 27
                ),
                ItemDBO(
                    name = "GoPro Hero 10",
                    time = 1633824000000,
                    tags = listOf("Камера", "Эксклюзив"),
                    amount = 25
                )
            )
        }
    }

}