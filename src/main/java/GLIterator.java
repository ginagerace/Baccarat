import java.util.Iterator;

public class GLIterator<I> implements Iterator<I>{

	GenericList<I>.Node<I> theList;
	
	GLIterator(GenericList<I>.Node<I> list){
		this.theList = list;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		if(theList == null) {
		return false;
		}
		else {
			return true;
		}
	}

	@Override
	public I next() {
		// TODO Auto-generated method stub
		I val = theList.data;
		theList = theList.next;
		return val;
	}

}
