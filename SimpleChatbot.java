import edu.stanford.nlp.pipeline.*;
import java.util.Properties;
import java.util.Scanner;

public class SimpleChatbot {
    private static StanfordCoreNLP pipeline;

    public static void main(String[] args) {
        // Set up the NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner,parse");
        props.setProperty("outputFormat", "text");
        pipeline = new StanfordCoreNLP(props);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I am your chatbot. Type 'exit' to quit.");

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            String response = getResponse(input);
            System.out.println(response);
        }
        scanner.close();
    }

    private static String getResponse(String input) {
        // Create a document for analysis
        CoreDocument document = new CoreDocument(input);
        pipeline.annotate(document);
        
        // Simple keyword-based response logic
        String response = "I'm not sure how to respond to that.";
        for (CoreSentence sentence : document.sentences()) {
            String text = sentence.toString().toLowerCase();
            if (text.contains("hello")) {
                response = "Hello! How can I help you?";
            } else if (text.contains("help")) {
                response = "Sure! What do you need help with?";
            }
        }
        return response;
    }
}
