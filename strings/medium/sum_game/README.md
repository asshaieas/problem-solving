# Sum Game

## Problem Summary

Given an even-length string containing digits and `?` characters, Alice and Bob take turns replacing `?` with digits from `0` to `9`.

Alice wins if the final sums of the two halves are different. Bob wins if the sums are equal.

The goal is to determine whether Alice will win when both players play optimally.

## Approach

First, define variables to store the known digit sums and the number of unknown digits in both halves:

- `sumL`: Sum of known digits in the left half.
- `sumR`: Sum of known digits in the right half.
- `cntL`: Number of `?` characters in the left half.
- `cntR`: Number of `?` characters in the right half.

Then, calculate half of the string length because each position only needs to be processed once from each half.

Use a `for` loop from `1` to `half + 1`. For each position, check whether the character is a `?`.

If it is a known digit, convert it to an integer and add it to the corresponding half's sum. Otherwise, increment the number of unknown characters for that half.

After calculating the sums and unknown characters, calculate:

- `difference = sumL - sumR`
- `extra_qmarks = cntL - cntR`

If the number of extra question marks is odd, Alice immediately wins because the players cannot distribute the unknown digits equally.

Otherwise, Bob can win only if the existing difference can be exactly balanced by the remaining unknown digits. The maximum contribution of each unknown digit is `9`, so we check whether:

`difference + (extra_qmarks / 2) * 9 == 0`

If this condition is true, Bob wins, so return `False`. Otherwise, Alice wins, so return `True`.

## Complexity Analysis

- **Time Complexity:** `O(n)`, where `n` is the length of the string. We process the characters from both halves in a single loop.
- **Space Complexity:** `O(1)`, because only a constant number of variables are used.

## LeetCode

[Sum Game](https://leetcode.com/problems/sum-game/description/?envType=daily-question&envId=2026-08-23)