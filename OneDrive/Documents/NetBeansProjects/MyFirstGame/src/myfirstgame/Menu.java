package myfirstgame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.sound.sampled.Clip;
import myfirstgame.Game.MODE;
import myfirstgame.Game.STATE;

public class Menu extends MouseAdapter {

    private Game game;
    private Handler handler;
    private HUD hud;
    public Clip tempt = Sound.play("null");

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        Font f1 = new Font("arial", 1, 50);
        Font f2 = new Font("arial", 1, 30);

        if (game.state == STATE.Menu) {
            g.setColor(Color.white);
            g.setFont(f1);
            g.drawString("Menu", 245, 100);

            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Play", 280, 190);
            g.drawRect(210, 150, 200, 64);

            g.setColor(Color.white);
            g.drawString("Help", 280, 290);
            g.drawRect(210, 250, 200, 64);

            g.setColor(Color.white);
            g.drawString("Quit", 280, 390);
            g.drawRect(210, 350, 200, 64);
        } else if (game.state == STATE.End) {
            g.setColor(Color.white);
            g.setFont(f1);
            g.drawString("Game Over!", 175, 100);

            g.setColor(Color.white);
            g.setFont(f2);
            String literal = "You finished with a score of: " + hud.getScore();
            g.drawString(literal, 100, 200);

            g.setColor(Color.white);
            g.drawString("Try Again", 245, 390);
            g.drawRect(210, 350, 200, 64);
        } else if (game.state == STATE.Select) {
            g.setColor(Color.white);
            g.setFont(f1);
            g.drawString("Difficulty", 210, 100);

            g.setFont(f2);
            g.setColor(Color.white);
            g.drawString("Easy", 275, 190);
            g.drawRect(210, 150, 200, 64);

            g.setColor(Color.white);
            g.drawString("Normal", 260, 290);
            g.drawRect(210, 250, 200, 64);

            g.setColor(Color.white);
            g.drawString("Hard", 270, 390);
            g.drawRect(210, 350, 200, 64);
        }
    }

    private boolean isWithin(int mx, int my, int x, int y, int width, int height) {
        return mx >= x && mx <= x + width && my >= y && my <= y + height;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        // PLAY BUTTON 
        if (isWithin(mx, my, 210, 150, 200, 64)) {
            if (game.state == STATE.Menu) {
                game.state = STATE.Select;
            } else if (game.state == STATE.Select) {
                game.mode = MODE.Easy;
                game.state = STATE.Game;
                handler.addObject(new Player(300, (float) 213, ID.Player, handler, Game.image));
                handler.clearAll();
                handler.addObject(new BouncingEnemy(45, 0, ID.BouncingEnemy, handler));
                Clip bg = game.getbg();
                bg.stop();
                game.clipRunning = false;
                tempt = Sound.play("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/03_TEMPTATION.wav");
            }
        }

        // help button 
        if (isWithin(mx, my, 210, 250, 200, 64)) {
            if (game.state == STATE.Menu) {
                
            } else if (game.state == STATE.Select) {
                game.mode = MODE.Normal;
                game.state = STATE.Game;
                handler.addObject(new Player(300, (float) 213, ID.Player, handler, Game.image));
                handler.clearAll();
                handler.addObject(new BouncingEnemy(45, 0, ID.BouncingEnemy, handler));
                Clip bg = game.getbg();
                bg.stop();
                game.clipRunning = false;
                tempt = Sound.play("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/03_TEMPTATION.wav");
            }
        }

        // quit button
        if (isWithin(mx, my, 210, 350, 200, 64)) {
            if (game.state == STATE.Menu) {
                System.exit(1);
            } else if (game.state == STATE.Select) {
                game.mode = MODE.Hard;
                game.state = STATE.Game;
                handler.addObject(new Player(300, (float) 213, ID.Player, handler, Game.image));
                handler.clearAll();
                handler.addObject(new BouncingEnemy(45, 0, ID.BouncingEnemy, handler));
                Clip bg = game.getbg();
                bg.stop();
                game.clipRunning = false;
                tempt = Sound.play("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/03_TEMPTATION.wav");
            } else if (game.state == STATE.End) {
                game.state = STATE.Select;
                HUD.health = 100;
                hud.setScore(0);
                hud.setLevel(0);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
}
