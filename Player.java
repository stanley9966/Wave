/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class Player extends GameObject{
    
    Handler handler;
    private BufferedImage image;
    
    public Player(float x, float y, ID id, Handler handler, BufferedImage image) {
        super(x, y, id);
        this.handler = handler;
        this.image = image;
    }
    
    @Override
    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp(x, 0, Game.W - 38);
        y = Game.clamp(y, 0, Game.H - 61);
        collision();
    }
    
    private void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.id == ID.BouncingEnemy || temp.id == ID.BouncierEnemy || temp.id == ID.SmartEnemy || temp.id == ID.BossEnemy
                    || temp.id == ID.Bullet) {
            if (this.bounds().intersects(temp.bounds())) {
                HUD.health -= 2;
            }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        //g.setColor(Color.yellow);
        //g.fillRect((int) x, (int) y, 32, 32);
        g.drawImage(image, (int) x, (int) y, null);
    }
    
    @Override
    public Rectangle bounds() {
        return new Rectangle((int) x, (int) y, 32, 32);
    }
    
}
