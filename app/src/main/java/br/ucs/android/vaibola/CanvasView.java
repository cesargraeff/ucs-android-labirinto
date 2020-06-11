package br.ucs.android.vaibola;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CanvasView extends View {

    private int radius = 25; //  raio da peolota
    private float mr = 1.5f; // raio maximo usado para saida de nivel
    private int[][] m; // matriz
    private int l; // numero de linhas
    private int c; // numero de colunas
    private int fase; // fase atual
    Context context;
    private float currX = 100; // pos atual x
    private float currY = 100; // pos atual Y

    // This is the circle color.
    private int circleColor = Color.GREEN;

    private int mX; // tamnanho tele
    private int mY; // tamnanho tele

    private float fx;
    private float fy;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLACK);

        Paint p3 = new Paint();
        p3.setColor(Color.BLACK);
        p3.setTextSize(50);

        switch (fase) {
            case 1:
            case 2:
            case 3:
                drawMatrix(canvas);
                drawBord(canvas);
                canvas.drawCircle(fx, fy, radius * mr, p);
                break;
            default:
                drawBord(canvas);
                canvas.drawText("PARA REINICIAR APERTE VOLTAR", 30, mY/2, p3);
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

        Paint p3 = new Paint();
        p3.setTextSize(30);
        p3.setColor(Color.GREEN);
        canvas.drawText("Fase: " + fase, 30, 30, p3);
        canvas.drawText("PARA REINICIAR APERTE VOLTAR", 30, mY - 25, p3);
    }

    public void setMatrixFase(int fase) {
        this.currX = (100);
        this.currY = (100);

        this.fase = fase;
        int i;
        switch (fase) {
            case 1:
                this.clearM();
                this.bord();

                this.fx = (c - 5) * 10 - radius * mr;
                this.fy = (l - 5) * 10 - radius * mr;

                i = 15;
                for (int j = 5; j < l - 15; j++) {
                    m[j][i] = 1;
                    m[j][i + 1] = 1;
                    m[j][i + 2] = 1;
                }

                i = c / 2 - 2;
                for (int j = 20; j < l; j++) {
                    m[j][i] = 1;
                    m[j][i + 1] = 1;
                    m[j][i + 2] = 1;
                }

                i = c - 30;
                for (int j = 5; j < l - 15; j++) {
                    m[j][i] = 1;
                    m[j][i + 1] = 1;
                    m[j][i + 2] = 1;
                }

                break;
            case 2:
                this.clearM();
                this.bord();

                this.fx = (c - 5) * 10 - radius * mr;
                this.fy = (l - 5) * 10 - radius * mr;

                i = 15;
                for (int j = 5; j < c - 15; j++) {
                    m[i][j] = 1;
                    m[i + 1][j] = 1;
                    m[i + 2][j] = 1;
                }

                i = l / 2 - 2;
                for (int j = 15; j < c - 1; j++) {
                    m[i][j] = 1;
                    m[i + 1][j] = 1;
                    m[i + 2][j] = 1;
                }

                i = l - 20;
                for (int j = 5; j < c - 15; j++) {
                    m[i][j] = 1;
                    m[i + 1][j] = 1;
                    m[i + 2][j] = 1;
                }

                break;
            case 3:
                this.clearM();
                this.bord();

                this.currY = mY - 100;
                this.currX = 100;

                this.fx = (c - 5) * 10 - radius * mr;
                this.fy = (l - 5) * 10 - radius * mr;

                i = 15;
                for (int j = 15; j < l; j++) {
                    m[j][i] = 1;
                    m[j][i + 1] = 1;
                    m[j][i + 2] = 1;
                }

                i = 15;
                for (int j = 15; j < c - 20; j++) {
                    m[i][j] = 1;
                    m[i + 1][j] = 1;
                    m[i + 2][j] = 1;
                }

                i = c - 20;
                for (int j = 15; j < l; j++) {
                    m[j][i] = 1;
                    m[j][i + 1] = 1;
                    m[j][i + 2] = 1;
                }
                break;
            default:
                this.clearM();
                this.bord();
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
            m[i][c - 5] = 2;
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

    private void drawMatrix(Canvas canvas) {
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

    public boolean isValidMov(int x, int y) {
        return isValidX(x) && isValidY(y);
    }

    public boolean isValidY(int y) {
        int mx = (int) currX / 10;
        int my;
        if (y < 0) {
            my = (int) (currY + y - radius) / 10;
        } else {
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

    public float getMaxFx() {
        return this.fx + radius * mr;
    }

    public float getMinFx() {
        return this.fx - radius * mr;
    }

    public float getMaxFy() {
        return this.fy + mr * radius;
    }

    public float getMinFy() {
        return this.fy - mr * radius;
    }

    public void setCurrX(float currX) {
        this.currX = currX;
    }

    public void setCurrY(float currY) {
        this.currY = currY;
    }

    public void clearM() {
        l = mY / 10;
        c = mX / 10;
        m = new int[l][c];

        for (int i = 0; i < this.l; i++) {
            for (int j = 0; j < this.c; j++) {
                this.m[i][j] = 0;
            }
        }
    }
}