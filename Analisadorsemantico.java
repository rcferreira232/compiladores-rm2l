import java.util.*;

public class Analisadorsemantico {	
	public boolean verificar_atribuicao(Object variavel, Object valor, String tipo_identificador) {
	boolean erro = false;

		// Verificar se tentando atribuir decimal a inteiro
		if(tipo_identificador.equals("inteiro") && valor.toString().contains(".")) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo inteiro e nao pode receber valor decimal");
			erro = true;
		}
		// Verificar se tentando atribuir string/char a tipos numericos
		else if((tipo_identificador.equals("float") || tipo_identificador.equals("inteiro")) && valor.toString().startsWith("\"")) {
			System.out.println("Erro: Variavel '" + variavel + "' deve ter um valor numerico");
			erro = true;
		}
		// Verificar se tentando atribuir numero a string/char
		else if(tipo_identificador.equals("string") && !valor.toString().startsWith("\"")) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo string e deve receber valor entre aspas");
			erro = true;
		}
		// Verificar se char recebe string muito longa (mais que 1 caractere entre aspas)
		else if(tipo_identificador.equals("char") && valor.toString().startsWith("\"") && valor.toString().length() > 3) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo char e deve receber apenas um caractere");
			erro = true;
		}
		// Verificar se tentando atribuir numero a char
		else if(tipo_identificador.equals("char") && !valor.toString().startsWith("\"")) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo char e deve receber valor entre aspas");
			erro = true;
		}
	return erro;
}

	// Verificacao semantica: Declaracao de variavel ja existente
	public boolean verificar_declaracao_duplicada(Object identificador, Tabelasimbolos tabela) {
		boolean erro = false;
		
		if(tabela.simbolo_existe(identificador)) {
			System.out.println("Erro: Variavel '" + identificador + "' ja foi declarada anteriormente");
			erro = true;
		}
		
		return erro;
	}
	
	// Verificacao semantica: Divisao por zero
	public boolean verificar_divisao_por_zero(Object operador, Object operando_direito) {
		boolean erro = false;
		
		if(operador.toString().equals("/") && operando_direito.toString().equals("0")) {
			System.out.println("Erro: Divisao por zero detectada");
			erro = true;
		}
		else if(operador.toString().equals("%") && operando_direito.toString().equals("0")) {
			System.out.println("Erro: Operacao modulo por zero detectada");
			erro = true;
		}
		
		return erro;
	}
	
}
