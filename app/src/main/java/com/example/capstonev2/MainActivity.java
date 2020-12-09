package com.example.capstonev2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity
{
    public static final Integer RecordAudioRequestCode = 1;
    private spee speechRecognizer;
    private ImageView micButton;
    Button Transcribe;
    TextView Listen;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null)
        {
            @SuppressLint("ShowToast") final Toast no_bluetooth_detected = Toast.makeText(getApplicationContext(),"No bluetooth detected", Toast.LENGTH_SHORT);
            finish();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECT_REQUESTED);
        filter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        this.registerReceiver(mReceiver, filter);
    }

    public final void bluetooth ( View view) {
        Intent intentOpenBluetoothSettings = new Intent();
        intentOpenBluetoothSettings.setAction("android.settings.BLUETOOTH_SETTINGS");
        this.startActivity(intentOpenBluetoothSettings);
    }

    public final void ListenButton (View view)
    {
        Listen = (TextView)findViewById(R.id.textView2);
        Transcribe = (Button)findViewById(R.id.Listener);
        Listen.setText("Listening...");
    }

    public void setSpeechRecognizer(spee speechRecognizer) {
        this.speechRecognizer = speechRecognizer;
        final Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver()
    {
        TextView Connectivity;
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

            if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
                @SuppressLint("ShowToast") final Toast no_bluetooth_detected = Toast.makeText(getApplicationContext(),"Bluetooth Device is now Connected", Toast.LENGTH_SHORT);
                Connectivity.setText("Disconnected");
                //Device is now connected
            }
            else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
                @SuppressLint("ShowToast") final Toast no_bluetooth_detected = Toast.makeText(getApplicationContext(),"Bluetooth Device has been Disconnected", Toast.LENGTH_SHORT);
                Connectivity.setText("Disconnected");
                //Device has disconnected
            }
        }
    };
}
