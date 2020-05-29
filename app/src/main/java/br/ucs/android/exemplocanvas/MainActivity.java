package br.ucs.android.exemplocanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import static android.content.Context.SENSOR_SERVICE;

public class MainActivity extends Activity {

    private CanvasView customCanvas;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Point point = new Point();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager wm = getWindowManager();
        Display d = wm.getDefaultDisplay();
        d.getSize(point);


        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(new AccelerometerSensor(), mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME);

        customCanvas.setmX(point.x);
        customCanvas.setmY(point.y);
    }

    private class AccelerometerSensor implements  SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float currY = event.values[0];
            float currX =event.values[1];
            float currZ = event.values[2];


            if(currX > 2 || currX < -2){
                if(customCanvas.getCurrX() > 0 && customCanvas.getCurrX() < point.x){
                    customCanvas.updateX((int)currX);
                }
            }

            if(currY > 2.5 || currY < -2.5){
//                if(customCanvas.getCurrY() > 0 && customCanvas.getCurrY() < point.y)
                customCanvas.updateY((int)currY);
                System.out.println(String.format("%.2f", currY));
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
}
