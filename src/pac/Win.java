package pac;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Win extends JFrame {
	
	//List<Point> points = new LinkedList();
	private Pan pan = new Pan();
	private JPanel container = new JPanel();
	
	private JOptionPane aboutPane = new JOptionPane();
	
	private JMenuBar menu = new JMenuBar();
	private JMenu fileMenu = new JMenu("File");
	private JMenu aboutMenu = new JMenu("About");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem aboutItem = new JMenuItem("About MagicPaintime");
	private JMenuItem quitItem = new JMenuItem("Quit");
	private JMenuItem clearItem = new JMenuItem("Clear");
	
	private JToolBar toolBar = new JToolBar();
	private JLabel labTool = new JLabel("Size: ");
	private JComboBox sizeBox = new JComboBox();
	private JLabel lab1Tool = new JLabel("Color: ");
	private JComboBox<String> colorBox = new JComboBox<String>();
	private JLabel lab2Tool = new JLabel("Shape: ");
	private JComboBox<String> shapeBox = new JComboBox<String>();
	private JLabel lab3Tool = new JLabel("Tool: ");
	private JComboBox<String> toolBox = new JComboBox<String>();
	

	
	public Win() {
		this.setTitle("MagicPaintimev0.1b");
		this.setSize(650, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//this.setIconImage(new Image("MagicPaintime/MagicPaintime.png"));
		
		
		container.setLayout(new BorderLayout());
		container.add(pan);
		
		
		sizeBox.setSize(100, 20);
		sizeBox.addItem(2);
		sizeBox.addItem(5);
		sizeBox.addItem(10);
		sizeBox.addItem(15);
		sizeBox.addItem(20);
		sizeBox.addItem(30);
		sizeBox.addItem(40);
		sizeBox.addItem(50);
		sizeBox.addItem(60);
		sizeBox.addItem(70);
		sizeBox.addItem(80);
		sizeBox.addItem(90);
		sizeBox.addItem(100);
		sizeBox.setSelectedItem(20);
		sizeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(sizeBox.getSelectedItem());
				int[] a = {(int)sizeBox.getSelectedItem(), (int)sizeBox.getSelectedItem()};
				pan.setSizePoint(a);
			}
		});
		
		colorBox.setSize(100, 20);
		colorBox.addItem("Black");
		colorBox.addItem("Blue");
		colorBox.addItem("Red");
		colorBox.addItem("Green");
		colorBox.addItem("Cyan");
		colorBox.addItem("Gray");
		colorBox.addItem("Light Gray");
		colorBox.addItem("Dark Gray");
		colorBox.addItem("Pink");
		colorBox.addItem("Magenta");
		colorBox.addItem("Orange");
		colorBox.addItem("Yellow");
		colorBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(colorBox.getSelectedItem());
				pan.setCo((String)colorBox.getSelectedItem());
			}
		});
		
		shapeBox.setSize(100, 20);
		shapeBox.addItem("Fill Oval");
		shapeBox.addItem("Draw Oval");
		shapeBox.addItem("Fill Rectangle");
		shapeBox.addItem("Draw Rectangle");
		shapeBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(shapeBox.getSelectedItem());
				pan.setShape((String)shapeBox.getSelectedItem());
			}
		});
		
		toolBox.setSize(100, 20);
		toolBox.addItem("Pencil");
		toolBox.addItem("Eraser");
		toolBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(toolBox.getSelectedItem());
				pan.setTool((String)toolBox.getSelectedItem());
				switch((String)toolBox.getSelectedItem()) {
				case "Pencil":
					colorBox.setEnabled(true);
					colorBox.setSelectedItem(pan.getCoS());
					colorBox.removeItem("White");
					pan.setCo(pan.getCoS());
					break;
				case "Eraser":
					colorBox.addItem("White");
					colorBox.setSelectedItem("White");
					colorBox.setEnabled(false);
					pan.setCo("White");
					break;
				}
				System.out.println(pan.getCoS());
			}
		});
		
		
		this.initMenu();
		this.setContentPane(container);
		this.initToolBar();
		this.setVisible(true);
	}
	
	private void initToolBar() {
		this.toolBar.add(lab3Tool);
		this.toolBar.add(toolBox);
		this.toolBar.add(lab2Tool);
		this.toolBar.add(shapeBox);
		this.toolBar.addSeparator();
		this.toolBar.add(labTool);
		this.toolBar.add(sizeBox);
		this.toolBar.addSeparator();
		this.toolBar.add(lab1Tool);
		this.toolBar.add(colorBox);
		
		this.add(toolBar, BorderLayout.NORTH);
	}
	
	private void initMenu() {
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.CTRL_DOWN_MASK));
		clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));
		this.fileMenu.add(clearItem);
		this.fileMenu.add(quitItem);
		this.aboutMenu.add(aboutItem);
		quitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("quit");
				System.exit(0);
			}
		});
		clearItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pan.clearPoints();
			}
		});
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aboutPane.showMessageDialog(null, "MagicPaintime is a VERY SIMPLE SOFTWARE that gives ya the ability\nto draw some schemes or sketchs with always a lot of fun!\nIf you want to save your work... take a screenshot xD that works good!\nTo get starting, draw a smile :)\n\n/!\\ This is not a final release but beta v0.1b! /!\\", "About MagicPaintimev0.1b", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		aboutMenu.setMnemonic('A');
		fileMenu.setMnemonic('F');
		this.menu.add(fileMenu);
		//this.menu.add(edit);
		this.menu.add(aboutMenu);
		this.setJMenuBar(menu);
	}
	
	
	
	
}
