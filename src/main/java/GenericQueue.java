public class GenericQueue<I> extends GenericList<I> {
	 
	
	GenericQueue(I data){
		add(data);
		this.setLength(true);
	}
	
	public void enqueue(I data) {
		add(data);
		this.setLength(true);
	}
	
	public I dequeue() {
		if(head != null) {
			this.setLength(false);
		}
		return delete();
	}
	@Override
	public void add(I data) {
		// TODO Auto-generated method stub
		
		Node<I> iter = new Node<I>();
		
		if(head == null) {
		Node<I> temp = new Node<I>();
		temp.data = data;
		temp.next = head;
		head = temp;
		return;
		}
		else {
			
			iter = head;
			
			while(iter.next != null) {
				iter = iter.next;
			}
		}
		
		Node<I> newNode = new Node<I>();
		newNode.data = data;
		newNode.next = null;
		iter.next = newNode;
		
	}

}
