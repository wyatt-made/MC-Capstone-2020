package com.example.capstonev2;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
        {
            final Toast no_bluetooth_detected = Toast.makeText(getApplicationContext(),"No bluetooth detected", Toast.LENGTH_SHORT);
            finish();
        }
            }

    public final void bluetooth( View view) {
        Intent intentOpenBluetoothSettings = new Intent();
        intentOpenBluetoothSettings.setAction("android.settings.BLUETOOTH_SETTINGS");
        this.startActivity(intentOpenBluetoothSettings);
    }
}
