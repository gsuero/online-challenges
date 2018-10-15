import java.util.ArrayList;

public class MaxStockProfit {

    
    public static void main(String[] argvs) {
        long[] values = {30, 20, 30, 10, 50, 100, 10, 20, 10, 40, 50, 0, 50, 60};
        
        long startTime = java.lang.System.currentTimeMillis();
        System.out.println("Start time: " + startTime);
        System.out.println(maxProfit(values)); 
        System.out.println("End time: " + (java.lang.System.currentTimeMillis() - startTime));
        
        startTime = java.lang.System.currentTimeMillis();
        System.out.println("Start time: " + startTime);
        System.out.println(maxProfitOptimal(values)); 
        System.out.println("End time: " + (java.lang.System.currentTimeMillis() - startTime));
    }
    /******************************************************************
    Coding Exercise
    Given the stock prices of one company for a day, create a method
    that would return the maximum profit for the day given the
    following conditions:
    * you can only buy and sell once
    * the difference between buy and sell time must be at least five
      seconds long
     
    Assumptions:
    * the difference in time between each stock price is 1 second
    * stock prices are already in order by time
    * if there is no profit for the day, return 0
    ******************************************************************/
    // 30 20 30 10 50 100 10 20 10 40 50 0 50 60 
    static long maxProfit(long[] prices) {
        long profit  = 0;
        for (int a = 0; a < prices.length ; a++) {
            for (int b = a + 5; b < prices.length; b++) {

                long tempProfit = prices[b] - prices[a];
                if (tempProfit > profit) {
                    profit = tempProfit;
                }
            }
            
        }
        
        return profit;
        
    }

    static long maxProfitOptimal(long[] prices) {
        long profit  = 0;
        long minPrice = prices[0];
        
        for (int a = 1; a < prices.length ; a++) {
     
            if (prices[a] < minPrice) {
                minPrice = prices[a];
            }
            int nextPosition = a + 5;
            if (nextPosition >= prices.length) {
                break;
            }
            long tempProfit = prices[nextPosition] - minPrice;
            if (tempProfit > profit) {
                profit = tempProfit;
            }
        }

        return profit;
    }

}
