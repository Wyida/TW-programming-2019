package util;

import java.util.Arrays;

/**
 * Created by Yida on 2019/6/12.
 */
public class MatrixState {

    //矩阵高度
    private int height;

    //矩阵宽度
    private int width;

    //变化时间
    private int roundTime;

    //总变化次数
    private int transfromNum = 0;

    //生命状态矩阵，1表示活，0表示死
    private int[][] state;

    public MatrixState(int height, int width, int roundTime, int transfromNum, int[][] state) {
        this.height = height;
        this.width = width;
        this.roundTime = roundTime;
        this.transfromNum = transfromNum;
        this.state = state;
    }


//    //状态转移
//    public void transform1() {
//        int[][] nextState = new int[height][width];
//        for (int y = 0; y < state.length; y++) {
//            for (int x = 0; x < state[0].length; x++) {
//                nextState[y][x] = 0;
//                int nearNum = lifedNum(y, x);
//                //等于3，则下一状态总是活
//                if (nearNum == 3) {
//                    nextState[y][x] = 1;
//                }
//                //等于2，则与上一状态一样
//                else if (nearNum == 2) {
//                    nextState[y][x] = state[y][x];
//                }
//            }
//        }
//        state = nextState;
//    }

    //状态转移函数
    public void transform(){
        int[][] nextState=new int[height][width];
        for (int y = 0; y < state.length; y++) {
            for (int x = 0; x < state[0].length; x++) {
                int nearNum= lifedNum(y,x);
                //等于3，则下一状态总是活
                if(state[y][x]==1){
                    if(nearNum<2||nearNum>3)nextState[y][x]=0;
                    else nextState[y][x]=1;
                }
                else{
                    if(nearNum==3)nextState[y][x]=1;
                    else nextState[y][x]=0;
                }
            }
        }
        state=nextState;
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

    public int getTransfromNum() {
        return transfromNum;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setMatrix(int[][] matrix) {
        this.state = matrix;
    }
}
