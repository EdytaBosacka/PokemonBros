package pokemon.mario;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import pokemon.mario.entity.Entity;
import pokemon.mario.entity.Player;
import pokemon.mario.graphics.Sprite;
import pokemon.mario.graphics.SpriteSheet;
import pokemon.mario.graphics.gui.Launcher;
import pokemon.mario.input.KeyInput;
import pokemon.mario.input.MouseInput;
import pokemon.mario.tile.Wall;

public class Main extends Canvas implements Runnable {

	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH * 2 / 4;
	public static final int SCALE = 3;
	public static final int DEATHTIME = 180;
	public static final String TITLE = "Pokemon bros";
	
	private static BufferedImage[] levels = new BufferedImage[2]; 

	private Thread thread;
	private static Handler handler;
	private static Camera camera;
	private static Launcher launcher;
	
	private static int playerX, playerY;
	private static int level = 0;

	private static int coins = 0;
	private static int lives = 5;
	private static int deathScreenTimer = 0;

	private static boolean deathScreen = true;
	private static boolean gameover = false;
	private static boolean playing = false;

	private static SpriteSheet sheet;
	private static SpriteSheet groundsheet;
	private static SpriteSheet playersheet;
	private static SpriteSheet mushroomsheet;
	private static SpriteSheet charmandersheet;
	private static SpriteSheet squirtlesheet;
	private static SpriteSheet squirtleshellsheet;
	private static SpriteSheet powerupsheet;
	private static SpriteSheet usedpowerupsheet;
	private static SpriteSheet pipesheet;
	private static SpriteSheet victreebelsheet;
	private static SpriteSheet coinsheet;
	private static SpriteSheet bosssheet;
	private static SpriteSheet upstarsheet;
	private static SpriteSheet bluestarsheet;
	private static SpriteSheet flagsheet;

	public static Sprite grass;
	public static Sprite ground;
	public static Sprite ground2;
	public static Sprite ground3;
	public static Sprite powerUp;
	public static Sprite usedPowerUp;
	public static Sprite player[] = new Sprite[20];
	public static Sprite mushroom;
	public static Sprite[] charmander = new Sprite[54];
	public static Sprite[] squirtle = new Sprite[10];
	public static Sprite squirtleshell;
	public static Sprite pipe;
	public static Sprite victreebel[] = new Sprite[8];
	public static Sprite coin;
	public static Sprite[] flag = new Sprite[3];
	public static Sprite[] boss = new Sprite[4];
	public static Sprite upstar;
	public static Sprite bluestar;

	private boolean running = false;
	public static BufferedImage backgroundmenu;

	private synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread(this, "thread");
		thread.start();

	}

	public static Handler getHandler() {
		return handler;
	}

	public void initialize() {
		
		addKeyListener(new KeyInput());
		addMouseListener(new MouseInput());
		
		handler = new Handler();
		camera = new Camera();
		launcher = new Launcher();
		
		sheet = new SpriteSheet("/wall.png");
		groundsheet = new SpriteSheet("/ground.png");
		playersheet = new SpriteSheet("/ash.png");
		mushroomsheet = new SpriteSheet("/kanto.png");
		charmandersheet = new SpriteSheet("/charmander.png");
		squirtlesheet = new SpriteSheet("/squirtle.png");
		squirtleshellsheet = new SpriteSheet("/shell.png");
		powerupsheet = new SpriteSheet("/powerUp.jpg");
		usedpowerupsheet = new SpriteSheet("/usedPowerUp.jpg");
		pipesheet = new SpriteSheet("/pipe.png");
		victreebelsheet = new SpriteSheet("/Victreebel.png");
		coinsheet = new SpriteSheet("/pokeball.png");
		flagsheet = new SpriteSheet("/flag.png");
		bosssheet = new SpriteSheet("/charizard.png");
		upstarsheet = new SpriteSheet("/1upstar.png");
		bluestarsheet = new SpriteSheet("/powerupstar.png");
		
		try {
			backgroundmenu = ImageIO.read(getClass().getResource("/menu.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		grass = new Sprite(sheet, 2, 1);
		ground = new Sprite(groundsheet, 1, 1);
		mushroom = new Sprite(mushroomsheet, 1, 1);
		squirtleshell = new Sprite(squirtleshellsheet,1,1);
		powerUp = new Sprite(powerupsheet, 1, 1);
		usedPowerUp = new Sprite(usedpowerupsheet, 1, 1);
		pipe = new Sprite(pipesheet, 1, 1);
		coin = new Sprite(coinsheet, 1, 1);
		upstar = new Sprite(upstarsheet,1,1);
		bluestar = new Sprite(bluestarsheet,1,1);

		int picture_counter = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 5; j++) {
				player[picture_counter] = new Sprite(playersheet, j, i);
				picture_counter++;
			}

		}
		picture_counter = 0;
		for (int i = 1; i <= 6; i++) {
			for (int j = 1; j <= 9; j++) {
				charmander[picture_counter] = new Sprite(charmandersheet, j, i);
				picture_counter++;
			}

		}
		picture_counter = 0;
			for (int j = 1; j <= 10; j++) {
				squirtle[picture_counter] = new Sprite(squirtlesheet, j, 1);
				picture_counter++;
			}
			
		picture_counter = 0;
			for (int j = 1; j <= 8; j++) {
				victreebel[picture_counter] = new Sprite(victreebelsheet, j, 1);
				picture_counter++;
			}
		picture_counter = 0;
			for (int j = 1; j <= 3; j++) {
				flag[picture_counter] = new Sprite(flagsheet, 1, j);
				picture_counter++;
			}

		
		picture_counter = 0;
		for (int i = 1; i <= 2; i++) {
			for (int j = 1; j <= 2; j++) {
				boss[picture_counter] = new Sprite(bosssheet, j, i);
				picture_counter++;
			}

		}

		try {
			for(int i = 1; i<=levels.length;i++) {
			levels[i-1] = ImageIO.read(getClass().getResource("/level" + i +".png"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		initialize();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.00;
		double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int updates = 0;

		while (running) {
			long now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			draw();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames + "frames per second" + updates + "Updates per second");
				frames = 0;
				updates = 0;
			}
		}

	}

	public void draw() {
		BufferStrategy buf = getBufferStrategy();
		if (buf == null) {
			createBufferStrategy(4);
			return;
		}
		Graphics graph = buf.getDrawGraphics();
		graph.setColor(new Color(135, 206, 235));
		graph.fillRect(0, 0, getWidth(), getHeight());
		if (!deathScreen) {
			graph.drawImage(coin.getBufferedImage(), 20, 20, 64, 64, null);
			graph.setColor(Color.WHITE);
			graph.setFont(new Font("Courier", Font.BOLD, 20));
			graph.drawString("x" + coins, 90, 80);
		} else {

			if (!gameover) {
				graph.drawImage(player[3].getBufferedImage(), 500, 250, 70, 70, null);
				graph.setColor(Color.WHITE);
				graph.setFont(new Font("Courier", Font.BOLD, 20));
				graph.drawString("x" + lives, 570, 290);
			} else {
				graph.setColor(Color.WHITE);
				graph.setFont(new Font("Courier", Font.BOLD, 50));
				graph.drawString("Game over", 450, 290);
			}
		}

		if (playing) {
			graph.translate(camera.getX(), camera.getY());
		}
		if (!deathScreen && playing) {
			handler.draw(graph);
		}else if(!playing) {
			launcher.draw(graph);
		}
		graph.dispose();
		buf.show();

	}

	public void update() {
		if (playing) {
			handler.update();
		}
		for (int i = 0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if (e.getId() == ID.player) {
				if (!e.goingDownPipe)
					camera.update(e);
			}
		}
		if (deathScreen && !gameover&& playing) {
			deathScreenTimer++;
			if (deathScreenTimer >= DEATHTIME) {
				deathScreen = false;
				deathScreenTimer = 0;
				handler.clearLevel();
				handler.createLevel(levels[level]);

			}
		}

	}

	public static int getFrameWidth() {
		return WIDTH * SCALE;
	}

	public static int getFrameHeight() {
		return HEIGHT * SCALE;
	}
	
	public static Rectangle getVisibleArea() {
		for(int i=0; i < handler.entity.size(); i++) {
			Entity e = handler.entity.get(i);
			if(e.getId() == ID.player) {
				if(!e.goingDownPipe) {
				playerX = e.getX();
				playerY = e.getY();
				
				return new Rectangle(playerX-getFrameWidth()/2-5,playerY-getFrameHeight()/2-5, getFrameWidth()+10, getFrameHeight()+10);
				}
			}
		}
		return new Rectangle(playerX-getFrameWidth()/2-5,playerY-getFrameHeight()/2-5, getFrameWidth()+10, getFrameHeight()+10);
	}

	private synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Main() {

		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public static void main(String[] args) {
		Main object = new Main();
		JFrame frame = new JFrame(TITLE);
		frame.add(object);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		object.start();
	}
	
	public static void switchLevel() {
		level++;
		
		handler.clearLevel();
		handler.createLevel(levels[level]);
		
	}

	public static int getCoins() {
		return coins;
	}

	public static void setCoins(int coins) {
		Main.coins = coins;
	}

	public static int getLives() {
		return lives;
	}

	public static void setLives(int lives) {
		Main.lives = lives;
	}

	public static boolean isDeathScreen() {
		return deathScreen;
	}

	public static void setDeathScreen(boolean deathScreen) {
		Main.deathScreen = deathScreen;
	}

	public static boolean isGameover() {
		return gameover;
	}

	public static void setGameover(boolean gameover) {
		Main.gameover = gameover;
	}

	public static Launcher getLauncher() {
		return launcher;
	}

	public static boolean isPlaying() {
		return playing;
	}

	public static void setPlaying(boolean playing) {
		Main.playing = playing;
	}

}
