package utils;

import demo.EuclideanGraph;
import demo.Vertex;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class InOutUtils {
    public static EuclideanGraph load(String filePath) {
        EuclideanGraph graph = new EuclideanGraph();

        try (BufferedReader fReader = Files.newBufferedReader(Paths.get(filePath), StandardCharsets.UTF_8)) {

            String line = fReader.readLine().trim();
            String[] part = line.split("\\s+");
            if (part.length != 2) {
                throw new RuntimeException("Patikrinkite pirmąją eilutę, kurioje turėtų būti tik viršūnių ir briaunų skaičius");
            }
            int verticesCount = Integer.parseInt(part[0]);
            int edgesCount = Integer.parseInt(part[1]);

            String empty1 = fReader.readLine();
            if (!empty1.isEmpty()) {
                throw new RuntimeException("Nepaliktas tarpelis po pirmos eilutės");
            }

            Vertex[] vertices = new Vertex[verticesCount];
            for (int i = 0; i < verticesCount; i++) {
                String lines = fReader.readLine().trim();
                if (lines.isEmpty()) continue;

                String[] parts = lines.split("\\s+");
                if (parts.length != 3) {
                    throw new RuntimeException("Patikrinkite viršūnių aprašymą, kiekvienoje viršūnę aprašančioje eilutėje turėtų būti viršūnės numeris, x ir y koordinatės");
                }
                int v = Integer.parseInt(parts[0]);
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);

                if (v < 0 || v >= verticesCount) throw new RuntimeException("Illegal vertex number");
                vertices[v] = new Vertex(x, y);
            }

            String empty2 = fReader.readLine();
            if (!empty2.isEmpty()) {
                throw new RuntimeException("Nepaliktas tarpelis po viršūnių koordinačių aprašymo arba aprašyta per daug briaunų");
            }

            ArrayList<Integer>[] edges = new ArrayList[verticesCount];

            for (int i = 0; i < verticesCount; i++) {
                edges[i] = new ArrayList<>();
            }

            for (int i = 0; i < edgesCount; i++) {
                String lines = fReader.readLine().trim();
                if (lines.isEmpty()) continue;

                String[] parts = lines.split("\\s+");
                if (parts.length != 2) {
                    throw new RuntimeException("Patikrinkite briaunų aprašymą, kiekvienoje briauną aprašančioje eilutėje turėtų būti po dvi viršūnes, kurios nurodo briaunos pradžią ir pabaigą");
                }
                int v = Integer.parseInt(parts[0]);
                int w = Integer.parseInt(parts[1]);

                if (v < 0 || v >= verticesCount || w < 0 || w >= verticesCount)
                    throw new RuntimeException("Negalimos viršūnių reikšmės aprašant briaunas");

                edges[v].add(w);
                edges[w].add(v);
            }



            String empty3 = fReader.readLine();
            if (!empty3.isEmpty()) {
                throw new RuntimeException("Nepaliktas tarpelis po briaunų aprašymo arba aprašyta per daug briaunų");
            }
            String l = fReader.readLine().trim();
            String[] p = l.split("\\s+");
            int start = Integer.parseInt(p[0]);
            int end = Integer.parseInt(p[1]);
            if (p.length != 2) {
                throw new RuntimeException("Patikrinkite paskutinę eilutę, kurioje turėtų būti tik kelio, kurį norite rasti pradžios ir pabaigos viršūnės");
            }

            graph = new EuclideanGraph(verticesCount, edgesCount, vertices, edges, start, end);
        } catch (IOException e) {
            System.err.println("Klaida skaitant failą: " + e.getMessage());
        }
        return graph;
    }
}