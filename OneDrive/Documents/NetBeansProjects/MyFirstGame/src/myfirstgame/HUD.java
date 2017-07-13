/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myfirstgame;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    public static int health = 100;
    private int greenvalue = 255;
    private int score = 0;
    private int level = 1;
    
    public void tick() {
        health = (int) Game.clamp(health, 0, 100);
        Game.clamp(greenvalue, 0, 255);
        greenvalue = health * 2;
        
        score++;
    }
    
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(50, greenvalue, 0));
        g.fillRect(15, 15, health * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level " + level, 15, 80);
    }
    
    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level = level;
    }

    public void setScore(int i) {
        score = i;
    }

}
