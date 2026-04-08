package com.nurfadhilahfajrin0047.waterintakeapp.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nurfadhilahfajrin0047.waterintakeapp.R
import com.nurfadhilahfajrin0047.waterintakeapp.navigation.Screen
import com.nurfadhilahfajrin0047.waterintakeapp.ui.theme.WaterIntakeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.Materi.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.MenuBook,
                            contentDescription = stringResource(R.string.materi)
                        )
                    }

                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(id = R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier = Modifier) {
    var berat by rememberSaveable { mutableStateOf("") }
    var beratError by rememberSaveable { mutableStateOf(false) }

    var tinggi by rememberSaveable { mutableStateOf("") }
    var tinggiError by rememberSaveable { mutableStateOf(false) }

    val kategoriUmurOptions = listOf(
        stringResource(R.string.anak),
        stringResource(R.string.dewasa)
    )
    var kategoriUmur by rememberSaveable { mutableStateOf(kategoriUmurOptions[1]) }

    val genderOptions = listOf(
        stringResource(R.string.laki_laki),
        stringResource(R.string.perempuan)
    )
    var gender by rememberSaveable { mutableStateOf(genderOptions[0]) }

    val radioOptions = listOf(
        stringResource(R.string.aktivitas_ringan),
        stringResource(R.string.aktivitas_sedang),
        stringResource(R.string.aktivitas_berat)
    )

    var aktivitas by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var hasil by rememberSaveable { mutableDoubleStateOf(0.0) }
    var kalori by rememberSaveable { mutableDoubleStateOf(0.0) }
    var rekomendasi by rememberSaveable { mutableDoubleStateOf(0.0) }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.hitung_intro),
            style = MaterialTheme.typography.bodyLarge
        )

        OutlinedTextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text(stringResource(R.string.tinggi_badan)) },
            trailingIcon = { IconPicker(tinggiError, "cm") },
            supportingText = { ErrorHint(tinggiError) },
            isError = tinggiError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = berat,
            onValueChange = { berat = it },
            label = { Text(stringResource(R.string.berat_badan)) },
            trailingIcon = { IconPicker(beratError, "kg")},
            supportingText = { ErrorHint(beratError) },
            isError = beratError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(stringResource(R.string.kategori_umur))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(4.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            kategoriUmurOptions.forEach { text ->
                Row(
                    modifier = Modifier.selectable(
                        selected = kategoriUmur == text,
                        onClick = { kategoriUmur = text },
                        role = Role.RadioButton
                    )
                ) {
                    RadioButton(selected = kategoriUmur == text, onClick = null)
                    Text(text)
                }
            }
        }

        Text(stringResource(R.string.jenis_kelamin))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(4.dp))
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            genderOptions.forEach { text ->
                Row(
                    modifier = Modifier.selectable(
                        selected = gender == text,
                        onClick = { gender = text },
                        role = Role.RadioButton
                    )
                ) {
                    RadioButton(selected = gender == text, onClick = null)
                    Text(text)
                }
            }
        }

        Text(
            text = stringResource(R.string.aktivitas),
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .border(
                    1.dp,
                    MaterialTheme.colorScheme.outline,
                    RoundedCornerShape(4.dp)
                )
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            radioOptions.forEach { text ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.selectable(
                        selected = aktivitas == text,
                        onClick = { aktivitas = text },
                        role = Role.RadioButton
                    )
                ) {
                    RadioButton(
                        selected = aktivitas == text,
                        onClick = null
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Button(
                onClick = {
                    beratError = (berat.isEmpty() || berat == "0")
                    tinggiError = (tinggi.isEmpty() || tinggi == "0")

                    if (beratError || tinggiError) return@Button

                    val w = berat.toDouble()
                    val h = tinggi.toDouble()

                    val tinggiFactor = when {
                        h < 150 -> 0.9
                        h <= 170 -> 1.0
                        else -> 1.1
                    }

                    val faktorAir = when (aktivitas) {
                        radioOptions[0] -> 30
                        radioOptions[1] -> 35
                        else -> 40
                    }

                    val faktorKalori = when (aktivitas) {
                        radioOptions[0] -> 25
                        radioOptions[1] -> 30
                        else -> 35
                    }

                    val isAnak = kategoriUmur == kategoriUmurOptions[0]
                    val isPerempuan = gender == genderOptions[1]

                    val hasilAwal = w * faktorAir / 1000
                    val airAdjusted = if (isAnak) hasilAwal * 0.8 else hasilAwal
                    hasil = if (airAdjusted * tinggiFactor < 1.5) 1.5 else
                        airAdjusted * tinggiFactor

                    var kaloriAwal = w * faktorKalori
                    if (isPerempuan) kaloriAwal *= 0.9
                    if (isAnak) kaloriAwal *= 0.8
                    kalori = if (kaloriAwal < 1200) 1200.0 else kaloriAwal

                    rekomendasi = hasil * 1000 / 8
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(stringResource(R.string.hitung))
            }

            Button(
                onClick = {
                    berat = ""
                    tinggi = ""
                    hasil = 0.0
                    kalori = 0.0
                    rekomendasi = 0.0
                    beratError = false
                    tinggiError = false
                    kategoriUmur = radioOptions[1]
                    gender = radioOptions[0]
                    aktivitas = radioOptions[0]
                },
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(stringResource(R.string.reset))
            }
        }

        if (hasil != 0.0) {

            val message = stringResource(R.string.bagikan_template, hasil)

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = stringResource(R.string.hasil_air, hasil),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(R.string.hasil_kalori, kalori),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = stringResource(R.string.rekomendasi_minum, rekomendasi),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = { shareData(context, message) }
                    ) {
                        Text(stringResource(R.string.bagikan))
                    }
                }
            }
        }
    }
}

@Composable
fun IconPicker(isError: Boolean, unit: String) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    } else {
        Text(text = unit)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.input_invalid))
    }
}

private fun shareData(context: Context, message: String) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)
    }
    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(shareIntent)
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun MainScreenPreview() {
    WaterIntakeAppTheme{
       MainScreen(rememberNavController())
    }
}
