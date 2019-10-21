package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GraphProcessorImpl implements GraphProcessor {
    @Override
    public Vertex[] findShortest(Graph g, int start, int end) {
        int startIndex = start - 1;
        int[] distances = new int[g.vertices.size()];
        int[] previous = new int[g.vertices.size()];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distances[startIndex] = 0;
        ArrayList<NumberedVertex> candidates = new ArrayList<>();
        ArrayList<NumberedVertex> processed = new ArrayList<>();
        for (int i = 0; i < g.vertices.size(); i++) {
            candidates.add(new NumberedVertex(i, g.vertices.get(i)));
        }
        while (candidates.size() > 0){
            NumberedVertex currentVertex = null;
            int distanceCandidate = Integer.MAX_VALUE;
            for (NumberedVertex candidate : candidates) {
                if(distances[candidate.number] < distanceCandidate){
                    currentVertex = candidate;
                    distanceCandidate = distances[candidate.number];
                }
            }
            if(currentVertex == null){
                break;
            }
            for (int p : previous) {
                System.out.print(" " + p);
            }
            System.out.println("");
            for (int distance : distances) {
                System.out.print(" " + distance);
            }
            System.out.println(" : " + currentVertex.number);
            NumberedVertex finalCurrentVertex = currentVertex;
            NumberedVertex[] neighbors = g.edges
                    .stream()
                    .filter(edge -> edge.begin == finalCurrentVertex.vertex)
                    .map(edge -> edge.end)
                    .map(vertex -> new NumberedVertex(g.vertices.indexOf(vertex), vertex))
                    .toArray(NumberedVertex[]::new);
            for (NumberedVertex neighbor: neighbors) {
                int newDistance = distances[currentVertex.number] + neighbor.vertex.content.length();
                if(newDistance < distances[neighbor.number]){
                    distances[neighbor.number] = newDistance;
                    previous[neighbor.number] = currentVertex.number;
                }
            }
            processed.add(currentVertex);
            candidates.remove(currentVertex);
        }
        ArrayList<Vertex> results = new ArrayList<>();
        if(distances[end-1] < Integer.MAX_VALUE){
            int last = end - 1;
            while(last != -1){
                results.add(0, g.vertices.get(last));
                last = previous[last];
            }
        }
        return results.toArray(Vertex[]::new);
    }

    private class NumberedVertex{
        int number;
        Vertex vertex;

        NumberedVertex(int number, Vertex vertex){
            this.number = number;
            this.vertex = vertex;
        }
    }
}
