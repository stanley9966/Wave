
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BouncingEnemy extends GameObject{

    private Handler handler;
    
    public BouncingEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
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
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 0.03f, 16, 16, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int)x, (int) y, 16, 16);
    }
    
}
