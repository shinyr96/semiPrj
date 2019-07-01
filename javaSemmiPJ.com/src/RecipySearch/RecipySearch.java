package RecipySearch;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import View.RecipyView;

public class RecipySearch extends JFrame {
	RecipyView recipy;

	public RecipySearch() {
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screenSize=kit.getScreenSize();
		
		recipy = new RecipyView();
		JTabbedPane pane = new JTabbedPane();
		pane.add("레시피검색", recipy);
		pane.setSelectedIndex(0);
		add("Center", pane);

		Image img2=kit.getImage("src\\image\\cook.png");//준비	
		setIconImage(img2);
		setTitle("레시피검색");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new RecipySearch();
	}
}
