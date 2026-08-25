# 3718. Smallest Missing Multiple of K

**Source:** [LeetCode — Smallest Missing Multiple of K](https://leetcode.com/problems/smallest-missing-multiple-of-k/description/?envType=daily-question&envId=2026-08-25)

## Problem

Given an integer array `nums` and an integer `k`, return the **smallest positive multiple of `k` that is missing from `nums`**.

**Difficulty:** Easy

## Approach

1. Convert `nums` into a **set** to remove duplicates and allow fast membership checks.
2. Start `multiple` with `k`, since `k` is the first positive multiple.
3. Use a `while` loop because we don't know in advance how many multiples we need to check.
4. If `multiple` exists in the set, increase it by `k` to check the next multiple.
5. When `multiple` is not in the set, return it immediately.

If `k` is missing from the set from the beginning, we immediately return `k`.

## Complexity

- **Time:** `O(n)` average — building the set takes `O(n)`, and we perform average `O(1)` membership checks. At most `n + 1` multiples need to be checked.
- **Space:** `O(n)` — the set can contain up to `n` elements.

## Key Insight

We only need to check the multiples of `k` in increasing order:

`k, 2k, 3k, 4k, ...`

The first one that is not in the set is automatically the smallest missing multiple.