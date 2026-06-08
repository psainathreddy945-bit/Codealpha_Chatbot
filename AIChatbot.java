import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class AIChatbot extends JFrame implements ActionListener {

    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton, clearButton, exitButton;

    private HashMap<String, String> faq;

    public AIChatbot() {

        faq = new HashMap<>();

        faq.put("hello", "Hello! How can I help you?");
        faq.put("hi", "Hi! Nice to meet you.");
        faq.put("java", "Java is an object-oriented programming language.");
        faq.put("python", "Python is a high-level programming language.");
        faq.put("ai", "Artificial Intelligence enables machines to think and learn.");
        faq.put("machine learning", "Machine Learning is a subset of Artificial Intelligence.");
        faq.put("data science", "Data Science is the process of extracting insights from data.");
        faq.put("college", "Our college provides quality education and technical training.");
        faq.put("course", "We offer Java, Python, AI, ML, Data Science and Web Development courses.");
        faq.put("thank you", "You're welcome!");
        faq.put("bye", "Goodbye! Have a nice day.");

        setTitle("AI Chatbot - CodeAlpha");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scrollPane = new JScrollPane(chatArea);

        inputField = new JTextField();
        inputField.addActionListener(this);

        sendButton = new JButton("Send");
        clearButton = new JButton("Clear");
        exitButton = new JButton("Exit");

        sendButton.addActionListener(this);
        clearButton.addActionListener(this);
        exitButton.addActionListener(this);

        JPanel bottomPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sendButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        bottomPanel.add(inputField, BorderLayout.CENTER);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        chatArea.append("Bot: Hello! I am an AI Chatbot.\n");
        chatArea.append("Bot: Ask me anything.\n\n");

        setVisible(true);
    }

    private String getResponse(String input) {

        input = input.toLowerCase().trim();

        if (input.contains("date")) {
            return "Today's Date: " +
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        }

        if (input.contains("time")) {
            return "Current Time: " +
                    LocalDateTime.now().format(
                            DateTimeFormatter.ofPattern("hh:mm a"));
        }

        for (String key : faq.keySet()) {
            if (input.contains(key)) {
                return faq.get(key);
            }
        }

        return "Sorry, I don't understand your question. Please try another one.";
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == sendButton || e.getSource() == inputField) {

            String userText = inputField.getText().trim();

            if (userText.isEmpty()) {
                return;
            }

            chatArea.append("You: " + userText + "\n");

            String response = getResponse(userText);

            chatArea.append("Bot: " + response + "\n\n");

            inputField.setText("");
        }

        if (e.getSource() == clearButton) {
            chatArea.setText("");
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AIChatbot();
            }
        });
    }
}