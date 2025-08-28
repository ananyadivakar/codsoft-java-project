import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();

            int totalRounds = 0;
            int score = 0;
            String playAgain;

            System.out.println("🎮 Welcome to the Number Guessing Game!");

            do {
                int numberToGuess = random.nextInt(100) + 1; // 1 to 100
                int attemptsLeft = 7;

                totalRounds++;
                System.out.println("\n🔁 Round " + totalRounds);
                System.out.println("I'm thinking of a number between 1 and 100.");
                System.out.println("You have " + attemptsLeft + " attempts to guess it!");

                while (attemptsLeft > 0) {
                    System.out.print("Enter your guess: ");
                    int guess;

                    if (scanner.hasNextInt()) {
                        guess = scanner.nextInt();
                    } else {
                        System.out.println("❌ Invalid input. Please enter a number.");
                        scanner.next(); // clear invalid
                        continue;
                    }

                    attemptsLeft--;

                    if (guess == numberToGuess) {
                        System.out.println("✅ Correct! You guessed it!");
                        score++;
                        break;
                    } else if (guess < numberToGuess) {
                        System.out.println("📉 Too low!");
                    } else {
                        System.out.println("📈 Too high!");
                    }

                    if (attemptsLeft > 0) {
                        System.out.println("Attempts left: " + attemptsLeft);
                    } else {
                        System.out.println("💥 You're out of attempts! The number was " + numberToGuess + ".");
                    }
                }

                System.out.print("\n🔄 Do you want to play again? (yes/no): ");
                scanner.nextLine(); // consume newline
                playAgain = scanner.nextLine().trim().toLowerCase();

            } while (playAgain.equals("yes"));

            System.out.println("\n🏁 Game Over! You played " + totalRounds + " round(s) and won " + score + " time(s).");
            System.out.println("🎉 Thanks for playing!");
        }
    }
}
