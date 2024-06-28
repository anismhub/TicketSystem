package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
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
import com.anismhub.ticketsystem.BuildConfig
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.ReusableDatePicker
import com.anismhub.ticketsystem.utils.AndroidDownloader
import com.anismhub.ticketsystem.utils.isInvalid
import com.anismhub.ticketsystem.utils.toLocalDate
import java.time.LocalDate

@Composable
fun ExportReportScreen(
    modifier: Modifier = Modifier,
    viewModel: ExportReportViewModel = hiltViewModel()
) {
    val context = LocalContext.current


    val baseUrl = BuildConfig.BASE_URL
    val accessToken by viewModel.accessToken
    var startDate by remember { mutableStateOf(InputTextState()) }
    var endDate by remember { mutableStateOf(InputTextState()) }


    ExportReportContent(
        startDate = startDate,
        onStartDateChanged = { startDate = it },
        endDate = endDate,
        onEndDateChanged = { endDate = it },
        downloadFile = { start, end ->
            val downloader = AndroidDownloader(context, baseUrl, start, end)
            downloader.downloadFile(accessToken)
        },
        modifier = modifier
    )

}

@Composable
fun ExportReportContent(
    startDate: InputTextState,
    onStartDateChanged: (InputTextState) -> Unit,
    endDate: InputTextState,
    onEndDateChanged: (InputTextState) -> Unit,
    downloadFile: (String, String) -> Unit,
    modifier: Modifier = Modifier
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
    val startError by remember { mutableStateOf("Tanggal harus diisi") }
    var endError by remember { mutableStateOf("Tanggal harus diisi") }


    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tanggal Dari :")
        // Tanggal Dari DatePicker
        ReusableDatePicker(
            dateState = startDate,
            onDateSelected = onStartDateChanged,
            errorText = startError
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Tanggal Sampai :")
        // Tanggal Sampai DatePicker
        ReusableDatePicker(
            dateState = endDate,
            minDateAllowed = startDate.value.toLocalDate("yyyy-MM-dd") ?: LocalDate.now()
                .minusYears(2),
            onDateSelected = onEndDateChanged,
            errorText = endError
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                when {
                    Build.VERSION.SDK_INT < Build.VERSION_CODES.Q -> {
                        storagePermissionResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    }

                    startDate.isInvalid() -> {
                        onStartDateChanged(startDate.copy(isError = true))
                    }

                    endDate.isInvalid() -> {
                        onEndDateChanged(endDate.copy(isError = true))
                    }

                    startDate.value.toLocalDate("yyyy-MM-dd")!! > endDate.value.toLocalDate("yyyy-MM-dd")!! -> {
                        onEndDateChanged(endDate.copy(isError = true))
                        endError = "Tanggal akhir tidak boleh lebih awal dari tanggal awal"
                    }

                    else -> {
                        downloadFile(
                            startDate.value,
                            endDate.value
                        )
                        Toast.makeText(
                            context,
                            "Mengekspor laporan...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
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