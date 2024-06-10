package com.anismhub.ticketsystem.data

import com.anismhub.ticketsystem.domain.DummyPengguna
import com.anismhub.ticketsystem.domain.DummyTicket

object DataDummy {
    val tickets = listOf(
        DummyTicket("Investigate memory leak", "App crashes when loading large images", "High", "Open", "2023-08-01"),
        DummyTicket("Fix login issue", "Users can't log in with correct credentials", "Medium", "On Progress", "2023-08-02"),
        DummyTicket("Improve performance", "App takes too long to load data", "Low", "Closed", "2023-08-03"),
        DummyTicket("Add new feature", "Implement a new feature for managing tasks", "High", "Open", "2023-08-04"),
        DummyTicket("Resolve UI bug", "Text overlaps with buttons on certain devices", "Medium", "On Progress", "2023-08-05"),
        DummyTicket("Update documentation", "Provide clearer instructions for using the API", "Low", "Closed", "2023-08-06"),
        DummyTicket("Handle network errors", "App doesn't handle network connectivity issues gracefully", "High", "Open", "2023-08-07"),
        DummyTicket("Optimize database queries", "Improve the efficiency of database operations", "Medium", "On Progress", "2023-08-08"),
        DummyTicket("Enhance security", "Implement additional security measures to protect user data", "High", "Closed", "2023-08-09"),
        DummyTicket("Integrate with third-party service", "Connect the app with a third-party API", "Low", "Open", "2023-08-10"),
        DummyTicket("Refactor code", "Improve the code quality and maintainability", "Medium", "On Progress", "2023-08-11"),
        DummyTicket("Fix crash on startup", "App crashes when launched on some devices", "High", "Closed", "2023-08-12"),
        DummyTicket("Improve accessibility", "Make the app more accessible for users with disabilities", "Low", "Open", "2023-08-13"),
        DummyTicket("Optimize memory usage", "Reduce the app's memory footprint", "Medium", "On Progress", "2023-08-14"),
        DummyTicket("Add support for new language", "Translate the app into a new language", "Low", "Closed", "2023-08-15"),
        DummyTicket("Enhance user experience", "Improve the overall user experience of the app", "High", "Open", "2023-08-16"),
        DummyTicket("Fix minor bugs", "Resolve minor bugs and issues reported by users", "Medium", "On Progress", "2023-08-17"),
        DummyTicket("Update dependencies", "Upgrade the app's dependencies to the latest versions", "Low", "Closed", "2023-08-18"),
        DummyTicket("Implement new design", "Redesign the app's user interface", "High", "Open", "2023-08-19"),
        DummyTicket("Improve performance on low-end devices", "Optimize the app for better performance on low-end devices", "Medium", "On Progress", "2023-08-20")
    )

    val dummyPenggunas = listOf(
        DummyPengguna("John Doe", "Administrator"),
        DummyPengguna("Jane Smith", "Karyawan"),
        DummyPengguna("Bob Johnson", "Teknisi"),
        DummyPengguna("Alice Brown", "Administrator"),
        DummyPengguna("Tom Green", "Karyawan"),
        DummyPengguna("Mary Miller", "Teknisi"),
        DummyPengguna("David Wilson", "Administrator"),
        DummyPengguna("Sarah Jones", "Karyawan"),
        DummyPengguna("Michael Davis", "Teknisi"),
        DummyPengguna("Lisa Martinez", "Administrator")
    )
}