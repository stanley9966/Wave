package myfirstgame;

import java.util.Random;
import myfirstgame.Game.MODE;

public class Spawner {

    private HUD hud;
    private Handler handler;
    private Random rand = new Random();
    private Game game;

    private int scorekeeper = 0;

    public Spawner(HUD hud, Handler handler, Game game) {
        this.hud = hud;
        this.handler = handler;
        this.game = game;
    }

    public void tick() {
        scorekeeper++;
        if (scorekeeper >= 100) {
            scorekeeper = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (game.mode == MODE.Easy) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new BouncingEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncingEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BouncierEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncierEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new SmartEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearAll();
                    handler.addObject(new BossEnemy(270, -95, ID.BossEnemy, handler));
                }
            } else if (game.mode == MODE.Normal) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new BouncingEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncingEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BouncierEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncierEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new SmartEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearAll();
                    handler.addObject(new BossEnemy(270, -95, ID.BossEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new HardEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.HardEnemy, handler));
                }
            } else if (game.mode == MODE.Hard) {
                if (hud.getLevel() == 2) {
                    handler.addObject(new BouncingEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncingEnemy, handler));
                } else if (hud.getLevel() == 3) {
                    handler.addObject(new BouncierEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncierEnemy, handler));
                    handler.addObject(new BouncierEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncierEnemy, handler));
                    handler.addObject(new BouncierEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.BouncierEnemy, handler));
                } else if (hud.getLevel() == 4) {
                    handler.addObject(new SmartEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.SmartEnemy, handler));
                    handler.addObject(new SmartEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.SmartEnemy, handler));
                } else if (hud.getLevel() == 10) {
                    handler.clearAll();
                    handler.addObject(new BossEnemy(270, -95, ID.BossEnemy, handler));
                } else if (hud.getLevel() == 5) {
                    handler.addObject(new HardEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.HardEnemy, handler));
                    handler.addObject(new HardEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.HardEnemy, handler));
                    handler.addObject(new HardEnemy(rand.nextInt(Game.W - 50), rand.nextInt(Game.H - 50), ID.HardEnemy, handler));
                }
            }
        }
    }
}
