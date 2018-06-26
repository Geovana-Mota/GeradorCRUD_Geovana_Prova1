package gerador;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GerarGUI {

    public GerarGUI(String caminho) {
        List<String> lista = new ArrayList<>();
        List<String> c = new ArrayList<>();
        File file = new File(caminho);
        String nomeClasse = file.getName();
        String auxA[] = nomeClasse.split("\\.");
        nomeClasse = auxA[0];
        ManipulaArquivo manipulaArquivo = new ManipulaArquivo();

        lista = manipulaArquivo.abrirArquivo(caminho);

        c.add("package classeGerada;\n");
        c.add("import java.awt.BorderLayout;\n"
                + "import java.awt.Color;\n"
                + "import java.awt.Container;\n"
                + "import java.awt.FlowLayout;\n"
                + "import java.awt.GridLayout;\n"
                + "import java.awt.Point;\n"
                + "import java.awt.event.ActionEvent;\n"
                + "import java.awt.event.ActionListener;\n"
                + "import java.awt.event.FocusEvent;\n"
                + "import java.awt.event.FocusListener;\n"
                + "import java.awt.event.WindowAdapter;\n"
                + "import java.awt.event.WindowEvent;\n"
                + "import java.io.File;\n"
                + "import javax.swing.JButton;\n"
                + "import javax.swing.JFrame;\n"
                + "import javax.swing.JLabel;\n"
                + "import javax.swing.JOptionPane;\n"
                + "import javax.swing.JPanel;\n"
                + "import javax.swing.JTextField;\n"
                + "import javax.swing.JToolBar;\n"
                + "import javax.swing.WindowConstants;\n"
                + "import java.text.SimpleDateFormat;\n"
                + "import javax.swing.JRadioButton;\n"
                + "import javax.swing.ButtonGroup;\n"
                + "import javax.swing.JCheckBox;\n"
                + "import tools.JanelaPesquisar;\n"
                + "import tools.ManipulaArquivo;\n"
                + "import java.util.List;\n"
                + "import java.util.ArrayList;\n"
                + "import java.awt.Image;\n"
                + "import java.awt.Toolkit;");

        c.add("public class " + nomeClasse + "GUI extends JFrame {\n"
                + "    JButton btnCreate;\n"
                + "    JButton btnRetrieve;\n"
                + "    JButton btnUpdate;\n"
                + "    JButton btnDelete;\n"
                + "    JButton btnSave;\n"
                + "    JButton btnCancel;\n"
                + "    JButton btnList;"
                + "    JButton btnQuit;"
                + " ");
        String[] s;
        STRS ajustar = new STRS();

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            c.add("JLabel label" + ajustar.inicialMaiuscula(s[0]) + " = new JLabel(\"" + ajustar.inicialMaiuscula(s[0]) + "\");");
            if (s[1].equals("Date")) {
                c.add("SimpleDateFormat sdf" + s[0] + " = new SimpleDateFormat(\"" + s[2] + "\");");
                c.add("JTextField textField" + ajustar.inicialMaiuscula(s[0]) + " = new JTextField(10);");
                /////////////////////////////////////////////////
            } else if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equalsIgnoreCase("radiobutton")) {
                    c.add("ButtonGroup buttonGroup" + ajustar.inicialMaiuscula(s[0]) + " = new ButtonGroup();");
                    c.add("JRadioButton radioButton" + ajustar.inicialMaiuscula(s[0]) + "1 = new JRadioButton(\"" + aux[1] + "\");");
                    c.add("JRadioButton radioButton" + ajustar.inicialMaiuscula(s[0]) + "2 = new JRadioButton(\"" + aux[2] + "\");");
                    c.add("JPanel pnBotoes" + ajustar.inicialMaiuscula(s[0]) + " = new JPanel();");
                } else {
                    c.add("JCheckBox checkBox" + s[0] + " = new JCheckBox(\"" + aux[1] + "\");");
                }

            } else {
                c.add("JTextField textField" + ajustar.inicialMaiuscula(s[0]) + " = new JTextField();"
                        + " ");
            }
        }
        c.add("private JPanel painelFundo = new JPanel(new GridLayout(1,2));\n"
                + "    private JPanel painelEsquerda = new JPanel(new GridLayout(2,1));\n"
                + "    private JPanel painelDados = new JPanel(new FlowLayout());\n"
                + "    private JPanel painelChave = new JPanel(new FlowLayout());\n"
                + "    private JPanel painelDireita = new JPanel();\n"
                + "    private JPanel painelBaixo = new JPanel(new GridLayout(1,2));\n"
                + "    private JPanel painelSair = new JPanel(new FlowLayout());"
                + " ");

        c.add("JPanel aviso = new JPanel();\n"
                + "    JLabel labelAviso = new JLabel(\"\");\n"
                + "    String acao = \"\";//variavel para facilitar insert e update \n\n");

        c.add(nomeClasse + "ControlaLista cl = new " + nomeClasse + "ControlaLista(); \n"
                + "    " + nomeClasse + " " + nomeClasse.toLowerCase() + " = new " + nomeClasse + "();");
        c.add("ManipulaArquivo file = new ManipulaArquivo();");
        c.add("  private void atvBotoes(boolean c, boolean r, boolean u, boolean d, boolean l, boolean q) {\n"
                + "        btnCreate.setEnabled(c);\n"
                + "        btnRetrieve.setEnabled(r);\n"
                + "        btnUpdate.setEnabled(u);\n"
                + "        btnDelete.setEnabled(d);\n"
                + "        btnList.setEnabled(l);\n"
                + "        btnQuit.setEnabled(q);\n"
                + "    }\n"
                + "\n\n");
        c.add("public void mostrarBotoes(boolean visivel) {\n"
                + "        btnCreate.setVisible(visivel);\n"
                + "        btnRetrieve.setVisible(visivel);\n"
                + "        btnUpdate.setVisible(visivel);\n"
                + "        btnDelete.setVisible(visivel);\n"
                + "        btnList.setVisible(visivel);\n"
                + "        btnSave.setVisible(!visivel);\n"
                + "        btnCancel.setVisible(!visivel);\n"
                + "        btnQuit.setVisible(visivel);\n"
                + "    }");

        String atributos = "";

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            atributos += "boolean " + s[0] + ",";
        }
        atributos = atributos.substring(0, atributos.length() - 1);

        c.add("    private void habilitarAtributos(" + atributos + ") {\n");

        String[] chave = lista.get(0).split(";");

        c.add("           if (" + chave[0] + ") {\n"
                + "            textField" + ajustar.inicialMaiuscula(chave[0]) + ".requestFocus();\n"
                + "            textField" + ajustar.inicialMaiuscula(chave[0]) + ".selectAll();\n"
                + "        }\n" + "        textField" + ajustar.inicialMaiuscula(chave[0]) + ".setEnabled(" + chave[0] + ");\n");

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equalsIgnoreCase("radiobutton")) {
                    c.add("radioButton" + ajustar.inicialMaiuscula(s[0]) + "1.setEnabled(" + s[0] + ");");
                    c.add("radioButton" + ajustar.inicialMaiuscula(s[0]) + "2.setEnabled(" + s[0] + ");");

                } else {
                    c.add("checkBox" + s[0] + ".setEnabled(" + s[0] + ");");
                }

            } else {
                c.add("        textField" + ajustar.inicialMaiuscula(s[0]) + ".setEditable(" + s[0] + ");");
            }

        }

        c.add("    }\n");
        c.add(" public void zerarAtributos() {");
        for (int i = 1; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equalsIgnoreCase("radiobutton")) {
                    c.add("radioButton" + ajustar.inicialMaiuscula(s[0]) + "1.setSelected(true);");
                } else {
                    c.add("checkBox" + s[0] + ".setSelected(false);");
                }
            } else {
                c.add("        textField" + ajustar.inicialMaiuscula(s[0]) + ".setText(\"\");");
            }
        }

        c.add("}\n");
        c.add(" public " + nomeClasse + "GUI() {\n"
                + "setIcon();\n"
                + "            btnCreate = new JButton(\"Criar\");\n"
                + "            btnRetrieve = new JButton(\"Buscar\");\n"
                + "            btnUpdate = new JButton(\"Atualizar\");\n"
                + "            btnDelete = new JButton(\"Remover\");\n"
                + "            btnSave = new JButton(\"Salvar\");\n"
                + "            btnCancel = new JButton(\"Cancelar\");\n"
                + "            btnQuit = new JButton(\"Sair\");\n"
                + "            btnList = new JButton(\"Listar\");\n");

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equals("radiobutton")) {
                    c.add("buttonGroup" + ajustar.inicialMaiuscula(s[0]) + ".add(radioButton"
                            + ajustar.inicialMaiuscula(s[0]) + "1);");
                    c.add("buttonGroup" + ajustar.inicialMaiuscula(s[0]) + ".add(radioButton"
                            + ajustar.inicialMaiuscula(s[0]) + "2);");
                    c.add("pnBotoes" + ajustar.inicialMaiuscula(s[0]) + ".add(radioButton"
                            + ajustar.inicialMaiuscula(s[0]) + "1);");
                    c.add("pnBotoes" + ajustar.inicialMaiuscula(s[0]) + ".add(radioButton"
                            + ajustar.inicialMaiuscula(s[0]) + "2);");
                }

            } else if (s[1].equals("Date")) {
                c.add("sdf" + s[0] + ".setLenient(false);");
            }
        }

        c.add("//OBSERVAR O CONSTRUTOR DA ENTIDADE");

        c.add("  setTitle(\"Cadastro de " + nomeClasse + "\");");
        c.add(" try {\n"
                + "            List<String> abrir= file.abrirArquivo(\"src/dadosDaEntidade/dados"
                + nomeClasse + ".txt\");\n"
                + "if (abrir != null){\n"
                + "                for(String m: abrir){\n"
                + "                    String[] var = m.split(\";\");\n"
                + "                    ///////////// GERAR CONSTRUTOR NA ENTIDADE \n"
                + nomeClasse + " " + nomeClasse.toLowerCase() + " = new "
                + nomeClasse + "(");
        for (int i = 0; i < lista.size(); i++) {
            String a = "";
            s = lista.get(i).split(";");
            if (s[1].equals("int")) {
                a += "Integer.valueOf(var[" + i + "])";
            } else if (s[1].equals("Date")) {
                a += "sdf" + s[0] + ".parse(var[" + i + "])";
            } else {
                a += ajustar.inicialMaiuscula(s[1]) + ".valueOf(var[" + i + "])";
            }
            if (i != lista.size() - 1) {
                a += ",";
            } else {
                a += ");";
            }
            c.add(a);
        }
        c.add("cl.inserir(" + nomeClasse.toLowerCase() + ");");
        c.add("}\n}");
        c.add("        } catch (Exception e) {\n"
                + "            System.out.println(\"arquivo não encontrado\");\n"
                + "        }");
        c.add(" setSize(800, 600);//tamanho da janela\n"
                + "        setLocationRelativeTo(null);\n"
                + "        setLayout(new BorderLayout());//informa qual gerenciador de layout será usado\n"
                + "        setBackground(Color.CYAN);//cor do fundo da janela\n"
                + "        Container cp = getContentPane();//container principal, para adicionar nele os outros componentes\n"
                + "\n"
                + "        atvBotoes(false, true, false, false, true, true);");

        atributos = "";
        for (int i = 1; i < lista.size(); i++) {
            atributos += "false,";
        }
        atributos = atributos.substring(0, atributos.length() - 1);
        String atributosBKP = atributos;

        ////////////////////////////////////////////////////
        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            s[2].trim();
            if (s[2].equalsIgnoreCase("chaveEstrangeira")) {
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".addActionListener(new ActionListener() {");
                c.add("@Override\n"
                        + "            public void actionPerformed(ActionEvent e) {\n"
                        + "                ManipulaArquivo manipulaArquivo = new ManipulaArquivo();\n"
                        + "                List<String> listaAuxiliar = manipulaArquivo.abrirArquivo("
                        + "\"src/dadosDaEntidade/dados" + ajustar.inicialMaiuscula(s[0]) + ".txt\");");
                c.add("if (listaAuxiliar.size() > 0) {\n"
                        + "                    String selectedItem = new JanelaPesquisar(listaAuxiliar, getBounds().x - getWidth() / 2 + getWidth() + 5, getBounds().y).getValorRetornado();\n"
                        + "                    if (!selectedItem.equals(\"\")) {\n"
                        + "                        String[] aux = selectedItem.split(\";\");");
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".setText(aux[0]);");
                c.add("} else {");
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".requestFocus();");
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".selectAll();");
                c.add("}\n"
                        + "                }\n"
                        + "            }\n"
                        + "        });");
            }
        }
        /////////////////////////////////////////////////////
        c.add("habilitarAtributos(true, " + atributos + ");");
        c.add(" btnCreate.setToolTipText(\"Inserir novo registro\");\n"
                + "        btnRetrieve.setToolTipText(\"Pesquisar por chave\");\n"
                + "        btnUpdate.setToolTipText(\"Alterar\");\n"
                + "        btnDelete.setToolTipText(\"Excluir\");\n"
                + "        btnList.setToolTipText(\"Listar todos\");\n"
                + "        btnSave.setToolTipText(\"Salvar\");\n"
                + "        btnCancel.setToolTipText(\"Cancelar\");\n"
                + "        btnQuit.setToolTipText(\"Sair\");\n"
                + "        JToolBar Toolbar1 = new JToolBar();\n"
                + "        Toolbar1.add(btnRetrieve);\n"
                + "        Toolbar1.add(btnCreate);\n"
                + "        Toolbar1.add(btnUpdate);\n"
                + "        Toolbar1.add(btnDelete);\n"
                + "        Toolbar1.add(btnSave);\n"
                + "        Toolbar1.add(btnCancel);\n"
                + "        Toolbar1.add(btnList);\n"
                + "        Toolbar1.add(btnQuit);\n"
                + "        btnSave.setVisible(false);\n"
                + "        btnCancel.setVisible(false);  //atributos;");
        String max = String.valueOf(lista.size());
        c.add("painelDados.setLayout(new GridLayout(" + max + ", 2));");

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            c.add("painelDados.add(label" + ajustar.inicialMaiuscula(s[0]) + ");");
            if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equalsIgnoreCase("radiobutton")) {
                    c.add("painelDados.add(pnBotoes" + ajustar.inicialMaiuscula(s[0]) + ");");
                } else {
                    c.add("painelDados.add(checkBox" + s[0] + ");");
                }

            } else {
                c.add("painelDados.add(textField" + ajustar.inicialMaiuscula(s[0]) + ");");
            }
        }

        c.add("  cp = getContentPane();\n"
                + "        cp.setLayout(new GridLayout(2, 1));\n"
                + "        aviso.add(labelAviso);\n"
                + "        aviso.setBackground(Color.yellow);\n"
                + "        painelDireita.add(Toolbar1);\n"
                + "        painelEsquerda.add(painelChave);\n"
                + "        painelEsquerda.add(painelDados);\n"
                + "        cp.add(painelFundo);\n"
                + "        cp.add(painelBaixo);\n"
                + "        painelFundo.add(painelEsquerda);\n"
                + "        painelFundo.add(painelDireita);\n"
                + "        painelBaixo.add(aviso);\n"
                + "        painelBaixo.add(painelSair, FlowLayout.CENTER);\n"
                + "        painelSair.add(btnQuit);\n"
                + "        labelAviso.setText(\"Digite uma placa e clic [Pesquisar]\");");

        c.add("painelSair.setBackground(Color.decode(\"#00BFFF\"));\n"
                + "        painelDados.setBackground(Color.decode(\"#228B22\"));\n"
                + "        painelChave.setBackground(Color.decode(\"#228B22\"));\n"
                + "        painelEsquerda.setBackground(Color.decode(\"#00BFFF\"));\n"
                + "        painelDireita.setBackground(Color.decode(\"#00BFFF\"));\n"
                + "        painelFundo.setBackground(Color.decode(\"#00BFFF\"));\n"
                + "        painelBaixo.setBackground(Color.decode(\"#00BFFF\"));");

        //BUSCAR   
        c.add(" btnRetrieve.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {");
        c.add("try {");
        String tipo = "";
        String fecha = "";
        //////////////////////////

        if (chave[1].equals("int")) {
            tipo = "Integer.valueOf(";
            fecha = ")";
        } else if (chave[1].equals("long")) {
            tipo = "Long.valueOf(";
            fecha = ")";
        }
        c.add(
                "                textField" + ajustar.inicialMaiuscula(chave[0])
                + ".setText(textField" + ajustar.inicialMaiuscula(chave[0])
                + ".getText().trim());//caso tenham sido digitados espaços\n"
                + "                if (textField" + ajustar.inicialMaiuscula(chave[0]) + ".getText().equals(\"\")) {\n"
                + "                    MinhaJOptionPane myJOptionPane\n"
                + "                            = new MinhaJOptionPane(\n"
                + "                                    new Point(getBounds().x + (int) (getBounds().getWidth() / 2),\n"
                + "                                            getBounds().y + (int) (getBounds().getHeight() / 2)),\n"
                + "                                    \"Deve ser informado um valor para esse campo\");\n"
                + "                    if (myJOptionPane.getValorRetornado()) {\n"
                + "                        textField" + ajustar.inicialMaiuscula(chave[0]) + ".requestFocus();\n"
                + "                        textField" + ajustar.inicialMaiuscula(chave[0]) + ".selectAll();\n"
                + "                    }\n"
                + "                } ");
        c.add("else {\n"
                + "  " + nomeClasse.toLowerCase() + " = new " + nomeClasse + "();\n"
                + "                " + nomeClasse.toLowerCase() + ".set"
                + ajustar.inicialMaiuscula(chave[0]) + "(" + tipo
                + "textField" + ajustar.inicialMaiuscula(chave[0]) + ".getText()"
                + fecha + ");\n"
                + "                    " + nomeClasse.toLowerCase() + " = cl.retrieve(" + nomeClasse.toLowerCase() + ");\n"
                + "                    if (" + nomeClasse.toLowerCase() + " != null) { //se encontrou na lista\n");

        //SE ENCONTROU NA LISTA
        for (int i = 1; i < lista.size(); i++) {
            s = lista.get(i).split(";");

            if (s[1].equals("boolean")) {
                String aux[] = s[2].split(",");
                if (aux[0].equalsIgnoreCase("radiobutton")) {
                    c.add("if (" + nomeClasse.toLowerCase() + ".get" + ajustar.inicialMaiuscula(s[0]) + "()){");
                    c.add("radioButton" + ajustar.inicialMaiuscula(s[0]) + "1.setSelected(true);}");
                    c.add("else{");
                    c.add("radioButton" + ajustar.inicialMaiuscula(s[0]) + "2.setSelected(true);}");
                } else {
                    c.add("checkBox" + s[0] + ".setSelected("
                            + nomeClasse.toLowerCase() + ".get" + ajustar.inicialMaiuscula(s[0]) + "());");
                }
            } else if (s[1].equals("Date")) {
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".setText(sdf"
                        + s[0] + ".format(" + nomeClasse.toLowerCase() + ".get"
                        + ajustar.inicialMaiuscula(s[0]) + "()));");
            } else {
                c.add("textField" + ajustar.inicialMaiuscula(s[0]) + ".setText(String.valueOf("
                        + nomeClasse.toLowerCase() + ".get" + ajustar.inicialMaiuscula(s[0])
                        + "()));");
            }
        }

        c.add("                        atvBotoes(false, true, true, true, true, true);\n"
                + "                        habilitarAtributos(true, " + atributos + ");\n"
                + "                        labelAviso.setText(\"Encontrou - clic [Pesquisar], [Alterar] ou [Excluir]\");\n"
                + "                        acao = \"encontrou\";\n"
                + "                    } else {\n"
                + "                        atvBotoes(true, true, false, false, true, true);\n"
                + "                        zerarAtributos();\n"
                + "                        labelAviso.setText(\"Não cadastrado - clic [Inserir] ou digite outra placa [Pesquisar]\");\n"
                + "                    }\n"
                + "                }");

        atributos = "";
        for (int i = 1; i < lista.size(); i++) {
            atributos += "true,";
        }
        atributos = atributos.substring(0, atributos.length() - 1);
        String[] c1 = lista.get(1).split(";");
        c.add("} catch (Exception err) {\n"
                + "                    System.out.println(err);\n"
                + "                    labelAviso.setText(\"Erro nos dados\");\n"
                + "labelAviso.setOpaque(true);\n"
                + "                    labelAviso.setBackground(Color.red);\n"
                + "                }");
        c.add("}\n});");

        //CRIAR
        c.add(" btnCreate.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                zerarAtributos();\n"
                + "                habilitarAtributos(false, " + atributos + ");\n"
                + "                textField" + ajustar.inicialMaiuscula(c1[0]) + ".requestFocus();\n"
                + "                mostrarBotoes(false);\n"
                + "                labelAviso.setText(\"Preencha os campos e clic [Salvar] ou clic [Cancelar]\");\n"
                + "                acao = \"insert\";\n"
                + "            }\n"
                + "        });");

        //ATUALIZAR
        c.add(" btnUpdate.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                acao = \"update\";\n"
                + "                mostrarBotoes(false);\n"
                + "                habilitarAtributos(false, " + atributos + ");\n"
                + "            }\n"
                + "        });");

        //DELETE
        c.add("        btnDelete.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null,\n"
                + "                        \"Confirma a exclusão do registro <ID = \" + " + nomeClasse.toLowerCase()
                + ".get" + ajustar.inicialMaiuscula(chave[0]) + "() + \">?\", \"Confirm\",\n"
                + "                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)) {\n"
                + "                    labelAviso.setText(\"Registro excluído...\");\n"
                + "                    cl.excluir(" + nomeClasse.toLowerCase() + ");\n"
                + "                    zerarAtributos();\n"
                + "                    textField" + ajustar.inicialMaiuscula(chave[0]) + ".requestFocus();\n"
                + "                    textField" + ajustar.inicialMaiuscula(chave[0]) + ".selectAll();\n"
                + "                }\n"
                + "            }\n"
                + "        });\n\n");

        //SALVAR
        c.add("btnSave.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {");

        c.add("try {");

        //SAIR
        c.add(" btnQuit.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                acao = \"update\";\n"
                + "                dispose();\n"
                + "             }\n"
                + "        });\n"
                + "        \n"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            public void windowClosing(WindowEvent e) {\n"
                + "                //antes de sair, salvar a lista em disco\n"
                + "                acao = \"update\";\n"
                + "                // Sai do sistema  \n"
                + "                dispose();\n"
                + "            }\n"
                + "        });");

        c.add(nomeClasse + " " + nomeClasse.toLowerCase() + "Novo = new " + nomeClasse + "();");
        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            if (s[1].equals("Date")) {
                c.add(" " + nomeClasse.toLowerCase() + "Novo.set" + ajustar.inicialMaiuscula(s[0])
                        + "(sdf" + s[0] + ".parse(textField" + ajustar.inicialMaiuscula(s[0])
                        + ".getText()));");
            } else if (s[1].equals("boolean")) {
                String[] aux = s[2].split(",");
                if (aux[0].equals("radiobutton")) {
                    c.add(" " + nomeClasse.toLowerCase() + "Novo.set" + ajustar.inicialMaiuscula(s[0])
                            + "(radioButton" + ajustar.inicialMaiuscula(s[0]) + "1.isSelected());");
                    System.out.println("Entrou");
                } else {
                    c.add(" " + nomeClasse.toLowerCase() + "Novo.set" + ajustar.inicialMaiuscula(s[0])
                            + "(checkBox" + s[0] + ".isSelected());");
                }
            } else if (s[1].equals("int")) {
                c.add(" " + nomeClasse.toLowerCase() + "Novo.set" + ajustar.inicialMaiuscula(s[0])
                        + "(Integer.valueOf(textField" + ajustar.inicialMaiuscula(s[0])
                        + ".getText()));");
            } else {
                c.add(" " + nomeClasse.toLowerCase() + "Novo.set" + ajustar.inicialMaiuscula(s[0]) + "("
                        + ajustar.inicialMaiuscula(s[1]) + ".valueOf(textField" + ajustar.inicialMaiuscula(s[0])
                        + ".getText()));");
            }
        }

        c.add("if (acao.equals(\"insert\")) {");

        c.add(" cl.inserir(" + nomeClasse.toLowerCase() + "Novo);\n"
                + "                    labelAviso.setText(\"Registro inserido...\");\n");

        c.add("                } else {  //acao = update");

//        c.add(nomeClasse + " original = " + nomeClasse.toLowerCase() + ";");
//        for (int i = 0; i < lista.size(); i++) {
//            c.add(listaAux.get(i));
//        }
        c.add("cl.atualizar(" + nomeClasse.toLowerCase() + ", " + nomeClasse.toLowerCase() + "Novo);\n"
                //                + "                    mostrarBotoes(true);\n"
                //                + "                     habilitarAtributos(true, " + atributosBKP + ");\n"
                //                + "                    atvBotoes(false, true, false, false);\n"
                + "                    labelAviso.setText(\"Registro atualizado...\");\n"
                + "    } "
                + "               habilitarAtributos(true, " + atributosBKP + ");\n"
                + "               mostrarBotoes(true);\n"
                + "               atvBotoes(false, true, false, false, true, true);\n"
                + "} catch (Exception err) {\n"
                + "                    System.out.println(err);\n"
                + "                    labelAviso.setText(\"Erro nos dados\");\n"
                + "                    labelAviso.setOpaque(true); \n"
                + "                    labelAviso.setBackground(Color.red);\n"
                + "                }");

        c.add("            } \n");
        c.add("        });");

        //Cancelar
        c.add("btnCancel.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "                zerarAtributos();\n"
                + "                atvBotoes(false, true, false, false, true, true);\n"
                + "                habilitarAtributos(true, " + atributosBKP + ");\n"
                + "                mostrarBotoes(true);\n"
                + "            }\n"
                + "        });");

        //LISTAR
        c.add("  btnList.addActionListener(new ActionListener() {\n"
                + "            @Override\n"
                + "            public void actionPerformed(ActionEvent ae) {\n"
                + "\n"
                + "                acao = \"list\";\n"
                + "                " + nomeClasse + "GUIListagem listagem = new "
                + nomeClasse + "GUIListagem(cl.getLista(), getBounds().x, getBounds().y);\n"
                + "            }\n"
                + "        });\n\n");

        c.add("  setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //antes de sair do sistema, grava os dados da lista em disco\n"
                + "        addWindowListener(new WindowAdapter() {\n"
                + "            @Override\n"
                + "            public void windowClosing(WindowEvent e) {\n");
        //+ "                //antes de sair, salvar a lista em disco\n"
        //+ "                cl.serializaLista(\"" + nomeClasse + ".dat\");\n"
        c.add("List<" + nomeClasse + "> lista" + nomeClasse + " = cl.getLista();\n"
                + "                List<String> s = new ArrayList<>();\n"
                + "                for (" + nomeClasse + " " + nomeClasse.substring(0, 1).toLowerCase() + ": lista" + nomeClasse + "){\n"
                + "                    s.add(" + nomeClasse.substring(0, 1).toLowerCase() + ".toString());\n"
                + "                }\n"
                + "                file.salvarArquivo(\"src/dadosDaEntidade/dados" + nomeClasse + ".txt\", s);");
        c.add("                // Sai do sistema  \n"
                + "                System.exit(0);\n"
                + "            }\n"
                + "        });\n"
                + "\n");

        for (int i = 0; i < lista.size(); i++) {
            s = lista.get(i).split(";");
            if (!s[1].equals("boolean")) {
                c.add("        textField" + ajustar.inicialMaiuscula(s[0]) + ".addFocusListener(new FocusListener() { //ao receber o foco, fica verde\n"
                        + "            @Override\n"
                        + "            public void focusGained(FocusEvent fe) {\n"
                        + "                textField" + ajustar.inicialMaiuscula(s[0]) + ".setBackground(Color.GREEN);\n"
                        + "            }\n"
                        + "\n"
                        + "            @Override\n"
                        + "            public void focusLost(FocusEvent fe) { //ao perder o foco, fica branco\n"
                        + "                textField" + ajustar.inicialMaiuscula(s[0]) + ".setBackground(Color.white);\n"
                        + "            }\n"
                        + "        });\n\n");
            } else {
                c.add("// nada");
            }

        }
        c.add("        setVisible(true);//faz a janela ficar visível\n"
                + "        textField" + ajustar.inicialMaiuscula(chave[0]) + ".requestFocus();");
        c.add("");
        c.add("} //fim do construtor \n\n "
                + "private void setIcon() {\n"
                + "        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(\"brasil.png\")));\n"
                + "     }"
                + "}//fim");

        manipulaArquivo.salvarArquivo("src/classeGerada/" + nomeClasse + "GUI.java", c);

    }

}
