package com.olegkostiuk.colorpicker;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 
 * @author Oleg Kostiuk
 * 
 */

public class ColorSelectorUI extends JFrame {

	private MainWindow jFrameFather;
	private TransparentPanel fullscreenTransparentPanel;
	private Color selectedColor = new Color(255, 255, 255);

	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
	}

	public void returnColorToFatherFrame() {
		jFrameFather.setSelectedColor(selectedColor);
		jFrameFather.setVisible(true);
		this.dispose();
	}

	public ColorSelectorUI(MainWindow father) {

		jFrameFather = father;

		fullscreenTransparentPanel = new TransparentPanel(this);
		setContentPane(fullscreenTransparentPanel);

		// setting parameters of JFrame

		getContentPane().setPreferredSize(
				Toolkit.getDefaultToolkit().getScreenSize());
		setTitle("Color selector");
		setResizable(false);
		setUndecorated(true);
		setAlwaysOnTop(true);
		
		pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// add listeners to transparent panel

		fullscreenTransparentPanel
				.addMouseMotionListener(new MotionMouseListener(
						fullscreenTransparentPanel));

		this.addFocusListener(new ColorPickerFocusLostAction(this));

		// fullscreen realization
		if (System.getProperty("os.name").equals("Linux")) {
			setBounds(GraphicsEnvironment
					.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		} else {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			setBounds(0, 0, screenSize.width, screenSize.height);
		}

		setLocationRelativeTo(null);
		setLocation(0, 0);

	}

}
