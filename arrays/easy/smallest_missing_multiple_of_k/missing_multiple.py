from typing import List
class Solution:
    def missingMultiple(self, nums: List[int], k: int) -> int:
        set_nums = set(nums)
        multiple = k
        while multiple in set_nums:
            multiple += k
        return multiple
#test solution
test = Solution()
nums = [8,2,3,4,6]
k = 2
print(test.missingMultiple(nums, k))