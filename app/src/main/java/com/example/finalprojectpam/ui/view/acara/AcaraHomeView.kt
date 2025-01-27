package com.example.finalprojectpam.ui.view.acara

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectpam.R
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.navigation.DestinasiNavigasi
import com.example.finalprojectpam.ui.customwidget.CostumeTopAppBar
import com.example.finalprojectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.HomeUiState

object DestinasiHomeAcara : DestinasiNavigasi {
    override val route = "home_acara"
    override val titleRes = "Home Acara"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcaraHomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: AcaraHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiHomeAcara.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,

                onRefresh = { viewModel.getAcr()}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Tambah Acara")
            }
        }
    ) { innerPadding ->
        HomeStatus(
            homeUiState = viewModel.acrUIState,
            retryAction = { viewModel.getAcr() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteAcr(it.id_acara)
                viewModel.getAcr()
            }
        )
    }
}

@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Acara) -> Unit = {},
    onDetailClick: (String) -> Unit
) {
    when (homeUiState) {
        is HomeUiState.Loading -> {
            OnLoading(modifier = modifier.fillMaxSize())
        }
        is HomeUiState.Success -> {
            if (homeUiState.acara.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data")
                }
            } else {
                AcrLayout(
                    acara = homeUiState.acara,
                    modifier = modifier.fillMaxWidth(),
                    onDetailClick = { onDetailClick(it.id_acara) },
                    onDeleteClick = { onDeleteClick(it) }
                )
            }
        }
        is HomeUiState.Error -> {
            OnError(retryAction, modifier = modifier.fillMaxSize())
        }
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(
            text = stringResource(R.string.loading_failed),
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun AcrLayout(
    acara: List<Acara>,
    modifier: Modifier = Modifier,
    onDetailClick: (Acara) -> Unit,
    onDeleteClick: (Acara) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(acara) { acara ->
            AcrCard(
                acara = acara,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(acara) },
                onDeleteClick = { onDeleteClick(acara) }
            )
        }
    }
}

@Composable
fun AcrCard(
    acara: Acara,
    modifier: Modifier = Modifier,
    onDeleteClick: (Acara) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Nama Acara: ${acara.nama_acara}",
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(acara) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null,
                    )
                }
            }
            Text(
                text = "ID Acara: ${acara.id_acara}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Deskripsi Acara: ${acara.deskripsi_acara}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Tanggal Mulai: ${acara.tanggal_mulai}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Tanggal Berakhir: ${acara.tanggal_berakhir}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "ID Lokasi: ${acara.id_lokasi}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "ID Klien: ${acara.id_klien}",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "Status Acara: ${acara.status_acara}",
                style = MaterialTheme.typography.titleMedium
            )

        }
    }
}