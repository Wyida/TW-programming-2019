package ui;

import util.MatrixState;
import util.JButtonAxis;
import util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yida&Yulin on 2019/6/12.
 */
public class GameOfLifeSwing extends JFrame {

    private JButton gameBtn = new JButton("开始游戏");

    private JTextField durationTextField = new JTextField();

    //游戏开始的标志
    private boolean isStart;

    //游戏结束的标志
    private boolean stop;

    private MatrixState matrixState;

    private JPanel gridPanel = new JPanel();

    private JButton[][] cellBtnsMatrix;

    //步长默认200ms
    private static final int DEFAULT_ROUNDTIME = 200;

    //动画间隔
    private int roundTime = DEFAULT_ROUNDTIME;

    public GameOfLifeSwing() {
        setTitle("生命游戏");
        gameBtn.addActionListener(new StartGameActioner());
        JButton clearBoardBtn = new JButton("清除");
        clearBoardBtn.addActionListener(new clearBoardActioner());
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
        buttonPanel.add(gameBtn);
        buttonPanel.add(clearBoardBtn);
        JLabel durationPromtLabel = new JLabel("控制动画速度（单位ms）");
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        buttonPanel.setBackground(Color.WHITE);
        getContentPane().add("North", buttonPanel);
        isStart = false;
        stop = true;
        gameBtn.setText("开始游戏");
        matrixState = Utils.initMatrix();
        initGridLayout();
        repaintMatrix();
        gridPanel.updateUI();
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }



    private void repaintMatrix() {
        int[][] state = matrixState.getState();
        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[0].length; x++) {
                if (state[y][x] == 1) {
                    cellBtnsMatrix[y][x].setBackground(Color.BLACK);
                } else {
                    cellBtnsMatrix[y][x].setBackground(Color.WHITE);
                }
            }
        }
    }

    private void initGridLayout() {
        int rows = matrixState.getHeight();
        int cols = matrixState.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        cellBtnsMatrix = new JButton[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JButtonAxis text = new JButtonAxis(x, y);
                text.addActionListener(e -> {
                    int[][] state = matrixState.getState();
                    text.setBackground(Color.BLACK);
                    state[text.getyValue()][text.getxValue()] = 1;
                    matrixState.setMatrix(state);
                });
                cellBtnsMatrix[y][x] = text;
                gridPanel.add(text);
            }
        }
        add("Center", gridPanel);
    }

    private class StartGameActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!isStart) {
                //获取时间
                try {
                    roundTime = Integer.parseInt(durationTextField.getText().trim());
                } catch (NumberFormatException e1) {
                    roundTime = DEFAULT_ROUNDTIME;
                }
                new Thread(new GameControlTask()).start();
                isStart = true;
                stop = false;
                gameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                gameBtn.setText("开始游戏");
            }
        }
    }

    private class clearBoardActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            isStart = false;
            stop = true;
            gameBtn.setText("开始游戏");
            matrixState = Utils.initMatrix();
            initGridLayout();
            repaintMatrix();
            gridPanel.updateUI();
        }
    }

    private class GameControlTask implements Runnable {
        @Override
        public void run() {

            while (!stop) {
                matrixState.transform();
                repaintMatrix();

                try {
                    TimeUnit.MILLISECONDS.sleep(roundTime);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

}
