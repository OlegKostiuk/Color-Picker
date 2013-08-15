package com.olegkostiuk.colorpicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 * 
 * @author Oleg Kostiuk
 * 
 */

public class ColorProvider {

	private BufferedImage screenshotImage;
	private Raster screenshotRaster;

	public BufferedImage getCurrentScreenshot() {
		return screenshotImage;
	}

	public ColorProvider() {

		Rectangle screenRect;

		// if os==Linux get screenshot of area without
		// system panels
		if (System.getProperty("os.name").equals("Linux")) {
			screenRect = new Rectangle(GraphicsEnvironment
					.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		} else {
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			screenRect = new Rectangle(0, 0, screenSize.width,
					screenSize.height);
		}
		
		Robot robot;
		try {
			robot = new Robot();
			screenshotImage = robot.createScreenCapture(screenRect);
			screenshotRaster = screenshotImage.getData();
		} catch (AWTException e) {
			// TODO: handle exception
		}
	}

	/**
	 * 
	 * @param firstPixelX
	 * @param firstPixelY
	 * @param width
	 *            width of selection area
	 * @param height
	 *            width of selection area
	 * @return returns array of Color with colors of each pixel in area
	 */
	public Color[][] getColorsOfArea(int firstPixelX, int firstPixelY,
			int width, int height) {
		Color[][] pixelColorsArray = new Color[height][width];

		int[] colTemp = null;

		// calculate coordinates of each pixel and get its color
		try {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {

					colTemp = screenshotRaster.getPixel(firstPixelX + i,
							firstPixelY + j, colTemp);
					pixelColorsArray[i][j] = new Color(colTemp[0], colTemp[1],
							colTemp[2]);
				}
			}
		} catch (IndexOutOfBoundsException e) {
			// IndexOutOfBoundsException is generated when mouse
			// pointer goes out of screen area
		}
		return pixelColorsArray;

	}

	public Color[][] getColorsOfArea(Point firstPixel, int width, int height) {
		return getColorsOfArea(firstPixel.x, firstPixel.y, width, height);
	}

}
