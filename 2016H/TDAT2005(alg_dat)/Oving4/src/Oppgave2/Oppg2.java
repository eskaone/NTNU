package Oppgave2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;

public class Oppg2 {
    private static Stack<Character> brackets = new Stack<Character>();

    public static void main(String[] args) throws IOException {

        createStack();

    }

    public static char[] readFile(String filename) throws IOException {

        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return content.toCharArray();
    }

    public static void createStack() throws IOException {

        char[] chars = readFile("src/Oppgave2/code.txt");
        boolean endCheck = true;

        for (char c : chars) {
            if (c == '{' || c == '[' || c == '(') {
                brackets.push(c);
            } else if (c == '}' || c == ']' || c == ')') {
                if (brackets.size() == 0 && (c == '}' || c == ']' || c == ')')){
                    endCheck = false;
                } else if ((brackets.peek() == '{' && c == '}') || (brackets.peek() == '[' && c == ']') || (brackets.peek() == '(' && c == ')')) {
                    brackets.pop();
                }
            }
        }

        if (brackets.size() == 0 && endCheck) {
            System.out.println("OK");
        } else {
            System.out.println("NOT OK");
        }
    }
}
