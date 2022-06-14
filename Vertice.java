import java.util.*;

public class Vertice {
    String nome;
    List<Aresta> adj;

    
    Vertice(String nome) {
        this.nome = nome;
        this.adj = new ArrayList<Aresta>();
    }

    void addAdj(Aresta e) {
        adj.add(e);
    }
}