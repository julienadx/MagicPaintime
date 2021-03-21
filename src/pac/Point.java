package pac;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Point extends Component {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private Color co;
	private String shape;
	private String tool;
	
	public Point(int x, int y, int w, int h, Color co, String shape, String tool) {
		this.setX(x);
		this.setY(y);
		this.setW(w);
		this.setH(h);
		this.setCo(co);
		this.setShape(shape);
		this.setTool(tool);
	}
	
	

	public String toString() {
		return "x = " + this.x + ", y = " + this.y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	public int getW() {
		return w;
	}


	public void setW(int w) {
		this.w = w;
	}


	public int getH() {
		return h;
	}


	public void setH(int h) {
		this.h = h;
	}


	public Color getCo() {
		return co;
	}


	public void setCo(Color co) {
		this.co = co;
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
}
