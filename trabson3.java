import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;

public class trabson3 {
    
    public static void main(String[] args) throws FileNotFoundException {

        File file    = new File("grafo.txt");       // Carregar o arquivo do grafo
        Scanner scan  = new Scanner(file);
        Scanner leia = new Scanner(System.in);
        int n_vertices = scan.nextInt();            // Carrega o primeiro dado do arquivo, no caso a linha com o numero de vertices 
        int somapeso=0;

        Grafo grafo = new Grafo(n_vertices);                  // Cria o obj Grafo
        
        for(int i =0; i < n_vertices; i++){
                grafo.addVertice(Integer.toString(i+1)); // Aqui cria-se os vertices com quantidade definida em n_vertices
        }
        while (scan.hasNextLine()) {                // Cada linha do documento possue 3 valores, indicando a origem da aresta, o destino e o peso desta  
            int origem = scan.nextInt();            // primeiro dado, de onde parte a aresta
            int destino = scan.nextInt();           // o destino da aresta
            int peso = scan.nextInt();              // o peso
            grafo.addAresta(grafo.vertices.get(origem-1), grafo.vertices.get(destino - 1), peso); // adiciona a aresta
            somapeso+=peso;
        }
        ////////// Questao 1 ////////
        grafo.gerar_matriz(n_vertices);             // Gera a matriz de adjacencias
        grafo.imp_matriz(n_vertices);               // Imprime a matriz de adjacenias
        
        ////////// Questao 2 ////////
        Scanner inp = new Scanner(System.in);
        int origem = inp.nextInt();
        int destino = inp.nextInt();
        
        grafo.n_arestas(origem-1, destino-1);// numero de arestas entre uma aresta e outra
        System.out.println("\n" + "Numero de Arestas entre Origem e destino : " + grafo.n_ar);

        System.out.print("\n" + "Caminho de um vertice a outro : " );
        grafo.caminho(origem-1, destino-1);// numero de arestas entre uma aresta e outra
        //grafo.p.prt();
        
        System.out.print("\n" + "Imprimir todas as arestas : " );
        grafo.todas_arestas();
        //grafo.prt_adj();
        
        ///////// Questao 4 ///////////
        ////busca do menor caminho
        System.out.println("\n\nInicio:");
        int inicio = leia.nextInt();
        System.out.println("Fim:");
        int fim = leia.nextInt();
        if(somapeso>0){
            int distancias2[] = grafo.dijkstra(inicio-1); 
            System.out.println("Menor distancia do inicio ao fim: (Busca de Dijkstra) " + distancias2[fim-1]);
        }else{
            int distancias[] = grafo.busca((inicio-1), n_vertices);	//ele pega o valor com base na matriz 
            System.out.println("Menor distancia do inicio ao fim: (Busca em Largura) " + distancias[fim-1]);
        }
       
    }

}
