package yy.gameOfLife.util;

import java.io.*;

/**
 * Matrix初始化类
 * Created by Yida on 2019/6/12.
 */
public class Utils {
    public static CurrentMatrix initMatrix() {
        try {
            int width = 30;
            int height = 30;
            int duration = 3000000;
            int totalNum = 900;
            int[][] matrix = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = 0;
                }
            }
            CurrentMatrix currentMatrix = new CurrentMatrix(height, width, duration, totalNum, matrix);
            return currentMatrix;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
