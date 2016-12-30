// Programmer: Nate Sobol
// Title: Java Certification Exam
// Last Modified: 12/15/16
package jaca.certification.exam;

// libraries
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.*;
import javax.swing.*;
import java.util.Random;

public class JavaCertificationExam extends JFrame {

    // Declarations
    String title = "Java Certification Test";
    int qNum;
    int score;
    int tryNum = 0;

    // JPanels
    JPanel loginPanel = new JPanel(new BorderLayout());
    JPanel resultPanel = new JPanel(new BorderLayout());
    JPanel questionPanel = new JPanel(new BorderLayout());

    // Login Screen Components
    JTextField userIDField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JLabel usernameLabel = new JLabel("username");
    JLabel passwordLabel = new JLabel("password");
    JButton loginButton = new JButton("Login");
    
    // Test Screen Components
    JLabel questionLabel = new JLabel();
    JRadioButton RadioA = new JRadioButton();
    JRadioButton RadioB = new JRadioButton();
    JRadioButton RadioC = new JRadioButton();
    JRadioButton RadioD = new JRadioButton();
    JRadioButton RadioE = new JRadioButton();
    JButton nextButton = new JButton("Next");
    JButton previousButton = new JButton("Previous");
    
    // Results Screen Componenets
    JLabel resultLabel = new JLabel("test");
    JButton tryAgainButton = new JButton("Try Again");
    JButton exitButton = new JButton("Exit");
    
    // Constructor
    public JavaCertificationExam() {
        runTest();
    }

    public void runTest() {
        
        qNum = 1;
        score = 0;

        // Frame Properties
        setSize(300, 350);
        setTitle("Login");

        //Panel Properties
        loginPanel.setLayout(null);

        // Set Bounds of Componenets
        //                      x    y   w   h
        usernameLabel.setBounds(50, 50, 85, 20);
        passwordLabel.setBounds(50, 80, 85, 20);
        userIDField.setBounds(140, 50, 85, 20);
        passwordField.setBounds(140, 80, 85, 20);
        loginButton.setBounds(70, 120, 90, 20);

        // Componenet Properties
        usernameLabel.setFont(new Font("Courier New", Font.ITALIC, 16));
        passwordLabel.setFont(new Font("Courier New", Font.ITALIC, 16));

        // Add components
        loginPanel.add(usernameLabel);
        loginPanel.add(passwordLabel);
        loginPanel.add(userIDField);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        setContentPane(loginPanel);
        loginPanel.setVisible(true);
        setVisible(true);

        // Event for LoginButton when pressed
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Panel Properties
                loginPanel.setVisible(false);
                resultPanel.setVisible(false);
                questionPanel.setLayout(null);
                questionPanel.setSize(400, 400);

                // Frame Properties
                setSize(800, 400);
                setTitle("Java Certification Exam");
                // Set Bounds
                questionLabel.setBounds(10, 50, 750, 20);
                RadioA.setBounds(50, 100, 300, 20);
                RadioB.setBounds(50, 120, 300, 20);
                RadioC.setBounds(50, 140, 300, 20);
                RadioD.setBounds(50, 160, 300, 20);
                RadioE.setBounds(50, 180, 300, 20);
                nextButton.setBounds(300, 300, 100, 30);
                previousButton.setBounds(200, 300, 100, 30);

                // Add components
                questionPanel.add(RadioA);
                questionPanel.add(RadioB);
                questionPanel.add(RadioC);
                questionPanel.add(RadioD);
                questionPanel.add(RadioE);
                questionPanel.add(questionLabel);
                questionPanel.add(nextButton);
                questionPanel.add(previousButton);

                setContentPane(questionPanel);
                questionPanel.setVisible(true);
                setVisible(true);
                previousButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        qNum--;
                        makeGUI();
                        
                    }
                    });
                makeGUI();
                qNum++;
                nextButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        char correctAnswer;
                        RadioA.setSelected(false);
                        RadioB.setSelected(false);
                        RadioC.setSelected(false);
                        RadioD.setSelected(false);
                        RadioE.setSelected(false);
                        boolean correct = false;
                        
                        if (qNum < 25) {
                            int answerNum = makeGUI();
                            qNum++;

                            if (answerNum <= 25) {
                                correctAnswer = isEasyCorrect(answerNum);
                                correct = isCorrect(correctAnswer);
                            } else if (answerNum > 25 && answerNum <= 50) {
                                answerNum = answerNum - 25;
                                correctAnswer = isMediumCorrect(answerNum);
                                correct = isCorrect(correctAnswer);
                            } else if (answerNum > 50 && answerNum <= 75) {
                                answerNum = answerNum - 50;
                                correctAnswer = isHardCorrect(answerNum);
                                correct = isCorrect(correctAnswer);
                            } else if (answerNum > 75 && answerNum <= 100) {
                                answerNum = answerNum - 75;
                                correctAnswer = isHardestCorrect(answerNum);
                                correct = isCorrect(correctAnswer);
                            }

                            if (correct) {
                                score++;
                            }

                            if (qNum == 26) {
                                nextButton.setText("Finish");
                            }
                        } else {
                            // show result screen
                            int newScore = score * 4;
                            tryNum++;

                            setTitle("Java Certification Results");
                            questionPanel.setVisible(false);
                            resultPanel.setLayout(null);
                            resultPanel.setSize(400, 400);
                            tryAgainButton.setVisible(false);

                            resultLabel.setBounds(10, 50, 750, 20);
                            tryAgainButton.setBounds(70, 120, 90, 20);
                            exitButton.setBounds(70, 120, 90, 20);

                            if (tryNum < 2 && newScore < 65) {
                                // allow user to try again
                                resultLabel.setText("You recieved a " + newScore + ". Try again? ");
                                tryAgainButton.setVisible(true);
                            } else if (tryNum < 2 && newScore >= 65) {
                                resultLabel.setText("You recieved a " + newScore + ". Congratulations!");
                                exitButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.exit(0);
                                    }
                                });
                            } else if (tryNum >= 2) {
                                resultLabel.setText("You recieved a " + newScore + ". All out of trys");
                                exitButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        System.exit(0);
                                    }
                                });
                            }
                            resultPanel.add(tryAgainButton);
                            resultPanel.add(exitButton);
                            resultPanel.add(resultLabel);
                            setContentPane(resultPanel);
                            resultPanel.setVisible(true);

                            tryAgainButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    resultPanel.setVisible(false);
                                    nextButton.setText("Finish");
                                    runTest();
                                }
                            });
                        }
                    }
                });

            }

        });
    }

    public int makeGUI() {
        // Declarations
        int randQuesNum = 0;
        int answerChoice = 0;
        int difficultyLevel = 1;
        int answerNum = 0;

        randQuesNum = randomQuestionNum();                          // Gets random number between 0-24
        difficultyLevel = randomDifficultyNum();
        answerChoice = randQuesNum * 5;
        switch (difficultyLevel) {                                    // gets questions from different difficulty levels depending on user's status
            case 1:
                questionLabel.setText(qNum + ". " + getEasyQuestion(randQuesNum) + " " + score + " " + qNum); // Sets the question label to corresponding text
                RadioA.setText(getEasyAnswers(answerChoice));
                RadioB.setText(getEasyAnswers(answerChoice + 1));
                RadioC.setText(getEasyAnswers(answerChoice + 2));
                RadioD.setText(getEasyAnswers(answerChoice + 3));
                RadioE.setText(getEasyAnswers(answerChoice + 4));
                break;
            case 2:
                questionLabel.setText(qNum + ". " + getMediumQuestion(randQuesNum) + " " + score + " " + qNum);
                RadioA.setText(getMediumAnswers(answerChoice));
                RadioB.setText(getMediumAnswers(answerChoice + 1));
                RadioC.setText(getMediumAnswers(answerChoice + 2));
                RadioD.setText(getMediumAnswers(answerChoice + 3));
                RadioE.setText(getMediumAnswers(answerChoice + 4));
                break;
            case 3:
                questionLabel.setText(qNum + ". " + getHardQuestion(randQuesNum) + " " + score + " " + qNum);
                RadioA.setText(getHardAnswers(answerChoice));
                RadioB.setText(getHardAnswers(answerChoice + 1));
                RadioC.setText(getHardAnswers(answerChoice + 2));
                RadioD.setText(getHardAnswers(answerChoice + 3));
                RadioE.setText(getHardAnswers(answerChoice + 4));
                break;
            case 4:
                questionLabel.setText(qNum + ". " + getHardestQuestion(randQuesNum) + " " + score + " " + qNum);
                RadioA.setText(getHardestAnswers(answerChoice));
                RadioB.setText(getHardestAnswers(answerChoice + 1));
                RadioC.setText(getHardestAnswers(answerChoice + 2));
                RadioD.setText(getHardestAnswers(answerChoice + 3));
                RadioE.setText(getHardestAnswers(answerChoice + 4));
                break;
        }

        return answerNum;
    }

    public String getEasyQuestion(int randomNum) {
        String question = "";
        ArrayList easy = new ArrayList();

        easy.add("How many bytes in an integer?");
        easy.add("The decrement operator lowers the value of the variable by?");
        easy.add("Which of the following statements will add 2 to the value of int x");
        easy.add("Which of the following statements prints 'hello world' to the console?");
        easy.add("What will this code print? int array[] = new int [5]; System.out.print(array);");
        easy.add("How many bytes in an array?");
        easy.add("which of the following best describes the definition of a 'nested loop'?");
        easy.add("What was the Java Programming Language initially called?");
        easy.add("What is the output of the following code: int x = 55, y = 2; int z = x + y");
        easy.add("What is the value of i after execution: int i; for (i = 0; i <= 10; i++);");                 // question 10

        easy.add("What is the value of x after execution: char x = 65;");
        easy.add("What will this line of code do to variable x: String result = Integer.toString(x, 8); ");
        easy.add("What will this line of code do to variable x: String result = Integer.toString(x, 6); ");
        easy.add("Identify the type of error: int x = 4");
        easy.add("Which of the following code type is used for characters in Java?");
        easy.add("Which of the following is the proper method for declaring and initializing a boolean?");
        easy.add("What is the output: int x = 2; System.out.print(--x* 8);");
        easy.add("What is the output: int x = 2; System.out.print(++x* 8);");
        easy.add("What operator is logical or in Java?");
        easy.add("What operator is bitwise and in Java");                            // question 20

        easy.add("What is the value of x after execution: int x = Math.pow(8, 2)");
        easy.add("What is the value of x after execustion: int x = 3.2)");
        easy.add("Who is considered to be the founder of the Java Programming Language?");
        easy.add("What year was Java first released?");
        easy.add("What is the value of x after execution: int x = 4; x++;");

        question = (String) easy.get(randomNum);
        return question;
    }

    public String getMediumQuestion(int randomNum) {
        String question = "";
        ArrayList medium = new ArrayList();

        medium.add("Which of these keywords is used to make a class?");
        medium.add("Which of these operators is used to allocate memory for an object?");
        medium.add("What will happen if you do not define a constructor for a class?");
        medium.add("Which method can be defined only once in a program?");
        medium.add("Which keyword is used to reference the object that invoked it?");
        medium.add("Which of these statements will create a class called Dog?");
        medium.add("Which ones are allowed to be overloaded?");
        medium.add("What is it called when a method calls itself?");
        medium.add("Which of these keywords are used to refer to member of the base-class from a sub-class?");
        medium.add("Which of these methods of the String class can extract a single character from a String object?");       //question 10

        medium.add("What is the correct statement to declare an empty destructor in class Dog? ");
        medium.add("Which method is used to compare two Strings for their equality?");
        medium.add("Which of these access specifiers allows members to be accessed by a different class in another package?");
        medium.add("What keyword is used to import a package?");
        medium.add("What does the method setCharAt() do?");
        medium.add("What does the following code perform: import java.util.Random;");
        medium.add("Which method will make a character lowercase");
        medium.add("What is the output: char x = 'a'; x.toUpperCase(); System.out.print(x);");
        medium.add("Which of these methods can create a duplicate copy of the object in which it is called?");
        medium.add("What is the output of the following code: int i = 010; int j = 07; System.out.println(i); System.out.println(j);");                                                                                                      //question 20

        medium.add("Which of these method is a rounding function of Math class?");
        medium.add("Which of these classes is used for input and output operation when working with bytes?");
        medium.add("What is the output of thise code: InputStream x = new FileInputStream(\"inputoutput.java\"); System.out.print(x.available());");
        medium.add("Which of these method of FileReader class is used to read characters from a file?");
        medium.add("Which of these is a method to clear all the data present in output buffers?");

        question = (String) medium.get(randomNum);
        return question;
    }

    public String getHardQuestion(int randomNum) {
        String question = "";
        ArrayList hard = new ArrayList();
        hard.add("Which of these is a process of writing the state of an object to a byte stream?");
        hard.add("Which of these interface extends DataOutput interface?");
        hard.add("What is the output of this program: InetAddress obj1 = InetAddress.getByName(\"amazon.com\"); System.out.print(obj1.getHostName());");
        hard.add("Which of the following packages is nessicary for networking?");
        hard.add("Which of these is the protocol for sending packets across a network?");
        hard.add("How many bits are in an IP Address?");
        hard.add("Which of these interface abstractes the output of messages from httpd?");
        hard.add("Which of these methods is used to make raw MIME formatted string?");
        hard.add("What is the output of the code: URL obj = new URL(\"http://www.amazon.com\"); System.out.print(obj.toExternalForm());");
        hard.add("What is the output: ArrayList obj = new ArrayList(); obj.add(\"A\"); obj.add(\"B\"); obj.add(\"C\"); obj.add(1, \"D\"); System.out.println(obj);");   //question 10

        hard.add("What is the output: ArrayList obj = new ArrayList(); obj.add(\"A\"); obj.add(0, \"B\"); System.out.println(obj.size());");
        hard.add("What is the output:  ArrayList obj = new ArrayList(); obj.add(\"A\"); obj.ensureCapacity(3); System.out.println(obj.size());");
        hard.add("Which of these class is related to all the exceptions that are explicitly thrown?");
        hard.add("Which of these handles the exception when no catch is used?");
        hard.add("Which of these handles is used to monitor for exceptions?");
        hard.add("What does AWT stands for?");
        hard.add("What is the output: StringBuffer c = new StringBuffer(\"Program\"); System.out.println(c.length());");
        hard.add("What is the output: StringBuffer s1 = new StringBuffer(\"Hello\"); StringBuffer s2 = s1.reverse(); System.out.println(s2);");
        hard.add("What is the default value of priority variable MIN_PRIORITY");
        hard.add("What is the default value of priority variable MAX_PRIORITY");               // question 20;

        hard.add("Which of these method waits for the thread to treminate?");
        hard.add("Which of these method is used to explicitly set the priority of a thread?");
        hard.add("Which of these method is used to implement Runnable interface?");
        hard.add("Which of these method wakes up all the threads?");
        hard.add("Which of these packages contain all the Java’s built in exceptions?");

        question = (String) hard.get(randomNum);
        return question;
    }

    public String getHardestQuestion(int randomNum) {
        String question = "";
        ArrayList hardest = new ArrayList();

        hardest.add("Which of the following constant are defined in Boolean wrapper?");
        hardest.add("Which of these methods return string equivalent of Boolean object?");
        hardest.add("Which of the following methods return the value as a double?");
        hardest.add ("What is the output: Double i = new Double(47.5); boolean x = i.isNaN(); System.out.print(x);");
        hardest.add("What is the output: Integer i = new Integer(39); byte x = i.byteValue(); System.out.print(x);");
        hardest.add("What is the output: Double i = new Double(20.578123456789); float x = i.floatValue(); System.out.print(x);");
        hardest.add("What is the output: double x = 3.14; int y = (int) Math.toDegrees(x); System.out.print(y);");
        hardest.add("double x = 3.14; int y = (int) Math.toRadians(x); System.out.print(y);");
        hardest.add("Which of these keywords are used to define an abstract class?");
        hardest.add("Which of these is the wildcard symbol?");
        
        hardest.add("What is the output: Integer i = new Integer(39); byte x = i.byteValue(); System.out.print(x);");
        hardest.add("What is the output: Double i = new Double(20.578123456789); float x = i.floatValue(); System.out.print(x);");
        hardest.add("Which of these methods return string equivalent of Boolean object?");
        hardest.add("Which of the following methods return the value as a double?");
        hardest.add ("What is the output: Double i = new Double(47.5); boolean x = i.isNaN(); System.out.print(x);");
        hardest.add("What is the output: double x = 3.14; int y = (int) Math.toDegrees(x); System.out.print(y);");
        hardest.add("double x = 3.14; int y = (int) Math.toRadians(x); System.out.print(y);");
        hardest.add("Which of the following constant are defined in Boolean wrapper?");
        hardest.add("Which of these methods return string equivalent of Boolean object?");
        hardest.add("Which of the following methods return the value as a double?");
        
        hardest.add("Which of these keywords are used to define an abstract class?");
        hardest.add("Which of these is the wildcard symbol?");
        hardest.add("Which of these methods return string equivalent of Boolean object?");
        hardest.add("Which of the following methods return the value as a double?");
        hardest.add ("What is the output: Double i = new Double(47.5); boolean x = i.isNaN(); System.out.print(x);");
        
        question = (String) hardest.get(randomNum);
        return question;
    }

    public String getEasyAnswers(int randomNum) {
        String question = "";
        ArrayList easy = new ArrayList();

        // 1
        easy.add("A. 1");
        easy.add("B. 2");
        easy.add("C. 4");
        easy.add("D. 8");
        easy.add("E. None of the Above");

        // 2
        easy.add("A. 1");
        easy.add("B. 2");
        easy.add("C. 8");
        easy.add("D. 10");
        easy.add("E. The value of the variable");

        //3 
        easy.add("A. x + 2");
        easy.add("B. x = x + 2");
        easy.add("C. x++++");
        easy.add("D. x+++");
        easy.add("E. None of the Above");

        //4 
        easy.add("A. system.out.print(\"hello world \");");
        easy.add("B. System.out.print(hello world);");
        easy.add("C. System.out.print(\"hello world \");");
        easy.add("D. System.out.println(\"hello world \");");
        easy.add("E. None of the Above");

        //5 
        easy.add("A. 0");
        easy.add("B. 12345");
        easy.add("C. 01234");
        easy.add("D. pointer to location of array in memory");
        easy.add("E. None of the Above");

        //6
        easy.add("A. 4");
        easy.add("B. 32");
        easy.add("C. 256");
        easy.add("D. 512");
        easy.add("E. None of the Above");

        //7
        easy.add("A. A loop that calls a method with a loop inside of it");
        easy.add("B. a loop that runs inside of a loop");
        easy.add("C. a loop that terminates the program");
        easy.add("D. a loop that calls the main function");
        easy.add("E. a loop in which birds lay eggs");

        //8
        easy.add("A. Oracle");
        easy.add("B. Tree");
        easy.add("C. Oak");
        easy.add("D. Leaf");
        easy.add("E. Java");

        //9
        easy.add("A. No Output");
        easy.add("B. 57");
        easy.add("C. Syntax Error");
        easy.add("D. Runtime Error");
        easy.add("E. None of the Above");

        //10
        easy.add("A. 9");
        easy.add("B. 0");
        easy.add("C. 10");
        easy.add("D. 11");
        easy.add("E. None of the Above");

        //11
        easy.add("A. A");
        easy.add("B. (Space)");
        easy.add("C. a");
        easy.add("D. B");
        easy.add("E. None of the Above");

        //12
        easy.add("A. Convert x to a String as a hex");
        easy.add("B. Convert x to a String as an octal");
        easy.add("C. Convert x to a a String");
        easy.add("D. Point to x's memory location and convert to string");
        easy.add("E. None of the Above");

        //13
        easy.add("A. Convert x to a String as a hex");
        easy.add("B. Convert x to a String as an octal");
        easy.add("C. Convert x to a a String");
        easy.add("D. Point to x's memory location and convert to string");
        easy.add("E. None of the Above");

        //14
        easy.add("A. Syntax Error");
        easy.add("B. Logical Error");
        easy.add("C. Runtime Error");
        easy.add("D. No Error");
        easy.add("E. None of the Above");

        //15
        easy.add("A. ISO-LATIN-1");
        easy.add("B. ASCII");
        easy.add("C. EBCDIC");
        easy.add("D. UNICODE");
        easy.add("E. None of the Above");

        //16
        easy.add("A. boolean doesExist = 0");
        easy.add("B. bool doesEsit = 0");
        easy.add("C. boolean doesExist = 'false'");
        easy.add("D. boolean doesExist = false");
        easy.add("E. None of the Above");

        //17
        easy.add("A. 8");
        easy.add("B. 16");
        easy.add("C. 24");
        easy.add("D. 32");
        easy.add("E. None of the Above");

        //18
        easy.add("A. 8");
        easy.add("B. 16");
        easy.add("C. 24");
        easy.add("D. 32");
        easy.add("E. None of the Above");

        //19
        easy.add("A. &&");
        easy.add("B. ||");
        easy.add("C. |");
        easy.add("D. ~");
        easy.add("E. None of the Above");

        //20
        easy.add("A. &&");
        easy.add("B. &");
        easy.add("C. |");
        easy.add("D. ~");
        easy.add("E. None of the Above");

        //21
        easy.add("A. 10");
        easy.add("B. 16");
        easy.add("C. 32");
        easy.add("D. 64");
        easy.add("E. None of the Above");

        //22
        easy.add("A. Syntax Error");
        easy.add("B. Logical Error");
        easy.add("C. Runtime Error");
        easy.add("D. No Error");
        easy.add("E. None of the Above");

        //23 
        easy.add("A. Bill Gates");
        easy.add("B. James Gosling");
        easy.add("C. Guido van Rossum");
        easy.add("D. Dennis Ritchie");
        easy.add("E. None of the Above");

        //24
        easy.add("A. 1993");
        easy.add("B. 1988");
        easy.add("C. 1982");
        easy.add("D. 1995");
        easy.add("E. None of the Above");

        //25
        easy.add("A. 3");
        easy.add("B. 4");
        easy.add("C. 5");
        easy.add("D. 0");
        easy.add("E. None of the Above");

        question = (String) easy.get(randomNum);
        return question;
    }

    public String getMediumAnswers(int randomNum) {
        String question = "";
        ArrayList medium = new ArrayList();

        //1
        medium.add("A. struct");
        medium.add("B. class");
        medium.add("C. Class");
        medium.add("D. function");
        medium.add("e. None of the above");

        //2
        medium.add("A. malloc");
        medium.add("B. memalloc");
        medium.add("C. ()");
        medium.add("D. new");
        medium.add("e. None of the above");

        //3
        medium.add("A. The IDE will give you a syntax error");
        medium.add("B. Your program will eventually encounter a runtime error and crash");
        medium.add("C. No issue; you are not explicitly required to define one if you don't need one");
        medium.add("D. The IDE will automatically write a blank one when you compile your code");
        medium.add("e. None of the above");

        //4
        medium.add("A. final method");
        medium.add("B. static method");
        medium.add("C. main method");
        medium.add("D. private method");
        medium.add("e. None of the above");

        //5
        medium.add("A. try");
        medium.add("B. this");
        medium.add("C. private");
        medium.add("D. catch");
        medium.add("e. None of the above");

        //6
        medium.add("A. class Dog");
        medium.add("B. Class Dog");
        medium.add("C. public Dog()");
        medium.add("D. public Dog");
        medium.add("e. None of the above");

        //7
        medium.add("A. Constructors");
        medium.add("B. Functions");
        medium.add("C. Classes");
        medium.add("D. Main Methods");
        medium.add("E. None of the above");

        //8
        medium.add("A. Abstraction");
        medium.add("B. Encapsulation");
        medium.add("C. Recursion");
        medium.add("D. Self-Referencing");
        medium.add("E. None of the above");

        //9
        medium.add("A. super");
        medium.add("B. extends");
        medium.add("C. inherits");
        medium.add("D. implements");
        medium.add("E. None of the above");

        //10
        medium.add("A. getChar()");
        medium.add("B. charAt()");
        medium.add("C. CharAt()");
        medium.add("D. toChar(index)");
        medium.add("E. None of the above");

        //11
        medium.add("A. Dog()~");
        medium.add("B. ~Dog()");
        medium.add("C. -Dog()");
        medium.add("D. _Dog()");
        medium.add("E. None of the above");

        //12
        medium.add("A. isEqual()");
        medium.add("B. equalTo()");
        medium.add("C. isEqual()");
        medium.add("D. compare()");
        medium.add("E. None of the above");

        //13
        medium.add("A. Private");
        medium.add("B. global");
        medium.add("C. Protected");
        medium.add("D. Public");
        medium.add("E. None of the above");

        //14
        medium.add("A. import");
        medium.add("B. extends");
        medium.add("C. #include");
        medium.add("D. using");
        medium.add("E. None of the above");

        //15
        medium.add("A. setChar()");
        medium.add("B. setCharAt()");
        medium.add("C. modifyChar()");
        medium.add("D. char()");
        medium.add("E. None of the above");

        //16
        medium.add("A. Imports the utility class");
        medium.add("B. Imports the Random class");
        medium.add("C. calls the Random class ");
        medium.add("D. defines the Random class");
        medium.add("E. None of the above");

        //17
        medium.add("A. tolowercase()");
        medium.add("B. toLowerCase()");
        medium.add("C. toUpperCase()");
        medium.add("D. isLowerCase()");
        medium.add("E. None of the above");

        //18
        medium.add("A. A");
        medium.add("B. a");
        medium.add("C. B");
        medium.add("D. 0");
        medium.add("E. None of the above");

        //19
        medium.add("A. copy()");
        medium.add("B. clone()");
        medium.add("C. transfer()");
        medium.add("D. duplicate()");
        medium.add("E. None of the above");

        //20
        medium.add("A. 9 7");
        medium.add("B. 10 7");
        medium.add("C. 8 7");
        medium.add("D. Runtime Error");
        medium.add("E. None of the above");

        //21
        medium.add("A. roundUp()");
        medium.add("B. roundDown()");
        medium.add("C. abs()");
        medium.add("D. All of the above");
        medium.add("E. None of the above");

        //22
        medium.add("A. Readr)");
        medium.add("B. OutputStream");
        medium.add("C. File");
        medium.add("D. InputStreamReader");
        medium.add("E. None of the above");

        //23
        medium.add("A. Prints number of bytes to console");
        medium.add("B. Prints number of characters to console");
        medium.add("C. Prints number of bytes to console");
        medium.add("D. Prints number of characters to console");
        medium.add("E. None of the above");

        //24
        medium.add("A. scan())");
        medium.add("B. scanf()");
        medium.add("C. InputStream()");
        medium.add("D. get()");
        medium.add("E. None of the above");

        //25
        medium.add("A. flush()");
        medium.add("B. clear()");
        medium.add("C. CLS()");
        medium.add("D. System.Clear()");
        medium.add("E. None of the above");

        question = (String) medium.get(randomNum);
        return question;
    }

    public String getHardAnswers(int randomNum) {
        String question = "";
        ArrayList hard = new ArrayList();
        // 1
        hard.add("A. Byte streaming");
        hard.add("B. Bit manipulation");
        hard.add("C. Serialization");
        hard.add("D. Byte Filtering");
        hard.add("E. None of the above");

        //2
        hard.add("A. Serializable");
        hard.add("B. ObjectOutput");
        hard.add("C. StreamOutput");
        hard.add("D. FileOutput");
        hard.add("E. None of the above");

        //3
        hard.add("A. amazon");
        hard.add("B. http://www.amazon.com");
        hard.add("C. amazon.com");
        hard.add("D. www.amazon.com");
        hard.add("E. None of the above");

        //4
        hard.add("A. java.io.net");
        hard.add("B. java.util.net");
        hard.add("C. java.network");
        hard.add("D. java.net");
        hard.add("E. None of the above");

        //5
        hard.add("A. TCIP/IP");
        hard.add("B. http");
        hard.add("C. Socket");
        hard.add("D. ServerSocket");
        hard.add("E. None of the above");

        //6
        hard.add("A. 1");
        hard.add("B. 8");
        hard.add("C. 16");
        hard.add("D. 32");
        hard.add("E. 64");

        //7
        hard.add("A. LogMessage");
        hard.add("B. LogResonse");
        hard.add("C. Httpdserver");
        hard.add("D. httpResponse");
        hard.add("E. None of the above");

        //8
        hard.add("A. parse()");
        hard.add("B. toString()");
        hard.add("C. getString()");
        hard.add("D. parseString()");
        hard.add("E. None of the above");

        //9
        hard.add("A. amazon");
        hard.add("B. amazon.com()");
        hard.add("C. www.amazon.com");
        hard.add("D. http://amazon.com");
        hard.add("E. None of the above");

        //10
        hard.add("A. [A, B, C, D]");
        hard.add("B. [A, D, B, C]");
        hard.add("C. [A, D, C]");
        hard.add("D. [[A, B, C]");
        hard.add("E. None of the above");

        //11
        hard.add("A. 2");
        hard.add("B. 1");
        hard.add("C. 0");
        hard.add("D. 4");
        hard.add("E. None of the above");

        //12
        hard.add("A. 1");
        hard.add("B. 2");
        hard.add("C. 3");
        hard.add("D. 4");
        hard.add("E. None of the above");

        //13
        hard.add("A. ex");
        hard.add("B. Exception");
        hard.add("C. Error");
        hard.add("D. Throwable");
        hard.add("E. None of the above");

        //14
        hard.add("A. finally");
        hard.add("B. Default handler");
        hard.add("C. Event handler");
        hard.add("D. throw handler");
        hard.add("E. None of the above");

        //15
        hard.add("A. finally");
        hard.add("B. throw");
        hard.add("C. catch");
        hard.add("D. try");
        hard.add("E. None of the above");

        //16
        hard.add("A. A lot of Windows Tools");
        hard.add("B. Abstract Window Toolkit");
        hard.add("C. Abstract Writing Tools");
        hard.add("D. Abstract Writing Toolkit");
        hard.add("E. None of the above");

        //17
        hard.add("A. 5");
        hard.add("B. 6");
        hard.add("C. 7");
        hard.add("D. 8");
        hard.add("E. None of the above");

        //18
        hard.add("A. olleH");
        hard.add("B. Hello");
        hard.add("C. HellooelleH");
        hard.add("D. olleHHello");
        hard.add("E. None of the above");

        //19
        hard.add("A. 0");
        hard.add("B. 1");
        hard.add("C. 10");
        hard.add("D. 256");
        hard.add("E. None of the above");

        //20
        hard.add("A. 0");
        hard.add("B. 1");
        hard.add("C. 10");
        hard.add("D. 256");
        hard.add("E. None of the above");

        //21
        hard.add("A. sleep()");
        hard.add("B. isAlive()");
        hard.add("C. join()");
        hard.add("D. stop()");
        hard.add("E. None of the above");

        //22
        hard.add("A. set()");
        hard.add("B. make()");
        hard.add("C. setPriority()");
        hard.add("D. makePriority()");
        hard.add("E. None of the above");

        //23
        hard.add("A. run()");
        hard.add("B. stop()");
        hard.add("C. runThread()");
        hard.add("D. pauseThread()");
        hard.add("E. None of the above");

        //24
        hard.add("A. wakeAll()");
        hard.add("B. notify");
        hard.add("C. start()");
        hard.add("D. notifyAll()");
        hard.add("E. None of the above");

        //25
        hard.add("A. java.io");
        hard.add("B. java.lang");
        hard.add("C. java.util");
        hard.add("D. java.net");
        hard.add("E. None of the above");

        question = (String) hard.get(randomNum);
        return question;
    }

    public String getHardestAnswers(int randomNum) {
        String question = "";
        ArrayList hardest = new ArrayList();

        //1
        hardest.add("A. TRUE");
        hardest.add("B. True");
        hardest.add("C. true");
        hardest.add("D. BOOL");
        hardest.add("E. None of the above");

        //2
        hardest.add("A. toString(0");
        hardest.add("B. getString()");
        hardest.add("C. convertString()");
        hardest.add("D. getStringObject()");
        hardest.add("E. None of the above");
        
        //3
        hardest.add("A. doubleValue()");
        hardest.add("B. returnDouble()");
        hardest.add("C. getDouble()");
        hardest.add("D. doubleReturn()");
        hardest.add("E. None of the above");
        
        //4
        hardest.add("A. 1");
        hardest.add("B. 0");
        hardest.add("C. true");
        hardest.add("D. false");
        hardest.add("E. None of the above");
        
        // 5
        hardest.add("A. 0");
        hardest.add("B. 1");
        hardest.add("C. 38");
        hardest.add("D. 40");
        hardest.add("E. None of the above");
        
        // 6
        hardest.add("A. 0");
        hardest.add("B. 20");
        hardest.add("C. 20.57812");
        hardest.add("D. 20.578123456789");
        hardest.add("E. None of the above");
        
        // 7
        hardest.add("A. 0");
        hardest.add("B. 179");
        hardest.add("C. 180");
        hardest.add("D. 270");
        hardest.add("E. None of the above");
        
        // 8
        hardest.add("A. 0");
        hardest.add("B. 3");
        hardest.add("C. 3.1");
        hardest.add("D. 2.9");
        hardest.add("E. None of the above");
        
        // 9
        hardest.add("A. abstract");
        hardest.add("B. abstracts");
        hardest.add("C. Abstract");
        hardest.add("D. abstracted");
        hardest.add("E. None of the above");
        
        // 10
        hardest.add("A. !");
        hardest.add("B. &");
        hardest.add("C. ^");
        hardest.add("D. ?");
        hardest.add("E. None of the above");
        
        
        // 11
        hardest.add("A. TRUE");
        hardest.add("B. True");
        hardest.add("C. true");
        hardest.add("D. BOOL");
        hardest.add("E. None of the above");

        // 12
        hardest.add("A. toString(0");
        hardest.add("B. getString()");
        hardest.add("C. convertString()");
        hardest.add("D. getStringObject()");
        hardest.add("E. None of the above");
        
        // 13
        hardest.add("A. doubleValue()");
        hardest.add("B. returnDouble()");
        hardest.add("C. getDouble()");
        hardest.add("D. doubleReturn()");
        hardest.add("E. None of the above");
        
        // 14
        hardest.add("A. 1");
        hardest.add("B. 0");
        hardest.add("C. true");
        hardest.add("D. false");
        hardest.add("E. None of the above");
        
        // 15
        hardest.add("A. 0");
        hardest.add("B. 1");
        hardest.add("C. 38");
        hardest.add("D. 40");
        hardest.add("E. None of the above");
        
        // 16
        hardest.add("A. 0");
        hardest.add("B. 20");
        hardest.add("C. 20.57812");
        hardest.add("D. 20.578123456789");
        hardest.add("E. None of the above");
        
        // 17
        hardest.add("A. 0");
        hardest.add("B. 179");
        hardest.add("C. 180");
        hardest.add("D. 270");
        hardest.add("E. None of the above");
        
        // 18
        hardest.add("A. 0");
        hardest.add("B. 3");
        hardest.add("C. 3.1");
        hardest.add("D. 2.9");
        hardest.add("E. None of the above");
        
        // 19
        hardest.add("A. abstract");
        hardest.add("B. abstracts");
        hardest.add("C. Abstract");
        hardest.add("D. abstracted");
        hardest.add("E. None of the above");
        
        //20
        hardest.add("A. !");
        hardest.add("B. &");
        hardest.add("C. ^");
        hardest.add("D. ?");
        hardest.add("E. None of the above");
        
         // 17
        hardest.add("A. 0");
        hardest.add("B. 179");
        hardest.add("C. 180");
        hardest.add("D. 270");
        hardest.add("E. None of the above");
        
        // 18
        hardest.add("A. 0");
        hardest.add("B. 3");
        hardest.add("C. 3.1");
        hardest.add("D. 2.9");
        hardest.add("E. None of the above");
        
         // 17
        hardest.add("A. 0");
        hardest.add("B. 14.33");
        hardest.add("C. 57.322");
        hardest.add("D. 82.72");
        hardest.add("E. None of the above");
        
        // 18
        hardest.add("A. 10");
        hardest.add("B. 10.453");
        hardest.add("C. 10.453988");
        hardest.add("D. 11");
        hardest.add("E. None of the above");
        
         // 25
        hardest.add("A. 34");
        hardest.add("B. 35");
        hardest.add("C. 36");
        hardest.add("D. 37");
        hardest.add("E. None of the above");
        
        question = (String) hardest.get(randomNum);
        return question;
    }

    public char isEasyCorrect(int answerNum) {

        ArrayList easy = new ArrayList();
        easy.add('C');
        easy.add('A');
        easy.add('B');
        easy.add('C');
        easy.add('D');
        easy.add('E');
        easy.add('B');
        easy.add('C');
        easy.add('A');
        easy.add('D');  // question 10

        easy.add('A');
        easy.add('B');
        easy.add('A');
        easy.add('A');
        easy.add('D');
        easy.add('D');
        easy.add('A');
        easy.add('C');
        easy.add('B');
        easy.add('B');     // question 20

        easy.add('D');
        easy.add('B');
        easy.add('B');
        easy.add('D');
        easy.add('C');

        char correctAnswer = (char) easy.get(answerNum);
        return correctAnswer;

    }

    public char isMediumCorrect(int answerNum) {
        ArrayList medium = new ArrayList();

        medium.add('B');
        medium.add('D');
        medium.add('D');
        medium.add('C');
        medium.add('B');
        medium.add('A');
        medium.add('C');
        medium.add('C');
        medium.add('A');
        medium.add('B');           // question 10

        medium.add('B');
        medium.add('E');
        medium.add('D');
        medium.add('A');
        medium.add('B');
        medium.add('B');
        medium.add('B');
        medium.add('A');
        medium.add('B');
        medium.add('C');           // question 20

        medium.add('C');
        medium.add('B');
        medium.add('C');
        medium.add('E');
        medium.add('A');

        char correctAnswer = (char) medium.get(answerNum);
        return correctAnswer;
    }

    public char isHardCorrect(int answerNum) {
        ArrayList hard = new ArrayList();
        hard.add('C');
        hard.add('B');
        hard.add('C');
        hard.add('D');
        hard.add('A');
        hard.add('C');
        hard.add('A');
        hard.add("A");
        hard.add('D');
        hard.add('B');        // question 10

        hard.add('A');
        hard.add('A');
        hard.add('D');
        hard.add('B');
        hard.add('D');
        hard.add('B');
        hard.add('C');
        hard.add('A');
        hard.add('B');
        hard.add('C');       // question 20  

        hard.add('C');
        hard.add('C');
        hard.add('A');
        hard.add('D');
        hard.add('B');

        char correctAnswer = (char) hard.get(answerNum);
        return correctAnswer;
    }

    public char isHardestCorrect(int answerNum) {
        ArrayList hardest = new ArrayList();
        hardest.add('A');
        hardest.add('A');
        hardest.add('A');
        hardest.add('D');
        hardest.add('B');
        hardest.add('C');
        hardest.add('B');
        hardest.add('B');
        hardest.add('A');
        hardest.add('D');
        
        hardest.add('C');
        hardest.add('B');
        hardest.add('B');
        hardest.add('A');
        hardest.add('A');
        hardest.add('A');
        hardest.add('D');
        hardest.add('B');
        hardest.add('A');
        hardest.add('D');
        
        
        hardest.add('C');
        hardest.add('B');
        hardest.add('B');
        hardest.add('A');
        hardest.add('D');

        char correctAnswer = (char) hardest.get(answerNum);
        return correctAnswer;
    }

    public boolean isCorrect(char correctAnswer) {

        boolean isCorrect = false;

        if (correctAnswer == 'A' && RadioA.isSelected()) {
            isCorrect = true;
        } else if (correctAnswer == 'B' && RadioB.isSelected()) {
            isCorrect = true;
        } else if (correctAnswer == 'C' && RadioC.isSelected()) {
            isCorrect = true;
        } else if (correctAnswer == 'D' && RadioD.isSelected()) {
            isCorrect = true;
        } else if (correctAnswer == 'E' && RadioE.isSelected()) {
            isCorrect = true;
        }
        return isCorrect;
    }

    public int randomQuestionNum() {
        Random rand = new Random();
        int randomNum = rand.nextInt((24 - 0) + 1) + 0;
        return randomNum;
    }

    public int randomDifficultyNum() {
        Random rand = new Random();
        int randomNum = rand.nextInt((4 - 1) + 1) + 1;
        return randomNum;
    }

    public static void main(String[] args) {
        new JavaCertificationExam();

    }

}
