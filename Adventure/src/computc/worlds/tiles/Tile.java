
package computc.worlds.tiles;

import java.util.HashMap;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import computc.cameras.Camera;
import computc.worlds.rooms.Room;

public abstract class Tile
{
	protected Room room;
	
	protected int tx;
	protected int ty;
	
	protected Image image;
	protected Color color;
	
	public Tile(Room room, int tx, int ty)
	{
		this.room = room;
		
		this.tx = tx;
		this.ty = ty;
		
		this.image = Tile.images.get("null tile");
		this.color = Tile.colors.get("null tile");
	}
	
	public static void init() throws SlickException
	{
		Tile.images.put("null tile", new Image("./res/tiles/null.tile.png"));
		Tile.images.put("wall tile", new Image("./res/tiles/wall.tile.png"));
		Tile.images.put("floor tile", new Image("./res/tiles/floor.tile.png"));
		Tile.images.put("door tile", new Image("./res/tiles/door.tile.png"));
		Tile.images.put("northern arrow floor tile", new Image("./res/tiles/northern.arrow.tile.png"));
		Tile.images.put("southern arrow floor tile", new Image("./res/tiles/southern.arrow.tile.png"));
		Tile.images.put("eastern arrow floor tile", new Image("./res/tiles/eastern.arrow.tile.png"));
		Tile.images.put("western arrow floor tile", new Image("./res/tiles/western.arrow.tile.png"));
		
		Tile.colors.put("null tile", Color.pink);
		Tile.colors.put("wall tile", Color.darkGray);
		Tile.colors.put("floor tile", Color.gray);
		Tile.colors.put("door tile", Color.yellow);
		
		try
		{
			Document document = new SAXBuilder().build("./res/tileset.xml");
			
			List<Element> tiles = document.getRootElement().getChildren("tile");
			
			for(Element tile : tiles)
			{
				Element image = tile.getChild("image");
				Element color = tile.getChild("color");

				int red = color.getAttribute("red").getIntValue();
				int green = color.getAttribute("green").getIntValue();
				int blue = color.getAttribute("blue").getIntValue();

				int width = image.getAttribute("width").getIntValue();
				int height = image.getAttribute("height").getIntValue();
				String source = image.getAttribute("source").getValue();
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
	
	public void update(int delta)
	{
		//code goes here.
	}
	
	public void render(Graphics graphics, Camera camera)
	{
		int x = this.getX() - camera.getX();
		int y = this.getY() - camera.getY();
		
		this.image.draw(x, y);
	}
	
	public void renderOnMap(Graphics graphics, Camera camera)
	{
		int x = (this.getX() / 8) - camera.getX();
		int y = (this.getY() / 8) - camera.getY();
		
		final int UNIT = Tile.SIZE / 8;
		
		graphics.setColor(this.color);
		graphics.fillRect(x, y, UNIT, UNIT);
	}
	
	/*
	 * Returns the horizontal position
	 * of this tile in units of pixels
	 * and relative to the dungeon.
	 * 
	 * @units_of		pixels
	 * @relative_to		dungeon
	 */
	public int getX()
	{
		return this.getTileyX() * Tile.SIZE + this.room.getX();
	}
	
	/*
	 * Returns the vertical position
	 * of this tile in units of pixels
	 * and relative to the dungeon.
	 * 
	 * @units_of		pixels
	 * @relative_to		dungeon
	 */
	public int getY()
	{
		return this.getTileyY() * Tile.SIZE + this.room.getY();
	}
	
	/*
	 * Returns the horizontal position
	 * of this tile in units of tiles
	 * and relative to the room.
	 * 
	 * @units_of		tiles
	 * @relative_to		room
	 */
	public int getTileyX()
	{
		return this.tx;
	}
	
	/*
	 * Returns the vertical position
	 * of this tile in units of tiles
	 * and relative to the room.
	 * 
	 * @units_of		tiles
	 * @relative_to		room
	 */
	public int getTileyY()
	{
		return this.ty;
	}

	/*
	 * Returns the horizontal dimension
	 * of this tile in units of pixels.
	 * 
	 * @units_of		pixels
	 */
	public int getWidth()
	{
		return Tile.SIZE;
	}
	
	/*
	 * Returns the vertical dimension
	 * of this tile in units of pixels.
	 * 
	 * @units_of		pixels
	 */
	public int getHeight()
	{
		return Tile.SIZE;
	}

	/*
	 * Returns whether an entity can
	 * move here without collision.
	 */
	public boolean canMoveHere()
	{
		return true;
	}

	public static HashMap<String, Image> images = new HashMap<String, Image>();
	public static HashMap<String, Color> colors = new HashMap<String, Color>();
	
	public final static int SIZE = 64;

}