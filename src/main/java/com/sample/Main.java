package com.sample;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TriviaGame game = new TriviaGame();
        Scanner input = new Scanner(System.in);
        boolean gameOver = false;
        int games = 0;

        while (!gameOver) {
            game.createQuestionFromFact();
            System.out.println(game.getLastQuestion());
            System.out.println("Select your answer: ");
            game.printAnswersForSelection();
            int answer = input.nextInt();
            while (!game.getNumbers().contains(answer)) {
                System.out.println("Invalid input. Try again.");
                answer = input.nextInt();
            }
            if (answer != game.getCorrectAnswer()) {
                System.out.println("Incorrect answer! Game Over! Total correct answers: " + games);
                System.out.println("Last question: " + game.getLastQuestion()
                        + "\nAnd correct answer is: " + game.getCorrectAnswer());
                gameOver = true;
            } else {
                System.out.println("Correct!");
                games++;
            }
            if (games == 20) {
                gameOver = true;
                System.out.println("You won! Total correct answers: " + games);
            }
        }
        input.close();
    }
}