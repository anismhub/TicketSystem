package com.anismhub.ticketsystem.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log

interface Downloader {
    fun downloadFile(token: String): Long
}

class AndroidDownloader(
    context: Context,
    private val url: String
) : Downloader {
    private val downloadManager = context.getSystemService(DownloadManager::class.java)

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