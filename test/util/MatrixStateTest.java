package util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yulin on 2019/6/12.
 */
public class MatrixStateTest {

    MatrixState matrixState = null;

    @Before
    public void init() {
        matrixState = Utils.initMatrix();
    }

    @Test
    public void transform() throws Exception {

        int[][] expected = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        Assert.assertArrayEquals(expected, matrixState.getState());
        matrixState.transform();
        expected = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {1, 1, 1}
        };
        Assert.assertArrayEquals(expected, matrixState.getState());
        matrixState.transform();
        expected = new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        Assert.assertArrayEquals(expected, matrixState.getState());
    }


    @Test
    public void findLifedNum() throws Exception {
        Assert.assertEquals(1, matrixState.lifedNum(0, 0));
        Assert.assertEquals(3, matrixState.lifedNum(0, 1));
        Assert.assertEquals(1, matrixState.lifedNum(0, 2));
        Assert.assertEquals(4, matrixState.lifedNum(1, 0));
        Assert.assertEquals(5, matrixState.lifedNum(1, 1));
        Assert.assertEquals(4, matrixState.lifedNum(1, 2));
        Assert.assertEquals(2, matrixState.lifedNum(2, 0));
        Assert.assertEquals(3, matrixState.lifedNum(2, 1));
        Assert.assertEquals(2, matrixState.lifedNum(2, 2));
    }

}