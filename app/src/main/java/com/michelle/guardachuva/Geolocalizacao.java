package com.michelle.guardachuva;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.content.ContextCompat;

public class Geolocalizacao {
    private Context context;

    public Geolocalizacao(Context context) {
        this.context = context;
    }

    public Location getLocation() {
        if (ContextCompat.checkSelfPermission(
                context, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED) {

            LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        return null;
    }
}