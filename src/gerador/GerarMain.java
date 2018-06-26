package gerador;
/**
 *
 * @author Geovana
 */
import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class GerarMain{
        
    public GerarMain(String caminho) {
        List<String> lista = new ArrayList<>();
        List<String> c = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();
        
        lista = manipulaArquivo.abrirArquivo(caminho);
        c.add("package classeGerada;");
        c.add("public class "+ nomeClasse +"Main {");
        c.add("public static void main(String[] args) {");
        c.add( nomeClasse+"GUI " + nomeClasse.toLowerCase()+"GUI = new "
                + nomeClasse + "GUI();");
        c.add("}}");
        

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + "Main.java", c);
    }
}
