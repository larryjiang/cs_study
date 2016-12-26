package main.clrs.datastructures.elementarydatastructures;

public interface Set<T> extends Container<T>{
	
	/**
	 * Remove an object in the set.
	 * @return true if the underlying structure is 
	 * change and false otherwise
	 */
	@Override
	public boolean remove(Object o);

}
