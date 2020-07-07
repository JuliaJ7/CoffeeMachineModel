package jetbrains;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        String input = "";

        for (;;) {
            coffeeMachine.printUserPrompt();
            input = scanner.nextLine();
            coffeeMachine.processInput(input);

            if (coffeeMachine.getState() == MachineState.EXIT) {
                break;
            }
        }
    }
}
