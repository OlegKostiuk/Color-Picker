package com.olegkostiuk.colorpicker;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class MotionMouseListener implements MouseMotionListener {
	TransparentPanel transparentPanel;

	public MotionMouseListener(TransparentPanel panel) {
		transparentPanel = panel;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		transparentPanel.updatePixelPanel(e.getPoint());
	}

}
