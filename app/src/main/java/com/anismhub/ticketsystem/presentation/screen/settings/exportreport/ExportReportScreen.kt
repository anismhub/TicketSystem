package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.BuildConfig
import com.anismhub.ticketsystem.presentation.components.ReusableDatePicker
import com.anismhub.ticketsystem.utils.AndroidDownloader
import com.anismhub.ticketsystem.utils.Resource
import java.time.LocalDate

@Composable
fun ExportReportScreen(
    modifier: Modifier = Modifier,
    viewModel: ExportReportViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val storagePermissionResultLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasPermission = isGranted
        }
    )

    val baseUrl = BuildConfig.BASE_URL
    val exportReportState by viewModel.exportReport.collectAsStateWithLifecycle()
    val accessToken by viewModel.accessToken
    Log.d("Access Token", "token: $accessToken ")

    exportReportState.let {
        if (!it.hasBeenHandled) {
            when (val unhandled = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    // Handle loading state
                    Log.d("ExportReportScreen", "Loading...")
                }

                is Resource.Success -> {
//                    val response = unhandled.data
//                    if (response != null) {
//                        handleFileResponse(
//                            context,
//                            response
//                        ) // Define this function to save the file
//                    }
                }

                is Resource.Error -> {
                    Log.e("ExportReportScreen", "Error: ${unhandled.error}")
                    // Handle error state
                }

                else -> {}
            }
        }
    }

    var startDate by remember { mutableStateOf<LocalDate?>(LocalDate.now()) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tanggal Dari :")
        // Tanggal Dari DatePicker
        ReusableDatePicker(
            initialDate = LocalDate.now(), // Preselect a date 1 year ago
            onDateSelected = { startDate = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Tanggal Sampai :")
        // Tanggal Sampai DatePicker
        ReusableDatePicker(
            minDateAllowed = startDate ?: LocalDate.now().minusYears(2),
            onDateSelected = { endDate = it }
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                    storagePermissionResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                } else {
                    val downloader = AndroidDownloader(context, baseUrl)
                    downloader.downloadFile(accessToken)
                }
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Ekspor")
        }
    }
}