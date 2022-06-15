package com.example.pr12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = findViewById(R.id.animationView);
        // устанавливаем ресурс анимации
        img.setBackgroundResource(R.drawable.rabit_animation);
        // получаем объект анимации
        AnimationDrawable frameAnimation = (AnimationDrawable) img.getBackground();
        // по нажатию на ImageView
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // запускаем анимацию
                frameAnimation.start();
            }
        });
    }
}