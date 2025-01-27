package com.example.finalprojectpam.ui.view.acara

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectpam.model.Acara
import com.example.finalprojectpam.navigation.DestinasiNavigasi
import com.example.finalprojectpam.ui.customwidget.CostumeTopAppBar
import com.example.finalprojectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraDetailViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.DetailUiState


object DestinasiDetailACara: DestinasiNavigasi {
    override val route = "detail_acara"
    override val titleRes = "Detail Acara"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcaraDetailView(
    id_acara: String,
    navigateBack: () -> Unit,
    onClick: () -> Unit,
    viewModel: AcaraDetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    viewModel.getAcaraById(id_acara)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailACara.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getAcaraById(id_acara)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Acara")
            }
        },
    ) { innerPadding ->
        val uiState = viewModel.uiState.collectAsState().value
        DetailBody(
            uiState = uiState,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onDeleteClick = {
                viewModel.deleteAcr(id_acara)
                navigateBack()
            }
        )
    }
}


@Composable
fun DetailBody(
    uiState: DetailUiState,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {
    var deleteConfirmationRequired by remember { mutableStateOf(false) }

    when (uiState) {
        is DetailUiState.Loading ->OnLoading(modifier = modifier)
        is DetailUiState.Success -> Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ItemDetailacr(acara = uiState.acara)
            Button(
                onClick = { deleteConfirmationRequired = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
        is DetailUiState.Error -> OnError(
            retryAction = {},
            modifier = modifier
        )
    }

    if (deleteConfirmationRequired) {
        DeleteConfirmationDialog(
            onDeleteConfirm = {
                deleteConfirmationRequired = false
                onDeleteClick()
            },
            onDeleteCancel = { deleteConfirmationRequired = false }
        )
    }
}


@Composable
fun ItemDetailacr(
    modifier: Modifier = Modifier,
    acara: Acara
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            ComponentDetailacr(judul = "Nama Acara", isinya = acara.nama_acara)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Id Acara", isinya = acara.id_acara)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Deskripsi Acara", isinya = acara.deskripsi_acara)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Tanggal Mulai", isinya = acara.tanggal_mulai)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Tanggal Berakhir", isinya = acara.tanggal_berakhir)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Id Klien", isinya = acara.id_klien)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Id Lokasi", isinya = acara.id_lokasi)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailacr(judul = "Status Acara", isinya = acara.status_acara)
            Spacer(modifier = Modifier.height(8.dp))

        }
    }
}

@Composable
fun ComponentDetailacr(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )
        )
        Text(
            text = isinya,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        )
    }
}

@Composable
private fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { },
        title = { Text("Delete Data") },
        text = { Text("Apakah anda yakin ingin menghapus data?") },
        modifier = modifier,
        dismissButton = {
            TextButton(onClick = onDeleteCancel) {
                Text(text = "Cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = onDeleteConfirm) {
                Text(text = "Yes")
            }
        }
    )
}
