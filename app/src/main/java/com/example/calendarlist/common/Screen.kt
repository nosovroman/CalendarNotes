package com.example.calendarlist.common

sealed class Screen (val route: String, val title: String = Constants.APP_NAME_RUS) {
    object CalendarListScreen: Screen(route = "CalendarListScreen")
    object DetailTask: Screen(route = "DetailTaskScreen/{${Constants.NAV_KEY__CONTENT_ID}}", title = "Событие") {
        fun createRoute(contentId: Int) = this.route.replace(oldValue = "{${Constants.NAV_KEY__CONTENT_ID}}", newValue = contentId.toString())
    }
}
