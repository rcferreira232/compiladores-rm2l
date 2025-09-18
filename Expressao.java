/* 
Essa classe é usada para representar uma expressão em nosso compilador. 
Ela armazena o resultado da expressão, seu tipo e se é uma variável ou não.
*/ 

public class Expressao {

    public String resultado;
    public boolean ehVariavel;
    public TipoVariavel tipoResultado;

    public Expressao(String resultado, TipoVariavel tipoResultado, boolean ehVariavel) {
        this.resultado = resultado;
        this.tipoResultado = tipoResultado;
        this.ehVariavel = ehVariavel;
    }
}