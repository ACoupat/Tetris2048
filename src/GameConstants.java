import java.awt.Color;
import java.awt.Font;
import java.awt.Point;


public class GameConstants {

	//Brick
	public static final int TILE_SIDE_LENGTH = 50;
	public static final Point NEXT_BRICK_POSITION = new Point(-3, 1);
	public static final int TILES_PER_BRICK = 4;
	
	//Grid
	public static final int LINE_SIZE = 10;
	public static final int HORIZONTAL_TILE_NUMBER = 10;
	public static final int VERTICAL_TILE_NUMBER =15;

	public static final int titleHeight = 150;
	public static final int GRID_WIDTH = HORIZONTAL_TILE_NUMBER*GameConstants.TILE_SIDE_LENGTH+1;//+(nbColumns+1)*2*GameConstants.LINE_SIZE;
	public static final int gridHeight = (VERTICAL_TILE_NUMBER+1)*GameConstants.TILE_SIDE_LENGTH+1;//(GameConstants.VERTICAL_TILE_NUMBER)*GameConstants.tileSize+(/*GameConstants.VERTICAL_TILE_NUMBER+*/2)*GameConstants.LINE_SIZE; //+1
	public static final int PANELS_WIDTH=300;	
	
	public static final int WINDOW_WIDTH = GRID_WIDTH+350;
	public static final int WINDOW_HEIGHT = gridHeight+50+titleHeight;
	public static final int TILE_WIDTH = WINDOW_WIDTH / 4;
	public static final int TILE_HEIGHT = WINDOW_WIDTH / 4;
	public static final int DIRECTION_RIGHT = 1;
	public static final int DIRECTION_LEFT = 2;
	public static final int DIRECTION_TOP = 3;
	public static final int DIRECTION_BOTTOM = 4;
	public static final int TILE_VELOCITY = 5;
	
	public static final boolean RESIZABLE = false;
	public static final int SLEEP_TIME =250;
	
	
	//Colors
	public static final Color BACKGROUND_PANEL = new Color(187,173,160);
	public static final Color BACKGROUND_TILE = new Color(205,192,180);
	
	public static final Font FONT_PANELS = new Font("Arial",Font.BOLD,18);
	public static final Font GAME_OVER_FONT = new Font("Calibri",Font.BOLD,50);

}
