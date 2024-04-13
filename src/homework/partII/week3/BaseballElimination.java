package homework.partII.week3;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
    private final int n;
    private final String[] teams;
    private final ST<String, Integer> st;
    private final int[][] data;
    private final boolean[] isEliminated;
    private final Queue<String>[] winsTeam;

    // create homework.a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        if (filename == null) throw new IllegalArgumentException();

        In in = new In(filename);

        this.n = in.readInt();
        teams = new String[n];
        st = new ST<>();
        data = new int[n][n + 3];
        isEliminated = new boolean[n];
        winsTeam = (Queue<String>[]) new Queue[n];

        int maxWin = 0;
        int i = 0;
        int max = (n * n + n + 4) / 2;

        while (!in.isEmpty()) {
            String name = in.readString();
            st.put(name, i);
            teams[i] = name;

            int win = in.readInt();
            if (win > maxWin) maxWin = win;
            data[i][0] = win;

            for (int k = 1; k < n + 3; k++) data[i][k] = in.readInt();

            i++;
        }


        for (i = 0; i < n; i++) {
            int dealWin = data[i][0] + data[i][2];
            if (dealWin < maxWin) {
                winsTeam[i] = new Queue<>();
                isEliminated[i] = true;

                for (int j = 0; j < n; j++)
                    if (dealWin < data[j][0])
                        winsTeam[i].enqueue(teams[j]);

                continue;
            }

            FlowNetwork test = creatFN(i);
            FordFulkerson ff = new FordFulkerson(test, 0, max - 1);

            for (FlowEdge e : test.adj(0)) {
                if (e.flow() < e.capacity()) {
                    isEliminated[i] = true;
                    break;
                }
            }

            if (isEliminated[i]) {
                winsTeam[i] = new Queue<>();
                for (int j = max - n - 1; j < max; j++) {
                    if (ff.inCut(j))
                        winsTeam[i].enqueue(teams[n - max + 1 + j]);
                }
            }
        }
    }

    private FlowNetwork creatFN(int exclude) {
        FlowNetwork g = new FlowNetwork((n * n + n + 4) / 2);
        int tem = 1;
        int b = n - 1;
        int game = g.V() - n - 1;
        int remain = data[exclude][0] + data[exclude][2];

        for (int i = 0; i < n; i++) {
            if (i != exclude) {
                for (int j = i + 1; j < n; j++) {
                    if (j != exclude) {
                        FlowEdge e = new FlowEdge(0, tem, against(teams[i], teams[j]));
                        FlowEdge e2 = new FlowEdge(tem, game + i, Double.POSITIVE_INFINITY);
                        FlowEdge e3 = new FlowEdge(tem, game + j, Double.POSITIVE_INFINITY);
                        g.addEdge(e);
                        g.addEdge(e2);
                        g.addEdge(e3);
                    }
                    tem++;
                }
                FlowEdge e = new FlowEdge(game + i, g.V() - 1, remain - data[i][0]);
                g.addEdge(e);
            } else tem += b;
            b--;
        }
        return g;
    }

    // number of teams
    public int numberOfTeams() {
        return n;
    }

    // all teams
    public Iterable<String> teams() {
//        Queue<String> queue = new Queue<>();
//        for (String s : teams) queue.enqueue(s);
//        return queue;
        return st.keys();
    }

    // number of wins for given team
    public int wins(String team) {
        if (team == null || !st.contains(team)) throw new IllegalArgumentException();

        return data[st.get(team)][0];
    }

    // number of losses for given team
    public int losses(String team) {
        if (team == null || !st.contains(team)) throw new IllegalArgumentException();
        return data[st.get(team)][1];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        if (team == null || !st.contains(team)) throw new IllegalArgumentException();
        return data[st.get(team)][2];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        if (team1 == null || team2 == null) throw new IllegalArgumentException();
        if (!st.contains(team1) || !st.contains(team2)) throw new IllegalArgumentException();
        return data[st.get(team1)][st.get(team2) + 3];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (team == null || !st.contains(team)) throw new IllegalArgumentException();
        return isEliminated[st.get(team)];
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (team == null || !st.contains(team)) throw new IllegalArgumentException();
        return winsTeam[st.get(team)];
    }

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("HW_Project\\baseball\\teams12.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
