package test1;
import sim.util.*;

public class Item {
	
	public double[] features;
	public Int2D position;
	public boolean isCarried;
	private int correctClassification;
	
	public Item(double[] feats, int nFeatures, int correctClassification){
		features = new double[nFeatures];
		System.arraycopy(feats, 0, features, 0, nFeatures);
		isCarried = false;
		this.correctClassification = correctClassification;
	}
	
	public void setPosition(Int2D pos){
		position = pos;
	}
	
	
	public int getCorrectClassification(){
		return this.correctClassification;
	}
	
	
	public double distance(Item otherItem){
		double sum = 0;
		for(int i = 0; i < features.length; i++)
			sum += Math.pow((otherItem.features[i] - this.features[i]),2);
		return Math.sqrt(sum);
	}

	
}
