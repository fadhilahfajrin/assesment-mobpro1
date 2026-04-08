package com.nurfadhilahfajrin0047.waterintakeapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nurfadhilahfajrin0047.waterintakeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MateriScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.materi),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        MateriContent(Modifier.padding(innerPadding))
    }
}

@Composable
fun MateriContent(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        MateriSection(
            title = stringResource(R.string.materi_penting),
            desc = stringResource(R.string.materi_penting_desc),
            imageRes = R.drawable.drink_water
        )

        MateriSection(
            title = stringResource(R.string.materi_kalori),
            desc = stringResource(R.string.materi_kalori_desc)
        )

        MateriSection(
            title = stringResource(R.string.materi_faktor),
            desc = stringResource(R.string.materi_faktor_desc)
        )

        MateriSection(
            title = stringResource(R.string.materi_perhitungan),
            desc = stringResource(R.string.materi_perhitungan_desc)
        )

        MateriSection(
            title = stringResource(R.string.materi_tips),
            desc = stringResource(R.string.materi_tips_desc)
        )

        Text(
            text = stringResource(R.string.materi_highlight),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = stringResource(R.string.materi_personal),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MateriSection(
    title: String,
    desc: String,
    imageRes: Int? = null
) {
    Column {

        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Text(
            text = desc,
            style = MaterialTheme.typography.bodyMedium,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(
            thickness = 0.5.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )
    }
}