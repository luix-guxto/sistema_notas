package ferramentas.utilidades;

public class ValidaEmail {

    public static boolean isEmail(String email){
        // verifica se é um email valido
        return email.contains("@") && email.contains(".") && !email.equals("@.") && !email.equals(".@");
    }

    //testa a validação
    public static void main(String[] args) {
        String email = "email@email.com";
        System.out.println(isEmail(email));
    }
}
