package engine.Controllers;

import editor.Maps.Level;
import engine.Engine;

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

    private void computeCamera() {
        Level l = Engine.world.levelList.get(Engine.getEngineFrame().getDisplay().getLevel());

        int width = (int) (320 * zoom);
        int height = (int) (width * ratio);

        int pixel_width = l.getWidth() * l.getTileWidth();
        int pixel_height = l.getHeight() * l.getTileHeight();

        first.x = focus.x - width;
        first.y = focus.y - height;

        if (pixel_width > width * 2) {

            if (first.x < 0)
                first.x = 0;

            second.x = first.x + 2 * width;

            if (second.x >= pixel_width)
                second.x = pixel_width - 1;

            first.x = second.x - 2 * width;
        }
        else
            second.x = first.x + 2 * width;

        if (pixel_height > height * 2) {

            if (first.y < 0)
                first.y = 0;

            second.y = first.y + 2 * height;

            if (second.y >= pixel_height)
                second.y = pixel_height - 1;

            first.y = second.y - 2 * height;
        }
        else
            second.y = first.y + 2 * height;

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
