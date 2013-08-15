package com.olegkostiuk.colorpicker;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class ColorPickerFocusLostAction implements FocusListener {

	ColorSelectorUI frame;

	public ColorPickerFocusLostAction(ColorSelectorUI frame) {
		this.frame = frame;
	}

	@Override
	public void focusGained(FocusEvent arg0) {

	}

	@Override
	public void focusLost(FocusEvent arg0) {

		frame.returnColorToFatherFrame();

	}

}