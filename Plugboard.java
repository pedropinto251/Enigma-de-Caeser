import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Plugboard {

    // Método para configurar a plugboard com base em uma string de entrada
    public HashMap<Character, Character> setPlugboard(String plug) {
        // Remove parênteses e aspas simples da string de configuração
        plug = plug.substring(1, plug.length() - 1).replaceAll("'", "");

        // Divide a string em pares keyValue separados por vírgula
        String[] keyValuePairs = plug.split(",");
        // Cria um HashMap para armazenar as substituições
        HashMap<Character, Character> plugboard = new HashMap<>();

        // percorre sobre os pares keyValue
        for (String pair : keyValuePairs) {
            // Divide cada par em uma entrada e saída
            String[] entry = pair.split(":");
            // Obtém o primeiro caractere da entrada
            Character input1 = entry[0].trim().charAt(0);
            // Obtém o primeiro caractere da saída
            Character input2 = entry[1].trim().charAt(0);
            // Adiciona a entrada e saída ao HashMap
            plugboard.put(input1, input2);
        }

        return plugboard;
    }

    // Método para aplicar a plugboard a uma lista de palavras salted
    public ArrayList<String> EnterPlugboard(HashMap<Character, Character> plugboard, ArrayList<String> saltedWords) {
        // Lista para armazenar as palavras modificadas
        ArrayList<String> plugdWords = new ArrayList<>();

        // percorre sobre cada palavra salted
        for (String saltedWord : saltedWords) {
            // Cria uma cópia da palavra para modificação
            String plug = saltedWord;

            // Itera sobre as entradas da plugboard e substitui os caracteres
            // correspondentes
            for (Map.Entry<Character, Character> entry : plugboard.entrySet()) {
                plug = plug.replace(entry.getKey(), entry.getValue());
            }

            // Adiciona a palavra modificada à lista
            plugdWords.add(plug);
        }
        return plugdWords;
    }
}
