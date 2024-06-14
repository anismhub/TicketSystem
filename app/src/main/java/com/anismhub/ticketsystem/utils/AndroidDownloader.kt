package com.anismhub.ticketsystem.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

interface Downloader {
    fun downloadFile(token: String): Long
}

class AndroidDownloader(
    context: Context, baseUrl: String, startDate: String, endDate: String
): Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    private val url = "${baseUrl}tickets/export?startDate=${startDate}&endDate=${endDate}"

    override fun downloadFile(token: String): Long {
        val request = DownloadManager.Request(Uri.parse(url))
            .setMimeType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setTitle("report.xlsx")
            .addRequestHeader("Authorization", "Bearer $token")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "report.xlsx")
        return downloadManager.enqueue(request)
    }
}