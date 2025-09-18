/*
Este código implementa um analisador semântico que realiza várias verificações e operações 
relacionadas à semântica de variáveis e expressões. 

Ele manipula tabelas de símbolos para armazenar informações sobre variáveis e fornece métodos 
para realizar diferentes operações semânticas.
 */

import java.util.*;

public class AnalisadorSemantico {

    private Hashtable<Object, Identificador> identificadores = new Hashtable<>();
    private java_cup.runtime.Symbol currToken;
    /*
    Tabela de Símbolos para armazenar informações sobre identificadores (variáveis) 
    e uma variável para manter o token atual durante a análise semântica. 
    */

    // insere um identificador na tabela
    public String inserirSimbolo(Object variavel, TipoVariavel tipo, Object left, Object right) {
        if (identificadores.containsKey(variavel)) {
            System.out.println("Erro Semantico na linha " + left.toString() + " coluna " + right.toString() +
            ": Variavel \"" + variavel + "\" já foi previamente declarada");
            return "ERRO SEMÂNTICO";
        }
        identificadores.put(variavel.toString(), new Identificador(tipo));
        return tipo.toString().toLowerCase() + " " + variavel + ";";
    }

    // obtem um identificador da tabela
    public Identificador obterIdentificador(Object variavel, Object left, Object right) {
        Identificador identificador = identificadores.get(variavel);

        if (identificador == null) {
            System.out.println(String.format("Erro Semantico na linha " + left.toString() + " coluna " + right.toString()
            + ": Variavel \"" + variavel + "\" precisa ser declarada antes de ser usada."));
            return null;
        }
        return identificador;
    }

    // tenta atribuir uma variavel, se der errado retorna "ERRO SEMANTICO"
    // se der certo retorna a atribuição
    public String atribuirVariavel(Object variavel, Expressao valor, Object left, Object right) {
        TipoVariavel tipo1;
        TipoVariavel tipo2;
        if (!identificadores.containsKey(variavel)) {
            System.out.println("Erro Semantico na linha " + left.toString() + " coluna " + right.toString() + 
            ": Variavel \"" + variavel + "\" precisa ser declarada antes de ser usada.");
            return null;
            // return null;
        }
        tipo1 = identificadores.get(variavel).tipo;

        if (valor.ehVariavel) {
            if (!identificadores.containsKey(valor.resultado)) {
                System.out.println("\n\nErro Semantico na linha " + left.toString() + " coluna " + right.toString()
                + ": Variavel \"" + valor.resultado + "\" precisa ser declarada antes de ser usada.");
                return null;
                // return null;
            }
            if (!identificadores.get(valor.resultado).estaAtribuido) {
                System.out.println(String.format("Erro Semantico na linha " + left.toString() + " coluna " + right.toString()
                + ": Variavel \"" + valor.resultado + "\" precisa ser atribuida antes de ser usada."));
                return null;
                // return null;
            }
            tipo2 = identificadores.get(valor.resultado).tipo;
        } else {
            tipo2 = valor.tipoResultado;
        }

        if (tipo1 != tipo2 && (tipo1 == TipoVariavel.BOOL || tipo1 == TipoVariavel.CHAR || tipo2 == TipoVariavel.BOOL
                || tipo2 == TipoVariavel.CHAR)) {
            switch (tipo1) {
                case BOOL:
                    System.out.println("Erro Semantico na linha " + left.toString() + " coluna " + right.toString()
                    + ": O tipo boolean da variavel \"" + variavel + "\" so pode receber \"true\" ou \"false\" na atribuicao");
                    return null;
                // break;
                case INT:
                    System.out.println(String.format("Erro Semantico na linha " + left.toString() + 
                    " coluna " + right.toString() + ": O tipo inteiro da variavel \"" + variavel + 
                    "\" so pode receber numeros inteiros na atribuicao"));
                    return null;
                // break;
                case FLOAT:
                    System.out.println(String.format("Erro Semantico na linha " + left.toString() + " coluna " + right.toString() + 
                    ": O tipo float da variavel \"" + variavel + "\" so pode receber números decimais na atribuicao"));
                    return null;
                // break;
                case CHAR:
                    System.out.println(String.format("Erro Semantico na linha " + left.toString() + " coluna " + right.toString() + 
                    ": O tipo char da variavel \"" + variavel + "\" so pode receber um caractere por vez"));
                    return null;
                // break;
            }
        }
        identificadores.get(variavel).estaAtribuido = true;

        return variavel.toString() + "=" + valor.resultado + ";";
    }

    // testa uma expressão, se não for valida vai imprimir o erro e retornar nulo
    // se for valida cria uma expressão nova combinando as duas expressões que foram comparadas
    // e definindo como tipo numerico se for uma expressão +-*/ e tipo bool para qualquer outro operador
    public Expressao testarExpressao(Expressao var1, Expressao var2, String operador, Object left1, 
    Object right1, Object left2, Object right2) {
        TipoVariavel tipo1;
        TipoVariavel tipo2;
        if (var1.ehVariavel) {
            if (!identificadores.containsKey(var1.resultado)) {
                System.out.println("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                + ": Variavel \"" + var1.resultado + "\" precisa ser declarada antes de ser usada.");
                return null;
                // return null;
            }
            if (!identificadores.get(var1.resultado).estaAtribuido) {
                System.out.println(String.format("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                + ": Variavel \"" + var1.resultado + "\" precisa ser atribuida antes de ser usada."));
                return null;
                // return null;
            }
            tipo1 = identificadores.get(var1.resultado).tipo;
        } else {
            tipo1 = var1.tipoResultado;
        }
        if (var2.ehVariavel) {
            if (!identificadores.containsKey(var2.resultado)) {
                System.out.println("Erro Semantico na linha " + left2.toString() + " coluna " + right2.toString()
                + ": Variavel \"" + var2.resultado + "\" precisa ser declarada antes de ser usada.");
                return null;
                // return null;
            }
            if (!identificadores.get(var2.resultado).estaAtribuido) {
                System.out.println(String.format("Erro Semantico na linha " + left2.toString() + " coluna " + right2.toString()
                + ": Variavel \"" + var2.resultado + "\" precisa ser atribuida antes de ser usada."));
                return null;
                // return null;
            }
            tipo2 = identificadores.get(var2.resultado).tipo;
        } else {
            tipo2 = var2.tipoResultado;
        }

        switch (operador) {

            case ">":
            case "<":
            case "<=":
            case ">=":
            case "+":
            case "-":
            case "*":
            case "/":
                if (tipo1 == TipoVariavel.CHAR || tipo2 == TipoVariavel.CHAR) {
                    System.out.println("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                    + ": Operadores aritmeticos não podem ser executados com o tipo char");
                    return null;
                    // return null;
                }
                if (tipo1 == TipoVariavel.BOOL || tipo2 == TipoVariavel.BOOL) {
                    System.out.println("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                    + ": Operadores aritmeticos não podem ser executados com o tipo bool");
                    return null;
                    // return null;
                }
                if (operador == "+" || operador == "-" || operador == "*" || operador == "/") {
                    return new Expressao(var1.resultado + operador + var2.resultado, tipo1, false);
                }
                break;

            case "==":
            case "!=":
                if (tipo1 != tipo2) {
                    System.out.println("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                    + ": comparacao precisa ser de variaveis de mesmo tipo(" + tipo1 + " != " + tipo2 + ")");
                    return null;
                    // return null;
                }
                break;

            case "&&":
            case "||":
            case "^":
                if (tipo1 != TipoVariavel.BOOL || tipo2 != TipoVariavel.BOOL) {
                    System.out.println("Erro Semantico na linha " + left1.toString() + " coluna " + right1.toString()
                    + ": operadores logicos so podem ser realizados entre tipos booleanos");
                    return null;
                    // return null;
                }
                break;
        }
        return new Expressao(var1.resultado + operador + var2.resultado, TipoVariavel.BOOL, false);
    }

    // obtem o tipo do identificador, se não achar o identificador, retorna o tipo
    // do proprio objeto
    public TipoVariavel obterTipo(Object obj) {
        Identificador identificador = identificadores.get(obj);
        if (identificador != null) {
            return identificador.tipo;
        }
        if (obj.toString().startsWith("'")) {
            return TipoVariavel.CHAR;
        } else if (obj.toString() == "false" || obj.toString() == "true") {
            return TipoVariavel.BOOL;
        } else if (obj.toString().contains(".")) {
            return TipoVariavel.FLOAT;
        }
        return TipoVariavel.INT;
    }

    // obtem o caractere de um tipo, por exemplo float -> f. é usado na compilação
    // de printf e scanf
    public char obterCharDoTipo(TipoVariavel tipo) {
        switch (tipo) {
            case BOOL:
                return 'b';
            case INT:
                return 'i';
            case FLOAT:
                return 'f';
            case CHAR:
                return 'c';
        }
        return ' ';
    }
}