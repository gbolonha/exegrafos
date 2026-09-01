import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;



//definição de uma estrutura Matriz de Adjacência para armezanar um grafo
public class TGrafo {
	// Atributos Privados
	private	int n; // quantidade de vértices
	private	int m; // quantidade de arestas
	private	float adj[][]; //matriz de adjacência
	// Métodos Públicos
	//exe10
	public TGrafo( int n) {  // construtor
	    this.n = n;
	    // No início dos tempos não há arestas
	    this.m = 0; 
	    // alocação da matriz do TGrafo
	    this.adj = new float [n][n];

	    // Inicia a matriz com zeros
		for(int i = 0; i< n; i++)
			for(int j = 0; j< n; j++)
				this.adj[i][j]=Float.POSITIVE_INFINITY;	
	}

	// Insere uma aresta no Grafo tal que
	// v é adjacente a w
	
	public void insereA(int v, int w,float peso) {
	    // testa se nao temos a aresta
	    if(adj[v][w] == Float.POSITIVE_INFINITY){
	        adj[v][w] = peso;
	        m++; // atualiza qtd arestas
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    // testa se temos a aresta
	    if(adj[v][w] != Float.POSITIVE_INFINITY){
	        adj[v][w] = Float.POSITIVE_INFINITY;
	        m--; // atualiza qtd arestas
	    }
	}
	// Apresenta o Grafo contendo
	// número de vértices, arestas
	// e a matriz de adjacência obtida	
	public void show() {
	    System.out.println("n: " + n );
	    System.out.println("m: " + m );
	    for( int i=0; i < n; i++){
	    	System.out.print("\n");
	        for( int w=0; w < n; w++)
	            if(adj[i][w] !=Float.POSITIVE_INFINITY)
	            	System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
	            else System.out.print("Adj[" + i + "," + w + "]= inf" + " ");
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}
	// exe1
	public int inDegree(int v)
	{
		int grau=0;
		for (int i=0;i<n;i++)
			{
				if(adj[i][v]!=Float.POSITIVE_INFINITY) grau++;
			}
		return grau;
	}
	//exe2

	public int outDegree(int v)
	{
		int grau=0;
		for (int i=0;i<n;i++)
			{
				if(adj[v][i]!=Float.POSITIVE_INFINITY) grau++;
			}
		return grau;
	}
	//exe3
	public int degree(int v)
	{
		return outDegree(v)+inDegree(v);
	}
	//exe4
	public int isFonte(int v)
	{
		return (outDegree(v)>0 && inDegree(v)==0) ? 1:0;
	}
	//exe5
	public int isSorveduro(int v)
	{
		return (inDegree(v)>0 && outDegree(v)==0) ? 1:0;
	}
	//exe6
	public int isSimetrico()
	{
		for (int i=0;i<n;i++)
		{
			for (int j=0;j<n;j++)
			{
				if (adj[i][j]!=adj[j][i])
				{
					return 0;
				}
			}
		}
		return 1;
	}
	//exe7
	public static TGrafo lerArquivo(String nomeArq) throws FileNotFoundException
	{
    Scanner sc = new Scanner(new File(nomeArq));
    int V = sc.nextInt();
    int A = sc.nextInt();
    TGrafo g = new TGrafo(V);
    for(int i=0; i<A; i++)
	{
        int v = sc.nextInt();
        int w = sc.nextInt();
        float peso = sc.nextFloat();
        g.insereA(v, w,peso);
    }
    sc.close();
    return g;
	}
	


    //exe11 
    public void removeVertice(int v) {
        int novoN = n - 1;
        float novaAdj[][] = new float[novoN][novoN];

        for(int i=0; i<novoN; i++)
            for(int j=0; j<novoN; j++)
            {
                novaAdj[i][j] = Float.POSITIVE_INFINITY;
            }
                

        int li = 0;
        for(int i=0; i<n; i++)
        {
            if(i == v) continue;
            int lj = 0;
            for(int j=0; j<n; j++){
                if(j == v) continue;
                novaAdj[li][lj] = adj[i][j];
                lj++;
            }
            li++;
        }

        adj = novaAdj;
        n = novoN;

        m = 0;
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(adj[i][j] != Float.POSITIVE_INFINITY) 
                {
                    m++;
                }
            }
            
        }
        
    }


    //exe13: 
    public int isCompleto() 
    {
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                if(i != j && (adj[j][i]==Float.POSITIVE_INFINITY || adj[i][j] == Float.POSITIVE_INFINITY)){
                    return 0; 
                }
            }
        }
        return 1; 
    }

    //exe14
    public TGrafo complementar() {
        TGrafo comp = new TGrafo(n);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(i != j && adj[i][j] == Float.POSITIVE_INFINITY){
                    comp.insereA(i, j, 1); 
                }
            }
        }
        return comp;
    }

//exe16: categoria de conexidade do grafo dirigido
	// retorna 3 (C3-fortemente conexo), 2 (C2-unilateral), 1 (C1-fraco) ou 0 (C0-desconexo)

	// Auxiliar: DFS a partir de "origem" usando a Pilha, marcando em
	// "visitado" todos os vértices alcançados. Retorna quantos vértices
	// foram visitados no total (respeitando o sentido das arestas).
	private int dfsComPilha(int origem, boolean visitado[]) {
	    Pilha pilha = new Pilha(n);
	    pilha.push(origem);
	    visitado[origem] = true;
	    int totalVisitados = 1;

	    while (!pilha.isEmpty()) {
	        int atual = pilha.pop();
	        for (int j = 0; j < n; j++) {
	            if (adj[atual][j] != Float.POSITIVE_INFINITY && !visitado[j]) {
	                visitado[j] = true;
	                totalVisitados++;
	                pilha.push(j);
	            }
	        }
	    }
	    return totalVisitados;
	}

	// Auxiliar: mesma DFS, mas ignorando o sentido das arestas
	// (usada para testar conexidade fraca - C1)
	private int dfsComPilhaNaoDirigido(int origem, boolean visitado[]) {
	    Pilha pilha = new Pilha(n);
	    pilha.push(origem);
	    visitado[origem] = true;
	    int totalVisitados = 1;

	    while (!pilha.isEmpty()) {
	        int atual = pilha.pop();
	        for (int j = 0; j < n; j++) {
	            boolean existeArestaEmAlgumSentido =
	                adj[atual][j] != Float.POSITIVE_INFINITY ||
	                adj[j][atual] != Float.POSITIVE_INFINITY;
	            if (existeArestaEmAlgumSentido && !visitado[j]) {
	                visitado[j] = true;
	                totalVisitados++;
	                pilha.push(j);
	            }
	        }
	    }
	    return totalVisitados;
	}

	// Auxiliar: existe caminho dirigido de u até v?
	private boolean alcanca(int u, int v) {
	    boolean visitado[] = new boolean[n];
	    Pilha pilha = new Pilha(n);
	    pilha.push(u);
	    visitado[u] = true;

	    while (!pilha.isEmpty()) {
	        int atual = pilha.pop();
	        if (atual == v) return true;
	        for (int j = 0; j < n; j++) {
	            if (adj[atual][j] != Float.POSITIVE_INFINITY && !visitado[j]) {
	                visitado[j] = true;
	                pilha.push(j);
	            }
	        }
	    }
	    return visitado[v];
	}

	public int categoriaConexidade() {
 
	    // --- C3: fortemente conexo ---
	    boolean fortementeConexo = true;
	    for (int i = 0; i < n && fortementeConexo; i++) {
	        boolean visitado[] = new boolean[n];
	        if (dfsComPilha(i, visitado) < n) fortementeConexo = false;
	    }
	    if (fortementeConexo) return 3;
 
	    // --- C2: unilateralmente conexo ---
	    boolean unilateralmenteConexo = true;
	    for (int i = 0; i < n && unilateralmenteConexo; i++) {
	        for (int j = i + 1; j < n; j++) {
	            if (!alcanca(i, j) && !alcanca(j, i)) {
	                unilateralmenteConexo = false;
	                break;
	            }
	        }
	    }
	    if (unilateralmenteConexo) return 2;
 
	    // --- C1: fracamente conexo ---
	    boolean visitado[] = new boolean[n];
	    if (dfsComPilhaNaoDirigido(0, visitado) == n) return 1;
 
	    // --- C0: desconexo ---
	    return 0;
	}
 
	// Auxiliar de exe17: DFS no grafo original que empilha o vértice na
	// Pilha assim que termina de visitar todos os seus vizinhos (pós-ordem)
	private void dfsOrdemFinalizacao(int v, boolean visitado[], Pilha pilhaOrdem) {
	    visitado[v] = true;
	    for (int j = 0; j < n; j++) {
	        if (adj[v][j] != Float.POSITIVE_INFINITY && !visitado[j]) {
	            dfsOrdemFinalizacao(j, visitado, pilhaOrdem);
	        }
	    }
	    pilhaOrdem.push(v);
	}
 
	// Auxiliar de exe17: DFS no grafo transposto que rotula cada vértice
	// alcançado com o id da componente fortemente conexa atual
	private void dfsComponente(int v, boolean visitado[], int comp[], int idComp, float adjT[][]) {
	    visitado[v] = true;
	    comp[v] = idComp;
	    for (int j = 0; j < n; j++) {
	        if (adjT[v][j] != Float.POSITIVE_INFINITY && !visitado[j]) {
	            dfsComponente(j, visitado, comp, idComp, adjT);
	        }
	    }
	}
 
	//exe17: grafo reduzido (grafo de condensação das SCCs), via Kosaraju
	public TGrafo grafoReduzido() {
 
	    // 1) DFS no grafo original, empilhando por ordem de finalização
	    boolean visitado[] = new boolean[n];
	    Pilha pilhaOrdem = new Pilha(n);
	    for (int i = 0; i < n; i++) {
	        if (!visitado[i]) {
	            dfsOrdemFinalizacao(i, visitado, pilhaOrdem);
	        }
	    }
 
	    // 2) monta o grafo transposto (inverte todas as arestas)
	    float adjT[][] = new float[n][n];
	    for (int i = 0; i < n; i++)
	        for (int j = 0; j < n; j++)
	            adjT[i][j] = adj[j][i];
 
	    // 3) desempilha e faz DFS no transposto: cada DFS = 1 SCC
	    int comp[] = new int[n];
	    for (int i = 0; i < n; i++) comp[i] = -1;
	    boolean visitado2[] = new boolean[n];
	    int idComp = 0;
 
	    while (!pilhaOrdem.isEmpty()) {
	        int v = pilhaOrdem.pop();
	        if (!visitado2[v]) {
	            dfsComponente(v, visitado2, comp, idComp, adjT);
	            idComp++;
	        }
	    }
 
	    // 4) monta o grafo reduzido: 1 vértice por componente
	    TGrafo reduzido = new TGrafo(idComp);
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < n; j++) {
	            if (adj[i][j] != Float.POSITIVE_INFINITY && comp[i] != comp[j]) {
	                reduzido.insereA(comp[i], comp[j], 1);
	            }
	        }
	    }
	    return reduzido;
	}
}
