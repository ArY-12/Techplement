import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SimpleQuizApp {
    private static class Question {
        String text;
        List<String> choices;
        int correctChoiceIndex;

        Question(String text, List<String> choices, int correctChoiceIndex) {
            this.text = text;
            this.choices = choices;
            this.correctChoiceIndex = correctChoiceIndex;
        }
    }

    private static class Quiz {
        String name;
        List<Question> questions;

        Quiz(String name) {
            this.name = name;
            this.questions = new ArrayList<>();
        }

        void addQuestion(Question question) {
            questions.add(question);
        }
    }

    private final List<Quiz> quizList = new ArrayList<>();
    private final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        SimpleQuizApp app = new SimpleQuizApp();
        app.start();
    }

    private void start() {
        boolean keepRunning = true;
        while (keepRunning) {
            System.out.println("\nSimple Quiz App");
            System.out.println("1. Create Quiz");
            System.out.println("2. Add Question to Quiz");
            System.out.println("3. Take Quiz");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = Integer.parseInt(input.nextLine());

            switch (choice) {
                case 1 -> createQuiz();
                case 2 -> addQuestion();
                case 3 -> takeQuiz();
                case 4 -> keepRunning = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void createQuiz() {
        System.out.print("Enter quiz name: ");
        String name = input.nextLine();
        quizList.add(new Quiz(name));
        System.out.println("Quiz created.");
    }

    private void addQuestion() {
        System.out.print("Enter quiz name: ");
        String name = input.nextLine();
        Quiz quiz = findQuiz(name);

        if (quiz != null) {
            System.out.print("Enter question: ");
            String questionText = input.nextLine();

            List<String> choices = new ArrayList<>();
            System.out.print("How many choices? ");
            int numChoices = Integer.parseInt(input.nextLine());

            for (int i = 0; i < numChoices; i++) {
                System.out.print("Enter choice " + (i + 1) + ": ");
                choices.add(input.nextLine());
            }

            System.out.print("Enter the number of the correct choice: ");
            int correctChoice = Integer.parseInt(input.nextLine()) - 1;

            quiz.addQuestion(new Question(questionText, choices, correctChoice));
            System.out.println("Question added.");
        } else {
            System.out.println("Quiz not found.");
        }
    }

    private void takeQuiz() {
        System.out.print("Enter quiz name: ");
        String name = input.nextLine();
        Quiz quiz = findQuiz(name);

        if (quiz != null) {
            int score = 0;
            for (Question question : quiz.questions) {
                System.out.println(question.text);
                for (int j = 0; j < question.choices.size(); j++) {
                    System.out.println((j + 1) + ": " + question.choices.get(j));
                }
                System.out.print("Your answer: ");
                int answer = Integer.parseInt(input.nextLine()) - 1;

                if (answer == question.correctChoiceIndex) {
                    score++;
                }
            }
            System.out.println("Quiz complete! Your score: " + score + "/" + quiz.questions.size());
        } else {
            System.out.println("Quiz not found.");
        }
    }

    private Quiz findQuiz(String name) {
        for (Quiz quiz : quizList) {
            if (quiz.name.equalsIgnoreCase(name)) {
                return quiz;
            }
        }
        return null;
    }
}
