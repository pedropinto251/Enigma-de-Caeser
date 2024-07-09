import java.util.ArrayList;
import java.util.Arrays;

public class Caesar {

    // O alfabeto usado para criptografia de Caesar
    private Character[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public int fInc = 0; // Fator de incremento
    public int rotation = 0; // Rotação
    public String wordC; // Palavra criptografada
    public int plugI; // indice do plug

    public static int Ind;

    public static void getI() {
        Salter h = new Salter();
        Ind = h.numArr;
    }

    // Realiza todas as combinações de rotação e incremento nas palavras fornecidas.

    public ArrayList<String> rot(ArrayList<String> plugdWords) {
        getI();

        // Lista para armazenar as palavras criptografadas para todas as combinações.
        ArrayList<String> cryptedWords = new ArrayList<>();

        for (int x = 0; x < plugdWords.size(); x++) {
            String currentWord = plugdWords.get(x);

            // percorre todas as combinações possíveis de rotação e incremento.
            for (int rot = 0; rot < 26; rot++) {
                for (int inc = 0; inc < 26; inc++) {
                    String caesar = CaesarEnhanced(rot, inc, currentWord);
                    cryptedWords.add(caesar);
                }
            }
        }

        // Obtém a palavra criptografada correspondente ao índice do plug.
        wordC = cryptedWords.get(Ind);

        // Retorna a lista de palavras criptografadas.
        return cryptedWords;
    }

    // Determina a rotação e o fator de incremento com base na palavra
    // criptografada.
    public void rot1(ArrayList<String> plugdWords, ArrayList<String> cryptedWords) {
        // percorre cada palavra na lista de palavras antes da criptografia.
        for (int x = 0; x < plugdWords.size(); x++) {
            String currentWord = plugdWords.get(x);

            // percorre todas as combinações possíveis de rotação e incremento.
            for (int rot = 0; rot < 26; rot++) {
                for (int inc = 0; inc < 26; inc++) {
                    // Obtém a palavra criptografada usando o método de Caesar aprimorado.
                    String caesar = CaesarEnhanced(rot, inc, currentWord);

                    // Verifica se a palavra criptografada é igual à palavra criptografada desejada.
                    if (caesar.equals(wordC)) {
                        // Se encontrarmos uma correspondência, atribuímos os valores e saímos do loop.
                        rotation = rot + 1; // Incrementa a rotação por 1 para adequar à contagem normal.
                        fInc = inc + 1; // Incrementa o fator de incremento por 1 para adequar à contagem normal.
                        plugI = x;
                        break;
                    }
                }
            }
        }

        System.out.println("O fator de incremento foi de " + fInc + " e a rotação foi de " + rotation);
    }

    public String CaesarEnhanced(int rotation, int increment, String input) {
        // StringBuilder usado para construir a string criptografada de forma eficiente.
        StringBuilder result = new StringBuilder();

        // Itera através de cada caractere na string de entrada.
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Verifica se o caractere é alfabético.
            if (Character.isAlphabetic(currentChar)) {

                int alphabetIndex = Arrays.asList(alphabet).indexOf(currentChar);

                // Calcula a nova posição do caractere após rotação e incremento.
                int newPos = (alphabetIndex + rotation + (increment * i)) % 26;

                // Obtém o novo caractere criptografado.
                char newChar = alphabet[newPos];

                // Adiciona o caractere criptografado ao resultado.
                result.append(newChar);
            } else {
                // Se o caractere não for alfabético, adiciona-o ao resultado sem criptografia.
                result.append(currentChar);
            }
        }

        // Converte o StringBuilder resultante de volta para uma string e a retorna.
        return result.toString();
    }

    public int getPlugIndex() {
        return plugI;
    }

}
