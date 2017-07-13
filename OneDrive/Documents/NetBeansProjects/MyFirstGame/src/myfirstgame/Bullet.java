
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Bullet extends GameObject{
    private Handler handler;
    private Random rand = new Random();
    
    public Bullet(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = rand.nextInt(10) - 5;
        velY = 3;
        this.handler = handler;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y > Game.H + 50) {
            handler.removeObject(this);
        }
        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 0.05f, 11, 11, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 11, 11);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 11, 11);
    }
}
