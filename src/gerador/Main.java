package gerador;

class STRS {

    public String inicialMaiuscula(String s) {
        String plmaiuscula = s.substring(0, 1).toUpperCase() + s.substring(1);
        return plmaiuscula;
    }
}

public class Main {

    public static void main(String[] args) {
        GUIAtributos guiAtributos = new GUIAtributos();
    }

}
