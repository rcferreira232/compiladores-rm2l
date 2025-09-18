import java.io.FileNotFoundException;
import java.io.FileReader;
import java_cup.runtime.*;

class Main {

  static public void main(String[] args) throws java.io.IOException {

      /* Verifica se foi fornecido um arquivo como argumento */
      if (args.length == 0) {
          System.out.println("Uso: java Main <arquivo>");
          return;
      }
      
      String arquivo = args[0];
      
      /* Cria o scanner para o arquivo */
      scanner lexer = new scanner(new FileReader(arquivo));

      System.out.println("=== ANÁLISE LÉXICA DO ARQUIVO: " + arquivo + " ===\n");

      try {
        Symbol token;
        int contador = 0;
        do {
          token = lexer.next_token();
          
          if (token.sym != 0) { // 0 é EOF
            contador++;
            System.out.println("Token " + contador + ":");
            // System.out.println("  Nome: " + this.getName(token.sym));
            System.out.println("  Código: " + token.sym);
            System.out.println("  Linha: " + (token.left + 1));
            System.out.println("  Coluna: " + (token.right + 1));
            if (token.value != null) {
              System.out.println("  Valor: \"" + token.value + "\"");
            }
            System.out.println();
          }
          
        } while (token.sym != 0); // Continue até EOF

      System.out.println("=== ANÁLISE SINTÁTICA DO ARQUIVO: " + arquivo + " ===\n");
      scanner scanner_obj = new scanner(new FileReader(arquivo));
      Parser parser_obj = new Parser(scanner_obj);

        try {
          parser_obj.parse();
      } catch (Exception e) {
          System.out.println("Erro");
      } 

        System.out.println("=== FIM DA ANÁLISE ===");
        System.out.println("Total de tokens encontrados: " + contador);
        
      } catch (Exception e) {
          System.out.println("Erro durante a análise: " + e.getMessage());
          e.printStackTrace();
      }
  }
}

