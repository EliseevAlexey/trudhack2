package com.eliseev.trudhack2.model

enum class JobEnumDto(val entityName: String) {
    STOREKEEPER("Кладовщик"),
    DRIVER_LOADER("Водитель погрузчика"),
    WAITER("Официант");

    companion object {
        fun findByName(name: String) = values().first { it.entityName == name }
    }
}
