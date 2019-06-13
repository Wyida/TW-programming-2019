package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yulin on 2019/6/12.
 */
public class MatrixTest {

    Matrix matrix = null;

    @Before
    public void init() {



        int[][] currentState = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };

        matrix = Matrix.initMatrix(3,3,200,currentState);


    }

    @Test
    public void transform() throws Exception {

        int[][] expected = new int[][]{
                {0, 1, 0},
                {1, 0, 0},
                {1, 1, 1}
        };
        matrix.transform();
        Assert.assertArrayEquals(expected, matrix.getState());
//        expected = new int[][]{
//                {0, 0, 0},
//                {1, 0, 1},
//                {0, 1, 0}
//        };
//        Assert.assertArrayEquals(expected, matrix.getState());
//        matrix.transform();
//        expected = new int[][]{
//                {0, 0, 0},
//                {0, 1, 0},
//                {0, 0, 0}
//        };
//        Assert.assertArrayEquals(expected, matrix.getState());
    }


//    @Test
//    public void findLifedNum() throws Exception {
//        Assert.assertEquals(1, matrix.lifedNum(0, 0));
//        Assert.assertEquals(3, matrix.lifedNum(0, 1));
//        Assert.assertEquals(1, matrix.lifedNum(0, 2));
//        Assert.assertEquals(4, matrix.lifedNum(1, 0));
//        Assert.assertEquals(5, matrix.lifedNum(1, 1));
//        Assert.assertEquals(4, matrix.lifedNum(1, 2));
//        Assert.assertEquals(2, matrix.lifedNum(2, 0));
//        Assert.assertEquals(3, matrix.lifedNum(2, 1));
//        Assert.assertEquals(2, matrix.lifedNum(2, 2));
//    }

}