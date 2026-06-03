class BPlusTreeRangeCount { 
    public static int rangeCount(int[] prices, int lo, int hi) { 
        int count = 0; 
        for (int price : prices) { 
            if (price >= lo && price <= hi) count++; 
            if (price > hi) break; 
        } 
        return count; 
    } 
}