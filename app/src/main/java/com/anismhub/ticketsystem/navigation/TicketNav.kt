package com.anismhub.ticketsystem.navigation

sealed class TicketNav(val route: String) {
    data object Detail : TicketNav("detail/{ticketId}") {
        fun createRoute(ticketId: Int) = "detail/$ticketId"
    }
    data object Create : TicketNav("create")
    data object Export : TicketNav("export")
    data object ManageAccount : TicketNav("manage_account")
    data object CreateAccount : TicketNav("create_account")
    data object UpdateAccount : TicketNav("update_account")
}