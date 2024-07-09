import java.io.File;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

public class Salter {

    // Array de salt pré-definidos
    private String[] salt = { "!", "#", "$", "%", "&", "*", "+", "-", ":", ";", "<", "=", ">", "?", "@" };

    public static int numArr;

    // Método para ler palavras de um arquivo
    public ArrayList<String> readWordList() {
        ArrayList<String> temp = new ArrayList<>();

        try {
            // Obtém o caminho do arquivo a partir da classe BreakingEnigma
            Path path = Paths.get(BreakingEnigma.file);
            // Obtém o nome do arquivo
            String fileName = path.getFileName().toString().trim();
            // Cria um objeto File
            File file = new File(fileName).getAbsoluteFile();
            Scanner scanner = new Scanner(file);

            // Lê cada linha do arquivo e adiciona à lista temp
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                temp.add(word);
                System.out.println(word);
            }
            scanner.close();

            return temp;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    // Método para gerar uma lista de salts
    public ArrayList<String> generateSalt() {
        ArrayList<String> salts = new ArrayList<>();

        // Combina cada salt com todos os outros salts
        for (String currentSalt : salt) {
            for (String salt2 : salt) {
                String combinedSalt = currentSalt + salt2;
                salts.add(combinedSalt);
            }
        }

        return salts;
    }

    // Método para criar palavras salted combinando a palavra com salts
    public ArrayList<String> saltedWord(String word) {
        ArrayList<String> saltedWords = new ArrayList<>();
        ArrayList<String> salts = generateSalt();

        // Combina a palavra com cada salt
        for (String currentSalt : salts) {
            saltedWords.add(word.concat(currentSalt));
            saltedWords.add(currentSalt.concat(word));
        }

        return saltedWords;
    }

    // Método para obter o hash SHA-256 de uma palavra
    public static String getSHA256(String word) {
        String wordHashed = null;

        try {
            // Cria um objeto MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.reset();
            digest.update(word.getBytes("utf8"));

            // Calcula o hash e formata como uma string hexadecimal
            wordHashed = String.format("%064x", new BigInteger(1, digest.digest()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordHashed;
    }

    // Método para comparar hashes e imprimir a palavra-passe se for encontrada
    public boolean comparaHashes(ArrayList<String> plugdWords, String word) {
        for (String value : plugdWords) {
            if (getSHA256(value).equals(BreakingEnigma.hash)) {
                numArr = plugdWords.indexOf(value);
                System.out.println("Palavra-passe: " + word);
                return true;
            }
        }
        return false;
    }

    // Método para validar argumentos (hash, plugboard e caminho do arquivo)
    public boolean validarArgs(String hash, String plugboard, String file) {
        if (hash.length() != 64) {
            System.out.println("Por favor, introduza uma hash correta!");
            return false;
        }

        if (!plugboard.startsWith("{") || !plugboard.endsWith("}")) {
            System.out.println("Por favor, introduza um plugboard correto!");
            return false;
        }

        if (!file.endsWith(".txt")) {
            System.out.println("Por favor, introduza o caminho para um ficheiro .txt!");
            return false;
        }

        return true;
    }
}
