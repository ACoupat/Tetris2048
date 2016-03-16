import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


public class ScorePanel extends JPanel
{ 
	
	private int width = GameConstants.PANELS_WIDTH;
	private int height = 125;
	private int x = 2*GameConstants.LINE_SIZE+GameConstants.GRID_WIDTH;
	private int score=0;
	//private int y = GameConstants.LINE_SIZE;
	
	public ScorePanel()
	{
		setBorder(BorderFactory.createLineBorder(Color.BLACK));


	}
	
	public int getX()
	{
		return x;
	}


	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setScore(int score)
	{
		this.score = score; 
	}
	
	public void updateScore(int nb)
	{
		this.score +=(int)Math.pow(2, nb);
	}
	
	public void paintComponent(Graphics g)
	{
		g.setFont(GameConstants.FONT_PANELS);
		g.setColor(GameConstants.BACKGROUND_PANEL);
		g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
		g.setColor(GameConstants.BACKGROUND_TILE);
		g.fillRoundRect(5, 5, this.getWidth()-10, 40, 5, 5);
		g.setColor(Color.black);
		g.drawString("SCORE", 35, 25);
		g.drawString(""+score,35, 75);
	}
}
