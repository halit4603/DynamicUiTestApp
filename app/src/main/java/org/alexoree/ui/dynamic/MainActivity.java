package org.alexoree.ui.dynamic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.TilesOverlay;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    String internalval = UUID.randomUUID().toString();
    final static String TAG = BuildConfig.APPLICATION_ID;
    static boolean MOTION = false;
    static boolean DAY = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        findViewById(R.id.main_button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOTION = false;
                restart();
            }
        });

        findViewById(R.id.main_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MOTION = true;
                restart();
            }
        });

        findViewById(R.id.main_button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAY = true;
                restart();
            }
        });

        findViewById(R.id.main_button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAY = false;
                restart();
            }
        });

        MapView map = (MapView) findViewById(R.id.mapview1);
        map.setTilesScaledToDpi(true);
        map.getController().setZoom(3);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.getController().setCenter(new GeoPoint(39.634394, -76.465935));
        if (!DAY)
            map.getOverlayManager().getTilesOverlay().setColorFilter(TilesOverlay.INVERT_COLORS);
        else
            map.getOverlayManager().getTilesOverlay().setColorFilter(null);
        map.invalidate();
    }

    private void restart() {
        finish();
        startActivity(new Intent(this, this.getClass()));
    }


    public static void onActivityCreateSetTheme(Activity activity) {
        if (DAY && MOTION)
            activity.setTheme(R.style.AppTheme_Daylight_Motion);
        else if (!DAY && MOTION)
            activity.setTheme(R.style.AppTheme_Night_Motion);

        else if (DAY && !MOTION)
            activity.setTheme(R.style.AppTheme_Daylight_Stationary);
        else
            activity.setTheme(R.style.AppTheme_Night_Stationary);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart" + internalval);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause" + internalval);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume" + internalval);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop" + internalval);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy" + internalval);
    }

}
