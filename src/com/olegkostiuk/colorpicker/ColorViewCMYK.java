package com.olegkostiuk.colorpicker;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class ColorViewCMYK extends ColorView {

	public ColorViewCMYK() {
		fieldNames = new char[] { 'C', 'M', 'Y', 'K' };
		initialization();
	}

	@Override
	protected void updateColorView() {

		float[] RGBtemp = new float[3];

		for (int i = 0; i < RGB.length; i++) {
			RGBtemp[i] = RGB[i] / 255.0F;
		}

		float K = 1 - max(RGBtemp);
		float C = checkOnInfinity((1 - RGBtemp[0] - K) / (1 - K));
		float M = checkOnInfinity((1 - RGBtemp[1] - K) / (1 - K));
		float Y = checkOnInfinity((1 - RGBtemp[2] - K) / (1 - K));

		K = K * 255;
		C = C * 255;
		M = M * 255;
		Y = Y * 255;

		fields[1][0].setText(String.valueOf(Math.round(C)));
		fields[1][1].setText(String.valueOf(Math.round(M)));
		fields[1][2].setText(String.valueOf(Math.round(Y)));
		fields[1][3].setText(String.valueOf(Math.round(K)));

	}

}
