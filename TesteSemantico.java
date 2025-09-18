import java.util.*;

public class TesteSemantico {
    public static void main(String[] args) {
        AnalisadorSemantico analisador = new AnalisadorSemantico();
        
        System.out.println("=== TESTE DE ANÁLISE SEMÂNTICA ===");
        
        try {
            // Teste 1: Declaração normal
            System.out.println("Teste 1: Declaração normal de 'a'");
            String resultado1 = analisador.inserirSimbolo("a", TipoVariavel.INT, 1, 1);
            System.out.println("Resultado: " + resultado1);
            
            // Teste 2: Redeclaração (deve dar erro)
            System.out.println("\nTeste 2: Redeclaração de 'a' (deve dar erro)");
            String resultado2 = analisador.inserirSimbolo("a", TipoVariavel.INT, 2, 1);
            System.out.println("Resultado: " + resultado2);
            
        } catch (Exception e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
        
        System.out.println("\n=== FIM DO TESTE ===");
    }
}
