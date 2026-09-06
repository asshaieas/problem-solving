public class Main {
    public static void main(String[] args) {
      System.out.println("Hello, World!");
      Solution soltion = new Solution();
      System.out.println(soltion.firstStableIndex(new int[]{5, 0, 1, 4}, 3));
    }
}
//nums = [5, 0, 1, 4]
class Solution {
    public int firstStableIndex(int[] nums, int k) {
          int n = nums.length;
          // I need to build the prefix max 
          int [] prefix_max = new int [n];
          prefix_max[0] = nums[0];
          // now prefix_max= {5, 5, 5, 5}
          for (int i = 1; i < n; i ++){//i = 3, prefix_max[2] = max(5, 4)
            prefix_max[i] = Math.max(prefix_max[i - 1], nums[i]);
          }
          // now I need to build the suffix min 
          int [] suffix_min = new int [n];
          suffix_min[n - 1] = nums[n-1];
          for (int i = n -2; i >=0; i --){
            suffix_min[i] = Math.min(suffix_min[i + 1], nums[i]);
          }
          //now I need to check for stable 
          for (int i = 0; i < n; i ++){
            if ((prefix_max[i] - suffix_min[i]) <= k){
              return i;
            }
          }
          // if no stable return - 1 
          return -1;
    }
}