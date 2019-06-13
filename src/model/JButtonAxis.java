package model;

import javax.swing.*;

public class JButtonAxis extends JButton {
    private int xAxis;
    private int yAxis;

    public JButtonAxis(int xValue, int yValue) {
        this.xAxis = xValue;
        this.yAxis = yValue;
    }

    public int getxValue() {
        return xAxis;
    }

    public int getyValue() {
        return yAxis;
    }


}
