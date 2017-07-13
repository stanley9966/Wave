
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BossEnemy extends GameObject{
    private Handler handler;
    private boolean timer = true;
    private int ticker = 1;
    
    public BossEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 0;
        velY = 2;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (timer) {
            if (y >= 20) { 
                timer = false;
                velY = 0;
                velX = 2;
            }
        }
        if (x <=0 || x >= (Game.W - 95)) {
            velX *= -1;
        }
        if (ticker == 4) {
            handler.addObject(new Bullet((float) x + 20, (float) y + 20, ID.Bullet, handler));
            ticker = 1;
        } else {
            ticker++;
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 95, 95);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 95, 95);
    }
}
