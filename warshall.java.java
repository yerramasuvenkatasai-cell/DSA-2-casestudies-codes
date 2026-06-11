import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloydWarshallDabbawalla {

    // A large value to represent infinity that safely avoids integer overflow
    private static final int INF = 10000000;

    /** Returns dist[][] where dist[i][j] = shortest path from i to j.
     * Modifies edges[][] in place - no separate dp tables needed. */
    static int[][] floydWarshall(int n, int[][] edges) {
        int[][] dp = new int[n][n];
        
        // Initialise: copy edges; ∞ for missing edges; 0 on diagonal
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // TODO 1: dp[i][j] = (i == j) ? 0 : (edges[i][j] != 0 ? edges[i][j] : INF)
                dp[i][j] = (i == j) ? 0 : (edges[i][j] != 0 ? edges[i][j] : INF);
            }
        }

        // Main loop: for each intermediate vertex k
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // TODO 2: if dp[i][k] + dp[k][j] < dp[i][j], update
                    //         (with overflow guard: if either dp[i][k] or dp[k][j] is INF, skip)
                    if (dp[i][k] != INF && dp[k][j] != INF) {
                        if (dp[i][k] + dp[k][j] < dp[i][j]) {
                            dp[i][j] = dp[i][k] + dp[k][j];
                        }
                    }
                }
            }
        }
        return dp;
    }

    /** Reconstruct path from i to j using a 'next' matrix maintained during FW. */
    static List<Integer> reconstructPath(int[][] next, int i, int j) {
        // TODO 3: walk: u = i; while u != j: result.add(u); u = next[u][j]
        //         result.add(j); return result
        List<Integer> result = new ArrayList<>();
        
        // Guard check: if no path exists between i and j
        if (next == null || next[i][j] == -1) {
            return result;
        }
        
        int u = i;
        while (u != j) {
            result.add(u);
            u = next[u][j];
        }
        result.add(j);
        
        return result;
    }
}