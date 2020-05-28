package br.ucs.android.exemplocanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Path;

public class CanvasView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
  }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Retângulo
        Paint p1 = new Paint();
        p1.setColor(Color.BLUE);
        canvas.drawRect(100, 100, 200,250,p1);

        // Círculos
        Paint p2 = new Paint();
        p2.setColor(Color.YELLOW);
        canvas.drawCircle(300, 300, 35,p2);
        p2.setColor(Color.DKGRAY);
        canvas.drawCircle(600, 300, 80,p2);

        // Texto
        Paint p3 = new Paint();
        p3.setTextSize(48f);
        p3.setColor(Color.GREEN);
        canvas.drawText("Texto no canvas",100, 400, p3);

        // Linhas
        Paint p4 = new Paint();
        p4.setStrokeWidth(5f);
        p4.setColor(Color.RED);
        canvas.drawLine(100,500, 400, 550,p4);
        canvas.drawLine(100,500, 420, 650,p4);
        canvas.drawLine(100,500, 500, 800,p4);



    }

    public void clearCanvas() {
        invalidate();
    }

}