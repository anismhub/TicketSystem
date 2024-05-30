package com.anismhub.ticketsystem.data

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
}