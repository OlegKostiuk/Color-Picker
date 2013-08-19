package com.olegkostiuk.colorpicker;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Robot;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EtchedBorder;

/**
 * 
 * @author Oleg Kostiuk
 *
 */

public class MainWindow extends JFrame {

	private static final int BORDER_SIZE = 10;
	private static final int MIN_WINDOW_HEIGHT = 300;
	private static final int MIN_WINDOW_WIDTH = 170;
	private static final Color DEFAULT_COLOR = new Color(255, 255, 255);
	private JPanel mainPanel;
	private ColorView panelRGB;
	private ColorView panelHSV;
	private ColorView panelCMYK;
	private JPanel choosePanel;
	ColorSelectorUI colorSelector;

	public MainWindow() {

		initialiseComponents();

		setContentPane(mainPanel);
		setTitle("Color Picker");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		pack();

	}

	public void setSelectedColor(Color selectedColor) {
		choosePanel.setBackground(selectedColor);
		panelRGB.showColor(selectedColor);
		panelCMYK.showColor(selectedColor);
		panelHSV.showColor(selectedColor);

	}

	private void initialiseComponents() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InstantiationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(4, 1, 10, 10));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
		setMinimumSize(new Dimension(MIN_WINDOW_WIDTH, MIN_WINDOW_HEIGHT));

		choosePanel = new JPanel();
		choosePanel.setBackground(DEFAULT_COLOR);
		choosePanel.setBorder(BorderFactory
                .createEtchedBorder(EtchedBorder.RAISED));
        choosePanel.setToolTipText("Press here to select some color");

		choosePanel.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                MainWindow.this.setVisible(false);

                // ///////////////// delay main window should have time to hide
                try {
                    (new Robot()).delay(500);
                } catch (AWTException e1) {
                    e1.printStackTrace();
                }
                // ///////////////

                colorSelector = new ColorSelectorUI(MainWindow.this);
                colorSelector.setVisible(true);
            }

        });

		mainPanel.add(choosePanel);

		panelRGB = new ColorViewRGB();
		mainPanel.add(panelRGB);

		panelHSV = new ColorViewHSV();
		mainPanel.add(panelHSV);

	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setVisible(true);

	}

}
