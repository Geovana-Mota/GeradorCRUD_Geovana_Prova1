package gerador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GerarClasseDeControle {

    public GerarClasseDeControle(String caminho) {
        List<String> lista = new ArrayList<>();
        List<String> c = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        lista = manipulaArquivo.abrirArquivo(caminho);

        c.add("package classeGerada;");
        c.add("import java.io.FileInputStream;\n"
                + "import java.io.FileOutputStream;\n"
                + "import java.io.IOException;\n"
                + "import java.io.ObjectInputStream;\n"
                + "import java.io.ObjectOutputStream;\n"
                + "import java.util.ArrayList;\n"
                + "import java.util.List;");
        c.add("public class " + nomeClasse + "ControlaLista {");
//           "+string+"
        c.add("private List<" + nomeClasse + "> lista = new ArrayList<>();");

        STRS strs = new STRS();

        //metodos do crud
        c.add("    public void inserir(" + nomeClasse + " registro) {\n"
                + "        lista.add(registro);\n"
                + "    }");
        String chave[] = lista.get(0).split(";");
        c.add(" public " + nomeClasse + " retrieve(" + nomeClasse + " classe) {"
                + "        if (lista.size() > 0) {\n"
                + "               " + chave[1] + "   chaveProcurada = classe.get"
                + strs.inicialMaiuscula(chave[0]) + "();"
                + chave[1] + "  chaveNaLista;"
                + "            for (int i = 0; i < lista.size(); i++) {"
                + " chaveNaLista = lista.get(i).get"
                + strs.inicialMaiuscula(chave[0]) + "();\n"
        );
        String pl;
        if (chave[1].equals("String")) {
            pl = "if (chaveNaLista.equals(chaveProcurada)) {\n";
        } else {
            pl = "  if (chaveNaLista == chaveProcurada) {\n";
        }
        c.add(pl
                + "                    return lista.get(i);\n"
                + "                }\n"
                + "            }\n"
                + "        }\n"
                + "        return null;//não achou na lista\n"
                + "    }"
        );

        c.add(" public void atualizar(" + nomeClasse + " procurado, " + nomeClasse + " alterado) {\n"
                + "        lista.set(lista.indexOf(procurado), alterado);\n"
                + "    }\n\n");

        c.add("public void excluir(" + nomeClasse + " elemento) {\n"
                + "        lista.remove(elemento);\n"
                + "    }\n\n");

        c.add("public void desSerializaLista(String arquivo) {\n"
                + "        FileInputStream arqLeitura = null;\n"
                + "        ObjectInputStream in = null;\n"
                + "        lista.clear();\n"
                + "        try {\n"
                + "            //arquivo onde estao os dados serializados\n"
                + "            arqLeitura = new FileInputStream(arquivo);\n"
                + "\n"
                + "            //objeto que vai ler os dados do arquivo\n"
                + "            in = new ObjectInputStream(arqLeitura);\n"
                + "\n"
                + "            //recupera os dados\n"
                + "            lista = (ArrayList<" + nomeClasse + ">) in.readObject();\n"
                + "\n"
                + "        } catch (ClassNotFoundException ex) {\n"
                + "            System.out.println(\"erro 1: \" + ex);\n"
                + "        } catch (IOException ex) {\n"
                + "            System.out.println(\"erro 2: \" + ex);\n"
                + "        } finally {\n"
                + "            try {\n"
                + "                arqLeitura.close();\n"
                + "                in.close();\n"
                + "            } catch (IOException ex) {\n"
                + "                System.out.println(\"erro 3: \" + ex);\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "    }\n\n");

        c.add(" public void serializaLista(String arquivo) {\n"
                + "\n"
                + "        FileOutputStream arq = null;\n"
                + "        ObjectOutputStream out = null;\n"
                + "        try {\n"
                + "            //arquivo no qual os dados vao ser gravados\n"
                + "            arq = new FileOutputStream(arquivo);\n"
                + "\n"
                + "            //objeto que vai escrever os dados\n"
                + "            out = new ObjectOutputStream(arq);\n"
                + "            out.writeObject(lista);\n"
                + "        } catch (IOException ex) {\n"
                + "            System.out.println(\"erro: \" + ex);\n"
                + "        } finally {\n"
                + "            try {\n"
                + "                arq.close();\n"
                + "                out.close();\n"
                + "            } catch (IOException ex) {\n"
                + "            }\n"
                + "        }\n"
                + "    }");

        c.add("   public List<" + nomeClasse + "> getLista() {\n"
                + "        return lista;\n"
                + "    }");

        c.add("} //fim");
        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + "ControlaLista.java", c);

    }

}
