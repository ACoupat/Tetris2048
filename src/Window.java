import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Window extends JFrame
{
	


	private GradientPanel pan = new GradientPanel();
	private ScorePanel scPan = new ScorePanel();
	private NextPiecePanel nPP = new NextPiecePanel();
	private SettingsPanel sP = new SettingsPanel();
	private Title title = new Title();
	private Grid grid = new Grid(nPP,scPan,this);
	private Glass glass = new Glass();
	private Thread t;
	private boolean pause =true;

	public Window()
	{
		this.setResizable(GameConstants.RESIZABLE);
		this.setTitle("Tetris2048");
		this.setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    this.addKeyListener(new KeyboardListener(grid));
	    this.sP.getButton().addActionListener(new ButtonListener(this));

	    
	    this.setContentPane(pan);
	    this.setGlassPane(glass);
	    this.setVisible(true);
	   // 

	    glass.setVisible(false);
	 
	    sleep();
	   
		
		// pan settings
		pan.add(grid);
		pan.add(nPP);
		pan.add(scPan);
		pan.add(sP);
		pan.add(title);

		
		title.setBounds(0,0,this.getWidth(),this.getHeight());
		grid.setBounds(GameConstants.LINE_SIZE,GameConstants.LINE_SIZE+GameConstants.titleHeight,GameConstants.GRID_WIDTH,GameConstants.gridHeight);
		nPP.setBounds(nPP.getX(),nPP.getY(),nPP.getWidth(),nPP.getWidth());
		scPan.setBounds(scPan.getX(),nPP.getY()+nPP.getHeight()+GameConstants.LINE_SIZE,scPan.getWidth(),scPan.getHeight());
		sP.setBounds(sP.getX(),nPP.getY()+nPP.getWidth()+scPan.getHeight()+2*GameConstants.LINE_SIZE,sP.getWidth(),sP.getHeight());
		
		//this.getGlassPane().cre
		
		//title.setBounds(0,0,this.getWidth(),this.getHeight());
		
	//go();

	
		
		
	}
	
	public SettingsPanel getSP()
	{
		return sP;
	}
	
	public void setGlassPaneVisible(boolean gameOver)
	{
		glass.setVisible(gameOver);
		this.repaint();
	}
	
	private void go() {
		Boolean updateBrick = true;
		while(/*true*/!pause)
		{
			grid.autoUpdate(updateBrick);
			grid.repaint();
			if(updateBrick)
				updateBrick = false;
			else
				updateBrick = true;
			sleep();
		}
	}
	
	private void sleep() {
		try {
			Thread.sleep(GameConstants.SLEEP_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	class KeyboardListener implements KeyListener {
		private Grid grid;
		public KeyboardListener(Grid grid){
			this.grid = grid;
		}
		public void keyPressed(KeyEvent e) {
			if(!grid.getGameOver()/**/&& !pause)
			{
				grid.moveBrick(e.getKeyCode());
				grid.update();
				grid.repaint();
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {
		}
	}
	
	class ButtonListener implements ActionListener
	{
		private Window win;
		
		public ButtonListener(Window win)
		{
			this.win = win;
		
		}

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			
			
			t = new Thread(new Play());
			t.start();
			if (pause)
			{
				pause = false;
				//win.getSP().getButton().setBackground(Color.BLUE);
				//win.getSP().getButton().revalidate();
				win.requestFocus();
			}
			else
			{
				pause = true;
				//win.getSP().getButton().setText("Play");
				//win.revalidate();
			}
			
			
			
			
		}
		
	}
	
	class Play implements Runnable{

		@Override
		public void run()
		{
			
			go();
		}
		
		
	}

	

}
