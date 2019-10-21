package com.company;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ReaderImpl implements Reader {
    public Graph read(String input) {
        String[] lines = input.split("\\r?\\n");
        int verticesCount = Integer.parseInt(lines[0], 2);
        String[] verticesStructureDesc = Arrays.copyOfRange(lines, 1, verticesCount + 1);
        String[] verticesContentDesc = Arrays.copyOfRange(lines, verticesCount + 2, lines.length);
        System.out.println(Arrays.asList(verticesStructureDesc));
        System.out.println(Arrays.asList(verticesContentDesc));
        Vertex[] vertices = Arrays.stream(verticesContentDesc)
                .map(this::parseVertex)
                .toArray(Vertex[]::new);
        ArrayList<Edge> edges = new ArrayList<>();
        for (int i = 0; i < verticesCount; i++) {
            Integer[] vertexDigits = Arrays.stream(verticesStructureDesc[i].split(" "))
                    .map(s -> Integer.parseInt(s, 2))
                    .toArray(Integer[]::new);
            Integer[] ends = Arrays.copyOfRange(vertexDigits, 2, vertexDigits.length);
            for (Integer end: ends){
                edges.add(new Edge(vertices[i], vertices[end-1]));
                System.out.println("begin: " + (i + 1) + ", end: " + end);
            }
        }
        return new Graph(new ArrayList<>(Arrays.asList(vertices)), edges);
    }

    private Vertex parseVertex(String coded){
        String content = "";
        String regex = "[(][0-9]+x[0-9]+[)]";
        String[] texts = coded.split(regex);
        String[] repeaters = Pattern.compile(regex)
                .matcher(coded)
                .results()
                .map(MatchResult::group)
                .toArray(String[]::new);
        for (int i = 0; i < texts.length; i++) {
            content += texts[i];
            if(i<repeaters.length){
                content += repeat(content, repeaters[i]);
            }
        }
        System.out.println(content);
        System.out.println(content.length());
        return new Vertex(content);
    }

    private String repeat(String input, String repeater){
        String[] cleanRepeater = repeater.replaceAll("[()]", "").split("x");
        int fragmentLength = Math.min(input.length(), Integer.parseInt(cleanRepeater[0]));
        int repeats = Integer.parseInt(cleanRepeater[1]);
        String fragment = input.substring(input.length() - fragmentLength);
        String result = "";
        for (int i = 0; i < repeats - 1; i++) {
            result += fragment;
        }
        return result;
    }
}
