import java.util.LinkedList;
import java.awt.Point;

public class State {
	private State parent;
	private int[][] config;
	private int classification;
	private int utilityValue;
	
	private LinkedList<State> nodes = new LinkedList<State>();


	/*CONSTRUCTORS FOR STATE*/
	
	// root state
	public State () {
		config = new int[3][3];
		parent = null;

		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = 0;
			}
		}
	}

	// root state with predefined configuration
	public State (int[][] tiles) {
		config = new int[3][3];
		parent = null;

		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = tiles[x][y];
			}
		}
	}
	
	// child state with predefined configuration
	public State (int[][] tiles, State oldState) {
		config = new int[3][3];
		parent = oldState;

		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = tiles[x][y];
			}
		}
	}
	
	public State (int[][] tiles, int classInput) {
		config = new int[3][3];

		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = tiles[x][y];
			}
		}
		
		/*
		    classInput values
		        -1 = minimization
		         0 = utility
		         1 = maximization
		*/
		this.classification = classInput;
	}
	
	// state with configuration, parent state and classification(minimization, maximization, or utility)
	public State (int[][] tiles, int classInput, State oldState) {
		config = new int[3][3];
		parent = oldState;
		
		/*
		    classInput values
		        -1 = minimization
		         0 = utility
		         1 = maximization
		*/
		this.classification = classInput;

		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				config[x][y] = 0;
			}
		}
	}
	/*END OF CONSTRUCTORS*/

    public void computeUtility(){
        int totalValue = 0;
    
        for(int i=0; i<3;i++){
            for(int j=0; j<3; j++){
                if(config[i][j] == 1){
                    totalValue -= 2;
                }
                if(config[i][j] == 2){
                    totalValue += 2;
                }
            }
        }
        
        this.utilityValue = totalValue;
    }

	// prints the current config of the state
	public void printConfig () {
		for (int x = 0; x < 3; x += 1) {
			for (int y = 0; y < 3; y += 1) {
				System.out.print(config[x][y]);
				System.out.print(" ");
			}
			System.out.print("\n");
		}

		System.out.print("\n");
	}
	
	public void setType(int nodeType){
	    this.classification = nodeType;
	}

	// prints a point
	public void printPoint (Point point) {
		System.out.println(point.x + " and " + point.y);
		System.out.println("");            
	}

	// get configuration of the state
	public int[][] getConfig () {
		return config;
	}
	
	// get value of config per tile
	public void setConfigValue (int x, int y, int value) {
		config[x][y] = value;
	}

	// get value of config per tile
	public int getConfigValue (int x, int y) {
		return config[x][y];
	}
	
	// get parent
	public State getParent () {
		return parent;
	}
	
	//get child nodes of this state
	public LinkedList<State> getNodes(){
	    return nodes;
	}
	
	public int getNodeType(){
	    return classification;
	}
}
