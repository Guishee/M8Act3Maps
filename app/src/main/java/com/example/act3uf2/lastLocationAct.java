package com.example.act3uf2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;


public class lastLocationAct extends AppCompatActivity {

    //El boton de las coordenadas ademas del toast te manda a la actividad anterior
    //esteticamente no me gustaba un boton de 'atras' o flecha.

    private ImageButton sos;
    private LocationManager lm;
    private LocationListener locList;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_location);
        if(ContextCompat.checkSelfPermission(lastLocationAct.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        sos=findViewById(R.id.sosButton);
        lm=(LocationManager) getSystemService(Context.LOCALE_SERVICE);




        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FusedLocationProviderClient flpc= LocationServices.getFusedLocationProviderClient(lastLocationAct.this);

                if(ContextCompat.checkSelfPermission(lastLocationAct.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                    flpc.getLastLocation().addOnSuccessListener(lastLocationAct.this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if(location != null){
                                Toast.makeText(lastLocationAct.this,"sus coordenadas son: " + location.getLatitude()+", "+location.getLongitude(),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(lastLocationAct.this,"no hay coordenadas",Toast.LENGTH_LONG).show();
                            }
                        }

                    });
                }else{
                    ActivityCompat.requestPermissions(lastLocationAct.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }

                Intent intent= new Intent(lastLocationAct.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }


}