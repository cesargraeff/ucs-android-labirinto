package br.ucs.android.vaibola;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private int radius = 25;
    private int[][] m;
    private int l;
    private int c;
    private int fase;
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

//        // your Canvas will draw onto the defined Bitmap
//        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
//        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (fase){
            case 1:
                Paint p = new Paint();
                p.setColor(Color.BLACK);
                drawF1(canvas);
                drawBord(canvas);
                canvas.drawCircle(300, 400, radius + 30, p);
                break;
            case 2:
                break;
            default:
        }
        Paint player = new Paint();
        player.setColor(circleColor);
        canvas.drawCircle(currX, currY, radius, player);



    }

    private void drawBord(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.MAGENTA);
        canvas.drawRect(0, 0, 50, mY, p);
        canvas.drawRect((c - 5) * 10, 0, mX, mY, p);

        canvas.drawRect(0, 0, mX, 50, p);
        canvas.drawRect(0, (l - 5) * 10, mX, mY, p);
    }

    public void setF1() {
        l = mY / 10;
        c = mX / 10;
        m = new int[l][c];

        bord();

        for (int i = 5; i < l - 15; i++) {
            for (int j = 5; j < c - 15; j++) {
                if (j % 40 == 0) {
                    m[i][j] = 1;
                    m[i][j + 1] = 1;
                    m[i][j + 2] = 1;
                }
            }
        }

    }

    private void bord() {
        for (int i = 0; i < l; i++) {
            m[i][0] = 2;
            m[i][1] = 2;
            m[i][2] = 2;
            m[i][3] = 2;
            m[i][4] = 2;


            m[i][c - 1] = 2;
            m[i][c - 2] = 2;
            m[i][c - 3] = 2;
            m[i][c - 4] = 2;
            m[i][c- 5] = 2;
        }

        for (int i = 0; i < c; i++) {
            m[0][i] = 2;
            m[1][i] = 2;
            m[2][i] = 2;
            m[3][i] = 2;
            m[4][i] = 2;

            m[l - 1][i] = 2;
            m[l - 2][i] = 2;
            m[l - 3][i] = 2;
            m[l - 4][i] = 2;
            m[l - 5][i] = 2;
        }
    }

    private void drawF1(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        for (int i = 5; i < l - 5; i++) {
            int top = i * 10;
            int bottom = top + 10;
            for (int j = 5; j < c - 5; j++) {
                int left = j * 10;
                int right = left + 10;
                if (m[i][j] == 1) {
                    canvas.drawRect(left, top, right, bottom, p);
                }
            }
        }


    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public void updateY(int y) {
        this.currY += y;
    }

    public void updateX(int x) {
        this.currX += x;
    }

    public boolean isValidMov(int x, int y){
        return isValidX(x) && isValidY(y);
    }

    public boolean isValidY(int y) {
        int mx = (int) currX/10;
        int my;
        if(y < 0){
            my = (int) (currY + y - radius) / 10;
        }
        else {
            my = (int) (currY + y + radius) / 10;
        }

        if (m[my][mx] == 0) {
           return true;
        }
        return false;
    }

    public boolean isValidX(int x) {
        // esq
        int mx;
        int my = (int) (currY / 10);

        if (x < 0) {
            mx = (int) (currX + x - radius) / 10;
        } else {
            mx = (int) (currX + x + radius) / 10;
        }

        if (m[my][mx] == 0) {
           return true;
        }

        return false;
    }

    public void setmX(int mX) {
        this.mX = mX;
    }

    public void setmY(int mY) {
        this.mY = mY;
    }

    public float getCurrX() {
        return currX;
    }

    public float getCurrY() {
        return currY;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }
}