package com.olegkostiuk.colorpicker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.beans.Transient;
import javax.swing.JPanel;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class PixelsPanel extends JPanel {

	private Color[][] colorsOfPixels;

	private static final int ONE_PIXEL_SIZE = 18; // height and width of square
													// which
													// show one pixel

	private static final int SPACE_BETWEEN_PIXELS = 1;

	public PixelsPanel(Color[][] color) {
		colorsOfPixels = color;
		setBackground(Color.BLACK);
	}

	public Color getColorAtCenter() {
		return colorsOfPixels[colorsOfPixels.length / 2][colorsOfPixels.length / 2];
	}

	public void setColors(Color[][] colors) {
		colorsOfPixels = colors;
	}

	@Override
	@Transient
	public Dimension getMinimumSize() {
		Dimension dimension = new Dimension();

		dimension.height = ONE_PIXEL_SIZE * colorsOfPixels.length
				+ SPACE_BETWEEN_PIXELS * (colorsOfPixels.length + 1);
		dimension.width = ONE_PIXEL_SIZE * colorsOfPixels[0].length
				+ SPACE_BETWEEN_PIXELS * (colorsOfPixels[0].length + 1);

		return dimension;
	}

	@Override
	protected void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);

		for (int i = 0; i < colorsOfPixels.length; i++) {
			for (int j = 0; j < colorsOfPixels[i].length; j++) {
				graphics.setColor(colorsOfPixels[i][j]);
				graphics.fillRect(ONE_PIXEL_SIZE * i + SPACE_BETWEEN_PIXELS
						* (i + 1), ONE_PIXEL_SIZE * j + SPACE_BETWEEN_PIXELS
						* (j + 1), ONE_PIXEL_SIZE, ONE_PIXEL_SIZE);
			}
		}

		Graphics2D d = (Graphics2D) graphics;
		d.setColor(Color.RED);
		d.setStroke(new BasicStroke(3));
		d.drawRect(2 * ONE_PIXEL_SIZE + SPACE_BETWEEN_PIXELS * 3 - 1, 2
				* ONE_PIXEL_SIZE + SPACE_BETWEEN_PIXELS * 3 - 1,
				ONE_PIXEL_SIZE + 1, ONE_PIXEL_SIZE + 1);

	}

}
