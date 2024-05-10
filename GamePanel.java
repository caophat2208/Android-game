package com.example.cautruccoban_2174802010770;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    Bitmap bitmap;
    int mX;
    int mY;
    ParallaxBackground background;
    ArrayList<Bullet> bullets=new ArrayList<Bullet>();
    int thoigiannapdan=0;
    ArrayList<Enemies> enemies=new ArrayList<Enemies>();
    int thoigiankethu=0;//thoi gian ra ke thu, 10 se ra
    Enemies motkethu;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread=new MainThread(getHolder(),this);
        background=new ParallaxBackground(this.getResources());

        setFocusable(true);
        bitmap= BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_launcher_background);
    }
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(bitmap, mX, mY, null);
        background.doDrawRunning(canvas);
        thoigiannapdan++;
        Paint p = new Paint();
        canvas.drawRect(10,10, thoigiannapdan*10, 20, p);
        thoigiankethu++;

        if(myelement!=null) {
            myelement.doDraw(canvas);
            this.doDrawBullet(canvas);
            this.doDrawEnemies(canvas);
            xetvacham(canvas);
        }
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).doDraw(canvas);
        for(int i=0;i<bullets.size();i++)
            if(bullets.get(i).x>canvas.getWidth())
                bullets.remove(i);

        Log.d("viendan","so vien: "+bullets.size());
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder arg0) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder arg0) {
        if(thread.isAlive())
            thread.setRunning(false);
    }

    Element myelement;
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(myelement==null)
        {
            myelement=new Element(getResources(),(int)event.getX(),(int)event.getY());
            Log.d("abc","khoi tao dau tien");
            return true;
        }
        else
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
        }

        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","ddddddddddddddddddddddddddddown");
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","uuuuuuuuuuuuuuuuuuuuuuuuuuuup");
        }
        if(event.getAction()==MotionEvent.ACTION_MOVE)
        {
            myelement.mX=(int)event.getX()-myelement.bitmap.getWidth()/2;
            myelement.mY=(int)event.getY()-myelement.bitmap.getHeight()/2;
            Log.d("abc","mmmmmmmmmmmmmmmmmmmmmmmmmmove");
        }

        return true;//super.onTouchEvent(event);

    }
    public void doDrawBullet(Canvas canvas)
    {
        Paint p=new Paint();
        p.setColor(Color.WHITE);
        p.setTextSize(20);
        canvas.drawText("napdan:"+thoigiannapdan, 20, 20,p);

        if(thoigiannapdan>=10)
        {
            thoigiannapdan=0;
            Bullet motviendan=
                    new Bullet(getResources(), myelement.mX, myelement.mY,R.drawable.hinhlua);

            bullets.add(motviendan);
        }
        for(int i=0;i<bullets.size();i++)
            bullets.get(i).doDraw(canvas);

    }
    public void doDrawEnemies(Canvas canvas)
    {
        if(thoigiankethu>=10)
        {
            thoigiankethu=0;
            Enemies motkethu=new Enemies(getResources(),
                    canvas.getWidth(),canvas.getHeight());
            enemies.add(motkethu);
        }
        for(int i=0;i<enemies.size();i++)
            enemies.get(i).doDraw(canvas);

        for(int i=0;i<enemies.size();i++)
            if(enemies.get(i).y<0)
                enemies.remove(i);
        Log.d("viendan","so vien: "+enemies.size());
    }

    public boolean vc_b_e(Bullet bullet,Enemies enemies)
    {

        float nuarong_b=(float)bullet.getWidth()/2;
        int nuacao_b=bullet.getHeight()/2;
        float nuarong_e=(float)enemies.getWidth()/2;
        int nuacao_e=enemies.getHeight()/2;
        //khoang cach 2 tam theo x
        int kc_ht_x=Math.abs(bullet.gettamX()-enemies.gettamX());
        //khoang cach 2 tam theo y
        int kc_ht_y=Math.abs(bullet.gettamY()-enemies.gettamY());
        if(kc_ht_x<=nuarong_b+nuarong_e && kc_ht_y<=nuacao_b+nuacao_e)
            return true;
        else
            return false;
    }
    public  void xetvacham(Canvas canvas)
    {
        try{
            for(int i=0;i<bullets.size();i++)
                for(int j=0;j<enemies.size();j++)
                {
                    if(vc_b_e(bullets.get(i), enemies.get(j))==true)
                    {
                        bullets.remove(i);
                        enemies.remove(j);
                    }
                }
        }catch(Exception e)
        {
            Log.d("loi",e.toString());
        }

    }


}
