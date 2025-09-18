/*
Este código faz parte da implementação que gera um código e o armazena em um arquivo de texto chamado codigo_gerado.txt. 
A função gravarArquivo é usada para essa finalidade, adicionando o código fornecido como argumento ao final do 
arquivo, com algumas verificações adicionais para garantir que a formatação seja adequada.
 */

import java.io.*;
import java.util.Stack;

public class Gerador {

	String str1;
	String str2;
	String str3;

	public static void gravarArquivo(String cod) {
		if (cod.toString().endsWith(";") || cod.toString().endsWith(")")
			|| cod.toString().endsWith("{") || cod.toString().endsWith("}")) {
			Gerador.gravarArquivo("\n");
		}
		try {
			FileWriter fstream = new FileWriter("codigo_gerado.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(cod);
			out.close();
		} catch (Exception e) {
			System.err.println("Erro ao gravar arquivo: " +
					e.getMessage());
		}
	}
}