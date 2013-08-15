package com.olegkostiuk.colorpicker;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class ColorViewRGB extends ColorView {

	public ColorViewRGB() {
		fieldNames = new char[] { 'R', 'G', 'B' };
		initialization();
	}

	@Override
	protected void updateColorView() {
		for (int i = 0; i < RGB.length; i++) {
			fields[1][i].setText(String.valueOf(RGB[i]));
		}
	}
}
