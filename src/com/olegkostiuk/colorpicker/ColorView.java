package com.olegkostiuk.colorpicker;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public abstract class ColorView extends JPanel {

    public static final int GAP_BEETWEAN_VALUES = 15;
    protected char[] fieldNames;
	protected JLabel[][] fields;
	protected int fieldRowNumbers = 2;
	protected int fieldColumnNumbers;
	protected Color shownColor = new Color(255, 255, 255);
	protected int[] RGB = new int[3];

	public void showColor(Color color) {
		shownColor = color;
		getRGBArrayFromSelectedCollor();
		updateColorView();
	
	}

	protected void initialization() {
		getRGBArrayFromSelectedCollor();
		fieldColumnNumbers = fieldNames.length;
		fields = new JLabel[fieldRowNumbers][fieldColumnNumbers];

		fieldInstantiation();
		setNameOfFields();
		updateColorView();

		LayoutManager layout = new GridLayout(fieldRowNumbers,
				fieldColumnNumbers, GAP_BEETWEAN_VALUES, GAP_BEETWEAN_VALUES);

		setLayout(layout);

		// setBorder
		Border raisedetched = BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED);
		setBorder(raisedetched);

		addFieldsToPanel();

	}

	protected abstract void updateColorView();

	protected float checkOnInfinity(float number) {
		if (number == Float.POSITIVE_INFINITY
				|| number == Float.NEGATIVE_INFINITY || Float.isNaN(number)) {
			number = 0.0F;
		}
	
		return number;
	}

	protected float max(float[] arr) {
		float max = arr[0];
	
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
	
		return max;
	}

	protected float min(float[] arr) {
		float min = arr[0];
	
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min) {
				min = arr[i];
			}
		}
	
		return min;
	}

	private void addFieldsToPanel() {
		for (int i = 0; i < fieldRowNumbers; i++) {
			for (int j = 0; j < fieldColumnNumbers; j++) {
				add(fields[i][j]);
			}
		}
	}

	private void fieldInstantiation() {
		for (int i = 0; i < fieldRowNumbers; i++) {
			for (int j = 0; j < fieldColumnNumbers; j++) {
				fields[i][j] = new JLabel("", JLabel.CENTER);
			}
		}
	}

	private void setNameOfFields() {
		for (int i = 0; i < fieldColumnNumbers; i++) {
			fields[0][i].setText(String.valueOf(fieldNames[i]));
		}
	}

	private void getRGBArrayFromSelectedCollor() {
		RGB[0] = shownColor.getRed();
		RGB[1] = shownColor.getGreen();
		RGB[2] = shownColor.getBlue();
	}

}
