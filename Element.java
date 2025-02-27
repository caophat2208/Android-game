package com.example.cautruccoban_2174802010770;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {
    Bitmap bitmap; //hinh anh
    int mX; //toa do x
    int mY; //toa do y

    public Element(Resources res, int x, int y)
    {
        bitmap=BitmapFactory.decodeResource(res,R.drawable.ic_launcher_background);
        mX=x-bitmap.getWidth()/2;
        mY=y-bitmap.getHeight()/2;
    }
    public Element(Resources res, int x, int y, int idHinh)
    {
        bitmap=BitmapFactory.decodeResource(res,idHinh);
        mX=x-bitmap.getWidth()/2;
        mY=y-bitmap.getHeight()/2;
    }
    public void doDraw(Canvas canvas)
    {
        canvas.drawBitmap(bitmap, mX,mY, null);
    }

}
