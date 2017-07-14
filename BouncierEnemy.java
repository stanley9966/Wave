
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BouncierEnemy extends GameObject{
    private Handler handler;
    
    public BouncierEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 2;
        velY = 8;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= (Game.H - 48)) {
            velY *= -1;
        }
        if (x <=0 || x >= (Game.W - 24)) {
            velX *= -1;
        }
        handler.addObject(new Trail(x, y, ID.Trail, Color.cyan, 0.03f, 12, 12, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect((int) x, (int) y, 12, 12);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 12, 12);
    }
}
