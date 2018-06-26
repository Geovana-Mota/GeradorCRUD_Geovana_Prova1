package gerador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GerarGUIListagem {

    public GerarGUIListagem(String caminho) {
        List<String> lista = new ArrayList<>();
        List<String> c = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        lista = manipulaArquivo.abrirArquivo(caminho);

        c.add("package classeGerada;\n"
                + "\n"
                + "import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.ScrollPane;\n"
                + "import java.util.List;\n"
                + "import javax.swing.JDialog;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextArea;\n"
                + "import javax.swing.JToolBar;\n"
                + "public class " + nomeClasse + "GUIListagem extends JDialog {\n"
                + "\n"
                + "    JPanel painelTa = new JPanel();\n"
                + "    ScrollPane scroll = new ScrollPane();\n"
                + "    JTextArea ta = new JTextArea();\n"
                + "\n"
                + "    public " + nomeClasse + "GUIListagem(List<" + nomeClasse + "> texto, int posX, int posY) {\n"
                + "        setTitle(\"Listagem de " + nomeClasse + "\");\n"
                + "        setSize(500, 180);//tamanho da janela\n"
                + "        setDefaultCloseOperation(DISPOSE_ON_CLOSE);//libera ao sair (tira da memória a classe\n"
                + "        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado\n"
                + "        setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "        setModal(true);\n"
                + "        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "\n"
                + "        JToolBar toolBar = new JToolBar();\n"
                + "\n"
                + "        ta.setText(\"\");\n"
                + "        for (int i = 0; i < texto.size(); i++) {\n"
                + "            ta.append(texto.get(i).toString() + System.lineSeparator());\n"
                + "        }\n"
                + "\n"
                + "        scroll.add(ta);\n"
                + "        painelTa.add(scroll);\n"
                + "\n"
                + "        cp.add(toolBar, BorderLayout.NORTH);\n"
                + "        cp.add(scroll, BorderLayout.CENTER);\n"
                + "\n"
                + "        setLocation(posX + 20, posY + 20);\n"
                + "        setVisible(true);//faz a janela ficar visível        \n"
                + "    }\n"
                + "}\n"
                + "");

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + "GUIListagem.java", c);

    }

}
