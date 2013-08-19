package com.olegkostiuk.colorpicker;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Oleg Kostiuk
 *
 */
public class HexView extends ColorView {

    private JLabel colorViewTypeLabel;
    private JTextField htmlColorValueField;

    public HexView(){
        initialization();
    }

    @Override
    protected void initialization(){

        setLayout(new FlowLayout());

        colorViewTypeLabel = new JLabel("HTML: ");
        htmlColorValueField = new JTextField("#FFFFFF");

        add(colorViewTypeLabel);
        add(htmlColorValueField);
    }

    @Override
    protected void updateColorView() {
        String hexValue = "#"+Integer.toHexString(shownColor.getRGB()).substring(2);
        hexValue = hexValue.toUpperCase();

        htmlColorValueField.setText(hexValue);
    }
}
