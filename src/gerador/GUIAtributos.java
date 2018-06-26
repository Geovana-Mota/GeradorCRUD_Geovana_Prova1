package gerador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author radames
 */
public class GUIAtributos extends JFrame {

    private JPanel pnlBotoes;
    private JTable tblAtributos;
    private AtributoTableModel model;
    private final JButton btnAddAtributo = new JButton("Adicionar");
    private final JButton btnUpdAtributo = new JButton("Alterar");
    private final JButton btnDelAtributo = new JButton("Excluir");
    private final JButton btnSave = new JButton("Salvar");
    private final JButton btnQuit = new JButton("Sair");
    private final JButton btnAbrir = new JButton("...");
    private final JButton btnGerarClasseEntidade = new JButton("Entidade");
    private final JButton btnGerarClasseControle = new JButton("Controle");
    private final JButton btnGerarClasseGUI = new JButton("GUI");
    private final JButton btnGerarGUIListagem = new JButton("GUI Listagem");
    private final JButton btnMain = new JButton("Main");
    private Container cp;
    private final JPanel pnNorte = new JPanel(new GridLayout(3, 1));
    private final JPanel pnNorteB = new JPanel(new GridLayout(2, 3));
    private final JPanel pnNorteA = new JPanel(new FlowLayout());
    private final JPanel pnNorteC = new JPanel(new GridLayout(1, 2));
    private final JPanel pnCentro = new JPanel();
    private final JPanel pnSul = new JPanel(new GridLayout(2, 1));
    private final JPanel pnSulA = new JPanel();
    private final JPanel pnSulB = new JPanel();
    private final JLabel lbNome = new JLabel("Nome");
    private final JTextField tfEntidade = new JTextField(35);
    private final JTextField tfNome = new JTextField(5);
    private final JLabel lbTipo = new JLabel("Tipo");
    private final JTextField tfTipo = new JTextField(10);
    private final JLabel lbTamanho = new JLabel("Opções");
    private final JTextField tfTamanho = new JTextField(10);
    private String caminho = "";
    private ManipulaArquivo manipulaArquivo;
    private List<String> lista = new ArrayList<>();
    private List<Atributo> listaAtributo;
    private JFileChooser caixaDeDialogo = new JFileChooser();

    public GUIAtributos() {
        super("Editor de Classe e Atributos - v002");
        initialize();
    }

    private void initialize() {

        manipulaArquivo = new ManipulaArquivo();
        ///////////////////////////// MUDEI AQ P TESTAR UltimaExecucao.dat
        List<String> last = manipulaArquivo.abrirArquivo("UltimaExecucao.dat");// 
        if (last != null) {
            caminho = last.get(0);
            tfEntidade.setText(caminho);
            listaAtributo = new ArrayList();

            lista = manipulaArquivo.abrirArquivo(caminho);
            if (lista != null) {
                tfEntidade.setText(caminho);

                Atributo a;
                for (String string : lista) {
                    String[] aux = string.split(";");
                    a = new Atributo(aux[0], aux[1], aux[2]);
                    listaAtributo.add(a);
                    addAtributo(a);
                }
                // abre a última pasta no filechooser.
                File file = new File(caminho);
                caixaDeDialogo.setCurrentDirectory(file);
                tfEntidade.setBackground(Color.green);
            } else {
                tfEntidade.setBackground(Color.red);
                addAtributos();
            }
        }
        setSize(600, 600);
        CentroDoMonitorMaior centroDoMonitorMaior = new CentroDoMonitorMaior();
        setLocation(centroDoMonitorMaior.getCentroMonitorMaior(this));
        // addAtributos();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(pnNorte, BorderLayout.NORTH);
        cp.add(pnCentro, BorderLayout.CENTER);
        cp.add(pnSul, BorderLayout.SOUTH);

        pnCentro.add(new JScrollPane(getTblAtributos()));

        pnNorteA.add(new JLabel("Entidade"));
        pnNorteA.add(tfEntidade);
        pnNorteA.add(btnAbrir);

        pnNorteB.add(lbNome);
        pnNorteB.add(lbTipo);
        pnNorteB.add(lbTamanho);
        pnNorteB.add(tfNome);
        pnNorteB.add(tfTipo);
        pnNorteB.add(tfTamanho);

        pnNorte.add(pnNorteA);
        pnNorte.add(pnNorteB);
        pnNorte.add(pnNorteC);
        pnNorteC.add(btnAddAtributo);
        pnNorteC.add(btnUpdAtributo);
        pnNorteC.add(btnDelAtributo);

        pnSulA.add(btnSave);
        pnSulA.add(btnQuit);
        pnSulB.add(btnGerarClasseEntidade);
        pnSulB.add(btnGerarClasseControle);
        pnSulB.add(btnGerarClasseGUI);
        pnSulB.add(btnGerarGUIListagem);
        pnSulB.add(btnMain);
        pnSul.add(pnSulA);
        pnSul.add(pnSulB);

        pnCentro.setBackground(Color.decode("#BA55D3"));
        pnNorte.setBackground(Color.decode("#9400D3"));
        pnNorteB.setBackground(Color.decode("#8A2BE2"));
        pnSul.setBackground(Color.decode("#8B008B"));
        pnNorteA.setBackground(Color.decode("#DDA0DD"));
        pnSulA.setBackground(Color.decode("#800080"));
        pnSulB.setBackground(Color.decode("#800080"));

        setVisible(true);

        btnAddAtributo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAtributo();
            }
        });

        btnUpdAtributo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updAtributo();
            }
        });

        btnQuit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dispose();
            }
        });

        btnDelAtributo.addActionListener((ActionEvent e) -> {
            delAtributo();
        });

        btnSave.addActionListener((ActionEvent e) -> {
            List<String> lista = model.getListaAtributosString();
            if (tfEntidade.getText().equals("")) {
                pnSul.setBackground(Color.red);
                tfEntidade.requestFocus();
            }

            manipulaArquivo.salvarArquivo(tfEntidade.getText(), lista);
        });

        btnAbrir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                listaAtributo = new ArrayList();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                caixaDeDialogo.setFileFilter(filter);
                caixaDeDialogo.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (caixaDeDialogo.showOpenDialog(cp) == JFileChooser.APPROVE_OPTION) {
                    caminho = caixaDeDialogo.getSelectedFile().getAbsolutePath();

                    lista = manipulaArquivo.abrirArquivo(caminho);
                    tfEntidade.setText(caminho);

                    getModel();
                    model.limpar();
                    Atributo a;
                    for (int i = 0; i < lista.size(); i++) {
                        String[] aux = lista.get(i).split(";");
                        a = new Atributo(aux[0], aux[1], aux[2]);
                        System.out.println(a.toString());
                        listaAtributo.add(a);
                    }
                    model.addListaDeAtributos(listaAtributo);
                    tfEntidade.setBackground(Color.green);
                }
                lista = manipulaArquivo.abrirArquivo(tfEntidade.getText());
            }
        });

        btnGerarClasseEntidade.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarClasseEntidade gerarClasseEntidade = new GerarClasseEntidade(caminho);
            }
        });

        btnGerarClasseControle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarClasseDeControle gerarClasseDeControle = new GerarClasseDeControle(caminho);
            }
        });

        btnGerarClasseGUI.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarGUI gerarGUI = new GerarGUI(caminho);
            }
        });

        btnMain.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarMain gerarMain = new GerarMain(caminho);
            }
        });

        btnGerarGUIListagem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GerarGUIListagem gerarGUIListagem = new GerarGUIListagem(caminho);
                GerarMinhaJOptionPane gerarMinhaJOptionPane = new GerarMinhaJOptionPane();
            }
        });

        tblAtributos.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Atributo a = model.getAtributo(tblAtributos.getSelectedRow());
                tfNome.setText(a.getNome());
                tfTipo.setText(a.getTipo());
                tfTamanho.setText(String.valueOf(a.getTamanho()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //listenner ao fechar a janela
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                last.clear();
                caminho = tfEntidade.getText();
                System.out.println("caminho " + caminho);
                last.add(caminho);
                manipulaArquivo.salvarArquivo("UltimaExecucao.dat", last);
                // Sai do sistema  
                System.exit(0);
            }
        });
        /*
         String aux;
         if (caminho.substring(caminho.length() - 3, caminho.length()).equals("txt")) {
         aux = "";
         } else {
         aux = ".txt";
         } 
         */

    }

    private JTable getTblAtributos() {
        if (tblAtributos == null) {
            tblAtributos = new JTable();
            tblAtributos.setModel(new AtributoTableModel());
        }
        return tblAtributos;
    }

    private AtributoTableModel getModel() {
        if (model == null) {
            model = (AtributoTableModel) getTblAtributos().getModel();
        }
        return model;
    }

    private Atributo setAtributo(String nome, String tipo, String tamanho) {
        Atributo atributo = new Atributo();
        atributo.setNome(nome);
        atributo.setTipo(tipo);
        atributo.setTamanho(tamanho);
        return atributo;
    }

    private List<Atributo> autoAtributos() {
        List<Atributo> atributos = new ArrayList<Atributo>();
        Atributo atributo = new Atributo();
        atributo.setNome("id");
        atributo.setTipo("int");
        atributo.setTamanho("");
        atributos.add(atributo);

        atributo = new Atributo();
        atributo.setNome("nome");
        atributo.setTipo("String");
        atributo.setTamanho("");
        atributos.add(atributo);

        return atributos;
    }

    private void addAtributo() {
        getModel().addAtributo(setAtributo(tfNome.getText(), tfTipo.getText(), tfTamanho.getText()));
    }

    private void addAtributo(Atributo a) {
        getModel().addAtributo(a);
    }

    private void addAtributos() {
        getModel().addListaDeAtributos(autoAtributos());
    }

    private void updAtributo() {
        Atributo a = new Atributo(tfNome.getText(), tfTipo.getText(), tfTamanho.getText());
        model.setAtributo(tblAtributos.getSelectedRow(), a);

    }

    private void delAtributo() {
        if (tblAtributos.getSelectedRow() >= 0) {
            model.removeAtributo(tblAtributos.getSelectedRow());
        }
    }

}
