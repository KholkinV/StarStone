package org.jnity.starstone.gui;

import base.Camera;
import base.Object3d;
import base.Scene;
import materials.Texture2D;
import org.jnity.starstone.cards.Card;
import org.jnity.starstone.cards.CreatureCard;
import org.jnity.starstone.core.Game;
import org.jnity.starstone.core.Player;
import org.jnity.starstone.core.TextHolder;
import org.jnity.starstone.events.GameEvent;
import org.jnity.starstone.events.GameListener;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GameGui extends Thread implements GameListener {

	public static final String SUMMON_SICK = "SUMMON_SICK";
	public static final String AFTER_ATACK = "AFTER_ATACK";
	public static final String CANT_ATACK = "CANT_ATACK";
	
	public static final String NEED_MORE_RESOURSES = "NEED_MORE_RESOURSES";
	private final ConcurrentLinkedQueue<StoredEvent> events = new ConcurrentLinkedQueue<>();
	private Game game;
	private Player our_player;
	private MouseProcess mouseProcess;
	private Animation animation;

	public GameGui(Game game) throws IOException {
		this.game = game;
		game.addListener(this);
		start();
	}

	@Override
	public void run() {
		try {
			GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			int width = gd.getDisplayMode().getWidth();
			int height = gd.getDisplayMode().getHeight();
			Scene scene = new Scene();
			width = 1024;
			height = 768;
			Utils.initDisplay(width, height, false);
			Display.setVSyncEnabled(true);
			Camera camera = new Camera();
			camera.width = width;
			camera.height = height;
			camera.farPlane = 100;
			Object3d cameraBox = new Object3d();
			cameraBox.addChild(camera);
			scene.add(cameraBox);

			GuiCard.init(scene.getMaterialLibrary());
			
			camera.getPosition().move(0, -10, 0).roll(90).turn(90);
			scene.setBackColor(new Vector3f(0.5f, 1, 0.5f));
			long sysTime = 0;
			mouseProcess = new MouseProcess(scene, camera, game, this);
			while (!Display.isCloseRequested()) {
				float deltaTime = 0;
				if (System.currentTimeMillis() - sysTime < 1000) {
					deltaTime = (System.currentTimeMillis() - sysTime) / 1000f;
					//if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
						deltaTime *= 10;
				}
				sysTime = System.currentTimeMillis();
				mouseProcess.tick();

				if (animation != null && !animation.isFinished()) {
					animation.play(deltaTime, scene);
				} else if (!events.isEmpty()) {
					if (animation != null) {
						events.poll();
						animation = null;
					}
					if (!events.isEmpty()) {
						StoredEvent event = events.peek();
						if (event.getType() == GameEvent.GAME_BEGIN) {
							our_player = (Player) event.getCard();
							mouseProcess.player = our_player;
						}
						animation = Animation.createFor(event, scene, our_player);
						System.out.println("read anim " + animation);
					}
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_F4)) {
					Texture2D screenshot = new Texture2D(width, height);
					scene.renderToTexture(camera, screenshot);
					ImageIO.write(screenshot.getImage(),"PNG",new File("screenshot.png"));
				}
				scene.tick(deltaTime);
				scene.render(camera);
				Display.update();
			}
			Display.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}

	@Override
	public void on(GameEvent gameEvent, Card card, CreatureCard target) {
		events.add(new StoredEvent(gameEvent, card, target));
		System.out.println("wait");
		while (!events.isEmpty()){
			
		}
		System.out.println("end anim");
	}

	@Override
	public void on(GameEvent gameEvent, Card card) {
		events.add(new StoredEvent(gameEvent, card));
		System.out.println("wait");
		while (!events.isEmpty()){
			
		}
		System.out.println("end anim");
	}
	public void setMessage(String message) {
		Display.setTitle(TextHolder.getName(message));	
	}
}
