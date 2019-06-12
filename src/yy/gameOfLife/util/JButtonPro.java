package yy.gameOfLife.util;

import javax.swing.*;

public class JButtonPro extends JButton {
    private int xValue;
    private int yValue;

    public JButtonPro(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public int getxValue() {
        return xValue;
    }

    public void setxValue(int xValue) {
        this.xValue = xValue;
    }

    public int getyValue() {
        return yValue;
    }

    public void setyValue(int yValue) {
        this.yValue = yValue;
    }


}
