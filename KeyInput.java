
package myfirstgame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import myfirstgame.Game.STATE;

public class KeyInput extends KeyAdapter{
    private Handler handler;
    private boolean[] tf = new boolean[4];
    private Game game;
    
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        tf[0] = false;
        tf[1] = false;
        tf[2] = false;
        tf[3] = false;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_SPACE) {
            if (game.state == STATE.Game) {
                game.paused = !game.paused;
            }
        }
        
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP) {
                    temp.setVelY(-5);
                    tf[0] = true;
                }
                if(key == KeyEvent.VK_DOWN) {
                    temp.setVelY(5);
                    tf[1] = true;
                }
                if(key == KeyEvent.VK_LEFT) {
                    temp.setVelX(-5);
                    tf[2] = true;
                }
                if(key == KeyEvent.VK_RIGHT) {
                    temp.setVelX(5);
                    tf[3] = true;
                }
        }
    }
    }
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject temp = handler.object.get(i);
            if (temp.getId() == ID.Player) {
                if(key == KeyEvent.VK_UP) tf[0] = false;
                if(key == KeyEvent.VK_DOWN) tf[1] = false;
                if(key == KeyEvent.VK_LEFT) tf[2] = false;
                if(key == KeyEvent.VK_RIGHT) tf[3] = false;
                if (!tf[0] && !tf[1]) {
                    temp.setVelY(0);
                }
                if (!tf[2] && !tf[3]) {
                    temp.setVelX(0);
            } 
        }
    }
}
}