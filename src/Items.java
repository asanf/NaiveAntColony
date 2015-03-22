package test1;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
public class Items {

	public ArrayList<Item> list;
	
	public Items(){
		list = new ArrayList<Item>();
	}
	
	public Items(String fileName){
		this();
		loadFromFile(fileName);		
	}
	
	public void loadFromFile(String fileName){
		double [] features;
		double [] featuresMax;
		int numFeatures;
		BufferedReader reader;
		String line;
		String[] tokens;
		String lastClass = "";
		int currClass = -1;
		
		
		try{
			reader = new BufferedReader(new FileReader(fileName));
			
			numFeatures = Integer.parseInt(reader.readLine());
			
			features = new double[numFeatures];
			featuresMax = new double[numFeatures];
			for(int i = 0; i < numFeatures; i++) featuresMax[i] = 0;
			
			while((line = reader.readLine()) != null){
				tokens = line.split(",");
				for(int i = 0; i < numFeatures; i++){
					features[i] = Double.parseDouble(tokens[i]);
					if (features[i] > featuresMax[i])
						featuresMax[i] = features[i];
				}
				if(tokens[tokens.length-1].compareToIgnoreCase(lastClass) != 0){
					++currClass;
					lastClass = tokens[tokens.length -1 ];
				}
					
				
				list.add(new Item(features,
						numFeatures,
						currClass));
				
			}
			for(Item i: list){
				for(int j = 0; j < numFeatures; j++)
					i.features[j] /= featuresMax[j];
			}
		}catch(IOException e){
			System.out.println("Errore I/O");
		}
		
		
		
	}
}
