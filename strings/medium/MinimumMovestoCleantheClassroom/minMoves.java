import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int startR = -1, startC = -1;

        Map<Integer, Integer> litterIndex = new HashMap<>();

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    int cellCode = r * n + c;
                    litterIndex.put(cellCode, litterIndex.size());
                }
            }
        }

        int litterCount = litterIndex.size();
        int fullMask = (1 << litterCount) - 1;

        // Edge case: no litter at all
        if (fullMask == 0) return 0;

        // State = (r, c, e, mask) packed into one int:
        // code = ((r * n + c) * (energy+1) + e) * (fullMask+1) + mask
        int energyLevels = energy + 1;
        int maskLevels = fullMask + 1;

        boolean[] visited = new boolean[m * n * energyLevels * maskLevels];

        Queue<int[]> queue = new LinkedList<>();
        // queue element: {r, c, e, mask}
        queue.offer(new int[]{startR, startC, energy, 0});
        visited[encode(startR, startC, energy, 0, n, energyLevels, maskLevels)] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                int r = cur[0], c = cur[1], e = cur[2], mask = cur[3];

                if (mask == fullMask) return moves;

                // Can't move with 0 energy unless standing on 'R'
                if (e == 0 && classroom[r].charAt(c) != 'R') continue;

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                    char ch = classroom[nr].charAt(nc);
                    if (ch == 'X') continue;

                    int ne = (e == 0) ? energy - 1 : e - 1; // moved from R (full) or normal cell
                    if (ch == 'R') ne = energy - 1; // will be refreshed to full next time we act, but energy spent this move
                    // Actually simplest: moving always costs 1 energy from current e (which must be > 0, or we refilled at R)
                    ne = e - 1;
                    if (ch == 'R') {
                        // stepping onto R doesn't refill immediately mid-move; refill happens when "used"
                    }

                    int nmask = mask;
                    Integer idx = litterIndex.get(nr * n + nc);
                    if (idx != null) {
                        nmask = mask | (1 << idx);
                    }

                    // Determine effective energy after arriving:
                    // if landing on R, energy resets to full for future moves
                    int arrivalEnergy = ne;
                    if (ch == 'R') arrivalEnergy = energy;

                    int code = encode(nr, nc, arrivalEnergy, nmask, n, energyLevels, maskLevels);
                    if (!visited[code]) {
                        visited[code] = true;
                        queue.offer(new int[]{nr, nc, arrivalEnergy, nmask});
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    private int encode(int r, int c, int e, int mask, int n, int energyLevels, int maskLevels) {
        int cellCode = r * n + c;
        int code2 = cellCode * energyLevels + e;
        return code2 * maskLevels + mask;
    }
}

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] classroom = {"S.", "XL"};
        System.out.println(sol.minMoves(classroom, 2)); // expect 2
    }
}
