package com.example.finalprojectpam.ui.view.acara

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectpam.navigation.DestinasiNavigasi
import com.example.finalprojectpam.ui.customwidget.CostumeTopAppBar
import com.example.finalprojectpam.ui.customwidget.DynamicSelectedTextField
import com.example.finalprojectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraInsertViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.InsertUiEvent
import com.example.finalprojectpam.ui.viewmodel.acara.InsertUiState
import com.example.finalprojectpam.ui.viewmodel.klien.KlienHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.HomeUiState
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiHomeViewModel
import kotlinx.coroutines.launch

object DestinasiEntryAcara : DestinasiNavigasi {
    override val route = "entry_acara"
    override val titleRes = "Entry Acara"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryAcrScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AcaraInsertViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModelklien: KlienHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModelLokasi: LokasiHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiEntryAcara.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onAcaraValueChange = viewModel::updateInsertAcrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertAcr()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryBody(
    insertUiState: InsertUiState,
    onAcaraValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
            onValueChange = onAcaraValueChange,
            modifier = Modifier.fillMaxWidth(),
            viewModelLokasiViewModel = viewModel(),
            viewModelKlienViewModel = viewModel()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true,
    viewModelLokasiViewModel: LokasiHomeViewModel,
    viewModelKlienViewModel: KlienHomeViewModel
) {
    val lksUiState = viewModelLokasiViewModel.lksUIState
    val klnUiState = viewModelKlienViewModel.klnUIState

    // Menampilkan UI berdasarkan state
    when (lksUiState) {
        is HomeUiState.Loading -> {
            // Menampilkan indikator loading
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is HomeUiState.Error -> {
            // Menampilkan pesan error
            Text("Gagal mengambil data lokasi", color = MaterialTheme.colorScheme.error)
        }
        is HomeUiState.Success -> {
            val lokasiList = lksUiState.lokasi // Mendapatkan list tanaman
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = insertUiEvent.id_acara,
                    onValueChange = { onValueChange(insertUiEvent.copy(id_acara = it)) },
                    label = { Text("ID Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = insertUiEvent.nama_acara,
                    onValueChange = { onValueChange(insertUiEvent.copy(nama_acara = it)) },
                    label = { Text("Nama Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = insertUiEvent.deskripsi_acara,
                    onValueChange = { onValueChange(insertUiEvent.copy(deskripsi_acara = it)) },
                    label = { Text("Deskripsi Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = insertUiEvent.tanggal_mulai,
                    onValueChange = { onValueChange(insertUiEvent.copy(tanggal_mulai = it)) },
                    label = { Text("Tanggal Mulai") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = insertUiEvent.tanggal_berakhir,
                    onValueChange = { onValueChange(insertUiEvent.copy(tanggal_berakhir = it)) },
                    label = { Text("Tanggal Berakhir") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                DynamicSelectedTextField(
                    selectedValue = insertUiEvent.id_lokasi.toString(), // Mengonversi idTanaman ke String
                    options = lokasiList.map { it.id_lokasi.toString() }, // Mengonversi ID tanaman ke String
                    label = "Pilih ID Lokasi",
                    onValueChangedEvent = { selectedId: String ->
                        onValueChange(insertUiEvent.copy(id_lokasi = selectedId))
                    }
                )
                OutlinedTextField(
                    value = insertUiEvent.status_acara,
                    onValueChange = { onValueChange(insertUiEvent.copy(status_acara = it)) },
                    label = { Text("Status Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )

            }
        }
    }
    when (klnUiState) {
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Loading -> {
            // Menampilkan indikator loading
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Error -> {
            // Menampilkan pesan error
            Text("Gagal mengambil data Klien", color = MaterialTheme.colorScheme.error)
        }
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Success -> {
            val klienList = klnUiState.klien // Mendapatkan list tanaman
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DynamicSelectedTextField(
                    selectedValue = insertUiEvent.id_klien.toString(), // Mengonversi idTanaman ke String
                    options = klienList.map { it.id_klien.toString() }, // Mengonversi ID tanaman ke String
                    label = "Pilih ID Klien",
                    onValueChangedEvent = { selectedId: String ->
                        onValueChange(insertUiEvent.copy(id_klien = selectedId))
                    }
                )
            }
        }
    }
}