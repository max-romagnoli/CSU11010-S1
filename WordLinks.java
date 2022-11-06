import java.io.*;
import java.util.*;
import java.util.Collections;

public class WordLinks {

    final static long WORD_NUMBER = 658964;
    private static ArrayList<String> dictionary;

    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);
        File words = new File("words.txt");
        FileReader fr = new FileReader(words);
        BufferedReader br = new BufferedReader(fr);
        dictionary = new ArrayList<>();

        boolean quit = false;

        readDictionary(br);
        fr.close(); br.close();

        do
        {
            System.out.println("Enter a comma separated list of words (or an empty list to quit): ");
            String chain = input.nextLine();
            if (!Objects.equals(chain, ""))
            {
                ArrayList<String> chainList = readWordList(chain);
                isWordChain(chainList);
            }
            else
                quit = true;
        }
        while(!quit);

    }

    public static void readDictionary(BufferedReader br) throws IOException {
        for (long i = 0; i < WORD_NUMBER; i++)
            dictionary.add(br.readLine());
    }

    public static ArrayList<String> readWordList(String chain) {
        return new ArrayList<>(Arrays.asList(chain.replaceAll("\\s","").split(",")));
    }

    public static boolean isUniqueList(ArrayList<String> words) {
        Set<String> check = new HashSet<>(words);
        return check.size() == words.size();
    }

    public static boolean areEnglishWords(ArrayList<String> words) {            // changed method name to reflect parameter passing
        for (String word : words)
        {
            if(Collections.binarySearch(dictionary, word) < 0)
                return false;
        }
        return true;
    }

    public static boolean areDifferentByOne(ArrayList<String> words) {          // changed method name to reflect parameter passing
        for (int w = 0; w < words.size()-1; w++)
        {
            String w1 = words.get(w);
            String w2 = words.get(w+1);
            if (w1.length()==w2.length())
            {
                ArrayList<Character> chars1 = new ArrayList<>();
                for (char c : w1.toCharArray())
                    chars1.add(c);
                ArrayList<Character> chars2 = new ArrayList<>();
                for (char c : w2.toCharArray())
                    chars2.add(c);
                int differentChars = 0;
                for (int j = 0; j < chars2.size(); j++)
                {
                    if (differentChars <= 1)
                    {
                        if (chars1.get(j) != chars2.get(j))
                            differentChars++;
                    }
                    else
                        return false;
                }
            }
        }
        return true;
    }

    public static void isWordChain(ArrayList<String> chainList) {
        if(isUniqueList(chainList)
                && areEnglishWords(chainList)
                && areDifferentByOne(chainList))
            System.out.println("Valid chain of words from Lewis Carroll's word-links game.");
        else
            System.out.println("Not a valid chain of words from Lewis Carroll's word-links game.");
    }

}
