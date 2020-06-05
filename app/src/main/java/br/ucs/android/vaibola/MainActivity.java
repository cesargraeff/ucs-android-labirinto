package br.ucs.android.vaibola;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Display;
import android.view.WindowManager;

public class MainActivity extends Activity {

    private CanvasView customCanvas;
    private Point point = new Point();
    private MediaPlayer mp;
    private boolean som;
    private Vibrator v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vaiSom();

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        d.getSize(point);

        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new AccelerometerSensor(), mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);

        customCanvas.setmX(point.x);
        customCanvas.setmY(point.y);

        customCanvas.setF1();
        customCanvas.setFase(1);

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    }

    private class AccelerometerSensor implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float currY = event.values[0];
            float currX = event.values[1];

            if (currX > 2 || currX < -2) {
                if (customCanvas.isValidX((int) currX)) {
                    customCanvas.updateX((int) currX);
                } else {
                    v.vibrate(10);
                }
            }

            if (currY > 2.5 || currY < -2.5) {
                if (customCanvas.isValidY((int) currY)) {
                    customCanvas.updateY((int) currY);
                } else {
                    v.vibrate(10);
                }
            }

            if (customCanvas.getCurrX() >= customCanvas.getMinFx() && customCanvas.getCurrX() <= customCanvas.getMaxFx()
                    && customCanvas.getCurrY() >= customCanvas.getMinFy() && customCanvas.getCurrY() <= customCanvas.getMaxFy()) {
                customCanvas.setCurrX(100);
                customCanvas.setCurrY(100);
                customCanvas.setFase(2);
                vaiSom();
            }


            // Set circle color to blue.
            customCanvas.setCircleColor(Color.BLUE);

            // Notify drawCircleView to redraw. This will invoke DrawBallView's onDraw() method.
            customCanvas.invalidate();

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

    }

    @Override
    public void onBackPressed() {
        // TODO ARRUMAR ISSO, para forma de gente
//        super.onBackPressed();
        customCanvas.setFase(1);
    }

    private void vaiSom() {

        if (!som) {
            som = true;
            mp = MediaPlayer.create(getApplicationContext(), R.raw.tetra);

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                @Override
                public void onCompletion(MediaPlayer mp) {
                    som = false;
                    mp.release();
                }

            });

            mp.start();
        }


    }
}
