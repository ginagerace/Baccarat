
public class GenericStack<I> extends GenericList<I> {

	GenericStack(I data){
		head = new Node<I>();
		head.data = data;
		this.setLength(true);
	}
	
	public void push(I data) {
		add(data);
		this.setLength(true);
	}
	
	public I pop() {
		if(head != null) {
			this.setLength(false);
		}
		return delete();
	}
	@Override
	public void add(I data) {
		// TODO Auto-generated method stub
		Node<I> temp = new Node<I>();
		temp.data = data;
		temp.next = head;
		head = temp;
		
	}

}
