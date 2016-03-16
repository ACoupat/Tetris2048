import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


public class GradientPanel extends JPanel
{
	/*public GradientPanel()
	{
		this.setLayout(null);
	}*/
	
	public void paintComponent(Graphics g)
	{
		 Graphics2D g2d = (Graphics2D) g;
	        Color color1 = new Color(85,116,180);
	        Color color2 = new Color(9,27,51);
	        int w = getWidth();
	        int h = getHeight();
	        GradientPaint gp = new GradientPaint( 0, 0, color1, 0, h, color2);
	        g2d.setPaint(gp);
	        g2d.fillRect(0, 0, w, h);
	}
}
