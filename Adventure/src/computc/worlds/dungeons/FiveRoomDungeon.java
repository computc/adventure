package computc.worlds.dungeons;

import org.newdawn.slick.SlickException;

import computc.worlds.rooms.Room;

public class FiveRoomDungeon extends Dungeon
{
	public FiveRoomDungeon() throws SlickException
	{
		this.firstRoom = new Room(this, 2, 2, "grid");
		
		this.firstRoom.instantiateNorthernRoom("corners");
		this.firstRoom.instantiateSouthernRoom("threelines");
		this.firstRoom.instantiateEasternRoom("fivedots");
		this.firstRoom.instantiateWesternRoom("fourlines");
	}
}