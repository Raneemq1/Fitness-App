package com.example.fitnessapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.fitnessapp.R;
import com.example.fitnessapp.adapter.MealRecyclerAdapter;
import com.example.fitnessapp.model.Meal;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.Style;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.OnPointAnnotationClickListener;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotation;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.delegates.MapPluginProviderDelegate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private SearchView searchView;
    private Button btnSearch;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        double lon = 35.2332;
        double lat = 31.9522;

        mapView = findViewById(R.id.map_view);
        searchView = findViewById(R.id.text_search_map);
        btnSearch = findViewById(R.id.btn_search_map);
        setCameraPosition(lon, lat);
        setMarkerOnMap(lon, lat);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchView.getQuery().toString();
                String url = "https://api.opencagedata.com/geocode/v1/json?q=" + query + "&key=52b7280f7d7346e29ee3133f69d33d93&language=ar&pretty=1";
                queue = Volley.newRequestQueue(MapActivity.this);

                JsonObjectRequest request = new JsonObjectRequest
                        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray arr = response.getJSONArray("results");
                                    JSONObject obj = arr.getJSONObject(0);
                                    JSONObject geo = obj.getJSONObject("geometry");
                                    String lonString = geo.getString("lng");
                                    String latString = geo.getString("lat");
                                    double lon = Double.parseDouble(lonString);
                                    double lat = Double.parseDouble(latString);
                                    setCameraPosition(lon, lat);
                                    setMarkerOnMap(lon, lat);
                                    System.out.println(obj);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO: Handle error

                            }
                        });
                queue.add(request);
            }
        });

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

        pointAnnotationManager.addClickListener(new OnPointAnnotationClickListener() {
            @Override
            public boolean onAnnotationClick(@NonNull PointAnnotation pointAnnotation) {
                System.out.println("hi");
                return false;
            }
        });
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