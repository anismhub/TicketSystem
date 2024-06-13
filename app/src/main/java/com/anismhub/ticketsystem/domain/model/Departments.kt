package com.anismhub.ticketsystem.domain.model

data class Departments(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<DepartmentsData>
)

data class DepartmentsData(
    val departmentId: Int,
    val departmentName: String
)


