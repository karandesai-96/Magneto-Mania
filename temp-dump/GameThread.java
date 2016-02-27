package com.sdsmdg.kd.magnetomania;


import android.graphics.Canvas;

public class GameThread extends Thread {

    private boolean running=false;

    private Canvas mGameCanvas;
    private GameView mGameView;

    private int FPS = 50;
    private int frameCount = 0;

    public GameThread(GameView mGameView)
    {
        this.mGameView = mGameView;
        this.mGameCanvas = null;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public void incrementFrameCount() {
        this.frameCount++;
    }

    @Override
    public void run()
    {
        // Number of updates in one second
        final double TARGET_UPS = 40.0;

        // Calculate how many nanoseconds each frame should take for our target Game Hertz
        final double TIME_BETWEEN_UPDATES = 1000000000 / TARGET_UPS;

        // At the very most, the game will be updated this many times before a new render
        final int MAX_UPDATES_BEFORE_RENDER = 1;

        // The last time an update was done
        double lastUpdateTime = System.nanoTime();
        // The last time a render was done
        double lastRenderTime = System.nanoTime();

        final double TARGET_FPS = 60.0;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        // Simple way of finding FPS
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            // Do as many game updates as needed, game catching-up
            while ( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                mGameView.update();
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            // Rendering time now. For this, interpolation is to be calculated for smooth render
            float interpolation = Math.min(1.0f, (float)((now - lastUpdateTime)/TIME_BETWEEN_UPDATES));

            mGameCanvas = null;

            try {
                mGameCanvas = mGameView.getHolder().lockCanvas();
                synchronized (mGameView.getHolder()) {
                    if(mGameCanvas != null)
                        mGameView.draw(mGameCanvas, interpolation);
                }
            }
            finally {
                if (mGameCanvas != null) {
                    mGameView.getHolder().unlockCanvasAndPost(mGameCanvas);
                }
            }

            lastRenderTime = now;

            // Update the frames obtained
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime) {
                FPS = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            // Yield until it has been at least the target time between renders
            while (now - lastUpdateTime < TIME_BETWEEN_UPDATES && now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS) {
                Thread.yield();

                try {
                    Thread.sleep(1);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                now = System.nanoTime();
            }
        }
    }
}