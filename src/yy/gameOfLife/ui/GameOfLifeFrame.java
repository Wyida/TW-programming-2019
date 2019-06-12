package yy.gameOfLife.ui;

import yy.gameOfLife.util.CurrentMatrix;
import yy.gameOfLife.util.JButtonPro;
import yy.gameOfLife.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yida on 2019/6/12.
 */
public class GameOfLifeFrame extends JFrame {

    private JButton startGameBtn = new JButton("开始游戏");

    private JButton clearBoardBtn = new JButton("清除");
    private JLabel durationPromtLabel = new JLabel("控制动画速度（单位ms）");
    private JTextField durationTextField = new JTextField();
    /**
     * 游戏是否开始的标志
     */
    private boolean isStart = false;

    /**
     * 游戏结束的标志
     */
    private boolean stop = false;

    private CurrentMatrix currentMatrix;
    private JPanel buttonPanel = new JPanel(new GridLayout(2, 2));
    private JPanel gridPanel = new JPanel();

    private JButton[][] textMatrix;


    /**
     * 动画默认间隔200ms
     */
    private static final int DEFAULT_DURATION = 200;

    //动画间隔
    private int duration = DEFAULT_DURATION;

    public GameOfLifeFrame() {
        setTitle("生命游戏");

        startGameBtn.addActionListener(new StartGameActioner());
        clearBoardBtn.addActionListener(new clearBoardActioner());
        buttonPanel.add(startGameBtn);
        buttonPanel.add(clearBoardBtn);
        buttonPanel.add(durationPromtLabel);
        buttonPanel.add(durationTextField);
        buttonPanel.setBackground(Color.WHITE);

        getContentPane().add("North", buttonPanel);

        isStart = false;
        stop = true;
        startGameBtn.setText("开始游戏");
        currentMatrix = Utils.initMatrix();
        initGridLayout();
        showMatrix();
        gridPanel.updateUI();

        this.setSize(1000, 1200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    private class clearBoardActioner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            isStart = false;
            stop = true;
            startGameBtn.setText("开始游戏");
            currentMatrix = Utils.initMatrix();
            initGridLayout();
            showMatrix();
            gridPanel.updateUI();
        }

    }

    private void showMatrix() {
                int[][] matrix = currentMatrix.getMatrix();
                for (int y = 0; y < matrix.length; y++) {
                    for (int x = 0; x < matrix[0].length; x++) {
                        if (matrix[y][x] == 1) {
                            textMatrix[y][x].setBackground(Color.BLACK);
                        } else {
                            textMatrix[y][x].setBackground(Color.WHITE);
                        }
                    }
        }
    }

    /**
     * 创建显示的gridlayout布局
     */
    private void initGridLayout() {
        int rows = currentMatrix.getHeight();
        int cols = currentMatrix.getWidth();
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(rows, cols));
        textMatrix = new JButton[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                JButtonPro text = new JButtonPro(x,y);
                text.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int[][] matrix = currentMatrix.getMatrix();
                        text.setBackground(Color.BLACK);
                        matrix[text.getxValue()][text.getyValue()] = 1;
                        currentMatrix.setMatrix(matrix);
                    }
                });
                textMatrix[y][x] = text;
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
                    duration = Integer.parseInt(durationTextField.getText().trim());
                } catch (NumberFormatException e1) {
                    duration = DEFAULT_DURATION;
                }

                new Thread(new GameControlTask()).start();
                isStart = true;
                stop = false;
                startGameBtn.setText("暂停游戏");
            } else {
                stop = true;
                isStart = false;
                startGameBtn.setText("开始游戏");
            }
        }
    }

    private class GameControlTask implements Runnable {

        @Override
        public void run() {

            while (!stop) {
                currentMatrix.transform();
                showMatrix();

                try {
                    TimeUnit.MILLISECONDS.sleep(duration);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

}
