package com.vmware;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaxSumSubArrayTest {


    @Test
    public void aTest() {
        maxSubArray(IntStream.of(-3, 1, -8, 4, -1, 2, 1, -5, 5).toArray());

        Assert.assertTrue(true);
    }

    private int maxSubArray(int[] nums) {
        System.out.println(String.format("Array: %s", Arrays.toString(nums)));

        int n = nums.length;
        int maximumSubArraySum = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;

        for (int left = 0; left < n; left++) {

            int runningWindowSum = 0;

            for (int right = left; right < n; right++) {
                runningWindowSum += nums[right];

                if (runningWindowSum > maximumSubArraySum) {
                    maximumSubArraySum = runningWindowSum;
                    start = left;
                    end = right;
                }
            }
        }
        System.out.println(String.format("Found Maximum Subarray between %d and %d", start, end));
        return maximumSubArraySum;
    }
}






















