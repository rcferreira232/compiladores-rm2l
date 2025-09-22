import java.io.*;
import java.util.Stack;

public class Geradorcodigo {
	
	String str1;
	String str2;
	String str3;
	private boolean hasMainFunction = false;

	public void gerar_if(Object exec1, Object exec2) { 
		String exec = exec1.toString();
		String[] termos =  exec.split("[=]");
		int i = exec.indexOf("[");
		int i2 = exec.indexOf("]");
		str1 = exec.substring(i+1, i2);
		i = exec.lastIndexOf("e")+1;
		str2 = exec.substring(i);
		exec = exec2.toString();
		i = exec.lastIndexOf("e")+1;
		str3 = exec.substring(i);
		gravar_arquivo("        " + str1 + " = " + str2 + ";\n");
		gravar_arquivo("    } else {\n");
		gravar_arquivo("        " + str1 + " = " + str3 + ";\n");
		gravar_arquivo("    }\n");
	}
	
	public void gerar_else() {
		gravar_arquivo("    } else {\n");
	}
	
	public void gerar_cond(Object cond) {
		String condicional = cond.toString();
		String[] termos = condicional.split("[==|>|<]");
		str1 = termos[0];
		str2 = termos[1];
		if (condicional.contains("<")){
			gravar_arquivo("    if (" + str1 + " < " + str2 + ") {\n");
		}
		if (condicional.contains(">")){
			gravar_arquivo("    if (" + str1 + " > " + str2 + ") {\n");
		}
		if (condicional.contains("==")){
			str2 = termos[2];
			gravar_arquivo("    if (" + str1 + " == " + str2 + ") {\n");
		}
	}
	
	public void gravar_arquivo(String cod) {
		  try{
			  FileWriter fstream = new FileWriter("codigo_gerado.c",true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(cod);
			  out.close();
		  }catch (Exception e){
			 System.err.println("Erro ao gravar arquivo: " +
		      e.getMessage());
		  }
	}
	
	public void gerar_printa(Object texto) {
		if (!hasMainFunction) {
			inicializar_programa();
		}
		str1 = texto.toString();
		str1 = str1.replace("\"", "");
		
		gravar_arquivo("        printf(\"" + str1 + "\\n\");\n");
	}

	public void gerar_scanf(Object texto) {
		if (!hasMainFunction) {
			inicializar_programa();
		}
		str1 = texto.toString();
		str1 = str1.replace("\"", "");
		
		gravar_arquivo("        scanf(\"%" + "d\", &" + str1 + ");\n");
	}
	
	public void gerar_atribuicao(Object variavel, Object valor) {
		if (!hasMainFunction) {
			inicializar_programa();
		}
		str1 = variavel.toString();
		str2 = valor.toString();
		
		gravar_arquivo("    " + str1 + " = " + str2 + ";\n");
	}
	
	public void inicializar_programa() {
		gravar_arquivo("#include <stdio.h>\n\n");
		gravar_arquivo("int main() {\n");
		hasMainFunction = true;
	}
	
	public void finalizar_programa() {
		gravar_arquivo("    return 0;\n");
		gravar_arquivo("}\n");
	}
	
	public void declarar_variavel(String nome, String tipo) {
		if (!hasMainFunction) {
			inicializar_programa();
		}
		
		// Mapeia os tipos da linguagem para tipos C
		String tipoC = tipo;
		switch(tipo) {
			case "inteiro":
				tipoC = "int";
				break;
			case "float":
				tipoC = "float";
				break;
			case "char":
				tipoC = "char";
				break;
			case "string":
				tipoC = "char*";
				break;
			case "boolean":
				tipoC = "int"; // C não tem boolean nativo
				break;
		}
		
		gravar_arquivo("    " + tipoC + " " + nome + ";\n");
	}
	
	public void gerar_while(Object cond) {
		String condicional = cond.toString();
		String[] termos = condicional.split("[==|>|<]");
		str1 = termos[0];
		str2 = termos[1];
		if (condicional.contains("<")){
			gravar_arquivo("    while (" + str1 + " < " + str2 + ") {\n");
		}
		if (condicional.contains(">")){
			gravar_arquivo("    while (" + str1 + " > " + str2 + ") {\n");
		}
		if (condicional.contains("==")){
			str2 = termos[2];
			gravar_arquivo("    while (" + str1 + " == " + str2 + ") {\n");
		}
	}
	
	public void gerar_for(Object init, Object cond, Object incr) {
		String inicializacao = init.toString();
		String condicional = cond.toString(); 
		String incremento = incr.toString();
		gravar_arquivo("    for (" + inicializacao + "; " + condicional + "; " + incremento + ") {\n");
	}
	
	public void fechar_bloco() {
		gravar_arquivo("    }\n");
	}
}