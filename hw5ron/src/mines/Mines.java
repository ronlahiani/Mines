package mines;

import java.util.Random;

public class Mines {
	private Place[][] bord;
	private int numMines, height, width;
	private boolean showAll;

	public Mines(int height, int width, int numMines) { // builder for the game.
		this.height = height;
		this.width = width;
		this.numMines = numMines;
		this.showAll = false;
		bord = new Place[height][width];
		initBord(); // initialize the game
		initMines();
	}

	public void initBord() {
		for (int i = 0; i < height; i++) // initialize the game.
			for (int j = 0; j < width; j++) {
				bord[i][j] = new Place(i, j);
			}
	}

	public void initMines() { // put random mines in random places;
		Random rand = new Random();
		for (int x = 0; x < numMines; x++) {
			while (true) {
				int i = rand.nextInt(height);
				int j = rand.nextInt(width);
				if(addMine(i,j)) //if mine added succsesfully 
					break;
			}
		}
	}

	public boolean inLimits(int i, int j) { // checks if is in bounds.
		if (i < 0 || i >= height || j < 0 || j >= width)
			return false;
		return true;
	}

	public boolean addMine(int i, int j) { // adding mines to the bord and updating places.
		if (!inLimits(i, j))
			return false;
		if (bord[i][j].isMine)
			return false;
		bord[i][j].isMine = true;
		bord[i][j].minesArround = 0;
		// checks the neighbors and update that their neighbor is mine.

		if (i - 1 >= 0 && j - 1 >= 0 && !bord[i - 1][j - 1].isMine) {
			bord[i - 1][j - 1].setMines();
		}
		if (i - 1 >= 0 && !bord[i - 1][j].isMine) {
			bord[i - 1][j].setMines();
		}
		if (j - 1 >= 0 && !bord[i][j - 1].isMine) {
			bord[i][j - 1].setMines();
		}
		if (i + 1 < height && j + 1 < width && !bord[i + 1][j + 1].isMine) {
			bord[i + 1][j + 1].setMines();
		}
		if (i + 1 < height && !bord[i + 1][j].isMine) {
			bord[i + 1][j].setMines();
		}
		if (j + 1 < width && !bord[i][j + 1].isMine) {
			bord[i][j + 1].setMines();
		}
		if (i - 1 >= 0 && j + 1 < width && !bord[i - 1][j + 1].isMine) {
			bord[i - 1][j + 1].setMines();
		}
		if (i + 1 < height && j - 1 >= 0 && !bord[i + 1][j - 1].isMine) {
			bord[i + 1][j - 1].setMines();
		}

		// done updating, return true.
		return true;
	}

	public void toggleFlag(int x, int y) {
		// if flag is not exist
		if (!bord[x][y].isOpen && !bord[x][y].isFlag)
			bord[x][y].isFlag = true;
		else if (bord[x][y].isFlag) // if flag is exist - change it.
			bord[x][y].isFlag = false;
	}

	public String get(int i, int j) { // getting the sign for the cell
		if (showAll) {
			if (bord[i][j].isMine) // if its mine return x
				return "X";
			if (bord[i][j].minesArround == 0)
				return " ";
			if (bord[i][j].minesArround > 0)
				return bord[i][j].minesArround + "";
		} else { // if we didnt finish , return the value of the cell.
			if (bord[i][j].isOpen && bord[i][j].isMine)
				return "X";
			if (!bord[i][j].isOpen && !bord[i][j].isFlag)
				return ".";
			if (!bord[i][j].isOpen && bord[i][j].isFlag)
				return "F";
			if (bord[i][j].isOpen && bord[i][j].minesArround == 0)
				return " ";
			if (bord[i][j].isOpen && bord[i][j].minesArround > 0)
				return bord[i][j].minesArround + "";
		}
		return ".";
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public boolean isDone() { // method will tell us if all clear places opened
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				if (!bord[i][j].isOpen && !bord[i][j].isMine) // if we found place is not mine and closed.
					return false;
			}
		return true;
	}

	public boolean open(int i, int j) { // opening all the cells that can be opened.
		if (!inLimits(i, j)) // i , j in the bounds of matrix
			return false;
		if (bord[i][j].isFlag) // if its flag we dont open it
			return false;
		if (bord[i][j].isOpen) // if its already open we skip
			return false;
		if (bord[i][j].isMine) { // if its a mine return false;
			return false;
		}
		if (bord[i][j].minesArround > 0) {
			bord[i][j].isOpen = true;
			return true;
		}
		bord[i][j].isOpen = true;
		if (bord[i][j].minesArround == 0) {
			// open neighbors that is not mines and empty arround.
			if ((inLimits(i - 1, j)) && !bord[i - 1][j].isMine)
				open(i - 1, j);
			if ((inLimits(i - 1, j - 1)) && !bord[i - 1][j - 1].isMine)
				open(i - 1, j - 1);
			if ((inLimits(i, j - 1)) && !bord[i][j - 1].isMine)
				open(i, j - 1);
			if ((inLimits(i + 1, j + 1)) && !bord[i + 1][j + 1].isMine)
				open(i + 1, j + 1);
			if ((inLimits(i + 1, j)) && !bord[i + 1][j].isMine)
				open(i + 1, j);
			if ((inLimits(i, j + 1)) && !bord[i][j + 1].isMine)
				open(i, j + 1);
			if ((inLimits(i - 1, j + 1)) && !bord[i - 1][j + 1].isMine)
				open(i - 1, j + 1);
			if ((inLimits(i + 1, j - 1)) && !bord[i + 1][j - 1].isMine)
				open(i + 1, j - 1);
		}
		return true;
	}

	public String toString() { // printing the bord matrix style
		StringBuilder string = new StringBuilder();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				string.append(get(i, j));
			}
			string.append("\n");
		}
		return string.toString();
	}

	private class Place { // an object that the game is full of Places, will help us to identify mines and
							// if open\flag.
		private Boolean isMine, isOpen, isFlag;
		private int minesArround;

		public Place(int i, int j) { // builder for this class
			isMine = false;
			isOpen = false;
			isFlag = false;
			this.minesArround = 0;
		}

		public void setMines() { // increasing the mines around this place plus one.
			this.minesArround++;
		}
	}
}
