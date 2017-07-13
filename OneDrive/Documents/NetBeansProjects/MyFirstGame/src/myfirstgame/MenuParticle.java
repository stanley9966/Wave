package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MenuParticle extends GameObject{
    private Handler handler;
    private Random rand = new Random();
    private int r;
    private int g;
    private int b;
    private Color c;
    
    public MenuParticle(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        velX = 2;
        velY = 8;
        this.handler = handler;
        this.r = rand.nextInt(255);
        this.g = rand.nextInt(255);
        this.b = rand.nextInt(255);
        this.c = new Color(r, g, b);
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
        handler.addObject(new Trail(x, y, ID.Trail, c, 0.03f, 12, 12, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(c);
        g.fillRect((int) x, (int) y, 12, 12);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 12, 12);
    }
}