import java.util.*;

public class Analisadorsemantico {	
	public boolean verificar_atribuicao(Object variavel, Object valor, String tipo_identificador) {
	boolean erro = false;

		if(tipo_identificador.equals("inteiro") && valor.toString().contains(".")) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo inteiro");
			erro = true;
		}
		else if((tipo_identificador.equals("float") || tipo_identificador.equals("inteiro")) && valor.toString().contains("\"")) {
			System.out.println("Erro: Variavel '" + variavel + "' deve ter um valor numerico");
			erro = true;
		}
		else if((tipo_identificador.equals("string") || tipo_identificador.equals("char")) && !valor.toString().contains("\"")) {
			System.out.println("Erro: Variavel '" + variavel + "' nao pode receber valor numerico");
			erro = true;
		}
		else if(tipo_identificador.equals("char") && valor.toString().length()>3) {
			System.out.println("Erro: Variavel '" + variavel + "' eh do tipo char");
			erro = true;
		}
	return erro;
}
	
}
