/**
 * 
 */
package name.blah;

import java.util.Random;

/**
 * @author Jett Peterson
 *
 */
public class Player {
	
	private int x;
	private int y;
	public int health;
	
	Player(RoguelikeMap map)
	{
		this.setHealth(10);
		if(getHealth() > 0)
		{
			//This is here to make Java stop complaining, till health does something
		}
		
		int x, y;
		Random rand = new Random();
		x = rand.nextInt(24);
		y = rand.nextInt(24);
		
		while(!map.checkOpen(x, y))
		{
			x = rand.nextInt(24);
			y = rand.nextInt(24);
		}
		
		this.x = x;
		this.y = y;
		map.setPlayer(x, y);
	}
	
	public boolean move(Direction dir, RoguelikeMap map)
	{
		if(isgameover()){
			map.setGameOver();
			return false;
		}
		Random rand = new Random();
		switch(dir)
		{
		case NORTH:
			for(int i = 0; i < map.monsters.size(); i++)
				map.monsters.get(i).move(this.x, this.y, map, this);
			if(map.isItem(this.x-1, this.y)){
				if(map.itemAt(this.x-1, this.y) == "k"){
					this.setHealth(this.getHealth() + 5);
					for(int i = 0; i < map.locations.size(); i++){
						if(map.locations.get(i).getX() == x-1 && map.locations.get(i).getY() == y)
							map.locations.remove(i);
					}
				}
				if(map.itemAt(this.x-1, this.y) == ">"){
					x = rand.nextInt(24);
					y = rand.nextInt(24);
					while(map.location(x, y) != "."){
						x = rand.nextInt(24);
						y = rand.nextInt(24);
					}
					map.setPlayer(x, y);
				}
			}
			if(map.checkOpen(this.x-1, this.y))
			{
				this.x--;
				map.setPlayer(this.x, this.y);
				return true;
			}
			break;
			
		case SOUTH:
			for(int i = 0; i < map.monsters.size(); i++)
				map.monsters.get(i).move(this.x, this.y, map, this);
			if(map.isItem(this.x+1, this.y)){
				if(map.itemAt(this.x+1, this.y) == "k"){
					this.setHealth(this.getHealth() + 5);
					for(int i = 0; i < map.locations.size(); i++){
						if(map.locations.get(i).getX() == x+1 && map.locations.get(i).getY() == y)
							map.locations.remove(i);
					}
				}
				if(map.itemAt(this.x+1, this.y) == ">"){
					x = rand.nextInt(24);
					y = rand.nextInt(24);
					while(map.location(x, y) != "."){
						x = rand.nextInt(24);
						y = rand.nextInt(24);
					}
					map.setPlayer(x, y);
				}
			}
			if(map.checkOpen(this.x+1, this.y))
			{
				this.x++;
				map.setPlayer(this.x, this.y);
				return true;
			}
			break;
			
		case EAST:
			for(int i = 0; i < map.monsters.size(); i++)
				map.monsters.get(i).move(this.x, this.y, map, this);
			if(map.isItem(this.x, this.y+1)){
				if(map.itemAt(this.x, this.y+1) == "k"){
					this.setHealth(this.getHealth() + 5);
					for(int i = 0; i < map.locations.size(); i++){
						if(map.locations.get(i).getX() == x && map.locations.get(i).getY() == y+1)
							map.locations.remove(i);
					}
				}
				if(map.itemAt(this.x, this.y+1) == ">"){
					x = rand.nextInt(24);
					y = rand.nextInt(24);
					while(map.location(x, y) != "."){
						x = rand.nextInt(24);
						y = rand.nextInt(24);
					}
					map.setPlayer(x, y);
				}
			}
			if(map.checkOpen(this.x, this.y+1))
			{
				this.y++;
				map.setPlayer(this.x, this.y);
				return true;
			}
			break;
			
		case WEST:
			for(int i = 0; i < map.monsters.size(); i++)
				map.monsters.get(i).move(this.x, this.y, map, this);
			if(map.isItem(this.x, this.y-1)){
				if(map.itemAt(this.x, this.y-1) == "k"){
					this.setHealth(this.getHealth() + 5);
					for(int i = 0; i < map.locations.size(); i++){
						if(map.locations.get(i).getX() == x && map.locations.get(i).getY() == y-1)
							map.locations.remove(i);
					}
				}
				if(map.itemAt(this.x, this.y-1) == ">"){
					map = new RoguelikeMap();
					x = rand.nextInt(24);
					y = rand.nextInt(24);
					while(map.location(x, y) != "."){
						x = rand.nextInt(24);
						y = rand.nextInt(24);
					}
					map.setPlayer(x, y);
				}
			}
			if(map.checkOpen(this.x, this.y-1))
			{
				this.y--;
				map.setPlayer(this.x, this.y);
				return true;
			}
			break;
		}
		return false;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	public boolean isgameover(){
		if(health <= 0)
			return true;
		return false;
	}
}
