package ru.androidtools.patternfill;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
  // путь для рисования
  private Path drawPath;
  // Paint для рисования и для холста
  private Paint drawPaint, canvasPaint;
  // начальный цвет
  private int paintColor = 0xFF000000;
  // холст
  private Canvas drawCanvas;
  // битмап холста
  private Bitmap canvasBitmap;

  public DrawingView(Context context, AttributeSet attrs) {
    super(context, attrs);
    setupDrawing();
  }

  private void setupDrawing() {
    drawPath = new Path();
    drawPaint = new Paint();
    drawPaint.setColor(paintColor);
    drawPaint.setAntiAlias(true);
    drawPaint.setStrokeWidth(50);
    drawPaint.setStyle(Paint.Style.STROKE);
    drawPaint.setStrokeJoin(Paint.Join.ROUND);
    drawPaint.setStrokeCap(Paint.Cap.ROUND);
    canvasPaint = new Paint(Paint.DITHER_FLAG);
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    drawCanvas = new Canvas(canvasBitmap);
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
    canvas.drawPath(drawPath, drawPaint);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    float touchX = event.getX();
    float touchY = event.getY();
    // реакция на события "палец на экране", "движение по экрану" и "палец поднят"
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        drawPath.moveTo(touchX, touchY);
        break;
      case MotionEvent.ACTION_MOVE:
        drawPath.lineTo(touchX, touchY);
        break;
      case MotionEvent.ACTION_UP:
        drawPath.lineTo(touchX, touchY);
        drawCanvas.drawPath(drawPath, drawPaint);
        drawPath.reset();
        break;
      default:
        return false;
    }
    // перерисовать
    invalidate();
    return true;
  }

  public void setPattern(String newPattern) {
    // устанавливаем узор
    invalidate();
    int patternID =
        getResources().getIdentifier(newPattern, "drawable", BuildConfig.APPLICATION_ID);
    Bitmap patternBMP = BitmapFactory.decodeResource(getResources(), patternID);
    BitmapShader patternBMPshader =
        new BitmapShader(patternBMP, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
    drawPaint.setColor(0xFFFFFFFF);
    drawPaint.setShader(patternBMPshader);
  }
}