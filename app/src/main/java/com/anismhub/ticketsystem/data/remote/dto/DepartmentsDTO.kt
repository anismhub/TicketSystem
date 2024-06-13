package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DepartmentsDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<DepartmentsDataDTO>
)

data class DepartmentsDataDTO(
    @field:SerializedName("department_id")
    val departmentId: Int,

    @field:SerializedName("department_name")
    val departmentName: String
)
