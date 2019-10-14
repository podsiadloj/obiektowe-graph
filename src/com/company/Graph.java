package com.company;

import java.util.ArrayList;

public class Graph {
    public ArrayList<Vertex> vertices;
    public ArrayList<Edge> edges;

    public Graph(ArrayList<Vertex> vertices, ArrayList<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
}
