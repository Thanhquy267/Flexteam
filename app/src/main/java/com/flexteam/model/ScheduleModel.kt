package com.flexteam.model

class ScheduleModel {
}

enum class ScheduleType(val value: String) {
    AVAILABLE("available"),
    BUSY("busy"),
    NOT_PROVIDED("not_provided")
}