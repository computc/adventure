package computc.worlds.dungeons;

import org.newdawn.slick.SlickException;

import computc.worlds.rooms.Room;

public class OneRoomDungeon extends Dungeon
{
	public OneRoomDungeon(String filepath)
	{
		super(filepath);
		
		this.firstRoom = new Room(this, 2, 2, this.getRandomRoomLayout());
	}
}