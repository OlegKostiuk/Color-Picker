package com.olegkostiuk.colorpicker;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class TransparentPanel extends JPanel {

	private ColorProvider colorProvider;
	private PixelsPanel pixelPanel;
	private ColorSelectorUI frameContainer;
	private int currentShiftFromMousePointerX = 10; 		// pixel panel will be shifted on
	private int currentShiftFromMousePointerY = 10; 		// this value
	
	private static final int PIXEL_PANEL_SIZE = 5; 			// number of pixel height & width
	private static final int DISTANCE_TO_POINTER = 10;
	private static final int MIN_DISTANCE_TO_EDGE = 100;

	public TransparentPanel(ColorSelectorUI father) {

		frameContainer = father;

		setLayout(null);

		colorProvider = new ColorProvider();
		Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		Color[][] colors = colorProvider.getColorsOfArea(mouseLocation.x - 3,
				mouseLocation.y - 3, PIXEL_PANEL_SIZE, PIXEL_PANEL_SIZE);
		pixelPanel = new PixelsPanel(colors);
		add(pixelPanel);

		pixelPanel.setBounds(mouseLocation.x + currentShiftFromMousePointerX,
				mouseLocation.y + currentShiftFromMousePointerX,
				pixelPanel.getMinimumSize().height,
				pixelPanel.getMinimumSize().width);

		setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		// action listeners

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				frameContainer.setSelectedColor(pixelPanel.getColorAtCenter());
				frameContainer.returnColorToFatherFrame();

			}

		});

	}

	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);

		BufferedImage img = colorProvider.getCurrentScreenshot();
		arg0.drawImage((Image) img, 0, 0, null);
	}

	public void updatePixelPanel(Point pointInScreen) {

		pointInScreen.x -= 3; // shift center of pixelPanel to pointer location
		pointInScreen.y -= 3;

		changePixelPanelShift();

		// set new color array on pixel panel
		// and then set new position for pixel panel
		pixelPanel.setColors(colorProvider.getColorsOfArea(pointInScreen,
				PIXEL_PANEL_SIZE, PIXEL_PANEL_SIZE));
		pixelPanel.setBounds(pointInScreen.x + currentShiftFromMousePointerX,
				pointInScreen.y + currentShiftFromMousePointerY,
				pixelPanel.getMinimumSize().height,
				pixelPanel.getMinimumSize().width);
		pixelPanel.repaint();

	}

	/**
	 * set shift from pointer if pointer is near to any side of screen then
	 * shift will take value which will shift pixelPanel and it will not go over
	 * screen
	 */
	private void changePixelPanelShift() {
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();

		if (currentMouseLocation.x > screenDimension.width
				- MIN_DISTANCE_TO_EDGE) {
			currentShiftFromMousePointerX = -100;
		}

		if (currentMouseLocation.x < MIN_DISTANCE_TO_EDGE) {
			currentShiftFromMousePointerX = DISTANCE_TO_POINTER;
		}

		if (currentMouseLocation.y > screenDimension.height
				- MIN_DISTANCE_TO_EDGE) {
			currentShiftFromMousePointerY = -100;
		}

		if (currentMouseLocation.y < MIN_DISTANCE_TO_EDGE) {
			currentShiftFromMousePointerY = DISTANCE_TO_POINTER;
		}

	}

}
