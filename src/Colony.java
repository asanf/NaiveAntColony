package test1;
import sim.engine.*;
import java.util.ArrayList;
import sim.field.grid.*;
import sim.util.*;

public class Colony extends SimState{
	
	private static final long serialVersionUID = 1L;
	Configuration conf;
	public ObjectGrid2D itemGrid;
	public ObjectGrid2D antsGrid;
	
	public Items items;
	
	public Colony (long seed){
		super(seed);
		conf = Configuration.getConfiguration();
		items = new Items(Configuration.getConfiguration().getDataSetFileName());
	}
	
	public ArrayList<Item> getNeighborsAtPosition(Int2D position){
		int s = conf.getS();
		ArrayList<Item> toReturn = new ArrayList<Item>();
		Bag near = itemGrid.getNeighborsMaxDistance(position.x, position.y, (s-1)/2, false, null, null, null);
		for(Object i: near){
			if(i != null)
				toReturn.add((Item) i);
		}
		
		return toReturn;
		
	}
	
	public void start(){
		int _x, _y; //coordinate temporanee
		
		super.start();
		//inizializzazioni
		itemGrid = new ObjectGrid2D(conf.getGridWidth(), 
				conf.getGridHeight());
		antsGrid = new ObjectGrid2D(conf.getGridWidth(), 
				conf.getGridHeight());
		
		for(Item i: items.list){
			do {
				_x = (int) (conf.getGridWidth() * random.nextDouble());
				_y = (int) (conf.getGridHeight() * random.nextDouble());
				
			} while(itemGrid.get(_x, _y) != null);
			itemGrid.set(_x, _y, i);
			i.setPosition(new Int2D(_x,_y));
		}
		
		Ant ant;
		
		for(int i = 0; i < conf.getNumAnts(); i++){
			int speed = random.nextInt(conf.getMaxSpeed())+1;
			ant = new Ant(speed);

			do {
				_x = (int) (conf.getGridWidth() * random.nextDouble());
				_y = (int) (conf.getGridHeight() * random.nextDouble());
				
			} while(antsGrid.get(_x, _y) != null);
			
					
			antsGrid.set(_x, _y, ant);
			ant.setPosition(new Int2D(_x, _y));
			//schedule
			schedule.scheduleRepeating(ant);
		}
		
		
	}
	
	public static void main(String[] args){
		doLoop(Colony.class, args);
		System.exit(0);
	}

}
