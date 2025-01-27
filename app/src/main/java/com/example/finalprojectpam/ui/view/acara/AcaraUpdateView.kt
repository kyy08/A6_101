package com.example.finalprojectpam.ui.view.acara

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalprojectpam.navigation.DestinasiNavigasi
import com.example.finalprojectpam.ui.customwidget.CostumeTopAppBar
import com.example.finalprojectpam.ui.customwidget.DynamicSelectedTextField
import com.example.finalprojectpam.ui.viewmodel.PenyediaViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.AcaraUpdateViewModel
import com.example.finalprojectpam.ui.viewmodel.acara.UpdateUiEvent
import com.example.finalprojectpam.ui.viewmodel.acara.UpdateUiState
import com.example.finalprojectpam.ui.viewmodel.klien.KlienHomeViewModel
import com.example.finalprojectpam.ui.viewmodel.lokasi.HomeUiState
import com.example.finalprojectpam.ui.viewmodel.lokasi.LokasiHomeViewModel
import kotlinx.coroutines.launch

object DestinasiUpdateAcara : DestinasiNavigasi {
    override val route = "update_acara"
    override val titleRes = "Update Acara"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcaraUpdateView(
    id_acara: String,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AcaraUpdateViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModelKlien: KlienHomeViewModel = viewModel(factory = PenyediaViewModel.Factory),
    viewModelLokasi: LokasiHomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(id_acara) {
        viewModel.loadAcara(id_acara)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CostumeTopAppBar(
                title = DestinasiUpdateAcara.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBody(
            updateUiState = viewModel.uiState,
            onAcaraValueChange = viewModel::updateAcaraState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateAcara(id_acara)
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            viewModelKlien = viewModelKlien,
            viewModelLokasi = viewModelLokasi
        )
    }
}

@Composable
fun UpdateBody(
    updateUiState: UpdateUiState,
    onAcaraValueChange: (UpdateUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModelKlien: KlienHomeViewModel,
    viewModelLokasi: LokasiHomeViewModel
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            updateUiEvent = updateUiState.updateUiEvent,
            onValueChange = onAcaraValueChange,
            modifier = Modifier.fillMaxWidth(),
            viewModelLokasiViewModel = viewModelLokasi,
            viewModelKlienViewModel = viewModelKlien
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update")
        }
    }
}

@Composable
fun FormInput(
    updateUiEvent: UpdateUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateUiEvent) -> Unit = {},
    enabled: Boolean = true,
    viewModelLokasiViewModel: LokasiHomeViewModel,
    viewModelKlienViewModel: KlienHomeViewModel
) {
    val lksUiState = viewModelLokasiViewModel.lksUIState
    val klnUiState = viewModelKlienViewModel.klnUIState

    when (lksUiState) {
        is HomeUiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is HomeUiState.Error -> {
            Text("Gagal mengambil data lokasi", color = MaterialTheme.colorScheme.error)
        }
        is HomeUiState.Success -> {
            val lokasiList = lksUiState.lokasi
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = updateUiEvent.id_acara,
                    onValueChange = { onValueChange(updateUiEvent.copy(id_acara = it)) },
                    label = { Text("ID Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = updateUiEvent.nama_acara,
                    onValueChange = { onValueChange(updateUiEvent.copy(nama_acara = it)) },
                    label = { Text("Nama Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = updateUiEvent.deskripsi_acara,
                    onValueChange = { onValueChange(updateUiEvent.copy(deskripsi_acara = it)) },
                    label = { Text("Deskripsi Acara") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = updateUiEvent.tanggal_mulai,
                    onValueChange = { onValueChange(updateUiEvent.copy(tanggal_mulai = it)) },
                    label = { Text("Tanggal Mulai") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                OutlinedTextField(
                    value = updateUiEvent.tanggal_berakhir,
                    onValueChange = { onValueChange(updateUiEvent.copy(tanggal_berakhir = it)) },
                    label = { Text("Tanggal Berakhir") },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = enabled,
                    singleLine = true
                )
                DynamicSelectedTextField(
                    selectedValue = updateUiEvent.id_lokasi.toString(),
                    options = lokasiList.map { it.id_lokasi.toString() },
                    label = "Pilih ID Lokasi",
                    onValueChangedEvent = { selectedId: String ->
                        onValueChange(updateUiEvent.copy(id_lokasi = selectedId))
                    }
                )
            }
        }
    }

    when (klnUiState) {
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Loading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Error -> {
            Text("Gagal mengambil data klien", color = MaterialTheme.colorScheme.error)
        }
        is com.example.finalprojectpam.ui.viewmodel.klien.HomeUiState.Success -> {
            val klienList = klnUiState.klien
            Column(
                modifier = modifier,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DynamicSelectedTextField(
                    selectedValue = updateUiEvent.id_klien.toString(),
                    options = klienList.map { it.id_klien.toString() },
                    label = "Pilih ID Klien",
                    onValueChangedEvent = { selectedId: String ->
                        onValueChange(updateUiEvent.copy(id_klien = selectedId))
                    }
                )
            }
        }
    }
}