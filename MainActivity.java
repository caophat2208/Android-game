package com.example.cautruccoban_2174802010770;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        GamePanel d=new GamePanel(this);
        setContentView(d);
        //setContentView(R.layout.activity_main);
    }
    protected void onDestroy(){
        super.onDestroy();
        Log.d("looptest", "huy thread");
    }
}