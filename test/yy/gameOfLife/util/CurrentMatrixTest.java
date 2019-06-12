package yy.gameOfLife.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yulin on 2019/6/12.
 */
public class CurrentMatrixTest {

    CurrentMatrix currentMatrix = null;
    String path = "D:\\学习\\wx_devlop\\TXGameOfLife\\test.case";

    @Before
    public void init() {
        currentMatrix = Utils.initMatrix();
    }

    @Test
    public void transform() throws Exception {

        int[][] expected = new int[][]{
                {1, 0, 1},
                {0, 1, 0},
                {1, 1, 1}
        };
        Assert.assertArrayEquals(expected, currentMatrix.getMatrix());
        currentMatrix.transform();
        expected = new int[][]{
                {0, 1, 0},
                {0, 0, 0},
                {1, 1, 1}
        };
        Assert.assertArrayEquals(expected, currentMatrix.getMatrix());
        currentMatrix.transform();
        expected = new int[][]{
                {0, 0, 0},
                {1, 0, 1},
                {0, 1, 0}
        };
        Assert.assertArrayEquals(expected, currentMatrix.getMatrix());
    }


    @Test
    public void findLifedNum() throws Exception {
        Assert.assertEquals(1, currentMatrix.findLifedNum(0, 0));
        Assert.assertEquals(3, currentMatrix.findLifedNum(0, 1));
        Assert.assertEquals(1, currentMatrix.findLifedNum(0, 2));
        Assert.assertEquals(4, currentMatrix.findLifedNum(1, 0));
        Assert.assertEquals(5, currentMatrix.findLifedNum(1, 1));
        Assert.assertEquals(4, currentMatrix.findLifedNum(1, 2));
        Assert.assertEquals(2, currentMatrix.findLifedNum(2, 0));
        Assert.assertEquals(3, currentMatrix.findLifedNum(2, 1));
        Assert.assertEquals(2, currentMatrix.findLifedNum(2, 2));
    }

}