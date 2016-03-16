import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Grid  extends JPanel 
{
	private Boolean gameOver = false;
	private Brick activeBrick;
	private Brick nextBrick;
	private List<Brick> bricks = new ArrayList<Brick>();
	private Map<Point, Tile> tilesPositions = new HashMap<Point, Tile>();
	private List<Tile> tiles = new ArrayList<Tile>();
	private int score=0;
	
	private ScorePanel scP;	
	private NextPiecePanel nPP;
	private Window win;
	
	public Grid(NextPiecePanel nPP,ScorePanel scP, Window win)
	{
		this.nPP = nPP;
		setOpaque(true);
		this.scP =scP; 
		this.win=win;
		setSize(GameConstants.GRID_WIDTH, GameConstants.gridHeight);
		activeBrick = new Brick();
		bricks.add(activeBrick);
		nextBrick = new Brick();
		nPP.setNextBrick(nextBrick);
		nextBrick.previewBrick(1);
		tiles.addAll(activeBrick.getTiles());
		this.setLayout(null);
		
	}
	
	public Boolean getGameOver(){
		return gameOver;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		//BackGround
		g.setColor(GameConstants.BACKGROUND_PANEL);

		
		//Squares
		for (int j=0; j<GameConstants.VERTICAL_TILE_NUMBER+1;j++)
		{
			for(int i=0; i< GameConstants.HORIZONTAL_TILE_NUMBER;i++)
			{
				int abscissa = i*(GameConstants.TILE_SIDE_LENGTH);
				
				int ordinate = j*(GameConstants.TILE_SIDE_LENGTH);
						

				g.setColor(GameConstants.BACKGROUND_TILE);
				g.fillRect(abscissa,ordinate, GameConstants.TILE_SIDE_LENGTH, GameConstants.TILE_SIDE_LENGTH);
				g.setColor(Color.black);
				g.drawRect(abscissa,ordinate, GameConstants.TILE_SIDE_LENGTH, GameConstants.TILE_SIDE_LENGTH);
				

				
			}System.out.println("coucou"+j);
		}
		for(Tile tile : tiles)
		{
			tile.paintComponent(g);
		}
		for(Brick brick : bricks)
		{
			brick.paintComponent(g);
		}
	}
	
	public void moveBrick(int key){
		activeBrick.update(key, tilesPositions);
	}
	
	public void autoUpdate(Boolean updateBrick){
		if(updateBrick)
		{
			if(activeBrick.canMove(tilesPositions, new Point(0, 1)))
			{
				activeBrick.moveBrick(new Point(0, 1));
			}
			else
			{
				for(Tile tile : activeBrick.getTiles())
				{
					tile.setMoving(false);
				}
			}
		}
		for(Tile tile : tiles)
		{
			if(!tile.getIsPartOfBrick())
			{
				if(tile.getPosition().y + 1 <= GameConstants.VERTICAL_TILE_NUMBER)
					tile.translate(new Point(0, 1));
				else
					tile.setMoving(false);
			}
		}
		update();
	}
	public void update(){
		tilesPositions.clear();
		for(Tile tile : tiles)
		{
			while(tilesPositions.containsKey(tile.getPosition()))
			{
				Tile secondTile = tilesPositions.get(tile.getPosition());
				if(tile.getValue() == secondTile.getValue())
				{
					tile.setPosition(new Point(-1, -1));
					secondTile.setValue(secondTile.getValue()+1);
					//scP.updateScore(secondTile.getValue());
					//scP.repaint();
				}
				else
				{
					tile.setMoving(false);
					tile.setPosition(new Point(tile.getPosition().x, tile.getPosition().y - 1));
				}
			}
			if(tile.getPosition().x != -1)
			{
				tilesPositions.put(tile.getPosition(), tile);
			}
		}
		tiles.clear();
		removeLines();
		removeBrokenBricks();
		for(int i = 0; i <= GameConstants.HORIZONTAL_TILE_NUMBER; i++)
		{
			for(int j = GameConstants.VERTICAL_TILE_NUMBER; j >= 0; j--)
			{
				if(tilesPositions.containsKey(new Point(i, j)))
				{
					tiles.add(tilesPositions.get(new Point(i, j)));
				}
			}
		}
		
		if(!gameOver && (isActiveBrickLanded() || !bricks.contains(activeBrick)))
		{
			activeBrick = nextBrick;
			activeBrick.previewBrick(-1);
			bricks.add(activeBrick);
			if(checkGameOver())
			{
				gameOver = true;
				win.setGlassPaneVisible(true);
			}
			else
			{
				nextBrick = new Brick();
				nextBrick.previewBrick(1);
				nPP.setNextBrick(nextBrick);
				nPP.repaint();
				tiles.addAll(activeBrick.getTiles());
			}
		}
	}
	
	private Boolean isActiveBrickLanded()
	{
		for(Tile tile : activeBrick.getTiles())
		{
			if(!tile.getMoving())
				return true;
		}
		return false;
	}
	
	private Boolean checkGameOver(){
		for(Tile tile : activeBrick.getTiles())
		{
			if(tilesPositions.containsKey(tile.getPosition()))
				return true;
		}
		return false;
	}
	
	private void removeBrokenBricks(){
		for(int i=0; i<bricks.size(); i++)
		{
			int j=0;
			while(j < bricks.get(i).getTiles().size() &&
					tilesPositions.containsValue(bricks.get(i).getTiles().get(j)))
			{
				j++;
			}
			if(j != bricks.get(i).getTiles().size())
			{
				for(Tile tile : bricks.get(i).getTiles())
				{
					tile.setIsPartOfBrick(false);
				}
				bricks.remove(i);
			}
		}
	}
	
	private int removeLines(){
		int i = GameConstants.VERTICAL_TILE_NUMBER;
		int j = 0;
		while(i >= 0 && 
				tilesPositions.containsKey(new Point(j, i)) &&
				!tilesPositions.get(new Point(j, i)).getMoving())
		{
			
			while(j < GameConstants.HORIZONTAL_TILE_NUMBER && 
					tilesPositions.containsKey(new Point(j, i)) &&
					!tilesPositions.get(new Point(j, i)).getMoving())
			{
				j++;
			}
			if(j == GameConstants.HORIZONTAL_TILE_NUMBER)
			{
				for(j = 0; j < GameConstants.HORIZONTAL_TILE_NUMBER; j++)
				{
					scP.updateScore(tilesPositions.get(new Point(j, i)).getValue());
					scP.repaint();
					tilesPositions.remove(new Point(j, i));
				}
				for(int k = i - 1; k >= 0; k--)
				{
					for(j = 0; j < GameConstants.HORIZONTAL_TILE_NUMBER; j++)
					{
						if(tilesPositions.containsKey(new Point(j, i)))
							tilesPositions.get(new Point(j, i)).translate(new Point(0, 1));
					}
				}
			}
			i--;
			j = 0;
		}
		return score;
	}
}

	
