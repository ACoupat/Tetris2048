import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class NextPiecePanel extends JPanel
{
	private int width = GameConstants.PANELS_WIDTH;
	private int height = 300;
	private int x = 2*GameConstants.LINE_SIZE+GameConstants.GRID_WIDTH;
	private int y = GameConstants.LINE_SIZE+GameConstants.titleHeight;
	private Brick nextBrick=null;
	
	public NextPiecePanel()
	{
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
	
	public void setNextBrick(Brick nextBrick)
	{
		this.nextBrick=nextBrick;
		//System.out.println("blabla");
	}
	
	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
	
	public void paintComponent(Graphics g)
	{
		g.setFont(GameConstants.FONT_PANELS);
		g.setColor(new Color(187,173,160));
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		g.setColor(GameConstants.BACKGROUND_TILE);
		g.fillRoundRect(5, 5, this.getWidth()-10, 40, 5, 5);
		g.setColor(Color.black);
		g.drawString("NEXT PIECE", 35, 25);
		
		if (nextBrick!=null)
		{
			nextBrick.paintComponent(g);
		}

		
		
	}
}
