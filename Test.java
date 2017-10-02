
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.geom.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Test extends JFrame{
	static int counter=0;
	static int mousecounter=0;
	static ArrayList<Point[]> lines = new ArrayList<Point[]>();
	static ArrayList<Point[]> sortedlines = new ArrayList<Point[]>();
	static Point pointclicked = new Point(-10,-10);
	static Point pointclicked2 = new Point(-10,-10);
	

	public static void main(String[] args) {
		
		//--------------------------------------------------------PLEASE INSERT THE LINES HERE IN THE FORMAT CONSISTENT WITH THE STATEMENTS BELOW-----------------------------------------------------------------
		//PLEASE READ OUTPUT FOR INSTRUCTIONS
		lines.add(new Point[] {new Point(0,12), new Point(23,100)});
		lines.add(new Point[] {new Point(0,52), new Point(100,41)});
		lines.add(new Point[] {new Point(100,20), new Point(30,100)});
		lines.add(new Point[] {new Point(0,40), new Point(10,0)});
		lines.add(new Point[] {new Point(10,100), new Point(100,35)});
		
		//----------------------------------------------------------------------------------------------------------------------------------------------------
		
		//adds the ID section to each of the point, and sorts the array based on the x value
		for(int i = 0; i<lines.size(); i++){
			if(lines.get(i)[0].x<lines.get(i)[1].x){
			sortedlines.add(new Point[]{lines.get(i)[0], lines.get(i)[1], new Point(i, 0)});
			}
			else{
				sortedlines.add(new Point[]{lines.get(i)[1], lines.get(i)[0], new Point(i, 0)});
			}
				
		}
		
	
		
		for(int i = 0; i<sortedlines.size(); i++){
			BST.insert(sortedlines.get(i));
		}
		
		System.out.println("In order traversal of the tree : ");
		BST.printInOrder(BST.root);
		System.out.println();
		
		BST.externalnodecalc(BST.root);
		System.out.println("Number of external Nodes = " + BST.externalnodescalc);
		
		BST.externalpathlength=(BST.InternalPathLength(BST.root, 0)+ 2*BST.Countnodes(BST.root));
		double averagepathlength= ((double)BST.externalpathlength/(double)BST.externalnodescalc);
		
		System.out.println("External Path length = " + BST.externalpathlength);
		System.out.println("Average Path Length = " + averagepathlength);
		
		
		//GUI-----------------------------------------
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Test().setVisible(true);
            }
        });
		//--------------------------------------------
		
	
	}
	
	//---------------------------------GUI STUFF---------------------
	public Test(){
		
		super("Point Location");
		
		setSize(500,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event){
				mousecounter++;
				if(mousecounter%2==1){
				pointclicked.x=event.getX();
				pointclicked.y=event.getY();
				pointclicked2.x=-10;
				pointclicked2.y=-10;
				repaint();
				}
				else{
				pointclicked2.x=event.getX();
				pointclicked2.y=event.getY();
				
				
				Point comparea = new Point(pointclicked.x/5, 100-(pointclicked.y/5) );
				Point compareb = new Point(pointclicked2.x/5, 100-(pointclicked2.y/5) );
				System.out.println(comparea);
				System.out.println(compareb);
				
			    BST.comparepoints(comparea, compareb);
			  
			    
				BinarySearchTree.pathpointa.clear();
				BinarySearchTree.pathpointb.clear();
				repaint();}
			}
		});
	}
	
	void drawLines(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		for(int i = 0; i<lines.size(); i++){
			g2d.drawLine(5*lines.get(i)[0].x, 5*(100-lines.get(i)[0].y), 5*lines.get(i)[1].x, 5*(100-lines.get(i)[1].y));
		}
		
		
		
		
	}
	
	public void paint(Graphics g){
		super.paint(g);
		g.fillOval(pointclicked.x, (pointclicked.y), 10, 10);
		g.fillOval(pointclicked2.x, (pointclicked2.y), 10, 10);
		drawLines(g);
	}
	
	static //---------------------------------------------------------------
	BinarySearchTree<Integer> BST = new BinarySearchTree<Integer>();
	

}
