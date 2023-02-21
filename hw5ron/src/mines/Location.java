package mines;

import javafx.scene.control.Button;

public class Location extends Button { //class that will use us for the location in grid.
	private int x, y; //integers for the indexes
	public Location(int x, int y) { //builder
		this.x = x;
		this.y = y;
	}

	public int getX() {//return x
		return x;
	}

	public int getY() { // return y 
		return y;
	}
}
