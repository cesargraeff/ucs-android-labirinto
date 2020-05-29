package br.ucs.android.vaibola;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private Bitmap mBitmap;
    private Canvas mCanvas;
    Context context;


    // Record current ball horizontal ordinate.
    private float currX = 100;

    // Record current ball vertical ordinate
    private float currY = 100;

    // This is the circle color.
    private int circleColor = Color.GREEN;

    private int mX;
    private int mY;

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
        canvas.drawRect(100, 100, 200, 250, p1);

        // Círculos
        Paint p2 = new Paint();
        p2.setColor(Color.YELLOW);
        canvas.drawCircle(currX, currY, 35, p2);

        p2.setColor(circleColor);
        canvas.drawCircle(600, 300, 80, p2);

        // Texto
        Paint p3 = new Paint();
        p3.setTextSize(48f);
        p3.setColor(Color.GREEN);
        canvas.drawText("Texto no canvas", 100, 400, p3);

//        // Linhas
//        Paint p4 = new Paint();
//        p4.setStrokeWidth(5f);
//        p4.setColor(Color.RED);
//        canvas.drawLine(100,500, 400, 550,p4);
//        canvas.drawLine(100,500, 420, 650,p4);
//        canvas.drawLine(100,500, 500, 800,p4);


    }

    public float getCurrX() {
        return currX;
    }

    public void setCurrX(float currX) {
        this.currX = currX;
    }

    public float getCurrY() {
        return currY;
    }

    public void setCurrY(float currY) {
        this.currY = currY;
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public void updateY(int y) {
        if (currY + y > 0 && currY + y < mY) {
            this.currY += y;
        }
    }

    public void updateX(int x) {
        if (currX + x > 0 && currX + x < mX) {
            this.currX += x;
        }
    }

    public int getmX() {
        return mX;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public int getmY() {
        return mY;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }
}