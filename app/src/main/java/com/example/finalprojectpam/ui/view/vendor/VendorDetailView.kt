package com.example.finalprojectpam.ui.view.vendor

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
import com.example.finalprojectpam.model.Vendor
import com.example.finalprojectpam.navigation.DestinasiNavigasi
import com.example.finalprojectpam.ui.customwidget.CostumeTopAppBar
import com.example.finalprojectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprojectpam.ui.viewmodel.vendor.DetailUiState
import com.example.finalprojectpam.ui.viewmodel.vendor.VendorDetailViewModel

object DestinasiDetailVendor : DestinasiNavigasi {
    override val route = "detail_vendor"
    override val titleRes = "Detail Vendor"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VendorDetailView(
    id_vendor: String,
    navigateBack: () -> Unit,
    onClick: () -> Unit,
    viewModel: VendorDetailViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    viewModel.getVendorById(id_vendor)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiDetailVendor.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getVendorById(id_vendor)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Vendor")
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
                viewModel.deleteVndr(id_vendor)
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
        is DetailUiState.Loading -> OnLoading(modifier = modifier)
        is DetailUiState.Success -> Column(
            modifier = modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            ItemDetailVndr(vendor = uiState.vendor)
            Button(
                onClick = { deleteConfirmationRequired = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Delete")
            }
        }
        is DetailUiState.Error ->OnError(
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
fun ItemDetailVndr(
    modifier: Modifier = Modifier,
    vendor: Vendor
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
            ComponentDetailVndr(judul = "Nama Vendor", isinya = vendor.nama_vendor)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailVndr(judul = "Id Vendor", isinya = vendor.id_vendor)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailVndr(judul = "Jenis Vendor", isinya = vendor.jenis_vendor)
            Spacer(modifier = Modifier.height(8.dp))

            ComponentDetailVndr(judul = "Kontak Vendor", isinya = vendor.kontak_vendor)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ComponentDetailVndr(
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
