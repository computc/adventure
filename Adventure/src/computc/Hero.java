package computc;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Hero extends Entity
{
	public Hero(World world, int tx, int ty) throws SlickException
	{
		super(world, tx, ty);
		
		this.image = new Image("res/hero.png");
		
		this.acceleration = 0.015f;
		this.deacceleration = 0.001f;
		this.maximumVelocity = 0.2f;
		
		this.currentHealth = this.maximumHealth = 3;
	}
	
	public void update(Input input, int delta)
	{
		this.getNextPosition(input, delta);
		this.checkTileMapCollision();
		this.setPosition(xtemp, ytemp);
	}
	
	public void render(Graphics graphics, Camera camera)
	{
		super.render(graphics, camera);
	}
	
	private void getNextPosition(Input input, int delta) 
	{
		if(input.isKeyDown(Input.KEY_UP)) 
		{
			dy -= acceleration;
			if(dy < -maximumVelocity)
				dy = -maximumVelocity;
			
			dy *= delta;
		}
		else if(input.isKeyDown(Input.KEY_DOWN))
		{
			dy += acceleration;
			if(dy > maximumVelocity)
				dy = maximumVelocity;
			
			dy *= delta;
		}
		else //if neither KEY_UP nor KEY_DOWN
		{
			if(dy > 0)
			{
				dy -= deacceleration;
				if(dy < 0)
					dy = 0;
				
				dy *= delta;
			}
			else if(dy < 0)
			{
				dy += deacceleration;
				if(dy > 0)
					dy = 0;
				
				dy *= delta;
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			dx += acceleration;
			if(dx > maximumVelocity)
				dx = maximumVelocity;
			
			dx *= delta;
		}
		else if(input.isKeyDown(Input.KEY_LEFT)) 
		{
			dx -= acceleration;
			if(dx < -maximumVelocity)
				dx = -maximumVelocity;
			
			dx *= delta;
		}
		else  //if neither KEY_RIGHT nor KEY_LEFT
		{
			if (dx > 0) 
			{
				dx -= deacceleration;
				if(dx < 0)
					dx = 0;
				
				dx *= delta;
			}
			else if (dx < 0)
			{
				dx += deacceleration;
				if(dx > 0)
					dx = 0;
				
				dx *= delta;
			}
		}
		
		if(input.isKeyDown(Input.KEY_UP))
		{
			this.direction = Direction.NORTH;
		}
		else if(input.isKeyDown(Input.KEY_DOWN))
		{
			this.direction = Direction.SOUTH;
		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			this.direction = Direction.WEST;
		}
		else if(input.isKeyDown(Input.KEY_RIGHT))
		{
			this.direction = Direction.EAST;
		}
	}
}