package gerador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GerarClasseEntidade {

    public GerarClasseEntidade(String caminho) {
        List<String> descricaoEntidade = new ArrayList<>();
        List<String> textoGerado = new ArrayList<>();
        
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        descricaoEntidade = manipulaArquivo.abrirArquivo(caminho);
        
        textoGerado.add("package classeGerada;");
        textoGerado.add("import java.util.Date;");
        textoGerado.add("import java.text.SimpleDateFormat;");
        textoGerado.add("public class " + nomeClasse + " implements java.io.Serializable {");

        
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            String ss = "private " + aux[1] + " " + aux[0] + ";";
            textoGerado.add(ss);
            if (aux[1].equals("Date")){
                textoGerado.add("SimpleDateFormat sdf" + aux[0] + " = new SimpleDateFormat(\"" + 
                        aux[2] + "\");");
            }
        }
        textoGerado.add("");

        STRS strs = new STRS();

        //metodos set
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");

            String ss = "public void set" + strs.inicialMaiuscula(aux[0]) + "(" + aux[1]
                    + " " + aux[0] + "){\n" + "this." + aux[0] + "="
                    + aux[0] + ";\n}\n";
            textoGerado.add(ss);
        }
        textoGerado.add("");

        //metodos get
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");

            String ss = "public " + aux[1] + " get" + strs.inicialMaiuscula(aux[0]) + "(){\n"
                    + "return this." + aux[0] + ";"
                    + "\n}\n";
            textoGerado.add(ss);
        }
        textoGerado.add("");

        //metodo toString
        String campos = "";
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            if (aux[1].equals("Date")){
                campos += "sdf" + aux[0] + ".format(" + aux[0]+ ") +\";\"+";
                
            }else{
                campos += "this." + aux[0] + "+\";\"+";
            }
        }
        textoGerado.add("public" + " " + nomeClasse + "(");
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String a = "";
            String[] aux = descricaoEntidade.get(i).split(";");
            a += (aux[1] + " " + aux[0]);
            if (i != descricaoEntidade.size()-1){
                a += ",";
            }
            else{
                a += "){";
            }
            textoGerado.add(a);
        }
        for (int i = 0; i < descricaoEntidade.size(); i++) {
            String[] aux = descricaoEntidade.get(i).split(";");
            textoGerado.add("this." + aux[0] + " = " + aux[0] + ";");
        }
        textoGerado.add("}");
        textoGerado.add("public" + " " + nomeClasse + "(){ \n}");
        textoGerado.add("@Override\n"
                + "    public String toString() { return ");
        textoGerado.add(campos.substring(0, campos.length() - 5) + ";\n}\n}");

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + ".java", textoGerado);

    }

}
