package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

    private Handler handler;
    private GameObject player;
    private float distX;
    private float distY;
    private float distance;
    
    public SmartEnemy(float x, float y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        
        for(int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).id == ID.Player) {
                this.player = handler.object.get(i);
            }
        }
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        
        distX = x - player.getX();
        distY = y - player.getY();
        distance = (float) Math.sqrt(distX*distX + distY*distY);
        
        velX = (-1 / distance) * distX;
        velY = (-1 / distance) * distY;
        
        handler.addObject(new Trail(x, y, ID.Trail, Color.green, 0.03f, 16, 16, handler));
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int) x,(int) y, 16, 16);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }
    
}