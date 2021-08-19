package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
public class MainScreen extends AppCompatActivity {
    Animation top,bottom,left;
    TextView cr,gr,slogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_screen);
        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        left = AnimationUtils.loadAnimation(this,R.anim.left_animation);
        cr = findViewById(R.id.crypto);
        gr = findViewById(R.id.graphy);
        slogan = findViewById(R.id.slogan);
        cr.setAnimation(top);
        gr.setAnimation(bottom);
        slogan.setAnimation(left);
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(5000);
                    Intent intent = new Intent(MainScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}