package ferramentas.utilidades;

public class ValidaCPF {


    public static boolean isCPF(String cpf){
        // limpa todos os caracteres que não sejam números
        cpf = cpf.replaceAll("[^0-9]", "");
        // verifica se cpf é um cpf válido
        if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888") || cpf.equals("99999999999") || (cpf.length() != 11)) {
            return false;
        }else{
            char dig10, dig11;
            int sm, i, r, num, peso;
            try {
                sm = 0;
                peso = 10;
                for (i = 0; i < 9; i++) {
                    num = cpf.charAt(i) - 48;
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig10 = '0';
                } else {
                    dig10 = (char) (r + 48);
                }
                sm = 0;
                peso = 11;
                for (i = 0; i < 10; i++) {
                    num = cpf.charAt(i) - 48;
                    sm = sm + (num * peso);
                    peso = peso - 1;
                }
                r = 11 - (sm % 11);
                if ((r == 10) || (r == 11)) {
                    dig11 = '0';
                } else {
                    dig11 = (char) (r + 48);
                }
                return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
            } catch (Exception erro) {
                return false;
            }
        }
    }

    // testar validação de cpf
    public static void main(String[] args) {
        String cpf = "123.456.789-09";
        System.out.println(isCPF(cpf));
    }
}
