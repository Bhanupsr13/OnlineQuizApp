package main;

import dao.*;
import model.*;
import java.util.*;

public class QuizApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        QuizDAO quizDAO = new QuizDAO();
        QuestionDAO questionDAO = new QuestionDAO();
        ResultDAO resultDAO = new ResultDAO();

        System.out.println("=== Welcome to Online Quiz App ===");
        System.out.println("1. Register\n2. Login");
        int choice = sc.nextInt(); sc.nextLine();

        String username = "";
        if (choice == 1) {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            System.out.print("Enter password: ");
            String p = sc.nextLine();
            if (userDAO.register(username, p)) System.out.println("Registered successfully!");
            else { System.out.println("Registration failed."); return; }
        } else {
            System.out.print("Enter username: ");
            username = sc.nextLine();
            System.out.print("Enter password: ");
            String p = sc.nextLine();
            if (!userDAO.login(username, p)) {
                System.out.println("Invalid credentials!");
                return;
            }
            System.out.println("Login successful!");
        }

        System.out.println("\nAvailable Quizzes:");
        List<Integer> quizIds = quizDAO.getAllQuizIds();
        for (int id : quizIds)
            System.out.println(id + ". " + quizDAO.getQuizTitle(id));

        System.out.print("Enter quiz ID: ");
        int quizId = sc.nextInt(); sc.nextLine();

        List<Question> questions = questionDAO.getQuestionsByQuizId(quizId);
        int score = 0;
        for (Question q : questions) {
            System.out.println("\n" + q.getQuestionText());
            System.out.println("A. " + q.getOptionA());
            System.out.println("B. " + q.getOptionB());
            System.out.println("C. " + q.getOptionC());
            System.out.println("D. " + q.getOptionD());
            System.out.print("Your answer: ");
            char ans = sc.nextLine().toUpperCase().charAt(0);
            if (ans == q.getCorrectOption()) {
                System.out.println("✅ Correct!");
                score++;
            } else {
                System.out.println("❌ Wrong! Correct answer: " + q.getCorrectOption());
            }
        }

        System.out.println("\nQuiz Over! Your Score: " + score + "/" + questions.size());
        resultDAO.saveResult(1, quizId, score); // user_id assumed = 1 for simplicity
        System.out.println("Your result has been saved!");
    }
}
