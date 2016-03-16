import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;

public class Brick extends JPanel{
	
	private List<Tile> tiles = new ArrayList<Tile>();
	private Random rand = new Random();
	private Color color;
	
	public Brick(){
		color = getColor();
		randomBrick();
	}
	
	public List<Tile> getTiles(){
		return tiles;
	}	
	
	public void paintComponent(Graphics g){
		for(Tile tile : tiles)
		{
			tile.paintComponent(g);
		}
	}
	
	public void update(int key, Map<Point, Tile> tilesPositions){
		Point move = new Point(0, 0);
		switch(key)
		{
		case 81:
		case 37:
			move = new Point(-1, 0);
			break;
		case 68:
		case 39:
			move = new Point(1, 0);
			break;
		case 83:
		case 40:
			move = new Point(0, 1);
			break;
		}
		if(canMove(tilesPositions, move))
		{
			moveBrick(move);
		}
	}
	
	private Color getColor(){
		int value = rand.nextInt(7);
		switch(value)
		{
			case 0:
				return new Color(19, 191, 28);
			case 1:
				return new Color(0, 200, 198);
			case 2:
				return new Color(200, 34, 196);
			case 3:
				return new Color(0, 33, 193);
			case 4:
				return new Color(200, 197, 31);
			case 5:
				return new Color(252, 106, 13);
			case 6:
				return new Color(179, 21, 32);
			default:
				return Color.white;
		}
	}
	
	public boolean canMove(Map<Point, Tile> tilesPositions, Point move)
	{
		for(Tile tile : tiles)
		{
			Point nextPosition = new Point(tile.getPosition().x + move.x, tile.getPosition().y + move.y);
			if(nextPosition.x < 0 || 
					nextPosition.x >= GameConstants.HORIZONTAL_TILE_NUMBER ||
					nextPosition.y > GameConstants.VERTICAL_TILE_NUMBER)
				return false;
			if(tilesPositions.containsKey(nextPosition))
				if(!tiles.contains(tilesPositions.get(nextPosition)) && 
						tilesPositions.get(nextPosition).getValue() != tile.getValue())
					return false;
		}
		return true;
	}
	
	public void moveBrick(Point move)
	{
		for(Tile tile : tiles)
		{
			tile.translate(move);
		}
	}
	
	public void previewBrick(int preview){
		for(Tile tile : tiles)
		{
			tile.translate(new Point(preview * GameConstants.NEXT_BRICK_POSITION.x, preview * GameConstants.NEXT_BRICK_POSITION.y));
		}
	}
	
	private void randomBrick(){
		int randomForm = rand.nextInt(6);
		switch(randomForm){
			case 0:
				tiles.add(new Tile(randomValue(), new Point(4, 3), color));
				tiles.add(new Tile(randomValue(), new Point(4, 2), color));
				tiles.add(new Tile(randomValue(), new Point(4, 1), color));
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				break;
			case 1:
				tiles.add(new Tile(randomValue(), new Point(4, 1), color));
				tiles.add(new Tile(randomValue(), new Point(5, 1), color));
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				tiles.add(new Tile(randomValue(), new Point(5, 0), color));
				break;
			case 2:
				tiles.add(new Tile(randomValue(), new Point(4, 1), color));
				tiles.add(new Tile(randomValue(), new Point(5, 1), color));
				tiles.add(new Tile(randomValue(), new Point(6, 1), color));
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				break;
			case 3:
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				tiles.add(new Tile(randomValue(), new Point(5, 0), color));
				tiles.add(new Tile(randomValue(), new Point(6, 0), color));
				tiles.add(new Tile(randomValue(), new Point(7, 0), color));
				break;
			case 4:
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				tiles.add(new Tile(randomValue(), new Point(4, 1), color));
				tiles.add(new Tile(randomValue(), new Point(5, 1), color));
				tiles.add(new Tile(randomValue(), new Point(5, 2), color));
				break;
			case 5:
				tiles.add(new Tile(randomValue(), new Point(4, 0), color));
				tiles.add(new Tile(randomValue(), new Point(5, 0), color));
				tiles.add(new Tile(randomValue(), new Point(5, 1), color));
				tiles.add(new Tile(randomValue(), new Point(6, 1), color));
				break;
		}
	}
	
	private int randomValue(){
		return rand.nextInt(2)+1;
	}
}
