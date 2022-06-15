package ru.androidtools.patternfill;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
  private DrawingView drawView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    drawView = findViewById(R.id.drawing);
  }

  public void paintClicked(View view) {
    // устанавливаем узор
    String pattern = view.getTag().toString();
    drawView.setPattern(pattern);
  }
}