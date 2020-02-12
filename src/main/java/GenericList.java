import java.util.Iterator;

abstract class GenericList<I> implements CreateIterator<I>{

	
	Node<I> head;	//A linked list, could change to another data structure
	private int length;

	public abstract void add(I data);

	public void setLength(boolean how) {

		if (how) {
			++length;
		}

		else {
			--length;
		}
	}

	public int getLength() {
		return length;
	}

	public I delete() {

		if (head == null) {
			return null;
		}

		I obj = head.data;
		head = head.next;
		return obj;

	}
	

	public void print() {

		if (head == null) {
			System.out.println("Empty List!");
		} else {

			Node<I> temp = head;

			while (temp != null) {
				System.out.println(temp.data);
				temp = temp.next;
			}
		}
	}
	
	public Iterator<I> createIterator(){
		
		return new GLIterator<I>(head);
	}
	
	class Node<T> {
		T data;
		Node<T> next;
	}

	
	
}
