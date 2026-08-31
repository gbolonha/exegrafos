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
	

}

//exe11

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
