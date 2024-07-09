import java.util.ArrayList;
import java.util.HashMap;

public class BreakingEnigma {

    public static String hash;
    public static String plugboard;
    public static String file;

    public static void main(String[] args) {

        for (int x = 0; x < args.length; x++) {

            hash = args[0];
            plugboard = args[1];
            file = args[2];
            hash.toLowerCase();
        }

        if (args.length > 3) {

            System.out.println(
                    " Por favor introduza apenas a sua Hash, Plugboard e o caminho do respetivo ficheiro txt.");
            return;
        }

        // Inicialização de variáveis e objetos
        ArrayList<String> words = new ArrayList<>();
        HashMap<Character, Character> plugb = new HashMap<>();
        String word;
        boolean verifica = false;
        String salt;

        Salter s = new Salter();
        Plugboard p = new Plugboard();
        Caesar c = new Caesar();
        // Validação dos argumentos
        if (!s.validarArgs(hash, plugboard, file)) {
            return;
        }

        // Leitura da lista de palavras do arquivo
        words = s.readWordList();
        // Configuração da plugboard
        plugb = p.setPlugboard(plugboard);

        // Loop através das palavras para realizar o ataque de brute force
        for (int i = 0; i < words.size(); i++) {

            ArrayList<String> temp1 = new ArrayList<>();
            ArrayList<String> temp2 = new ArrayList<>();

            System.out.println("Palavra a ser testada: " + words.get(i));

            word = words.get(i);

            // Geração de palavras salted
            temp1 = s.saltedWord(word);
            // Aplicação da plugboard
            temp2 = p.EnterPlugboard(plugb, temp1);
            // Aplicação da cifra de Ceasar
            temp2 = c.rot(temp2);
            // Aplicação novamente da plugboard
            temp2 = p.EnterPlugboard(plugb, temp2);

            // Verificação da hash gerada com a hash fornecida
            verifica = s.comparaHashes(temp2, words.get(i));

            if (verifica) {
                // Se a hash é verificada com sucesso, realiza algumas operações adicionais

                // Limpa a lista temp2
                temp2.clear();
                temp2 = c.rot(temp1);
                temp2 = p.EnterPlugboard(plugb, temp2);
                c.rot1(temp1, temp2);
                // Obtém o "salt" da palavra
                salt = temp1.get(c.getPlugIndex());
                salt = salt.replace(words.get(i), "");

                System.out.println("O salt da sua palavra é: " + salt);
                break; // Sai do loop, pois a senha foi encontrada
            }
        }
    }
}
