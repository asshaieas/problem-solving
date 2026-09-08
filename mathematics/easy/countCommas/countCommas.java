import java.util.*;

public class Main {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
      Solution solution = new Solution();
      System.out.println(solution.countCommas(1003));
    }
}
class Solution {
    public int countCommas(int n) {
        int total_commas = 0;
        for (int d = 1; d<=n; d++){
          int low = (int) Math.pow(10, (d - 1));
          int high = (int) Math.pow(10, d)  - 1;
          if (low > n){
            break; // means no number of this length exit in [1, n]
          }
        high = Math.min(high, n);
        int count = high - low + 1;
        int commas_per_number = (d - 1)  / 3;
        total_commas += count * commas_per_number;
        }
        return total_commas;
    }
}
// time complexity anlysis 
/* time complexity is O( log n) and space complexity O(1) no extra space used*/
