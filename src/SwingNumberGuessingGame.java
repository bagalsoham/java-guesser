import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SwingNumberGuessingGame extends JFrame {
    private int targetNumber;
    private int numberOfPlayers;
    private int currentPlayer;
    private int guessCount;
    private boolean gameActive;
    private Random random;
    
    // UI Components
    private JLabel playerLabel;
    private JLabel feedbackLabel;
    private JLabel guessCountLabel;
    private JTextField guessField;
    private JButton submitButton;
    private JButton stopButton;
    private JButton newGameButton;
    private JSpinner playerSpinner;
    private JButton startButton;
    private JPanel gamePanel;
    private JPanel setupPanel;
    
    public SwingNumberGuessingGame() {
        random = new Random();
        initializeUI();
        showSetupPanel();
    }
    
    private void initializeUI() {
        setTitle("Number Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new CardLayout());
        
        createSetupPanel();
        createGamePanel();
        
        add(setupPanel, "setup");
        add(gamePanel, "game");
    }
    
    private void createSetupPanel() {
        setupPanel = new JPanel(new GridBagLayout());
        setupPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Welcome message
        JLabel titleLabel = new JLabel("🎯 Welcome to Number Guessing Game! 🎯");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 20, 30, 20);
        setupPanel.add(titleLabel, gbc);
        
        // Instructions
        JTextArea instructions = new JTextArea(
            "How to Play:\n" +
            "• I'll think of a number between 1 and 100\n" +
            "• Players take turns guessing the number\n" +
            "• I'll tell you if your guess is too high or too low\n" +
            "• First player to guess correctly wins!"
        );
        instructions.setFont(new Font("Arial", Font.PLAIN, 14));
        instructions.setBackground(new Color(240, 248, 255));
        instructions.setEditable(false);
        instructions.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 20, 20, 20);
        setupPanel.add(instructions, gbc);
        
        // Player selection
        JLabel playerSelectionLabel = new JLabel("Select number of players:");
        playerSelectionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        setupPanel.add(playerSelectionLabel, gbc);
        
        playerSpinner = new JSpinner(new SpinnerNumberModel(2, 1, 6, 1));
        playerSpinner.setFont(new Font("Arial", Font.PLAIN, 16));
        playerSpinner.setPreferredSize(new Dimension(80, 30));
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        setupPanel.add(playerSpinner, gbc);
        
        // Start button
        startButton = new JButton("🚀 Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(34, 139, 34));
        startButton.setForeground(Color.WHITE);
        startButton.setPreferredSize(new Dimension(150, 40));
        startButton.addActionListener(e -> startNewGame());
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 20, 20, 20);
        gbc.anchor = GridBagConstraints.CENTER;
        setupPanel.add(startButton, gbc);
    }
    
    private void createGamePanel() {
        gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Current player label
        playerLabel = new JLabel();
        playerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        playerLabel.setForeground(new Color(25, 25, 112));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 20, 10, 20);
        gamePanel.add(playerLabel, gbc);
        
        // Guess count label
        guessCountLabel = new JLabel();
        guessCountLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        guessCountLabel.setForeground(new Color(105, 105, 105));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        gbc.insets = new Insets(0, 20, 15, 20);
        gamePanel.add(guessCountLabel, gbc);
        
        // Guess input
        JLabel guessLabel = new JLabel("Enter your guess (1-100):");
        guessLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 20, 10, 10);
        gbc.anchor = GridBagConstraints.EAST;
        gamePanel.add(guessLabel, gbc);
        
        guessField = new JTextField(10);
        guessField.setFont(new Font("Arial", Font.PLAIN, 16));
        guessField.setPreferredSize(new Dimension(100, 30));
        guessField.addActionListener(e -> submitGuess());
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gamePanel.add(guessField, gbc);
        
        // Submit button
        submitButton = new JButton("Submit Guess");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(30, 144, 255));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> submitGuess());
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 20);
        gamePanel.add(submitButton, gbc);
        
        // Feedback label
        feedbackLabel = new JLabel(" ");
        feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 20, 20, 20);
        gamePanel.add(feedbackLabel, gbc);
        
        // Control buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(245, 245, 245));
        
        stopButton = new JButton("🛑 Stop Game");
        stopButton.setFont(new Font("Arial", Font.BOLD, 14));
        stopButton.setBackground(new Color(220, 20, 60));
        stopButton.setForeground(Color.WHITE);
        stopButton.addActionListener(e -> stopGame());
        buttonPanel.add(stopButton);
        
        newGameButton = new JButton("🔄 New Game");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 14));
        newGameButton.setBackground(new Color(34, 139, 34));
        newGameButton.setForeground(Color.WHITE);
        newGameButton.addActionListener(e -> showSetupPanel());
        newGameButton.setEnabled(false);
        buttonPanel.add(newGameButton);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 20, 20, 20);
        gamePanel.add(buttonPanel, gbc);
    }
    
    private void showSetupPanel() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "setup");
    }
    
    private void showGamePanel() {
        CardLayout cl = (CardLayout) getContentPane().getLayout();
        cl.show(getContentPane(), "game");
    }
    
    private void startNewGame() {
        numberOfPlayers = (Integer) playerSpinner.getValue();
        targetNumber = random.nextInt(100) + 1; // Random number between 1-100
        currentPlayer = 1;
        guessCount = 0;
        gameActive = true;
        
        updatePlayerLabel();
        updateGuessCountLabel();
        feedbackLabel.setText("Game started! Good luck to all players! 🍀");
        feedbackLabel.setForeground(new Color(34, 139, 34));
        
        guessField.setText("");
        guessField.setEnabled(true);
        submitButton.setEnabled(true);
        stopButton.setEnabled(true);
        newGameButton.setEnabled(false);
        
        showGamePanel();
        guessField.requestFocus();
    }
    
    private void submitGuess() {
        if (!gameActive) return;
        
        try {
            String guessText = guessField.getText().trim();
            if (guessText.isEmpty()) {
                showError("Please enter a number!");
                return;
            }
            
            int guess = Integer.parseInt(guessText);
            
            if (guess < 1 || guess > 100) {
                showError("Please enter a number between 1 and 100!");
                return;
            }
            
            guessCount++;
            updateGuessCountLabel();
            
            if (guess == targetNumber) {
                // Correct guess - player wins!
                feedbackLabel.setText("🎉 Correct! Player " + currentPlayer + " wins! 🎉");
                feedbackLabel.setForeground(new Color(255, 215, 0));
                endGame();
            } else if (guess < targetNumber) {
                // Guess is too low
                feedbackLabel.setText("📈 Too low! Try a higher number.");
                feedbackLabel.setForeground(new Color(255, 69, 0));
                nextPlayer();
            } else {
                // Guess is too high
                feedbackLabel.setText("📉 Too high! Try a lower number.");
                feedbackLabel.setForeground(new Color(255, 69, 0));
                nextPlayer();
            }
            
            guessField.setText("");
            guessField.requestFocus();
            
        } catch (NumberFormatException e) {
            showError("Please enter a valid number!");
        }
    }
    
    private void nextPlayer() {
        currentPlayer++;
        if (currentPlayer > numberOfPlayers) {
            currentPlayer = 1;
        }
        updatePlayerLabel();
    }
    
    private void updatePlayerLabel() {
        playerLabel.setText("🎮 Player " + currentPlayer + " is guessing...");
    }
    
    private void updateGuessCountLabel() {
        guessCountLabel.setText("Total guesses made: " + guessCount);
    }
    
    private void showError(String message) {
        feedbackLabel.setText("⚠️ " + message);
        feedbackLabel.setForeground(Color.RED);
    }
    
    private void stopGame() {
        gameActive = false;
        feedbackLabel.setText("🛑 Game stopped! The correct number was " + targetNumber);
        feedbackLabel.setForeground(new Color(105, 105, 105));
        endGame();
    }
    
    private void endGame() {
        gameActive = false;
        guessField.setEnabled(false);
        submitButton.setEnabled(false);
        stopButton.setEnabled(false);
        newGameButton.setEnabled(true);
    }
    
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Use default look and feel if system look and feel is not available
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingNumberGuessingGame().setVisible(true);
            }
        });
    }
}
