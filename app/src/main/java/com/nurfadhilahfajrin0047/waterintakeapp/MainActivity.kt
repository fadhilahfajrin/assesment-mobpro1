package com.nurfadhilahfajrin0047.waterintakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.nurfadhilahfajrin0047.waterintakeapp.navigation.SetupNavGraph
import com.nurfadhilahfajrin0047.waterintakeapp.ui.theme.WaterIntakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaterIntakeAppTheme {
                SetupNavGraph()
            }
        }
    }
}