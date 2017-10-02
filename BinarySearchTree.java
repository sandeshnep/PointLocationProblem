
import java.awt.Point;
import java.awt.geom.*;
import java.awt.*;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class BinarySearchTree<T extends Comparable<T>> extends Test implements BST <T>{
	public static Point intersectP;
	public static int externalnodescalc;
	public static int externalpathlength;
	public static int nodenum=0;
	public static  ArrayList<Integer> pathpointa = new ArrayList<Integer>();
	public  static ArrayList<Integer> pathpointb = new ArrayList<Integer>();
	
	MyTreeNode<T> root;
	public BinarySearchTree(){
		root=null;
	}
	
	
	public void insert(Point[] data) {
		root = insert(root, data);
	}
	private MyTreeNode<T> insert(MyTreeNode<T> p, Point[] toInsert ){
		
		if(p==null){
			return new MyTreeNode<T>(toInsert);}
		
			    
				if (ccw3(p.data,toInsert[0]).equals("ccw")){
					
								//If the lines intersect
								if(inter(toInsert[0], toInsert[1], p.data[0], p.data[1])==true){
									p.leftChild = insert(p.leftChild, new Point[]{toInsert[0],   interp(p.data[0], p.data[1], toInsert[0], toInsert[1]),  toInsert[2]});
								}
								else {
									p.leftChild = insert(p.leftChild, toInsert);
								}

					if(ccw3(p.data,toInsert[1]).equals("cw")){
								//If the lines intersect
								if(inter(toInsert[0], toInsert[1], p.data[0], p.data[1])==true){
									p.rightChild = insert(p.rightChild, new Point[]{toInsert[1], interp(p.data[0], p.data[1], toInsert[0], toInsert[1]),  toInsert[2]});
								}
								else{
									p.rightChild=insert(p.rightChild, toInsert);}
								}
				}
				else
					if(ccw3(p.data,toInsert[0]).equals("cw")){
								//If the lines intersect
								if(inter(toInsert[0], toInsert[1], p.data[0], p.data[1])==true){
									p.rightChild = insert(p.rightChild, new Point[]{toInsert[0],   interp(p.data[0], p.data[1], toInsert[0], toInsert[1]),  toInsert[2]});
								}
								else{
									p.rightChild=insert(p.rightChild, toInsert);}
						
						if(ccw3(p.data,toInsert[1]).equals("ccw")){
							
								//If the lines intersect
								if(inter(toInsert[0], toInsert[1], p.data[0], p.data[1])==true){
									p.leftChild = insert(p.leftChild, new Point[]{toInsert[1],  interp(p.data[0], p.data[1], toInsert[0], toInsert[1]),  toInsert[2]});
								}
								else{
								 p.leftChild=insert(p.leftChild, toInsert);}}
							
					}
		
		return p;
		
	}
	


	public String ccw3(Point[] Line, Point p2) {
		
		 Point[] Linesorted;
		 if(Line[0].y< Line[1].y){
			 Linesorted = new Point[] {Line[0], Line[1]};
		 }
		 else
			 Linesorted = new Point[] {Line[1], Line[0]};
		 
		 double dx1 = Linesorted[1].x - Linesorted[0].x;
		 double dy1 = Linesorted[1].y - Linesorted[0].y;
		 double dx2 = p2.x - Linesorted[0].x;
		 double dy2 = p2.y - Linesorted[0].y;
		 
		 if (dx1*dy2 > dy1*dx2) return "ccw";
		 else if (dx1*dy2 < dy1*dx2)  return "cw";
		 else if ((dx1*dx2 < 0) || (dy1*dy2 < 0)) return "cw";
		 else if ((dx1*dx1+dy1*dy1) < (dx2*dx2+dy2*dy2)) return "ccw";
		 else  return "co";
		}

//-----------------------------------------------------------------------------------------------PRINT IN ORDER Method-------------------------------------
	public void printInOrder(MyTreeNode<T> node) {
	  if(node!=null){
		  printInOrder(node.leftChild);
		  System.out.print(node.data[2].x);
		  printInOrder(node.rightChild);
	  }
	
	}
	
	public void externalnodecalc(MyTreeNode<T> node) {
		if(node==null){
			externalnodescalc++;
		}
		else
		  if(node!=null){ 
			  //total number of nodes
			  externalnodecalc(node.leftChild);
			  externalnodecalc(node.rightChild);
	
		  }
		
		}
	
	public int InternalPathLength ( MyTreeNode<T> root, int x ){
	    if (root == null) return 0;

	    return InternalPathLength (root.leftChild, x+1) + InternalPathLength (root.rightChild, x+1 ) + x;
	}
	
	public int Countnodes ( MyTreeNode<T> root )
	{
	    if ( root == null ) return 0;
	    
	    return Countnodes (root.leftChild) + Countnodes (root.rightChild) + 1;
	}


	
	public void comparepoints(Point a, Point b){
		pathgeneratora(root, a);
		pathgeneratorb(root, b);
	
		System.out.print("Path Point a : ");
		Printpath(pathpointa);
		System.out.println();
		System.out.print("Path Point b : ");
		Printpath(pathpointb);
		
		//Are the Points in the same region?
		if(Arrays.equals(pathpointa.toArray(), pathpointb.toArray())){
			System.out.println();
			System.out.println("The Points are in the same region.");
		}
		else{
			System.out.println();
			System.out.println("The Points are NOT in the same region.");
			for(int i=0; i<pathpointa.size(); i++){
				if(pathpointa.get(i)!= pathpointb.get(i)){
					System.out.println("The Points are saperated by Line " + pathpointa.get(i-1));
					break;
				}
			}
		}
	}
	
	public void Printpath(ArrayList<Integer> a){
		
		for(int i = 0; i<a.size(); i++){
			if(i%2==0){
				System.out.print("Line " + a.get(i));
			}
			else
			{
				if(a.get(i)==-1){
					System.out.print("-> left -> ");
				}
				else
					System.out.print("-> right -> ");
			}
		}
	}
	public void pathgeneratora(MyTreeNode<T> node, Point a){
		
		// -1 = left, 1 = right
		// PATH A PATH GENERATOR
		if(node!=null){
			if(ccw3(node.data, a).equals("ccw")){
			//	System.out.println(node.data[2].x);
				pathpointa.add(node.data[2].x);
				
			//	System.out.println("left");
				pathpointa.add(-1);
				
				pathgeneratora(node.leftChild, a);
			}
			else
				if(ccw3(node.data, a).equals("cw")){
			//		System.out.println(node.data[2].x);
					pathpointa.add(node.data[2].x);
					
			//		System.out.println("right");
					pathpointa.add(1);
					
					pathgeneratora(node.rightChild, a);
				}
		}
		
		
	}
	
	
	
	public void pathgeneratorb(MyTreeNode<T> node, Point a){
		
		// -1 = left, 1 = right
		// PATH A PATH GENERATOR
		if(node!=null){
			if(ccw3(node.data, a).equals("ccw")){
			//	System.out.println(node.data[2].x);
				pathpointb.add(node.data[2].x);
				
			//	System.out.println("left");
				pathpointb.add(-1);
				
				pathgeneratorb(node.leftChild, a);
			}
			else
				if(ccw3(node.data, a).equals("cw")){
			//		System.out.println(node.data[2].x);
					pathpointb.add(node.data[2].x);
					
			//		System.out.println("right");
					pathpointb.add(1);
					
					pathgeneratorb(node.rightChild, a);
				}
		}
	}

	
	

//--------------------------------------THIS CODE WAS SUPPLIED BY YANG YENG---------------------------------------------------------------------------------------------------------------

	//-----------------------------INTERSEcTION2 BOOLEAN---------------------------
	public double eps = 1e-10;
	public boolean inter(Point a, Point b, Point c, Point d) {
	    if (Math.min(a.x, b.x) > Math.max(c.x, d.x) ||
	            Math.min(a.y, b.y) > Math.max(c.y, d.y) ||
	            Math.min(c.x, d.x) > Math.max(a.x, b.x) ||
	            Math.min(c.y, d.y) > Math.max(a.y, b.y))
	        return false;
	    double h, i, j, k;
	    h = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
	    i = (b.x - a.x) * (d.y - a.y) - (b.y - a.y) * (d.x - a.x);
	    j = (d.x - c.x) * (a.y - c.y) - (d.y - c.y) * (a.x - c.x);
	    k = (d.x - c.x) * (b.y - c.y) - (d.y - c.y) * (b.x - c.x);
	    return h * i <= eps && j * k <= eps;
	}
	//----------------------------------------------------------------------------

	//Intersection Return Points
	class line {
	    double a, b, c;
	    line(Point s, Point e) {
	        a = s.y - e.y;
	        b = e.x - s.x;
	        c = s.x * e.y - e.x * s.y;
	    }
	}

	Point interp(Point a, Point b, Point c, Point d) {
	    Point res = new Point();
	    line l1 = new line(a, b);
	    line l2 = new line(c, d);
	    res.x = (int) ((l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b));
	    res.y = (int) ((l1.c * l2.a - l2.c * l1.a) / (l1.a * l2.b - l2.a * l1.b));
	    return res;
	}
	//-----------------------------------------------------------------
	
	
}

