package ferramentas.utilidades;

public class ValidaEmail {

    public static boolean isEmail(String email){
        // verifica se é um email valido
        if (email.contains("@") && email.contains(".")) {
            return true;
        } else {
            return false;
        }
    }
}
