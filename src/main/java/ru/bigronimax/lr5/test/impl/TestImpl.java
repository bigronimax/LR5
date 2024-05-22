package ru.bigronimax.lr5.test.impl;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.bigronimax.lr5.question.Question;
import ru.bigronimax.lr5.test.Test;

import java.util.HashMap;
import java.util.Scanner;

@RequiredArgsConstructor
@Component
public class TestImpl implements Test {

    @Value("AAA")
    private String name;
    @Value("BBB")
    private String surname;
    Scanner scanner = new Scanner(System.in);
    private final HashMap<Question, Boolean> questions;


    @Override
    public void introduce() {
        scanner = new Scanner(System.in);
        System.out.println("What is your name?");
        name = scanner.nextLine();
        System.out.println("What is your surname?");
        surname = scanner.nextLine();
    }

    @Override
    public void answer() {
        scanner = new Scanner(System.in);
        for (Question question : questions.keySet()) {
            System.out.println("Question: " + question.getQuestionText());
            String answer = scanner.next();
            questions.put(question, checkAnswer(answer, question));
        }
    }

    @Override
    public int rightAnswers() {
        int correctAnswers = 0;
        for (Question question : questions.keySet()) {
            if (questions.get(question))
                correctAnswers++;
        }
        return correctAnswers;
    }

    @Override
    public void result() {
        System.out.println(name + " " + surname + ", result is: " + rightAnswers() + "/" + questions.size());
    }

    private Boolean checkAnswer(String answer, Question question) {
        return answer.equals(question.getAnswer());
    }
}
