/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{

    private Handler handler;
    private float alpha = 1f;
    private Color color;
    private int height, width;
    
    // life should be a float between 0.001 - 0.1;
    private float life;
    
    public Trail(float x, float y, ID id, Color color, float life, int width, int height, Handler handler) {
        super(x, y, id);
        this.life = life;
        this.height = height;
        this.width = width;
        this.color = color;
        this.handler = handler;
    }

    @Override
    public void tick() {
        if (alpha > life) {
            alpha -= life - 0.001f;
        } else {
            handler.removeObject(this);
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        
        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);
        
        g2d.setComposite(makeTransparent(1));
    }

    private AlphaComposite makeTransparent(float alpha) {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    } 
    
    @Override
    public Rectangle bounds() {
        return null;
    }
    
}
