import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;


public class NumberGuessing extends JFrame implements ActionListener {
    private int randomNumber,maxNumber,maxAttempts,attempts,wins,losses =0;
    int currentLevel;
    private boolean isWin=false;
     private boolean gameOver = false;
     private JTextField guessField;
     private JButton guessButton,resetButton;
     private JLabel messageLabel, attemptLabel,scoreLabel, rangeLabel;
     private JTextArea historyArea;
     private JComboBox<String> difficultyBox;
     private  ArrayList<Integer> guessHistory = new ArrayList<>();
     
     public NumberGuessing() {
        setTitle("Number Guessing Gamme ");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        //Top Panel
        JPanel topPanel = new JPanel(new GridLayout(2,1));
        difficultyBox = new JComboBox<>(new String[] { "Easy (1-100)","Medium (1-500)","Hard (1-1000)"});
        difficultyBox.setFont(new Font("Times New Roman", Font.BOLD, 16));
        difficultyBox.setBackground(Color.yellow);
        topPanel.setBackground(Color.MAGENTA);
        
        
       difficultyBox.addActionListener(e -> resetGame());
       rangeLabel = new JLabel("",JLabel.CENTER);
     rangeLabel.setFont(new Font("Arial",Font.ITALIC,14));
    
     topPanel.add(difficultyBox);
     topPanel.add(rangeLabel);
     add(topPanel,BorderLayout.NORTH);

     //CENTER PANEL
     JPanel centerPanel = new JPanel(new GridLayout(3, 1));
     JPanel inputPanel = new JPanel();
     guessField = new JTextField(10);
     guessField.setFont(new Font("Arial",Font.PLAIN,20));
     guessField.setBackground(Color.white);
     inputPanel.add(new JLabel("Enter guess : "));
     inputPanel.setBackground(Color.pink);
     inputPanel.add(guessField);

     //button designing
     guessButton = new JButton("GUESS");
     guessButton.setFont(new Font("Arial",Font.BOLD,16));
     guessButton.setBackground(Color.CYAN);
     guessButton.addActionListener(this);
     inputPanel.add(guessButton);
     centerPanel.add(inputPanel);

     messageLabel = new JLabel("Guess the Number ",JLabel.CENTER);
messageLabel.setFont(new Font("Arial",Font.PLAIN,16));

centerPanel.add(messageLabel);
centerPanel.setBackground(Color.pink);
attemptLabel = new JLabel("Attempts Left:",JLabel.CENTER);
attemptLabel.setFont(new Font("Arial",Font.PLAIN,16));
centerPanel.add(attemptLabel);
add(centerPanel,BorderLayout.CENTER);

//RIGHT PANEL:HISTORY
JPanel historyPanel = new JPanel(new BorderLayout());
historyArea = new JTextArea();
historyArea.setEditable(false);
historyArea.setFont(new Font("Monospaced",Font.PLAIN,14));
historyArea.setBackground(Color.blue);
historyPanel.add(new JLabel(" Guess History: "),BorderLayout.NORTH);
historyPanel.setBackground(Color.orange);
historyPanel.add(new JScrollPane(historyArea),BorderLayout.CENTER);
add(historyPanel,BorderLayout.EAST);

//Bottom Panel
JPanel bottomPanel = new JPanel(new GridLayout(2, 1));
scoreLabel = new JLabel("Wins: 0 | Losses: 0",JLabel.CENTER);
scoreLabel.setFont(new Font("Arial",Font.BOLD,16));
scoreLabel.setOpaque(true); 
scoreLabel.setBackground(Color.orange); 

resetButton = new JButton("New Game");
resetButton.setBackground(Color.green);
resetButton.setFont(new Font("Arial",Font.BOLD,16));
resetButton.addActionListener(e -> resetGame());
bottomPanel.add(scoreLabel);
bottomPanel.add(resetButton);
add(bottomPanel,BorderLayout.SOUTH);

setVisible(true);
resetGame();
     }

     @Override
     public void actionPerformed(ActionEvent e) {
      if(gameOver)
      return;
      String input = guessField.getText().trim();
      try{
         int guess = Integer.parseInt(input);
         if(guess < 1 || guess > maxNumber) {
             messageLabel.setText("Enter number between 1 and " + maxNumber);
             return;
         }
         guessHistory.add(guess);
         attempts++;
         int remaining = maxAttempts - attempts;
         updateHistory();

         if(guess == randomNumber) {
            messageLabel.setText("Correct! you won game in " + attempts + "attempts");
            isWin=true;
            wins++;
            endGame();

         }
         else if (attempts >= maxAttempts) {
            
            messageLabel.setText("You Lost Game! Number Was " + randomNumber);
            isWin=false;
            losses++;
            endGame();

         }else if (Math.abs(guess - randomNumber) <= 5) {
            messageLabel.setText("Very close! Try again");
         }
         else {
            messageLabel.setText(guess < randomNumber ? "Too Low!" : "Too High!");

         }
         attemptLabel.setText("Attempts Left : " +(maxAttempts - attempts));

      }
      catch (NumberFormatException ex) {
         messageLabel.setText("Please enter a valid number!");
      }
      guessField.setText("");
     }
     private void resetGame() {
      gameOver = false;
      guessHistory.clear();
      updateHistory();

      switch(difficultyBox.getSelectedIndex()) {
         case 0 -> {
            maxNumber = 100;
            maxAttempts  = 10;
         }
         case 1 -> {
            maxNumber = 500;
            maxAttempts = 10;

         }
         case 2 -> {
            maxNumber = 1000;
            maxAttempts = 10;
         }
      }
      randomNumber = new Random().nextInt(maxNumber) + 1;
      attempts = 0;

      messageLabel.setText("Guess the Number!");
      attemptLabel.setText("Attempts Left:" +maxAttempts);
      rangeLabel.setText("Number Range : 1 to " +maxNumber);
      guessButton.setEnabled(true);
      guessField.setEditable(true);
      scoreLabel.setText("Wins: "+wins+" |Losses: "+losses);
      guessField.setText("");
      }
      private void endGame() {
         gameOver = true;
         guessButton.setEnabled(false);
         guessField.setEditable(false);
         scoreLabel.setText("Wins: "  + wins + "| Losses:" + losses);
       int  currentLevel = difficultyBox.getSelectedIndex();
         if(isWin && currentLevel < 2 ){
             Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                difficultyBox.setSelectedIndex(currentLevel + 1);
                resetGame();
            }
        });
        timer.setRepeats(false);
        timer.start();
           
         }
         else if (isWin && currentLevel == 2) {
      
        messageLabel.setText("Congratulations! You completed all levels!");
    }
    else if (!isWin) {
        
        messageLabel.setText("You lost! Restarting this level...");
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame(); 
            }
        } );
        timer.setRepeats(false);
        timer.start();
    }
}

         
      
      private void updateHistory() {
         StringBuilder sb = new StringBuilder();
         for(int i = 0; i < guessHistory.size(); i++) {
            sb.append("Try").append(i + 1).append(":").append(guessHistory.get(i)).append("\n");
         }
         historyArea.setText(sb.toString());
         }
      
      public static void main(String[] args) 
      {
         new NumberGuessing();
      }
     }

