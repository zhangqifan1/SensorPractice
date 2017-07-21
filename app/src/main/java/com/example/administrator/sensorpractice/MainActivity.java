package com.example.administrator.sensorpractice;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager manager;
    private MySensorEventListener mySensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);

        /**
         * 查看所有传感器
         */
        List<Sensor> sensorList = manager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s:sensorList){
            System.out.println(s.getName());
        }
        Sensor defaultSensor = manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mySensorEventListener = new MySensorEventListener();
        //注册传感器 参数1：监听 2.要坚挺的传感器对象 3.决定采用的敏感度  正常
        manager.registerListener(mySensorEventListener,defaultSensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    class MySensorEventListener implements SensorEventListener{
        //传感器数据变化调用的方法
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float value = sensorEvent.values[0];
            System.out.println("光线改变值："+value);
        }
        //传感器精度发生改变调用
        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        manager.unregisterListener(mySensorEventListener);
        mySensorEventListener=null;
    }
}
