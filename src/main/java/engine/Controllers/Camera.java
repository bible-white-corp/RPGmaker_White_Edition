package engine.Controllers;

import java.awt.*;

public class Camera implements Runnable{

    Point first = new Point(0,0);
    Point second = new Point(360,250);

    public Camera(){

        start();
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
    }

    Point focus = new Point(0,0);
    float zoom = 1f;
    float ratio = 1f;

    Point target_focus = focus;
    float target_zoom = zoom;
    float target_ratio = ratio;

    public Point getFocus() {
        return focus;
    }

    public float getZoom() {
        return zoom;
    }

    public float getRatio() {
        return ratio;
    }

    public void setFocus(Point new_focus)
    {
        target_focus = new_focus;
    }

    public void setZoom(float new_zoom)
    {
        target_zoom = new_zoom;
    }

    public void setRatio(float new_ratio)
    {
        target_ratio = new_ratio;
    }

    private void computeCamera()
    {
        int width = (int) (320 * zoom);
        int height = (int) (width * ratio);

        first.x = focus.x - width;
        first.y = focus.y - height;

        second.x = focus.x + width;
        second.y = focus.y + height;
    }


    Thread thread = null;

    public void start() {

        if (thread == null) {

            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {

        thread = null;
    }

    @Override
    public void run() {

        while (thread != null) {

            try
            {
                Thread.sleep(20);
            }
            catch (InterruptedException e)
            {

            }

            if (Math.abs(zoom - target_zoom) > 0.01)
                zoom -= 0.1 * (zoom - target_zoom);

            if (Math.abs(ratio - target_ratio) > 0.1)
                ratio -= 0.1 * (ratio - target_ratio);

            if (Math.hypot(focus.x - target_focus.x, focus.y - target_focus.y) > 0.1)
            {
                focus.x -= 0.1 * (focus.x - target_focus.x);
                focus.y -= 0.1 * (focus.y - target_focus.y);
            }

            computeCamera();
        }
    }
}
