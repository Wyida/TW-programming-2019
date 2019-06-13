package util;

/**
 * Matrix初始化类
 * Created by Yida on 2019/6/12.
 */
public class Utils {
    public static MatrixState initMatrix() {
        try {
            int width = 30;
            int height = 30;
            int roundTime = 3000000;
            int totalNum = 900;
            int[][] matrix = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    matrix[i][j] = 0;
                }
            }
            return new MatrixState(height, width, roundTime, totalNum, matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
