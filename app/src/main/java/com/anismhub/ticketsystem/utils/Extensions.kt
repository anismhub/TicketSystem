package com.anismhub.ticketsystem.utils

import android.content.Context
import android.os.Environment
import android.util.Log
import android.widget.Toast
import com.anismhub.ticketsystem.presentation.common.DropdownMenuState
import com.anismhub.ticketsystem.presentation.common.InputTextState
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun InputTextState.isInvalid(): Boolean {
    return this.value.isEmpty() || this.isError
}

fun DropdownMenuState.inInvalid(validIndex: Int): Boolean {
    return this.indexValue < validIndex || this.isError
}

fun String.toDateTime(
    dateFormat: String = "dd/MM/yyyy",
    timeFormat: String = "HH:mm:ss"
): String {
    val zonedDateTime = ZonedDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val jakartaZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Jakarta"))
    val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
    val timeFormatter = DateTimeFormatter.ofPattern(timeFormat)
    val formattedDate = jakartaZonedDateTime.toLocalDate().format(dateFormatter)
    val formattedTime = jakartaZonedDateTime.toLocalTime().format(timeFormatter)
    return "$formattedDate $formattedTime"
}

fun LocalDate.toFormattedString(): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return this.format(formatter)
}

fun handleFileResponse(context: Context, response: Response<ResponseBody>) {
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            val fileName = "report.xlsx" // Change this to your desired file name
//            saveFileToDisk(context, body, fileName)
        } else {
            Log.d("Retrieved failed", "handleFileResponse: ${response.errorBody()?.string()}")
        }
    } else {
        Log.d("Export failed", "handleFileResponse: ${response.errorBody()?.string()}")
    }
}

fun saveFileToDisk(context: Context, body: ResponseBody, fileName: String) {
    try {
        // Define the directory and the file to write to
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(directory, fileName)

        // Create the file and write the data
        val inputStream: InputStream = body.byteStream()
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(4096)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }
        outputStream.flush()
        outputStream.close()
        inputStream.close()

        Log.d("Saved file", "File saved to ${file.path}")
        Toast.makeText(context, "File saved to ${file.path}", Toast.LENGTH_LONG).show()
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "Error saving file: ${e.message}", Toast.LENGTH_SHORT).show()
    }
}