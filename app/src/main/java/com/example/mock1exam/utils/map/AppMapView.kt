package com.example.mock1exam.utils.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.scale
import com.example.assignment4.utils.map.Coordinate
import com.example.mock1exam.utils.permission.Permission
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.mapbox.geojson.Point
import com.mapbox.maps.MapView
import com.mapbox.maps.MapboxMap
import com.mapbox.maps.Style
import com.mapbox.maps.dsl.cameraOptions
import com.mapbox.maps.plugin.animation.MapAnimationOptions
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.locationcomponent.location
import com.mapbox.maps.plugin.scalebar.scalebar
import com.example.mock1exam.R

// Annotate a list of coordinates
// Can fly to specific coordinate
// Fly to user current location if specific location is not provided
// Trigger callback when an annotation is pressed
@SuppressLint("MissingPermission")
@ExperimentalPermissionsApi
@Composable
fun AppMapView(
    coordinates: List<Coordinate> = listOf(),
    flyToLocation: Coordinate? = null,
    modifier: Modifier = Modifier,
    onAnnotationPress: (coordinate: Coordinate) -> Unit = {}
) {
    // Require User permission to access user location
    Permission(
        permission = Manifest.permission.ACCESS_FINE_LOCATION,
        rationale = "Need your permission for map functioning properly",
        permissionNotAvailableContent = {
            Text("Map permission is not enable")
        }
    ) {
        AppMapViewOnPermissionGranted(
            coordinates = coordinates,
            flyToLocation = flyToLocation,
            modifier = modifier
        ) {
            onAnnotationPress(it)
        }
    }
}

@SuppressLint("MissingPermission")
@Composable
fun AppMapViewOnPermissionGranted(
    coordinates: List<Coordinate> = listOf(),
    flyToLocation: Coordinate? = null,
    modifier: Modifier = Modifier,
    onAnnotationPress: (coordinate: Coordinate) -> Unit = {}
) {
    var mapBox by remember { mutableStateOf<MapboxMap?>(null) }

    // Location Permission granted!
    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                mapBox = this.getMapboxMap()

                // Set Android View Styling
                layoutParams = ViewGroupLayoutParams()

                // Set user location style
                MapBoxLoadStyleSettings(mapBox)

                // Create AnnotationManager
                var pointAnnotationManager = annotations.createPointAnnotationManager(this)

                // Create Annotation Bitmap
                val bitmap = GetBitmap()

                // Pin each location on the map
                PinLocationsToMap(coordinates, bitmap, pointAnnotationManager, onAnnotationPress)

                // If InitialLocation is provided, go to that location,
                // Else fly to user current location
                if (flyToLocation !== null) {
                    FlyToLocation(flyToLocation, mapBox)
x                } else {
                    FlyToUserCurrentLocation(context, mapBox)
                }
            }
        }
    )
}

private fun FlyToLocation(
    initialLocation: Coordinate?,
    mapBox: MapboxMap?
) {
    val toLocationPoint = Point.fromLngLat(
        initialLocation?.longitude ?: 0.0,
        initialLocation?.latitude ?: 0.0
    )

    mapBox!!.flyTo(
        MapboxCameraOptions(toLocationPoint),
        MapboxAnimationOptions()
    )
}

@SuppressLint("MissingPermission")
private fun FlyToUserCurrentLocation(
    context: Context,
    mapBox: MapboxMap?
) {
    LocationServices.getFusedLocationProviderClient(context)
        .lastLocation.addOnCompleteListener {
            var latitude = it.result.latitude
            var longitude = it.result.longitude

            var currentPoint = Point.fromLngLat(
                longitude,
                latitude
            )

            mapBox!!.flyTo(
                MapboxCameraOptions(currentPoint),
                MapboxAnimationOptions()
            )
        }
}

private fun PinLocationsToMap(
    coordinates: List<Coordinate>,
    bitmap: Bitmap,
    pointAnnotationManager: PointAnnotationManager?,
    onAnnotationPress: (coordinate: Coordinate) -> Unit
) {
    for (coordinate in coordinates) {
        var point = Point.fromLngLat(
            coordinate.longitude,
            coordinate.latitude
        )

        val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(bitmap)

        pointAnnotationManager?.create(pointAnnotationOptions)
    }

    // add onAnnotationPress listener
    pointAnnotationManager?.addClickListener {
        onAnnotationPress(
            Coordinate(
                latitude = it.point.latitude(),
                longitude = it.point.longitude()
            )
        )
        // expect a boolean return
        true
    }
}

private fun ViewGroupLayoutParams() = ViewGroup.LayoutParams(
    ViewGroup.LayoutParams.MATCH_PARENT,
    ViewGroup.LayoutParams.MATCH_PARENT
)

private fun MapView.MapBoxLoadStyleSettings(mapBox: MapboxMap?) {
    mapBox?.loadStyleUri(Style.MAPBOX_STREETS) {
        // hide scalebar
        this.scalebar.enabled = false

        // user location setting
        this.location.updateSettings {
            this.enabled = true
            pulsingEnabled = true
        }
    }
}

private fun MapView.GetBitmap() = BitmapFactory
    .decodeResource(resources, R.drawable.cat_pink_paw_icon)
    .scale(150, 150)

private fun MapboxAnimationOptions() = MapAnimationOptions.mapAnimationOptions {
    duration(3000)
}

private fun MapboxCameraOptions(toLocationPoint: Point?) = cameraOptions {
    center(toLocationPoint)
    zoom(10.0)
}


