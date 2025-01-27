package com.example.finalprojectpam.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import com.example.finalprojectpam.navigation.DestinasiNavigasi

object DestinasiHomeUtama : DestinasiNavigasi{
    override val route = "Home_utama"
    override val titleRes = "Home Utama"
}

@Composable
fun HomeUtama(
    onAcaraClick: () -> Unit,
    onKlienClick: () -> Unit,
    onLokasiClick: () -> Unit,
    onVendorClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State for animation control
    var isVisible by remember { mutableStateOf(false) }

    // Trigger animation when the view is loaded
    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(Color(0xFFC0C0C0)) // Background color changed to silver
    ) {
        // Title at the top
        Text(
            text = "Acara",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp) // Reduced bottom padding
                .align(Alignment.CenterHorizontally)
        )

        // Main content with buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Buttons with appropriate icons
            RegularButton(text = "Acara", icon = Icons.Filled.Event, onClick = onAcaraClick)
            RegularButton(text = "Klien", icon = Icons.Filled.Person, onClick = onKlienClick)
            RegularButton(text = "Lokasi", icon = Icons.Filled.LocationOn, onClick = onLokasiClick)
            RegularButton(text = "Vendor", icon = Icons.Filled.Store, onClick = onVendorClick)
        }
    }
}


@Composable
fun RegularButton(text: String, icon: androidx.compose.ui.graphics.vector.ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF6200EE) // Regular color for the button
        ),
        shape = RoundedCornerShape(12.dp), // Rounded corners for rectangle
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .size(250.dp, 60.dp) // Increase width to make the button wider
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp) // Padding between icon and text
        ) {
            // Icon with regular size
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 8.dp) // Padding between icon and text
                    .size(30.dp), // Icon size
                tint = Color.White // White icon color
            )
            // Text
            Text(
                text = text,
                fontSize = 16.sp, // Adjust font size
                fontWeight = FontWeight.Bold,
                color = Color.White // White text color
            )
        }
    }
}