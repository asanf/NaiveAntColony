package test1;
import sim.engine.*;
import javax.swing.*;
import sim.display.*;
import java.awt.Color;
import sim.portrayal.grid.*;
import sim.portrayal.simple.*;

public class AntsGUI extends GUIState{
	
	public Display2D display;
	public JFrame frame;
	ObjectGridPortrayal2D antsPortrayal = new ObjectGridPortrayal2D();
	ObjectGridPortrayal2D itemPortrayal = new ObjectGridPortrayal2D();
	
	public static void main(String[] args){
		AntsGUI antsGUI = new AntsGUI();
		Console c = new Console(antsGUI);
		c.setVisible(true);
	}
	
	public AntsGUI(){ super(new Colony(System.currentTimeMillis()));}
	
	public AntsGUI(SimState state){ super(state);}
	
	public static String getName(){return "Ant Colony Clustering";}

	public void start(){
		super.start();
		setupPortrayals();
	}
	
	public void load(SimState state){
		super.load(state);
		setupPortrayals();
	}
	
	public void setupPortrayals(){
		Colony ants = (Colony) state;
		antsPortrayal.setField(ants.antsGrid);
		antsPortrayal.setPortrayalForNonNull(new OvalPortrayal2D(Color.white));
		itemPortrayal.setField(ants.itemGrid);
		itemPortrayal.setPortrayalForNonNull(new IrisPortrayal());
		//TODO come faccio a cambiare colore in base alla classe d'appartenenza?
		
		display.reset();
		display.setBackdrop(new Color(30,30,30));
		display.repaint();
	}
	
	public void init(Controller c){
		super.init(c);
		display = new Display2D(400,400,this);
		display.setClipping(false);
		
		frame = display.createFrame();
		frame.setTitle(getName());
		c.registerFrame(frame);
		frame.setVisible(true);
		display.attach(antsPortrayal, "Ants");
		display.attach(itemPortrayal, "Items");
	}
	
	public void quit(){
		super.quit();
		if(frame != null) frame.dispose();
		frame = null;
		display = null;
	}
}
