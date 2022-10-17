package br.com.vpn.valet.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.CaseMap
import android.util.JsonWriter
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import br.com.vpn.valet.R
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import org.json.JSONObject

@Composable
fun FindParkingMap() {

    val userLocation = LatLng(-5.184097, -37.346576)
    val parkingLocation1 = LatLng(-5.182842, -37.343668)
    val parkingLocation2 = LatLng(-5.187718, -37.347091)

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 16f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState,
        properties = MapProperties(
            mapStyleOptions = MapStyleOptions.loadRawResourceStyle(LocalContext.current, R.raw.map_style)
        )
    ) {
        Marker(
            state = MarkerState(position = userLocation),
            title = "Singapore",
            snippet = "Marker in Singapore"
        )
        ParkingMapMarker(
            context = LocalContext.current,
            position = parkingLocation1,
            title = "Parking 1",
            snippet = "Marker in Parking 1",
            iconResourceId = R.drawable.ic_parking_outline)
        ParkingMapMarker(
            context = LocalContext.current,
            position = parkingLocation2,
            title = "Parking 2",
            snippet = "Marker in Parking 2",
            iconResourceId = R.drawable.ic_parking_outline)
    }
}

@Composable
fun ParkingMapMarker(
    context: Context,
    position: LatLng,
    title: String,
    snippet: String,
    @DrawableRes iconResourceId: Int,
) {
    val icon = bitmapDescriptor(context, iconResourceId)
    
    Marker(
        state = MarkerState(position = position),
        title = title,
        snippet = snippet,
        icon = icon
    )
}

private fun bitmapDescriptor(context: Context, vectorResId: Int): BitmapDescriptor? {
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    
    val bitmap = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )
    
    val canvas = android.graphics.Canvas(bitmap)
    drawable.draw(canvas)
    
    return BitmapDescriptorFactory.fromBitmap(bitmap)
}