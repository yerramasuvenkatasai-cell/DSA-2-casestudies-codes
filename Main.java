import java.util.*;

class Activity {
    int id;
    int start, end;

    Activity(int id, int s, int e) {
        this.id = id;
        this.start = s;
        this.end = e;
    }
}

public class Main {

    static List<Activity> selectActivities(List<Activity> activities) {

        activities.sort((a, b) -> Integer.compare(a.end, b.end));

        List<Activity> result = new ArrayList<>();
        int lastEnd = Integer.MIN_VALUE;

        for (Activity a : activities) {
            if (a.start >= lastEnd) {
                result.add(a);
                lastEnd = a.end;
            }
        }

        return result;
    }

    static int weightedActivitySelection(List<Activity> activities, int[] weights) {

        int n = activities.size();

        activities.sort((a, b) -> Integer.compare(a.end, b.end));

        int[] p = new int[n];

        for (int i = 0; i < n; i++) {
            p[i] = -1;

            for (int j = i - 1; j >= 0; j--) {
                if (activities.get(j).end <= activities.get(i).start) {
                    p[i] = j;
                    break;
                }
            }
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {

            int taking = weights[i - 1]
                    + ((p[i - 1] == -1) ? 0 : dp[p[i - 1] + 1]);

            int skipping = dp[i - 1];

            dp[i] = Math.max(taking, skipping);
        }

        return dp[n];
    }

    public static void main(String[] args) {

        List<Activity> activities = new ArrayList<>();

        activities.add(new Activity(1, 900, 1030));
        activities.add(new Activity(2, 930, 1100));
        activities.add(new Activity(3, 1000, 1130));
        activities.add(new Activity(4, 1100, 1200));
        activities.add(new Activity(5, 1130, 1300));
        activities.add(new Activity(6, 1230, 1400));
        activities.add(new Activity(7, 1300, 1500));
        activities.add(new Activity(8, 1430, 1600));

        List<Activity> selected = selectActivities(activities);

        System.out.println("Selected Activities:");
        for (Activity a : selected) {
            System.out.println("A" + a.id + " : " + a.start + " - " + a.end);
        }

        System.out.println("Total Activities Selected = " + selected.size());

        int[] weights = {4, 2, 5, 3, 6, 5, 8, 4};

        int maxWeight = weightedActivitySelection(
                new ArrayList<>(activities), weights);

        System.out.println("Maximum Weight = " + maxWeight);
    }
}