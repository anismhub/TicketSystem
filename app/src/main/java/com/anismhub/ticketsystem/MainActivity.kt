package com.anismhub.ticketsystem

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.anismhub.ticketsystem.presentation.screen.MainViewModel
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicketSystemTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val startDestination by mainViewModel.startDestination
                    TicketSystemApp(
                        startDestination = startDestination
                    )

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val context = LocalContext.current
                        val isPermissionGranted = remember { mutableStateOf(
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.ACCESS_NOTIFICATION_POLICY
                            ) == PackageManager.PERMISSION_GRANTED
                        ) }

                        val requestPermissionLauncher = rememberLauncherForActivityResult(
                            contract = ActivityResultContracts.RequestPermission()
                        ) { isGranted: Boolean ->
                            if (isGranted) {
                                isPermissionGranted.value = true
                            } else {
                                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
                            }
                        }

                        if (!isPermissionGranted.value) {
                            requestPermissionLauncher.launch(Manifest.permission.ACCESS_NOTIFICATION_POLICY)
                        }
                    }
                }
            }
        }
    }
}