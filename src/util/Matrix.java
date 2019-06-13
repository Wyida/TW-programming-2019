package util;

import java.util.Arrays;

/**
 * Created by Yida on 2019/6/12.
 */
public class Matrix {

    //矩阵高度
    private int height;

    //矩阵宽度
    private int width;

    //变化时间
    private int roundTime;

    //生命状态矩阵，1表示活，0表示死
    private int[][] state;

    public Matrix(int height, int width, int roundTime, int[][] state) {
        this.height = height;
        this.width = width;
        this.roundTime = roundTime;
        this.state = state;
    }

    //状态转移函数
    public void transform() {
        int[][] nextState = new int[height][width];
        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[0].length; x++) {
                int nearNum = lifedNum(y, x);
                //等于3，则下一状态总是活
                if (state[y][x] == 1) {
                    if (nearNum < 2 || nearNum > 3) nextState[y][x] = 0;
                    else nextState[y][x] = 1;
                } else {
                    if (nearNum == 3) nextState[y][x] = 1;
                    else nextState[y][x] = 0;
                }
            }
        }
        state = nextState;
    }


    //计算每个细胞周围存活的细胞个数
    int lifedNum(int y, int x) {
        int num = 0;
        //左边
        if (x != 0) {
            num += state[y][x - 1];
        }
        //左上角
        if (x != 0 && y != 0) {
            num += state[y - 1][x - 1];
        }
        //上边
        if (y != 0) {
            num += state[y - 1][x];
        }
        //右上
        if (x != width - 1 && y != 0) {
            num += state[y - 1][x + 1];
        }
        //右边
        if (x != width - 1) {
            num += state[y][x + 1];
        }
        //右下
        if (x != width - 1 && y != height - 1) {
            num += state[y + 1][x + 1];
        }
        //下边
        if (y != height - 1) {
            num += state[y + 1][x];
        }
        //左下
        if (x != 0 && y != height - 1) {
            num += state[y + 1][x - 1];
        }
        return num;
    }

    public static int[][] initState() {
        int[][] emptyState = new int[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                emptyState[i][j] = 0;
            }
        }
        return emptyState;
    }

    public static Matrix initMatrix(int height, int width, int roundTime , int [][] state) {
        try {
            return new Matrix(height, width, roundTime, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < state.length; i++) {
            sb.append(Arrays.toString(state[i]) + "\n");
        }
        return sb.toString();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int[][] getState() {
        return state;
    }

    public void setState(int[][] matrix) {
        this.state = matrix;
    }

    public void setEmptyState() {
        state=initState();
    }



}
