package myfirstgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.sound.sampled.Clip;

public class Game extends Canvas implements Runnable {

    public static BufferedImage image;
    
    public static final int W = 640;
    public static final int H = W / 12 * 9;

    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private Spawner spawner;
    STATE state;
    MODE mode;
    private Menu menu;
    private Random rand = new Random();

    public boolean clipRunning;
    private Clip bg;

    boolean paused = false;

    public enum MODE {
        Easy, 
        Normal, 
        Hard,
    }
    
    public enum STATE {
        Game,
        Menu,
        End,
        Select,
    };

    // constructor
    public Game() {
        image = BufferedImageLoader.load("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/smiley.jpg");
        state = STATE.Menu;

        handler = new Handler(this);
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        bg = Sound.play("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/pacman.wav");
        clipRunning = true;

        new Window(W, H, "The Game", this);
        spawner = new Spawner(hud, handler, this);
        

        for (int i = 0; i < 20; i++) {
            int x = rand.nextInt(Game.W - 10);
            int y = rand.nextInt(Game.H - 10);
            handler.addObject(new MenuParticle(x, y, ID.MenuParticle, handler));
        }

    }

    public Clip getbg() {
        return bg;
    }

    // called in the window file, which in turn is called in the game constructor
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // because the game class implements Runnable
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {
        
        if (state == STATE.Game) {
            if (!paused) {
                handler.tick();
                hud.tick();
                spawner.tick();

                if (HUD.health <= 0) {
                    state = STATE.End;
                    handler.clearAll();
                    for (int i = 0; i < 20; i++) {
                        int x = rand.nextInt(Game.W - 10);
                        int y = rand.nextInt(Game.H - 10);
                        handler.addObject(new MenuParticle(x, y, ID.MenuParticle, handler));
                    }
                }
            }
        } else if (state == STATE.Menu || state == STATE.End || state == STATE.Select) { 
            handler.tick();
            menu.tick();
            if (!clipRunning) {
                bg = Sound.play("C:/Users/stanl/OneDrive/Documents/NetBeansProjects/MyFirstGame/pacman.wav");
                clipRunning = true;
            }

            menu.tempt.stop();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, W, H);
        handler.render(g);

        if (state == STATE.Game) {
            hud.render(g);
            if (paused) {
                g.setColor(Color.white);
                g.drawString("PAUSED", 100, 100);
            }
        } else if (state == STATE.Menu || state == STATE.End || state == STATE.Select) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max) {
        if (var <= min) {
            return min;
        } else if (var >= max) {
            return max;
        } else {
            return var;
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}
