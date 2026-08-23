class Solution:
    def sumGame(self, num: str) -> bool:
        # Count unknown digits and calculate the known digit sums for both halves of the string.
        sumL = cntL = sumR = cntR = 0
        half = len(num) // 2

        for i in range(1, half + 1):
            if num[i - 1] != '?':
                sumL += int(num[i - 1])
            else:
                cntL += 1

            if num[len(num) - i] != '?':
                sumR += int(num[len(num) - i])
            else:
                cntR += 1

        # Calculate the known sum difference and the difference between unknown digits in both halves.
        difference = sumL - sumR
        extra_qmarks = cntL - cntR

        # An odd number of extra unknown digits gives Alice the advantage regardless of digit choices.
        if extra_qmarks % 2 != 0:
            return True

        # Bob wins only when the existing difference can be exactly balanced by remaining unknown digits.
        if (difference + (extra_qmarks / 2) * 9) == 0:
            return False

        return True

# test the solution
test = Solution()
print(test.sumGame('1232'))