package iterator;

import java.util.Iterator;

public class TwoArrays implements Iterable<Integer> { //manage the iterator for two arrays each one on it turn.

	private int[] a1, a2;

	public TwoArrays(int[] a1, int[] a2) { //builder for the arrays.
		this.a1 = a1;
		this.a2 = a2;
	}

	@Override
	public Iterator<Integer> iterator() { //returning the iterator we implemented.
		return new myTwoArrays();

	}

	private class myTwoArrays implements Iterator<Integer> {
		private int countA = 0, countB = 0;
		private boolean flag = true; //flag will help us manage the turns

		@Override
		public boolean hasNext() { //returning if one of them not finished
			return (countA < a1.length || countB < a2.length); 
		}

		@Override
		public Integer next() { //return the next integer by his turn.
			if (flag) {
				if(countB<a2.length)
					flag=false; //marking for the second array to go 
				return a1[countA++];
			}
			else
			{
				if(countA<a1.length)
					flag=true; //marking for the first array to go.
				return a2[countB++];
			}
		}
	}
}
