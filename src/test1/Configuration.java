package test1;

public class Configuration {
	
	private static Configuration istance = null;

	private String dataSetFileName = "/home/e-vampire/workspace/iris.data";
	private int gridWidth = 75;
	private int gridHeight = 75;
	private int numAnts = 50;
	private double alpha = 0.25;
	private int s = 3;
	private int speed = 75;
	private int maxSpeed = 75;
	private double pickingCostant = 0.1;
	private double droppingCostant = 0.3;
	private int maxIterations = 1000000;
	private int memorySize = 20;
	
	public String getDataSetFileName() {
		return dataSetFileName;
	}
	public int getGridWidth() {
		return gridWidth;
	}
	public int getGridHeight() {
		return gridHeight;
	}
	public int getNumAnts() {
		return numAnts;
	}
	public double getAlpha() {
		return alpha;
	}
	public int getS() {
		return s;
	}
	public int getSpeed() {
		return speed;
	}
	public int getMaxSpeed() {
		return maxSpeed;
	}
	public double getPickingCostant() {
		return pickingCostant;
	}
	public double getDroppingCostant() {
		return droppingCostant;
	}
	public int getMaxIterations() {
		return maxIterations;
	}
	public int getMemorySize() {
		return memorySize;
	}
	public void setDataSetFileName(String dataSetFileName) {
		this.dataSetFileName = dataSetFileName;
	}
	public void setGridWidth(int gridWidth) {
		this.gridWidth = gridWidth;
	}
	public void setGridHeight(int gridHeight) {
		this.gridHeight = gridHeight;
	}
	public void setNumAnts(int numAnts) {
		this.numAnts = numAnts;
	}
	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}
	public void setS(int s) {
		this.s = s;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	public void setPickingCostant(double pickingCostant) {
		this.pickingCostant = pickingCostant;
	}
	public void setDroppingCostant(double droppingCostant) {
		this.droppingCostant = droppingCostant;
	}
	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}
	public void setMemorySize(int memorySize) {
		this.memorySize = memorySize;
	}
	
	private Configuration(){}
	
	public static synchronized Configuration getConfiguration(){
		if(istance == null)
			istance = new Configuration();
		return istance;
	}
	
}
