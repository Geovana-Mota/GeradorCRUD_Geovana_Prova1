package gerador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Implementação de Table Model para exibir os Atributo.
 *
 * @author Eric Yuzo
 */
public class AtributoTableModel extends AbstractTableModel {

    /* Lista de Atributo que representam as linhas. */
    private List<Atributo> linhas;

    /* Array de Strings com o nome das colunas. */
    private String[] colunas = new String[]{
        "   Nome", "Tipo", "Opções"};


    /* Cria um AtributoTableModel vazio. */
    public AtributoTableModel() {
        linhas = new ArrayList<>();
    }

    /* Cria um AtributoTableModel carregado com
     * a lista de sócios especificada. */
    public AtributoTableModel(List<Atributo> listaDeAtributos) {
        linhas = new ArrayList<>(listaDeAtributos);
    }


    /* Retorna a quantidade de colunas. */
    @Override
    public int getColumnCount() {
        // Está retornando o tamanho do array "colunas".
        // Mas como o array é fixo, vai sempre retornar 4.
        return colunas.length;
    }

    /* Retorna a quantidade de linhas. */
    @Override
    public int getRowCount() {
        // Retorna o tamanho da lista de sócios.
        return linhas.size();
    }

    /* Retorna o nome da coluna no índice especificado.
     * Este método é usado pela JTable para saber o texto do cabeçalho. */
    @Override
    public String getColumnName(int columnIndex) {
        // Retorna o conteúdo do Array que possui o nome das colunas
        // no índice especificado.
        return colunas[columnIndex];
    }

    ;

	/* Retorna a classe dos elementos da coluna especificada.
	 * Este método é usado pela JTable na hora de definir o editor da célula. */
	@Override
    public Class<?> getColumnClass(int columnIndex) {
        // Retorna a classe referente a coluna especificada.
        // Aqui é feito um switch para verificar qual é a coluna
        // e retornar o tipo adequado. As colunas são as mesmas
        // que foram especificadas no array "colunas".
        switch (columnIndex) {
            case 0: // Primeira coluna é o nome, que é uma String.
                return String.class;
            case 1: // Segunda coluna é o tipo
                return String.class;
            case 2:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /* Retorna o valor da célula especificada
     * pelos índices da linha e da coluna. */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Atributo atributo = linhas.get(rowIndex);
        switch (columnIndex) {
            case 0: // Primeira coluna é o nome.
                return atributo.getNome();
            case 1: // Segunda coluna é o telefone.
                return atributo.getTipo();
            case 2: // Terceira coluna é a data de cadastro.
                return atributo.getTamanho();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

    }

    /* Retorna um valor booleano que define se a célula em questão
     * pode ser editada ou não.
     * Este método é utilizado pela JTable na hora de definir o editor da célula.
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    ////////////////////////////////////////////////////////////
    // Os métodos declarados até aqui foram as implementações //
    // de TableModel, que são continuamente utilizados        //
    // pela JTable para definir seu comportamento,            //
    // por isso o nome Table Model (Modelo da Tabela).        //
    //                                                        //
    // A partir de agora, os métodos criados serão            //
    // particulares desta classe. Eles serão úteis            //
    // em algumas situações.                                  //
    ////////////////////////////////////////////////////////////
    /* Retorna a linha especificada. */
    public Atributo getAtributo(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addAtributo(Atributo atributo) {
        // Adiciona o registro.
        linhas.add(atributo);

        // Pega a quantidade de registros e subtrai um para achar
        // o último índice. É preciso subtrair um, pois os índices
        // começam pelo zero.
        int ultimoIndice = getRowCount() - 1;

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void setAtributo(int indiceLinha, Atributo a) {
        linhas.set(indiceLinha, a);
        // redesenha permitindo que visualizemos a atualização.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void removeAtributo(int indiceLinha) {
        // Remove a linha especificada. 
        linhas.remove(indiceLinha);
        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }

    public void addListaDeAtributos(List<Atributo> atributos) {
        // Pega o tamanho antigo da tabela.
        int tamanhoAntigo = getRowCount();
        
        // Adiciona os registros.
        linhas.addAll(atributos);

        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    /* Remove todos os registros. */
    public void limpar() {
        // Remove todos os elementos da lista de sócios.
        linhas.clear();
        // Reporta a mudança. O JTable recebe a notificação
        // e se redesenha permitindo que visualizemos a atualização.
        fireTableDataChanged();
    }

    /* Verifica se este table model está vazio. */
    public boolean isEmpty() {
        return linhas.isEmpty();
    }

    public List<String> getListaAtributosString() {
        List<String> lista = new ArrayList();
        for (int i = 0; i < linhas.size(); i++) {
            lista.add(linhas.get(i).toString());
        }
        return lista;
    }

}
