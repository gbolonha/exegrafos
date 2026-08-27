//definição de uma estrutura Matriz de Adjacência para armazenar um grafo NÃO dirigido
public class TGrafoND {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	int adj[][]; //matriz de adjacência
	// Métodos Públicos
	public TGrafoND( int n) {  // construtor (nome igual ao da classe!)
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafoND
	    this.adj = new int [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=0;	
	}

	// exe8: Insere uma aresta NÃO dirigida entre v e w
	// (marca os dois sentidos, mas conta só 1 aresta)
	public void insereA(int v, int w) {
	    // testa se nao temos a aresta (basta checar um dos sentidos,
	    // já que a matriz é sempre mantida simétrica)
	    if(adj[v][w] == 0 ){
	        adj[v][w] = 1;
	        adj[w][v] = 1;
	        m++; // atualiza qtd arestas (uma só, mesmo marcando 2 células)
	    }
	}
	
	// exe8: remove a aresta NÃO dirigida entre v e w
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] == 1 ){
	        adj[v][w] = 0;
	        adj[w][v] = 0;
	        m--; // atualiza qtd arestas
	    }
	}
	// exe8: Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida (sempre simétrica)
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] == 1)
	            	System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo (nao dirigido)." );
	}
}
