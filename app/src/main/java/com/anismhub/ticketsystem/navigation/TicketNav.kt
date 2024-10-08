package com.anismhub.ticketsystem.navigation

sealed class TicketNav(val route: String) {
    data object Detail : TicketNav("detail/{ticketId}") {
        fun createRoute(ticketId: Int) = "detail/$ticketId"
    }

    data object Create : TicketNav("create")
    data object Export : TicketNav("export")
    data object ManageAccount : TicketNav("manage_account")
    data object CreateAccount : TicketNav("create_account")
    data object UpdateAccount : TicketNav("update_account/{userId}") {
        fun createRoute(userId: Int) = "update_account/$userId"
    }
    data object ChangePassword : TicketNav("change_password")
    data object ImageComment : TicketNav("image_comment/{imageUrl}") {
        fun createRoute(imageUrl: String) = "image_comment/$imageUrl"
    }
}