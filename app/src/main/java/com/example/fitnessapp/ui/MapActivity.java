package com.example.fitnessapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.R;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.delegates.MapPluginProviderDelegate;


public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        double lon = 35.2332;
        double lat = 31.9522;

        mapView = findViewById(R.id.map_view);
        setCameraPosition(lon, lat);
        setMarkerOnMap(lon, lat);

    }

    private void setCameraPosition(double longitude, double latitude) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .zoom(4.0)
                .center(Point.fromLngLat(longitude, latitude))
                .build();
        // set camera position
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    private void setMarkerOnMap(double longitude, double latitude) {
        Bitmap bitmap = BitmapFactory.decodeResource(
                getResources(), R.drawable.red_marker);
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS);
        AnnotationPlugin annotationAPI = AnnotationPluginImplKt.getAnnotations((MapPluginProviderDelegate) mapView);
        PointAnnotationManager pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationAPI, mapView);
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(com.mapbox.geojson.Point.fromLngLat(longitude, latitude))
                .withIconImage(bitmap);
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}