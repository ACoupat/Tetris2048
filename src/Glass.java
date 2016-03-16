import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;




public class Glass extends JPanel
{
	private JLabel label = new JLabel("YOU LOSE");

	
	public Glass()
	{	this.setOpaque(false);
		
	label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.setForeground(Color.BLUE);

		label.setFont(GameConstants.GAME_OVER_FONT);
		label.setText("YOU LOSE");
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		this.add(Box.createRigidArea(new Dimension(GameConstants.WINDOW_WIDTH/2-250,GameConstants.WINDOW_HEIGHT/2-50)));
		this.add(label);


	}
	
	
}
