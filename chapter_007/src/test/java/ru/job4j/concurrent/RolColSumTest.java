package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;

public class RolColSumTest {

    @Test
    public void whenSequentialSumSquare() {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sum = RolColSum.sum(data);
        Assert.assertThat(sum[0].getRowSum(), is(6));
        Assert.assertThat(sum[0].getColSum(), is(12));

        Assert.assertThat(sum[1].getRowSum(), is(15));
        Assert.assertThat(sum[1].getColSum(), is(15));

        Assert.assertThat(sum[2].getRowSum(), is(24));
        Assert.assertThat(sum[2].getColSum(), is(18));
    }

    @Test
    public void whenSequentialSumRectangle1() {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        RolColSum.Sums[] sum = RolColSum.sum(data);
        Assert.assertThat(sum[0].getRowSum(), is(6));
        Assert.assertThat(sum[0].getColSum(), is(22));

        Assert.assertThat(sum[1].getRowSum(), is(15));
        Assert.assertThat(sum[1].getColSum(), is(26));

        Assert.assertThat(sum[2].getRowSum(), is(24));
        Assert.assertThat(sum[2].getColSum(), is(30));

        Assert.assertThat(sum[3].getRowSum(), is(33));
        Assert.assertThat(sum[3].getColSum(), is(0));
    }

    @Test
    public void whenSequentialSumRectangle2() {
        int[][] data = {
                {1, 2, 3, 1},
                {4, 5, 6, 2},
                {7, 8, 9, 3},
        };
        RolColSum.Sums[] sum = RolColSum.sum(data);
        Assert.assertThat(sum[0].getRowSum(), is(7));
        Assert.assertThat(sum[0].getColSum(), is(12));

        Assert.assertThat(sum[1].getRowSum(), is(17));
        Assert.assertThat(sum[1].getColSum(), is(15));

        Assert.assertThat(sum[2].getRowSum(), is(27));
        Assert.assertThat(sum[2].getColSum(), is(18));

        Assert.assertThat(sum[3].getRowSum(), is(0));
        Assert.assertThat(sum[3].getColSum(), is(6));
    }

    @Test
    public void whenAsynchSumSquare() throws ExecutionException, InterruptedException {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sum = RolColSum.asyncSum(data);
        Assert.assertThat(sum[0].getRowSum(), is(6));
        Assert.assertThat(sum[0].getColSum(), is(12));

        Assert.assertThat(sum[1].getRowSum(), is(15));
        Assert.assertThat(sum[1].getColSum(), is(15));

        Assert.assertThat(sum[2].getRowSum(), is(24));
        Assert.assertThat(sum[2].getColSum(), is(18));
    }

    @Test
    public void whenAsynchSumRectangle1() throws ExecutionException, InterruptedException {
        int[][] data = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 11, 12}
        };
        RolColSum.Sums[] sum = RolColSum.asyncSum(data);
        Assert.assertThat(sum[0].getRowSum(), is(6));
        Assert.assertThat(sum[0].getColSum(), is(22));

        Assert.assertThat(sum[1].getRowSum(), is(15));
        Assert.assertThat(sum[1].getColSum(), is(26));

        Assert.assertThat(sum[2].getRowSum(), is(24));
        Assert.assertThat(sum[2].getColSum(), is(30));

        Assert.assertThat(sum[3].getRowSum(), is(33));
        Assert.assertThat(sum[3].getColSum(), is(0));
    }

    @Test
    public void whenAsynchSumRectangle2() throws ExecutionException, InterruptedException {
        int[][] data = {
                {1, 2, 3, 1},
                {4, 5, 6, 2},
                {7, 8, 9, 3},
        };
        RolColSum.Sums[] sum = RolColSum.asyncSum(data);
        Assert.assertThat(sum[0].getRowSum(), is(7));
        Assert.assertThat(sum[0].getColSum(), is(12));

        Assert.assertThat(sum[1].getRowSum(), is(17));
        Assert.assertThat(sum[1].getColSum(), is(15));

        Assert.assertThat(sum[2].getRowSum(), is(27));
        Assert.assertThat(sum[2].getColSum(), is(18));

        Assert.assertThat(sum[3].getRowSum(), is(0));
        Assert.assertThat(sum[3].getColSum(), is(6));
    }
}
