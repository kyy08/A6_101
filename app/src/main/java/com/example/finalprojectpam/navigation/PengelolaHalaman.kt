package com.example.finalprojectpam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalprojectpam.ui.view.DestinasiHomeUtama
import com.example.finalprojectpam.ui.view.HomeUtama
import com.example.finalprojectpam.ui.view.acara.AcaraDetailView
import com.example.finalprojectpam.ui.view.acara.AcaraHomeScreen
import com.example.finalprojectpam.ui.view.acara.AcaraUpdateView
import com.example.finalprojectpam.ui.view.acara.DestinasiDetailACara
import com.example.finalprojectpam.ui.view.acara.DestinasiEntryAcara
import com.example.finalprojectpam.ui.view.acara.DestinasiHomeAcara
import com.example.finalprojectpam.ui.view.acara.DestinasiUpdateAcara
import com.example.finalprojectpam.ui.view.acara.EntryAcrScreen
import com.example.finalprojectpam.ui.view.klien.DestinasiDetailKlien
import com.example.finalprojectpam.ui.view.klien.DestinasiEntryKlien
import com.example.finalprojectpam.ui.view.klien.DestinasiHomeKlien
import com.example.finalprojectpam.ui.view.klien.DestinasiUpdateKlien
import com.example.finalprojectpam.ui.view.klien.EntryKlnScreen
import com.example.finalprojectpam.ui.view.klien.KlienDetailView
import com.example.finalprojectpam.ui.view.klien.KlienHomeScreen
import com.example.finalprojectpam.ui.view.klien.KlienUpdateView
import com.example.finalprojectpam.ui.view.lokasi.DestinasiDetailLokasi
import com.example.finalprojectpam.ui.view.lokasi.DestinasiEntryLokasi
import com.example.finalprojectpam.ui.view.lokasi.DestinasiHomeLokasi
import com.example.finalprojectpam.ui.view.lokasi.DestinasiUpdateLokasi
import com.example.finalprojectpam.ui.view.lokasi.EntryLksScreen
import com.example.finalprojectpam.ui.view.lokasi.LokasiDetailView
import com.example.finalprojectpam.ui.view.lokasi.LokasiHomeScreen
import com.example.finalprojectpam.ui.view.lokasi.LokasiUpdateView
import com.example.finalprojectpam.ui.view.vendor.DestinasiDetailVendor
import com.example.finalprojectpam.ui.view.vendor.DestinasiEntryVendor
import com.example.finalprojectpam.ui.view.vendor.DestinasiHomeVendor
import com.example.finalprojectpam.ui.view.vendor.DestinasiUpdateVendor
import com.example.finalprojectpam.ui.view.vendor.EntryVndrScreen
import com.example.finalprojectpam.ui.view.vendor.VendorDetailView
import com.example.finalprojectpam.ui.view.vendor.VendorHomeScreen
import com.example.finalprojectpam.ui.view.vendor.VendorUpdateView

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeUtama.route,
        modifier = Modifier
    )
    {
        // Home Utama route
        composable(DestinasiHomeUtama.route) {
            HomeUtama(
                onAcaraClick = { navController.navigate(DestinasiHomeAcara.route) },
                onKlienClick = { navController.navigate(DestinasiHomeKlien.route) },
                onLokasiClick = { navController.navigate(DestinasiHomeLokasi.route) },
                onVendorClick = { navController.navigate(DestinasiHomeVendor.route) }
            )
        }

        composable(DestinasiHomeAcara.route) {
            AcaraHomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryAcara.route)
                },
                onDetailClick = { id_acara->
                    if (id_acara.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailACara.route}/$id_acara")
                    }
                }
            )
        }
        composable(DestinasiEntryAcara.route) {
            EntryAcrScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeAcara.route) {
                        popUpTo(DestinasiHomeAcara.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(
            route = "${DestinasiDetailACara.route}/{id_acara}",
            arguments = listOf(navArgument("id_acara") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_acara = backStackEntry.arguments?.getString("id_acara") ?: ""
            AcaraDetailView(
                id_acara = id_acara,
                navigateBack = {
                    navController.navigate(DestinasiHomeAcara.route) {
                        popUpTo(DestinasiHomeAcara.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    // Navigate to the update screen when FAB is clicked
                    navController.navigate("${DestinasiUpdateAcara.route}/$id_acara")
                }
            )
        }

        // Update Screen
        composable(
            route = "${DestinasiUpdateAcara.route}/{id_acara}",
            arguments = listOf(navArgument("id_acara") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_acara = backStackEntry.arguments?.getString("id_acara") ?: ""
            AcaraUpdateView(
                id_acara = id_acara,
                navigateBack = {
                    navController.navigate(DestinasiHomeAcara.route) {
                        popUpTo(DestinasiHomeAcara.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(DestinasiHomeLokasi.route) {
            LokasiHomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryLokasi.route)
                },
                onDetailClick = { id_lokasi->
                    if (id_lokasi.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailLokasi.route}/$id_lokasi")
                    }
                }
            )
        }
        composable(DestinasiEntryLokasi.route) {
            EntryLksScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeLokasi.route) {
                        popUpTo(DestinasiHomeLokasi.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailLokasi.route}/{id_lokasi}",
            arguments = listOf(navArgument("id_lokasi") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_lokasi = backStackEntry.arguments?.getString("id_lokasi") ?: ""
            LokasiDetailView(
                id_lokasi = id_lokasi,
                navigateBack = {
                    navController.navigate(DestinasiHomeLokasi.route) {
                        popUpTo(DestinasiHomeLokasi.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    // Navigate to the update screen when FAB is clicked
                    navController.navigate("${DestinasiUpdateLokasi.route}/$id_lokasi")
                }
            )
        }

        // Update Screen
        composable(
            route = "${DestinasiUpdateLokasi.route}/{id_lokasi}",
            arguments = listOf(navArgument("id_lokasi") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_lokasi = backStackEntry.arguments?.getString("id_lokasi") ?: ""
            LokasiUpdateView(
                id_lokasi = id_lokasi,
                navigateBack = {
                    navController.navigate(DestinasiHomeLokasi.route) {
                        popUpTo(DestinasiHomeLokasi.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiHomeVendor.route) {
            VendorHomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryVendor.route)
                },
                onDetailClick = { id_vendor ->
                    if (id_vendor.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailVendor.route}/$id_vendor")
                    }
                }
            )
        }
        composable(DestinasiEntryVendor.route) {
            EntryVndrScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeVendor.route) {
                        popUpTo(DestinasiHomeVendor.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailVendor.route}/{id_vendor}",
            arguments = listOf(navArgument("id_vendor") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_vendor = backStackEntry.arguments?.getString("id_vendor") ?: ""
            VendorDetailView(
                id_vendor= id_vendor,
                navigateBack = {
                    navController.navigate(DestinasiHomeVendor.route) {
                        popUpTo(DestinasiHomeVendor.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    // Navigate to the update screen when FAB is clicked
                    navController.navigate("${DestinasiUpdateVendor.route}/$id_vendor")
                }
            )
        }

        // Update Screen
        composable(
            route = "${DestinasiUpdateVendor.route}/{id_vendor}",
            arguments = listOf(navArgument("id_vendor") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_vendor = backStackEntry.arguments?.getString("id_vendor") ?: ""
            VendorUpdateView(
                id_vendor= id_vendor,
                navigateBack = {
                    navController.navigate(DestinasiHomeVendor.route) {
                        popUpTo(DestinasiHomeVendor.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(DestinasiHomeKlien.route) {
            KlienHomeScreen(
                navigateToItemEntry = {
                    navController.navigate(DestinasiEntryKlien.route)
                },
                onDetailClick = { id_klien ->
                    if (id_klien.isNotEmpty()) {
                        navController.navigate("${DestinasiDetailKlien.route}/$id_klien")
                    }
                }
            )
        }
        composable(DestinasiEntryKlien.route) {
            EntryKlnScreen(
                navigateBack = {
                    navController.navigate(DestinasiHomeKlien.route) {
                        popUpTo(DestinasiHomeKlien.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = "${DestinasiDetailKlien.route}/{id_klien}",
            arguments = listOf(navArgument("id_klien") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_klien = backStackEntry.arguments?.getString("id_klien") ?: ""
            KlienDetailView(
                id_klien= id_klien,
                navigateBack = {
                    navController.navigate(DestinasiHomeKlien.route) {
                        popUpTo(DestinasiHomeKlien.route) {
                            inclusive = true
                        }
                    }
                },
                onClick = {
                    // Navigate to the update screen when FAB is clicked
                    navController.navigate("${DestinasiUpdateKlien.route}/$id_klien")
                }
            )
        }

        // Update Screen
        composable(
            route = "${DestinasiUpdateKlien.route}/{id_klien}",
            arguments = listOf(navArgument("id_klien") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_klien = backStackEntry.arguments?.getString("id_klien") ?: ""
            KlienUpdateView(
                id_klien= id_klien,
                navigateBack = {
                    navController.navigate(DestinasiHomeKlien.route) {
                        popUpTo(DestinasiHomeKlien.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}