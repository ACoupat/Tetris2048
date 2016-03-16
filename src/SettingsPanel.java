import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;


public class SettingsPanel extends JPanel
{
	
	private int width = GameConstants.PANELS_WIDTH;
	private int height = 105;
	private int x = 2*GameConstants.LINE_SIZE+GameConstants.GRID_WIDTH;
	private JButton button = new JButton("Start");
	//private int y = GameConstants.LINE_SIZE;
	
	public SettingsPanel()
	{
		this.setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		button.setVisible(true);
		this.add(button);
		this.button.setBounds(10,50,75,50);
		//button.setLocation(0, 0);
	}
	
	
	public int getX()
	{
		return x;
	}
	
	public JButton getButton()
	{
		return button;
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
		g.drawString("START / PAUSE", 35, 25);
		
	}
}
