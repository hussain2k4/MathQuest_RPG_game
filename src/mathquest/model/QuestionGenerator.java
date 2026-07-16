package mathquest.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generates math questions based on the game's current level.
 */
public class QuestionGenerator {
    private Random random;

    public QuestionGenerator() {
        this.random = new Random();
    }

    /**
     * Inner class representing a single math question.
     * Encapsulates the question text, the correct answer, and the 4 choices.
     */
    public static class MathQuestion {
        private String questionText;
        private int correctAnswer;
        private int[] options;

        public MathQuestion(String questionText, int correctAnswer, int[] options) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.options = options;
        }

        public String getQuestionText() { return questionText; }
        public int getCorrectAnswer() { return correctAnswer; }
        public int[] getOptions() { return options; }
    }

    /**
     * Generates a question based on the difficulty level.
     * @param level 1 (Easy), 2 (Medium), 3 (Hard)
     * @return A MathQuestion object
     */
    public MathQuestion generateQuestion(int level) {
        int num1, num2, correctAnswer;
        String operator;

        // Determine operation based on level
        int operationType = getOperationTypeForLevel(level);

        switch (operationType) {
            case 1: // Subtraction (Ensuring positive results for kids)
                num1 = random.nextInt(15) + 6; // 6 to 20
                num2 = random.nextInt(num1) + 1; // 1 to num1
                operator = "-";
                correctAnswer = num1 - num2;
                break;
            case 2: // Multiplication
                num1 = random.nextInt(10) + 1; // 1 to 10
                num2 = random.nextInt(10) + 1; // 1 to 10
                operator = "*";
                correctAnswer = num1 * num2;
                break;
            case 3: // Division (Clean division without remainders)
                num2 = random.nextInt(9) + 2; // 2 to 10 (Divisor)
                correctAnswer = random.nextInt(10) + 1; // 1 to 10 (Result)
                num1 = num2 * correctAnswer; // Dividend
                operator = "/";
                break;
            default: // 0 -> Addition
                num1 = random.nextInt(20) + 1;
                num2 = random.nextInt(20) + 1;
                operator = "+";
                correctAnswer = num1 + num2;
                break;
        }

        String questionText = num1 + " " + operator + " " + num2 + " = ?";
        int[] options = generateOptions(correctAnswer);

        return new MathQuestion(questionText, correctAnswer, options);
    }

    /**
     * Decides which mathematical operation to use based on the level.
     */
    private int getOperationTypeForLevel(int level) {
        if (level == 1) {
            // Level 1: Only + (0) and - (1)
            return random.nextInt(2);
        } else if (level == 2) {
            // Level 2: Only * (2) and / (3)
            return random.nextInt(2) + 2;
        } else {
            // Level 3: All operations (0, 1, 2, 3)
            return random.nextInt(4);
        }
    }

    /**
     * Generates 4 multiple choice options, including the correct answer.
     */
    private int[] generateOptions(int correctAnswer) {
        List<Integer> optionList = new ArrayList<>();
        optionList.add(correctAnswer);

        // Generate 3 unique distractors (wrong answers) close to the correct answer
        while (optionList.size() < 4) {
            int offset = random.nextInt(11) - 5; // Generates a number between -5 and +5
            int wrongAnswer = correctAnswer + offset;
            
            // Prevent duplicate options and negative answers (since it's for kids)
            if (wrongAnswer >= 0 && !optionList.contains(wrongAnswer)) {
                optionList.add(wrongAnswer);
            }
        }

        // Shuffle the options so the correct answer isn't always first
        Collections.shuffle(optionList);

        int[] optionsArray = new int[4];
        for (int i = 0; i < 4; i++) {
            optionsArray[i] = optionList.get(i);
        }
        return optionsArray;
    }
}