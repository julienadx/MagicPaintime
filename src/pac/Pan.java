package pac;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class Pan extends JPanel implements MouseMotionListener, MouseListener {
	
	private List<Point> points;
	private int[] sizePoint;
	private Color co;
	private String colored;
	private String shape;
	private String tool;
	private int[] curs;
	private String back;
	
	private String[] colora = {"Black", "Blue", "Red", "Green", "Cyan", "Gray", "Light Gray", "Dark Gray", "Pink", "Magenta", "Orange", "Yellow", "White"};
	
	private JPopupMenu cont = new JPopupMenu();
	private JMenuItem saveCont = new JMenuItem("Save");
	private JMenu backCont = new JMenu("Background");
	private JMenu shapeCont = new JMenu("Shape");
	private JMenu colorCont = new JMenu("Color");
	
	private JMenu colorBakCont = new JMenu("Color");
	
	
	
	
	private JMenuItem clearCont = new JMenuItem("Clear");
	private JMenuItem eraserCont = new JMenuItem("Eraser");
	
	
	
	
	
	
	
	
	public Pan() {
		super();
		this.setSize(new Dimension(500, 500));
		this.setPoints(new LinkedList<Point>());
		this.sizePoint = new int[2];
		this.sizePoint[0] = 20;
		this.sizePoint[1] = 20;
		this.curs = new int[2];
		this.curs[0] = -10000;
		this.curs[1] = -10000;
		this.co = Color.black;
		this.colored = new String("Black");
		this.setBack("White");
		this.setShape("Fill Oval");
		this.setTool("Pencil");
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	}
	
	@Override
	public void paintComponent(Graphics a) {
		a.setColor(strToColor(this.back));
		a.fillRect(0, 0, this.getWidth(), this.getHeight());
		//System.out.println("Pan: " + this.getWidth() + " * " + this.getHeight());
		//System.out.println("Rebuilt!");
		
		for(Point b: this.points) {
			setDess(a, b);
			
		}
		
		if(this.co == Color.white)
			a.setColor(Color.black);
		else
			a.setColor(this.co);
		
		switch(this.shape) {
		case "Fill Oval":
		case "Draw Oval":
			a.drawOval(this.curs[0], this.curs[1], this.sizePoint[0], this.sizePoint[1]);
			break;
		case "Fill Rectangle":
		case "Draw Rectangle":
			a.drawRect(this.curs[0], this.curs[1], this.sizePoint[0], this.sizePoint[1]);
			break;
		}
	}
	
	private void setDess(Graphics a, Point b) {
		//a.setColor(b.getCo());
		
		/*switch(this.tool) {
		case "Pencil":
			a.setColor(b.getCo());
			break;
		case "Eraser":
			a.setColor(Color.white);
			break;
		}*/
		
		switch(b.getTool()) {
		case "Eraser":
			a.setColor(strToColor(this.back));
			break;
		default:
			a.setColor(b.getCo());
			break;
		}
		
		
		switch(b.getShape()) {
		case "Fill Oval":
			a.fillOval(b.getX(), b.getY(), b.getW(), b.getH());
			break;
		case "Draw Oval":
			a.drawOval(b.getX(), b.getY(), b.getW(), b.getH());
			break;
		case "Fill Rectangle":
			a.fillRect(b.getX(), b.getY(), b.getW(), b.getH());
			break;
		case "Draw Rectangle":
			a.drawRect(b.getX(), b.getY(), b.getW(), b.getH());
			break;
		}
		
	}
	
	public void clearPoints() {
		this.points = new LinkedList<Point>();
		this.repaint();
		System.out.println("cleared");
	}
	
	
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}
	
	public void setSizePoint(int[] size) {
		this.sizePoint[0] = size[0];
		this.sizePoint[1] = size[1];
	}
	
	public void setCo(String a) {
		if(a != "White") {
			this.co = strToColor(a);
			this.colored = a;
		} else {
			this.co = strToColor(a);
		}
	}
	
	public Color strToColor(String c) {
		Color co = Color.BLACK;
		switch(c) {
		case "Black":
			co = Color.black;
			break;
		case "Blue":
			co = Color.blue;
			break;
		case "Red":
			co = Color.red;
			break;
		case "Green":
			co = Color.green;
			break;
		case "Cyan":
			co = Color.cyan;
			break;
		case "Gray":
			co = Color.gray;
			break;
		case "Light Gray":
			co = Color.LIGHT_GRAY;
			break;
		case "Dark Gray":
			co = Color.DARK_GRAY;
			break;
		case "Pink":
			co = Color.pink;
			break;
		case "Magenta":
			co = Color.magenta;
			break;
		case "Orange":
			co = Color.orange;
			break;
		case "Yellow":
			co = Color.yellow;
			break;
		case "White":
			co = Color.white;
			break;
		}
		return co;
	}
	
	public String getCoS() {
		System.out.println(this.colored);
		return this.colored;
	}
	
	
	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
	}
	
	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}
	
	public int[] getCurs() {
		return curs;
	}

	public void setCurs(int[] curs) {
		this.curs = curs;
	}
	
	public String getBack() {
		return back;
	}

	public void setBack(String back) {
		this.back = back;
		repaint();
	}
	
	
	//-----------------------------------------------------------------------------------------MOUSE MOTION--------------------------------------------------------------------------------

	@Override
	public void mouseDragged(MouseEvent e) {
		switch(this.tool) {
		case "Pencil":
			this.points.add(new Point(e.getX()-(this.sizePoint[0]/2), e.getY()-(this.sizePoint[1]/2), this.sizePoint[0], this.sizePoint[1], this.co, this.shape, this.tool));
			break;
		case "Eraser":
			this.points.add(new Point(e.getX()-(this.sizePoint[0]/2), e.getY()-(this.sizePoint[1]/2), this.sizePoint[0], this.sizePoint[1], strToColor(this.back), this.shape, this.tool));
			break;
		}
		if(e.getX() < this.getWidth()-2 && e.getX() > 2 && e.getY() < this.getHeight()-2 && e.getY() > 2) {
			this.curs[0] = e.getX()-(this.sizePoint[0]/2);
			this.curs[1] = e.getY()-(this.sizePoint[1]/2);
		} else {
			this.curs[0] = -10000;
			this.curs[1] = -10000;
		}
		
		
		this.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX() < this.getWidth()-2 && e.getX() > 2 && e.getY() < this.getHeight()-2 && e.getY() > 2) {
			this.curs[0] = e.getX()-(this.sizePoint[0]/2);
			this.curs[1] = e.getY()-(this.sizePoint[1]/2);
		} else {
			this.curs[0] = -10000;
			this.curs[1] = -10000;
		}
		
		this.repaint();
	}

	
	
	
	//--------------------------------------------------------------------------------------MOUSE LISTENER---------------------------------------------------------------------------------
	

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getButton());
		if(e.getButton() != MouseEvent.BUTTON3) {
			this.points.add(new Point(e.getX()-(this.sizePoint[0]/2), e.getY()-(this.sizePoint[1]/2), this.sizePoint[0], this.sizePoint[1], this.co, this.shape, this.tool));
			this.repaint();
			//System.out.println("clicked!");
		} else {
			colorBakCont.removeAll();
			for(String c: colora) {
				JMenuItem a = new JMenuItem(c);
				colorBakCont.add(a);
				a.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(a.getText());
						setBack(a.getText());
					}
				});
			}
			
			
			
			
			backCont.add(colorBakCont);
			this.cont.add(backCont);
			/*this.cont.add(saveCont);
			this.cont.add(shapeCont);
			this.cont.add(colorCont);
			this.cont.add(eraserCont);
			this.cont.add(clearCont);*/
			this.cont.show(this, e.getX(), e.getY());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

	

	

	
}
