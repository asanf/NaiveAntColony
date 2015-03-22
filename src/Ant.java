package test1;

import sim.util.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import sim.engine.*;

public class Ant implements Steppable{

	private static final long serialVersionUID = 1352639112746288267L;
	public boolean isCarryingObject;
	public Item carriedItem;
	private int speed;
	private Int2D position;
	private Queue<Item> memory;
	private int memoryMaxSize;
	
	public Ant(int speed){
		this.speed = speed;
		isCarryingObject = false;
		carriedItem = null;
		memory = new LinkedList<Item>();
		memoryMaxSize = Configuration.getConfiguration().getMemorySize();
	}
	
	public Ant(int speed, Int2D pos){
		this(speed);
		position = pos;
	}
	
	public void setPosition(Int2D pos){
		position = pos;
	}
	
	public double f(Item i, ArrayList<Item> neighbors){
		int s = Configuration.getConfiguration().getS();
		double alpha = Configuration.getConfiguration().getAlpha();
		int maxSpeed = Configuration.getConfiguration().getMaxSpeed();
		double sum = 0;
		double denom = alpha* (1 + (speed -1)/maxSpeed);
		
		for(Item j: neighbors){
			sum += (1 - (i.distance(j)/denom));
		}
		double result = Math.max(0, sum/Math.pow(s, 2));
		
		return result; 
		
	}
	
	public double pPick(double f){
		double k1 = Configuration.getConfiguration().getPickingCostant();
		return Math.pow(k1/(k1 + f), 2);
	}
	
	public double pDrop(double f){
		double k2 = Configuration.getConfiguration().getDroppingCostant();
		return (f < k2) ? 2*f : 1;
	}
	
	public Int2D move(SimState state){
		double xDir, yDir;
		int xNew, yNew;
		boolean outOfBounds;
		
		do{
			xDir = (state.random.nextDouble()*2) - 1;
			yDir = (1 - Math.abs(xDir));
			yDir = yDir * ((state.random.nextInt(2) == 0) ? -1 : 1);
	
			
			xNew = position.x + (int)(speed * xDir);
			yNew = position.y + (int)(speed * yDir);
			outOfBounds = xNew < 0 || xNew >= Configuration.getConfiguration().getGridWidth();
			outOfBounds = outOfBounds || yNew < 0 || yNew >= Configuration.getConfiguration().getGridHeight();
			
		} while(outOfBounds);
		
		return new Int2D(xNew, yNew);
	}
	
	public void step(SimState state){
		Colony ants = (Colony) state;
		Item itemAtAntPosition = (Item)ants.itemGrid.get(position.x, position.y);
		ArrayList<Item> neighbor = ants.getNeighborsAtPosition(position);
		
		if( this.isCarryingObject && itemAtAntPosition == null){
			double pDrop = pDrop(f(carriedItem,neighbor));
			
			if(ants.random.nextBoolean(pDrop)){
				System.out.println("DROP");
				carriedItem.setPosition(position);
				carriedItem.isCarried = false;
				ants.itemGrid.set(position.x, position.y, carriedItem);
				memory.add(carriedItem);
				if(memory.size() >= memoryMaxSize) memory.poll();
				carriedItem = null;
				isCarryingObject = false;
			}
		}else if( !this.isCarryingObject && itemAtAntPosition != null){
			double pPick = pPick(f(itemAtAntPosition, neighbor));
			
			if(ants.random.nextBoolean(pPick)){
				System.out.println("pick");
				carriedItem = itemAtAntPosition;
				carriedItem.isCarried = true;
				this.isCarryingObject = true;
				ants.itemGrid.set(position.x, position.y, null);
				itemAtAntPosition = null;
				double minDistance = 10000000000.0;
				double d = 0;
				Item nearest = null;
				
				for( Item memorizedItem : memory){
					if(memorizedItem == null) continue;
					d = carriedItem.distance(memorizedItem);
					if(d < minDistance){
						minDistance = d;
						nearest = memorizedItem;
					}
				}
				if(nearest != null){
					ants.antsGrid.set(position.x, position.y, null);
					this.position = new Int2D(nearest.position.x, nearest.position.y);
					ants.antsGrid.set(position.x, position.y, this);
				}
			}
		}
			
		ants.antsGrid.set(this.position.x, this.position.y, null);
		this.position = move(ants);
		ants.antsGrid.set(this.position.x, this.position.y, this);
	}
}
