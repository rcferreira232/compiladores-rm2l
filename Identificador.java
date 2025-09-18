/*
 Esta classe Identificador é usada para representar uma variável, 
 mantendo informações sobre seu tipo e se ela já foi atribuída a algum valor. 
 Essas informações são úteis durante a análise semântica do nosso compilador
 */

public class Identificador {
    public TipoVariavel tipo;
    public boolean estaAtribuido = false;

    public Identificador(TipoVariavel tipo) {
        this.tipo = tipo;
    }
}