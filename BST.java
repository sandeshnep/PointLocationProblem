import java.awt.Point;

public interface BST<T extends Comparable<T>> {
	public void printInOrder(MyTreeNode<T> root);
}