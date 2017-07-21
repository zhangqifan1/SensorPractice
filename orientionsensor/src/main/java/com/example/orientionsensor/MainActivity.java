package com.example.orientionsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private SensorManager manager;
    private MySensorEventlistener mySensorEventlistener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //得到系统管理者
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //得到指南针传感器
        Sensor defaultSensor = manager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mySensorEventlistener = new MySensorEventlistener();
        //注册
        manager.registerListener(mySensorEventlistener,defaultSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }
    public class MySensorEventlistener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            int value = (int) sensorEvent.values[0];
            if(value>0 && value<90){
                System.out.println("东北");
            }
            if(value>90 && value<180){
                System.out.println("东南");
            }
            if(value>180 && value<270){
                System.out.println("西南");
            }
            if(value>270 && value<360){
                System.out.println("西北");
            }
            if(value==0){
                System.out.println("北");
            }
            if(value==90){
                System.out.println("东");
            }
            if(value==180){
                System.out.println("南");
            }
            if(value==270){
                System.out.println("西");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    }
    //释放
    @Override
    protected void onDestroy() {
        super.onDestroy();
        manager.unregisterListener(mySensorEventlistener);
        mySensorEventlistener=null;
    }
}
