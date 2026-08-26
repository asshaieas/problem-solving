# 2904. Shortest and Lexicographically Smallest Beautiful String

[LeetCode Problem Link](https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/?envType=daily-question&envId=2026-08-26)

## Overview & Approach

1. **Collect Positions:** Store all 0-based indices of `'1'` in `s` inside array `ones`.
2. **Edge Case:** If `len(ones) < k`, return `""`.
3. **Sliding Window:** Loop through `ones` in windows of size $k$ (`ones[i]` to `ones[i + k - 1]`):
   - `start = ones[i]`, `end = ones[i + k - 1]`
   - `current_len = end - start + 1`
   - `substring = s[start : end + 1]`
4. **Track Best:**
   - If `current_len < min_len`: update `min_len` and `best_substring`.
   - If `current_len == min_len`: update `best_substring` if `substring < best_substring`.

---

## Python Solution

```python
class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        ones = [i for i, char in enumerate(s) if char == '1']
        
        if len(ones) < k:
            return ""
        
        min_len = float('inf')
        best_substring = ""
        
        for i in range(len(ones) - k + 1):
            start = ones[i]
            end = ones[i + k - 1]
            current_len = end - start + 1
            substring = s[start : end + 1]
            
            if current_len < min_len:
                min_len = current_len
                best_substring = substring
            elif current_len == min_len:
                if substring < best_substring:
                    best_substring = substring
                    
        return best_substring
```
**ComplexityTime Complexity:** $O(N)$ — Single pass to find '1's and $O(N)$ window traversals.

**Space Complexity:** $O(N)$ — Extra memory for storing index positions in `ones`.