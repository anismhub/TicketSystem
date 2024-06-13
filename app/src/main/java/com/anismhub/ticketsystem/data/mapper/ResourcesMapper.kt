package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.DepartmentsDTO
import com.anismhub.ticketsystem.data.remote.dto.DepartmentsDataDTO
import com.anismhub.ticketsystem.domain.model.Departments
import com.anismhub.ticketsystem.domain.model.DepartmentsData

fun DepartmentsDTO.toDepartments(): Departments {
    return Departments(
        error = error,
        status = status,
        message = message,
        data = data.map { it.toDepartmentsData() }
    )
}

fun DepartmentsDataDTO.toDepartmentsData(): DepartmentsData {
    return DepartmentsData(
        departmentId = departmentId, departmentName = departmentName
    )
}