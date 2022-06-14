import java.util.*;


public class Grafo {
    List<Vertice> vertices;
    List<Aresta> arestas;
    LinkedList<Integer> adj[];
    int [][]matriz_adj;
    int n_ar = 0, somapeso=0;
    boolean caminho_ver;
    int [] vetor;
    Pilha p;
	    
 
    public Grafo(int n_v) {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();

        p = new Pilha(); 

        adj = new LinkedList[n_v];
        for (int i = 0; i < n_v; ++i)
            adj[i] = new LinkedList();

        vetor = new int[n_v];
    } 
    Vertice addVertice(String nome) {
        Vertice v = new Vertice(nome);
        vertices.add(v);
        return v;
    }

    Aresta addAresta(Vertice origem, Vertice destino, int dist) {
        Aresta e = new Aresta(origem, destino, dist);
        origem.addAdj(e);
        arestas.add(e);
        adj[Integer.parseInt(origem.nome)-1].add(Integer.parseInt(destino.nome));
        return e;
    }

    public String toString() {
        String r = "";
        for (Vertice u : vertices) {
            r += u.nome + " -> ";
            for (Aresta e : u.adj) {
                Vertice v = e.destino;
                r += v.nome + ", ";
            }
            r += "\n";
        }
        return r;
    }

    public void gerar_matriz(int n_vertices){

        matriz_adj = new int[n_vertices][n_vertices]; 	//linhas e colunas qntidade n� de vertices
        int origem, destino;
        
        for (Vertice v : vertices){
            for (Aresta e : v.adj){		//lista de arestas interligadas
                
                origem = Integer.parseInt(e.origem.nome)-1;		//pega o nome de origem e destino de todas as arestas(linhas) e converte em int
                destino= Integer.parseInt(e.destino.nome)-1;
                somapeso+=e.peso;
                if(somapeso<1){
                    matriz_adj[origem][destino] = 1; //so vale em grafos nao direcionados
                    matriz_adj[destino][origem] = 1; // SE for direcionado deve mudar para nao preencher o outrolado
                }else{
                    matriz_adj[origem][destino] = e.peso; //so vale em grafos nao direcionados
                    matriz_adj[destino][origem] = e.peso;    // SE for direcionado deve mudar para nao preencher o outrolado
                    
                }
            }
        }
    }

    public void imp_matriz(int n_vertices){
        for(int i=0; i < n_vertices; i++){
            for(int j=0; j < n_vertices; j++){
                System.out.print(matriz_adj[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
    
    ///////////// questao 2 ///////////
    void DFSUtil(int v, boolean visited[],int d)
    {
        if(caminho_ver && !p.contains(v+1)){
            p.push(v+1);
            //p.prt();
            System.out.print(v+1 + " ");  
        }

        // Marcar no atual como visitado
        visited[v] = true;
        vetor[v] = 1;
        // Pecorrer todos os nos adjacentes a esse
        // vertex
        Iterator<Integer> i = adj[v].listIterator();
        while (i.hasNext()) 
        {
            int n = i.next()-1;
            
            //System.out.println("\n n: " + n + " v: " + v + " d: " + d);
            if (!visited[d]){
                n_ar++;
                DFSUtil(n, visited, d);
            }
        }
    }
  
    void DFS(int o, int d)
    {
        // Marcar todos os vertices como n visitados

        boolean visited[] = new boolean[vertices.size()];
        
        for (int i = d+1; i < visited.length; i++){
            visited[i] = true;
            //System.out.print(visited[i] + " : " + i + " ");
        }
        
        DFSUtil(o, visited,d);
    }

    public void n_arestas(int o, int d){
        DFS(o,d);
    }

    public void caminho(int o, int d){
        n_ar = 0;
        caminho_ver = true;
        DFS(o, d);
        caminho_ver = false;
    }

    public void prt_adj(){
        for(LinkedList<Integer> a : adj){
            System.out.println(a);
        }
    }
    
    public boolean contains(final int[] array, final int key) {
        return Arrays.asList(array).contains(key);
    }

    public void todas_arestas(){
        for(int e : p.a){
            if (e > 0)
                System.out.print(e + " ");
        }
    }

    //////////// Questao 4 /////////////
    
    ///////////////////////     busca largura - sem pesos       /////////////////////////////
    public int[] busca(int in, int f) {
    	 // FAZER UM IF, pra identificar se tem peso ou nao
    	// se tiver peso faz busca por Dijskrta senao busca em largura
    	int[] nivel = new int[f];
		Queue<Integer> fila = new LinkedList<Integer>();

		// Inicialinzando nivel de todos os v�rtices como -1
		for(int i=0; i<f; i++)
			nivel[i]=-1;

		// O n�vel do v�rtice inicial � zero
		nivel[in] = 0;	//ex: in = 0 = 1
		fila.add(in);

		// Executando a busca lateral
		while(!fila.isEmpty()){ //enquanto a fila est� cheia
			int atual = fila.remove();
			for(int i = 0; i < f; i++)      //pega os vertices q o in interliga, e vai seguindo o caminho de cada um
				if(ma(atual, i, matriz_adj) == 1 && nivel[i] == -1){	//nivel=-1 isso � nao foi visitado
					nivel[i] = nivel[atual] + 1;	//salvando menor nivel de cada vertice //ate o atual tinha 1 qnd analisa atual mais outros soma + o valor q ja tinha
					fila.add(i);
					
				}
		}
    	return nivel;
    }
    public static int ma(int i, int j, int[][] ma){ // deduzindo q a matriz seja simetrica
		if(i < j)	//atual<i
			return ma[i][j];
		else
			return ma[j][i]; //ma[j][i] se for nao dirigido - para chegar a conexao tanto sentido E com V, qnt V com E
	}                       

    ////////////////////// busca djikstra - com pesos       ///////////////////////////
    public int[] dijkstra(int in) {
        int v = matriz_adj.length;
        boolean visitados[] = new boolean[v];
        int dist[] = new int[v];
        
        for(int i=0;i<v;i++){
            dist[i]= Integer.MAX_VALUE; //todos recebem a maior distancia entre si
        }
        dist[in] = 0;   // o inicio selecionado
        for(int i=0; i<v-1;i++){
            int minVertice = buscaMinVertice(dist, visitados); 
            visitados[minVertice] = true;   //preenche vertices q foram visitados
            for(int j=0;j<v;j++){       //faz o percurso identificando e alterando as distancias, ao chegar no ultimo vertice
                if(matriz_adj[minVertice][j] != 0 && !visitados[j]){            //retorna para o 1º for e refaz o processo
                    int nvDist = dist[minVertice] + matriz_adj[minVertice][j];  //valor do vertice + distancia ate o outro vertice
                    if(nvDist<dist[j]){
                        dist[j]=nvDist;     //se for menor acrescenta no vetor nas posições referentes aos vertices
                    }                   // substitui a distancia do atual se encontrar um caminho menor
                }
            }  
        }
        return dist;
    }

    private static int buscaMinVertice(int[] distancia, boolean visitados[]){
        int minVertice = -1;
        for(int i=0; i<distancia.length;i++){
            if(!visitados[i] && (minVertice == -1 || distancia[i]<distancia[minVertice])){
                minVertice=i;           //peso do vert i < peso do menor vert
            } //visitados tem q ser diferente de true
        }
        return minVertice;
    }
    
}