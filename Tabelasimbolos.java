import java.util.*;

public class Tabelasimbolos {
        private Hashtable tabela = new Hashtable();
	    
        public String consultar_tipo_identificador (Object identificador) {
		       String id = new String();
               if (tabela.get(identificador)==null) {
	 	          System.out.println("Erro: identificador '" + identificador + "' nao existe.");
		          id = "";
	           }else{
		          id = tabela.get(identificador).toString();
               }
               return id;
	    }

	    public void inserir_simbolo (Object identificador, Object tipo) {
		   tabela.put(identificador, tipo);
	    }
	    
	    // Metodo para verificar se identificador existe sem imprimir erro
	    public boolean simbolo_existe (Object identificador) {
		   return tabela.get(identificador) != null;
	    }

}
