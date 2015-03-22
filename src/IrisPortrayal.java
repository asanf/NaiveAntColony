package test1;
import java.awt.Color;
import java.awt.Graphics2D;

import sim.field.grid.ObjectGrid2D;
import sim.portrayal.*;
import sim.portrayal.grid.ObjectGridPortrayal2D;
import sim.portrayal.simple.OvalPortrayal2D;
import sim.util.gui.ColorMap;

public class IrisPortrayal extends OvalPortrayal2D{
	
	public void draw(Object object, Graphics2D graphics, DrawInfo2D info){
		Item item;
		Color[] colorTable= new Color[10];
		colorTable[0]=Color.blue;
		colorTable[1]=Color.red;
		colorTable[2]=Color.green;
		colorTable[3]=Color.yellow;
		colorTable[4]=Color.magenta;
		colorTable[5]=Color.cyan;
		colorTable[6]=Color.orange;
		colorTable[7]=Color.pink;
		
		if(object != null){
			item = (Item)object;
			
			paint = colorTable[item.getCorrectClassification()]; 
			
		}
		super.draw(object, graphics, info);
	}
	

}
