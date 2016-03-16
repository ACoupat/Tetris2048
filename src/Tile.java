import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Tile extends JPanel {
	private int value;
	private Color color;
	private Point position;
	private Boolean moving = true;
	private Boolean isPartOfBrick = true;
	
	public Tile(int value, Point position, Color color){
		this.value = value;
		this.position = position;
		this.color = color;
	}
	
	public Boolean getMoving(){
		return moving;
	}
	
	public void setMoving(Boolean moving){
		this.moving = moving;
	}
	
	public Boolean getIsPartOfBrick(){
		return isPartOfBrick;
	}
	
	public void setIsPartOfBrick(Boolean isPartOfBrick){
		this.isPartOfBrick = isPartOfBrick;
	}
	
	public Point getPosition(){
		return position;
	}
	
	public void setPosition(Point position){
			this.position = position;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setValue(int value){
		this.value = value;
	}
	
	private Color getColor(){
		switch(value)
		{
			case 1:
				return new Color(238, 228, 218);
			case 2:
				return new Color(237, 224, 200);
			case 3:
				return new Color(242, 177, 121);
			case 4:
				return new Color(245, 149, 99);
			case 5:
				return new Color(246, 124, 95);
			case 6:
				return new Color(246, 94, 59);
			case 7:
				return new Color(237, 214, 142);
			case 8:
				return new Color(237, 204, 97);
			case 9:
				return new Color(237, 200, 81);
			case 10:
				return new Color(235, 199, 0);
			case 11:
				return new Color(236, 196, 0);
			default:
				return Color.BLACK;
		}
	}
	public void paintComponent(Graphics g){
		
		int drawX = position.x * GameConstants.TILE_SIDE_LENGTH;
		int drawY = position.y * GameConstants.TILE_SIDE_LENGTH;
		
		g.setFont(GameConstants.FONT_PANELS);
		//g.setColor(getColor());
		g.setColor(color);
		g.fillRect(drawX, drawY, GameConstants.TILE_SIDE_LENGTH, GameConstants.TILE_SIDE_LENGTH);
		g.setColor(Color.BLACK);
		g.drawRect(drawX, drawY, GameConstants.TILE_SIDE_LENGTH, GameConstants.TILE_SIDE_LENGTH);
		g.drawString(Integer.toString((int)Math.pow(2, value)), drawX + GameConstants.TILE_SIDE_LENGTH / 2, drawY + GameConstants.TILE_SIDE_LENGTH / 2);
	}
		
	public void translate(Point move){
		position.translate(move.x, move.y);
	}
}

