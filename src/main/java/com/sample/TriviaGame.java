package com.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TriviaGame {
    private List<String> factsList = new ArrayList<>();
    private String[] question = new String[2];
    private List<Integer> numbers = new ArrayList<>();
    private int correctAnswer;
    private String lastQuestion;
    private TriviaApiService triviaApiService;

    public TriviaGame() {
        this.triviaApiService = new TriviaApiService();
    }

    protected List<String> temporaryListWithFacts() {
        try {
            String fact;
            do {
                fact = triviaApiService.callApi();
            }
            while (factsList == null || factsList.contains(fact));
            factsList.add(fact);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return factsList;
    }

    protected void createQuestionFromFact() {
        try {
            StringBuilder temp = new StringBuilder(temporaryListWithFacts().getLast());
            correctAnswer = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
            lastQuestion = "What" + temp.substring(temp.indexOf(" "), temp.length() - 1) + "?";
            question[0] = String.valueOf(correctAnswer);
            question[1] = lastQuestion;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    protected List<Integer> createFakeAnswersForSelection() {
        numbers.clear();
        numbers.add(correctAnswer);
        Random random = new Random();
        while (numbers.size() < 4) {
            Integer answer = random.nextInt(correctAnswer - 10, correctAnswer + 10);
            if (!numbers.contains(answer)) {
                numbers.add(answer);
            }
        }
        Collections.shuffle(numbers);
        return numbers;
    }

    public void printAnswersForSelection() {
        numbers = createFakeAnswersForSelection();
        for (int i = 1; i <= numbers.size(); i++) {
            System.out.println(i + ": " + numbers.get(i - 1));
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }


    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public String getLastQuestion() {
        return lastQuestion;
    }
}
