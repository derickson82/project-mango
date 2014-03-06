/**
 * 
 */
package mango;

import java.io.File;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.command.Command;
import org.newdawn.slick.command.InputProvider;
import org.newdawn.slick.command.InputProviderListener;
import org.newdawn.slick.command.KeyControl;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

/**
 * @author derickson82
 *
 */
public class Main implements Game, InputProviderListener {
	
	private TiledMap sampleMap;
	
	private static final int DEFAULT_WINDOW_WIDTH = 640;
	private static final int DEFAULT_WINDOW_HEIGHT = 480;
	
	// player info
	
	private int playerX = 0;
	private int playerY = 0;
	private Image playerImage;
	
	private InputProvider inputProvider;
	
	public static enum Commands implements Command {
		UP, DOWN, LEFT, RIGHT;
	}
	
	@Override
	public String getTitle() {
		return "Rogue Mango";
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		sampleMap = new TiledMap("mango/maps/example.tmx");
		
//		String imageRef = sampleMap.getObjectImage(0, 65);
//		
//		TileSet tileSet = sampleMap.findTileSet(65);
//		playerImage = tileSet.tiles.getSubImage(0*16, 4*16, 16, 16);
		
		
		gc.getInput().enableKeyRepeat();
		inputProvider = new InputProvider(gc.getInput());
		
		inputProvider.bindCommand(new KeyControl(Input.KEY_UP), Commands.UP);
		inputProvider.bindCommand(new KeyControl(Input.KEY_DOWN), Commands.DOWN);
		inputProvider.bindCommand(new KeyControl(Input.KEY_LEFT), Commands.LEFT);
		inputProvider.bindCommand(new KeyControl(Input.KEY_RIGHT), Commands.RIGHT);
		inputProvider.addListener(this);
	}
	
	@Override
	public void controlPressed(Command command) {
		if (command instanceof Commands) {
			switch ((Commands)command) {
			case UP:
				playerY -= 1;
				break;
			case DOWN:
				playerY += 1;
				break;
			case LEFT:
				playerX -= 1;
				break;
			case RIGHT:
				playerX += 1;
				break;
			}
		}
	}
	
	@Override
	public void controlReleased(Command command) {
	}

	@Override
	public void render(GameContainer gc, Graphics graphics) throws SlickException {
//		sampleMap.render(0, 0, 0, 0, gc.getWidth() / sampleMap.getTileWidth(), gc.getHeight() / sampleMap.getTileHeight());
		
		playerImage.draw(0, 0);
//		graphics.drawImage(playerImage, 0, 0, 64, 64, 0, 0, 16, 16);
//		playerImage.draw(playerX * sampleMap.getTileWidth(), playerY * sampleMap.getTileHeight());
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
	}

	@Override
	public boolean closeRequested() {
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.setProperty("org.lwjgl.librarypath",new File("lib/natives/").getAbsolutePath());
		try {
			AppGameContainer container = new AppGameContainer(new Main(), DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT, false);
			
			container.setShowFPS(false);
			container.setForceExit(true);
			container.start();
			
		} catch (SlickException e) {
			e.printStackTrace(System.err);
		}
	}
	
}

