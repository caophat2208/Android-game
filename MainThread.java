package com.example.cautruccoban_2174802010770;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread{
    private SurfaceHolder surfaceholder;
    private GamePanel gamepanel;
    private boolean running;

    public MainThread(SurfaceHolder surfaceholder, GamePanel gamepanel)
    {
        this.surfaceholder=surfaceholder;
        this.gamepanel=gamepanel;
    }
    public void setRunning(boolean r)
    {
        running=r;
    }

    @Override
    public void run(){
        long dem=0L;
        super.run();
        Canvas canvas=null;
        while (running)
        {
            canvas=surfaceholder.lockCanvas();
            if(canvas!=null)
            {
                gamepanel.draw(canvas);
                surfaceholder.unlockCanvasAndPost(canvas);
            }
            Log.d("testloop:", "loop"+ dem);
        }
    }
}
