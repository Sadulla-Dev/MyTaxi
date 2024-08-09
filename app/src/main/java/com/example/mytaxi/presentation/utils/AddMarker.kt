package com.example.mytaxi.presentation.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import androidx.core.content.res.ResourcesCompat
import com.example.mytaxi.R
import com.google.android.gms.maps.model.LatLng
import com.mapbox.geojson.Point
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions

fun addMarkerToMap(
    location: LatLng,
    context: Context,
    pointAnnotationManager: PointAnnotationManager?,
) {
    pointAnnotationManager?.let { manager ->
        val bitmap = ResourcesCompat
            .getDrawable(context.resources, R.drawable.ic_marker, null)
            ?.let { drawable ->
                if (drawable is BitmapDrawable) {
                    drawable.bitmap
                } else {
                    Bitmap.createBitmap(
                        drawable.intrinsicWidth,
                        drawable.intrinsicHeight,
                        Bitmap.Config.ARGB_8888
                    ).also {
                        val canvas = Canvas(it)
                        drawable.setBounds(0, 0, canvas.width, canvas.height)
                        drawable.draw(canvas)
                    }
                }
            }

        val resizedBitmap = bitmap?.let {
            Bitmap.createScaledBitmap(it, 150, 150, false)
        }

        val pointAnnotationOptions: PointAnnotationOptions? = resizedBitmap?.let {
            PointAnnotationOptions()
                .withPoint(
                    Point.fromLngLat(location.longitude, location.latitude)
                )
                .withIconImage(it)
        }

        pointAnnotationOptions?.let {
            manager.create(it)
        }
    }
}