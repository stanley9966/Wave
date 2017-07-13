
package myfirstgame;

import java.awt.Graphics;
import java.util.LinkedList;
import myfirstgame.Game.STATE;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<>();  
    private Game game;
    
    
    public Handler(Game game) {
        this.game = game;
    }
    
    public void tick() {
        for(int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            temp.tick();
        }
    }
    
    public void render(Graphics g) {
        try{
            for(int i = 0; i < object.size(); i++) {
                GameObject temp = object.get(i);
                temp.render(g);
            }
        } catch (Exception e) {
            
        }
    }
    
    public void addObject(GameObject object) {
        this.object.add(object);
    }
    
    public void removeObject(GameObject object) {
        this.object.remove(object);
    }
    
    public void clearAll() {
        for(int i = 0; i < object.size(); i++) {
            GameObject temp = object.get(i);
            if (game.state == STATE.End) {
                object.clear();
                break;
            }
            
            else if (temp.id == ID.Player) {
                object.clear();
                addObject(new Player(temp.x, temp.y, ID.Player, this, Game.image));
                break;
            }
        }
    }
}
