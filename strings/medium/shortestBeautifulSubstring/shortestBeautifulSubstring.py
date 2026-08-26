class Solution:
    def shortestBeautifulSubstring(self, s: str, k: int) -> str:
        ones = []
        best_substring = ''

        # Step 1: Collect positions of all 1s
        for i in range(len(s)):
            if s[i] == '1':
                ones.append(i)

        # Step 2: Edge case check
        if len(ones) < k:
            return best_substring

        # Step 3 & 4: Track shortest length & slide window of size k
        min_len = float('inf')
        for i in range(len(ones) - k + 1):
            start = ones[i]
            end = ones[i + k - 1]
            current_len = end - start + 1
            substring = s[start: end + 1]

            # Step 5: Update trackers
            if current_len < min_len:
                min_len = current_len
                best_substring = substring
            elif min_len == current_len:
                if substring < best_substring:
                    best_substring = substring

        return best_substring

#test solution
test = Solution()
s = "100011001";k = 3
print(test.shortestBeautifulSubstring(s, k))
