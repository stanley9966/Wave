package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class HardEnemy extends GameObject{
    private Handler handler;
    private Random rand;
    
    public HardEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 5;
        velY = 5;
        this.handler = handler;
        this.rand = new Random();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (y <= 0 || y >= (Game.H - 48)) {
            float temp = velY * -1;
            temp /= Math.abs(temp);
            if (rand.nextInt(2) == 0) {
                temp *= 10;
            } else {
                temp *= 5;
            }
            velY = temp;
        }
        if (x <=0 || x >= (Game.W - 24)) {
            float temp = velX * -1;
            temp /= Math.abs(temp);
            if (rand.nextInt(2) == 0) {
                temp *= 10;
            } else {
                temp *= 5;
            }
            velX = temp;
        }
        handler.addObject(new Trail(x, y, ID.Trail, Color.magenta, 0.03f, 12, 12, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect((int) x, (int) y, 12, 12);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 12, 12);
    }
}
