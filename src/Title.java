import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class Title extends JPanel
{
	
	
	public Title()
	{
	
	}
	
	public void paintComponent(Graphics g)
	{
		 try {
		      Image img = ImageIO.read(new File("titre.png"));
		      g.drawImage(img, 0, 0, this);
		      //Pour une image de fond
		      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }                
		
		/*g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Calibri",75,75));
		g.drawString("2048Like", 10, 100);*/
	}
	
}
