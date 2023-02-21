package iterator;

import java.util.Iterator;

public class Combined<E> implements Iterable<E> { //generic class we will create iterator for it.
	private Iterator<E> first, second;

	public Combined(Iterable<E> first, Iterable<E> second) {
		this.first = first.iterator();
		this.second = second.iterator();
	}

	@Override
	public Iterator<E> iterator() { //returning the iterator
		return new myCombined();
	}

	private class myCombined implements Iterator<E> {
		private boolean flag = true; //the flag will help us to manage the turns.

		@Override
		public boolean hasNext() { //while one of them has next , we will continue.
			return (first.hasNext() || second.hasNext()); 
		}

		@Override
		public E next() { //method that will return the next by it turn
			if (flag) {
				if (second.hasNext())
					flag = false; //marking for secound to go 
				return first.next();
			} else {

				if (first.hasNext())
					flag = true; //marking for the first to go .
				return second.next();
			}
		}
	}

}
