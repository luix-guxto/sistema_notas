package ferramentas.utilidades;

public class ValidaTelefone {

    public static boolean isTelefone(String telefone){
        // verifica se é um telefone valido
        if (telefone.length() == 10 || telefone.length() == 11) {
            return true;
        } else {
            return false;
        }
    }
}
