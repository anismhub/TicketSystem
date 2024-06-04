package com.anismhub.ticketsystem.data

import com.anismhub.ticketsystem.domain.Pengguna
import com.anismhub.ticketsystem.domain.Ticket

object DataDummy {
    val dummyTickets: List<Ticket> by lazy {
        (1..25).map { index ->
            Ticket(
                title = "Title $index",
                description = "Description $index",
                priority = "Priority $index",
                status = "Status $index",
                date = "Date $index"
            )
        }
    }
    val tickets = listOf(
        Ticket("Investigate memory leak", "App crashes when loading large images", "High", "Open", "2023-08-01"),
        Ticket("Fix login issue", "Users can't log in with correct credentials", "Medium", "On Progress", "2023-08-02"),
        Ticket("Improve performance", "App takes too long to load data", "Low", "Closed", "2023-08-03"),
        Ticket("Add new feature", "Implement a new feature for managing tasks", "High", "Open", "2023-08-04"),
        Ticket("Resolve UI bug", "Text overlaps with buttons on certain devices", "Medium", "On Progress", "2023-08-05"),
        Ticket("Update documentation", "Provide clearer instructions for using the API", "Low", "Closed", "2023-08-06"),
        Ticket("Handle network errors", "App doesn't handle network connectivity issues gracefully", "High", "Open", "2023-08-07"),
        Ticket("Optimize database queries", "Improve the efficiency of database operations", "Medium", "On Progress", "2023-08-08"),
        Ticket("Enhance security", "Implement additional security measures to protect user data", "High", "Closed", "2023-08-09"),
        Ticket("Integrate with third-party service", "Connect the app with a third-party API", "Low", "Open", "2023-08-10"),
        Ticket("Refactor code", "Improve the code quality and maintainability", "Medium", "On Progress", "2023-08-11"),
        Ticket("Fix crash on startup", "App crashes when launched on some devices", "High", "Closed", "2023-08-12"),
        Ticket("Improve accessibility", "Make the app more accessible for users with disabilities", "Low", "Open", "2023-08-13"),
        Ticket("Optimize memory usage", "Reduce the app's memory footprint", "Medium", "On Progress", "2023-08-14"),
        Ticket("Add support for new language", "Translate the app into a new language", "Low", "Closed", "2023-08-15"),
        Ticket("Enhance user experience", "Improve the overall user experience of the app", "High", "Open", "2023-08-16"),
        Ticket("Fix minor bugs", "Resolve minor bugs and issues reported by users", "Medium", "On Progress", "2023-08-17"),
        Ticket("Update dependencies", "Upgrade the app's dependencies to the latest versions", "Low", "Closed", "2023-08-18"),
        Ticket("Implement new design", "Redesign the app's user interface", "High", "Open", "2023-08-19"),
        Ticket("Improve performance on low-end devices", "Optimize the app for better performance on low-end devices", "Medium", "On Progress", "2023-08-20")
    )

    val pengguna = listOf(
        Pengguna("John Doe", "Administrator"),
        Pengguna("Jane Smith", "Karyawan"),
        Pengguna("Bob Johnson", "Teknisi"),
        Pengguna("Alice Brown", "Administrator"),
        Pengguna("Tom Green", "Karyawan"),
        Pengguna("Mary Miller", "Teknisi"),
        Pengguna("David Wilson", "Administrator"),
        Pengguna("Sarah Jones", "Karyawan"),
        Pengguna("Michael Davis", "Teknisi"),
        Pengguna("Lisa Martinez", "Administrator")
    )
}