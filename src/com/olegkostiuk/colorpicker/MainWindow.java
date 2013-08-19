package com.olegkostiuk.colorpicker;

import java.awt.*;
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
	private static final int MIN_WINDOW_HEIGHT = 240;
	private static final int MIN_WINDOW_WIDTH = 150;
	private static final Color DEFAULT_COLOR = new Color(255, 255, 255);
	private JPanel mainPanel;
	private ColorView panelRGB;
	private ColorView panelHSV;
	private ColorView panelHEX;
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
        panelHEX.showColor(selectedColor);
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
		mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
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

		constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 0.3;
        mainPanel.add(choosePanel, constraints);

        constraints.weighty = 0.0;
        constraints.gridheight = 1;
        constraints.gridy = 1;
        panelHEX = new HexView();
        mainPanel.add(panelHEX, constraints);

        constraints.gridy = 2;
        panelRGB = new ColorViewRGB();
		mainPanel.add(panelRGB, constraints);

        constraints.gridy = 3;
		panelHSV = new ColorViewHSV();
		mainPanel.add(panelHSV, constraints);

	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.setVisible(true);

	}

}
