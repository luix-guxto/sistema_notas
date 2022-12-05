package ferramentas.utilidades;

public class ValidaTelefone {

    public static boolean isTelefone(String telefone){
        // Limpa o telefone
        telefone = telefone.replaceAll("[^0-9]", "");

        // verifica se é um telefone valido
        return telefone.length() == 10 || telefone.length() == 11;
    }

    //testa a validação
    public static void main(String[] args) {
        String telefone = "12 3456-7890";
        System.out.println(isTelefone(telefone));
    }
}
