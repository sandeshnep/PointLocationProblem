import java.awt.Point;


public class MyTreeNode<T extends Comparable<T>> {
	public Point[] data;
	public MyTreeNode<T> leftChild;
	public MyTreeNode<T> rightChild;
	public MyTreeNode<T> parent;
	
	//Constructor
	public MyTreeNode (Point[] data2){
		this.data = data2;
	}
	
} 