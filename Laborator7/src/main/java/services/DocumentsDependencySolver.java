package services;

import models.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentsDependencySolver {

    private List<List<Integer>> graph;
    private List<Integer> ids;
    private List<Integer> depends;
    private int N;

    public DocumentsDependencySolver() {
        graph = new ArrayList<>();
        ids = new ArrayList<>();
        depends = new ArrayList<>();
        N = 0;
    }

    public void addId(Integer id) {
        N++;
        ids.add(id);
        depends.add(0);
        graph.add(new ArrayList<>());
    }

    public void addEdge(Integer id1, Integer id2) {
        int idx1 = ids.indexOf(id1);
        int idx2 = ids.indexOf(id2);
        graph.get(idx2).add(idx1);
        depends.set(idx1, depends.get(idx1) + 1);
    }

    public int[] solve() {
        int[] order = new int[N + 2];
        boolean[] pickedAlready = new boolean[N + 2];

        for(int i = 0; i < N; i++) pickedAlready[i] = false;

        int picked = 0;

        while(picked < N) {
            for(int i = 0; i < N; i++) {
                if(depends.get(i) == 0 && !pickedAlready[i]) {
                    order[picked] = i;
                    pickedAlready[i] = true;
                    picked++;

                    for(Integer to: graph.get(i)) {
                        depends.set(to, depends.get(to) - 1);
                    }
                }
            }
        }

        return Arrays.stream(order).map(x -> ids.get(x)).toArray();
    }
}
