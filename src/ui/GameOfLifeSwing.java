package ui;

import model.JButtonAxis;
import model.Matrix;

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

    private Matrix matrix;

    private JPanel gridPanel = new JPanel();

    private JButton[][] cellBtnsMatrix;

    //步长默认200ms
    private static final int DEFAULT_ROUNDTIME = 200;

    //动画间隔
    private int roundTime = DEFAULT_ROUNDTIME;

    public GameOfLifeSwing() {

        isStart = false;
        stop = true;
        matrix = Matrix.initMatrix(100, 100, 200, Matrix.initState());
        initUi();

    }


    private void initUi() {
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initGridLayout();
        setTitle("Game of Life");
        gameBtn.setText("开始游戏");
        gameBtn.addActionListener(new StartGameActioner());
        JButton clearBoardBtn = new JButton("清除");
        clearBoardBtn.addActionListener(new ClearBoardActioner());
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        JLabel durationPromtLabel = new JLabel("控制动画速度（单位ms）");

        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        buttonPanel.add(gameBtn);
        buttonPanel.add(clearBoardBtn);
        buttonPanel.setBackground(Color.WHITE);
        getContentPane().add("South", buttonPanel);
        repaintMatrix();
        gridPanel.updateUI();

    }


    private void repaintMatrix() {
        int[][] state = matrix.getState();
        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[0].length; x++) {
                if (state[y][x] == 1) {
                    cellBtnsMatrix[y][x].setBackground(Color.WHITE);
                } else {
                    cellBtnsMatrix[y][x].setBackground(Color.BLACK);
                }
            }
        }
    }

    private void initGridLayout() {
        int rows = matrix.getHeight();
        int cols = matrix.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        cellBtnsMatrix = new JButton[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JButtonAxis text = new JButtonAxis(x, y);
                text.addActionListener(e -> {
                    int[][] state = matrix.getState();
                    text.setBackground(Color.WHITE);
                    state[text.getyValue()][text.getxValue()] = 1;
                    matrix.setState(state);
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
                new Thread(new GameCoreTask()).start();
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

    private class ClearBoardActioner implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            isStart = false;
            stop = true;
            gameBtn.setText("开始游戏");
            matrix.setEmptyState();
            initGridLayout();
            repaintMatrix();
            gridPanel.updateUI();
        }
    }

    private class GameCoreTask implements Runnable {
        @Override
        public void run() {

            while (!stop) {
                matrix.transform();
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
