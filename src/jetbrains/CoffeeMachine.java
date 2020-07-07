package jetbrains;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private MachineState state;

    public MachineState getState() {
        return state;
    }

    public CoffeeMachine() {
        this.water = 400;
        this.milk = 540;
        this.beans = 120;
        this.cups = 9;
        this.money = 550;
        this.state = MachineState.CHOOSE_MAIN_ACTION;
    }

    public void printMachineState(){
        System.out.println("The coffee machine has:");
        System.out.println(this.water + " of water");
        System.out.println(this.milk + " of milk");
        System.out.println(this.beans + " of coffee beans");
        System.out.println(this.cups + " of disposable cups");
        System.out.println(this.money + " of money");
        System.out.print("\n");
    }

    public void takeEarnings(){
        System.out.println("I gave you $" + this.money);
        System.out.print("\n");
        this.money = 0;
    }

    public String hasEnoughResources(String coffeeOption){
        String result = "";

        switch (coffeeOption) {
            case "1":
                if (this.water < 250) {
                    result = "Sorry, not enough water!";
                } else if (this.beans < 16) {
                    result = "Sorry, not enough beans!";
                } else if (this.cups < 1) {
                    result = "Sorry, not enough cups!";
                }
                break;
            case "2":
                if (this.water < 350) {
                    result = "Sorry, not enough water!";
                } else if (this.milk < 75) {
                    result = "Sorry, not enough milk!";
                } else if (this.beans < 20) {
                    result = "Sorry, not enough beans!";
                } else if (this.cups < 1) {
                    result = "Sorry, not enough cups!";
                }
                break;
            case "3":
                if (this.water < 200) {
                    result = "Sorry, not enough water!";
                } else if (this.milk < 100) {
                    result = "Sorry, not enough milk!";
                } else if (this.beans < 12) {
                    result = "Sorry, not enough beans!";
                } else if (this.cups < 1) {
                    result = "Sorry, not enough cups!";
                }
                break;
        }
        return result;
    }

    public void fillWater(int addWater) {
        this.water += addWater;
    }

    public void fillMilk(int addMilk) {
        this.milk += addMilk;
    }

    public void fillBeans(int addBeans) {
        this.beans += addBeans;
    }

    public void fillCups(int addCups) {
        this.cups += addCups;
    }

    public void makeCoffee(String coffeeOption) {

        if (coffeeOption.equals("back")) {
            return;
        }

        String message = hasEnoughResources(coffeeOption);

        if (message.equals("")) {
            System.out.println("I have enough resources, making you a coffee!");

            switch (coffeeOption) {
                case "1":
                    water -= 250;
                    beans -= 16;
                    money += 4;
                    cups--;
                    break;
                case "2":
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    money += 7;
                    cups--;
                    break;
                case "3":
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    money += 6;
                    cups--;
                    break;
            }
        } else {
            System.out.println(message);
        }
    }

    public void setStateFromMainAction(String action) {
        switch (action) {
            case("buy"):
                this.state = MachineState.BUY;
                break;
            case("fill"):
                this.state = MachineState.FILL;
                break;
            case("take"):
                this.state = MachineState.TAKE_EARNINGS;
                break;
            case("remaining"):
                this.state = MachineState.PRINT_STATE;
                break;
            case("exit"):
                this.state = MachineState.EXIT;
                break;
            default:
                this.state = MachineState.CHOOSE_MAIN_ACTION;
                break;
        }
    }

    public void printUserPrompt() {
        if (this.state == MachineState.CHOOSE_MAIN_ACTION) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            System.out.print("> ");
        }

        if (this.state == MachineState.CHOOSE_COFFEE_OPTION) {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            System.out.print("> ");
        }

        if (this.state == MachineState.FILL_WATER) {
            System.out.println("Write how many ml of water do you want to add:");
            System.out.print("> ");
        }

        if (this.state == MachineState.FILL_MILK) {
            System.out.println("Write how many ml of milk do you want to add:");
            System.out.print("> ");
        }

        if (this.state == MachineState.FILL_BEANS) {
            System.out.println("Write how many grams of coffee beans do you want to add:");
            System.out.print("> ");
        }

        if (this.state == MachineState.FILL_CUPS) {
            System.out.println("Write how many disposable cups of coffee do you want to add:");
            System.out.print("> ");
        }
    }

    public void processInput(String input) {

        if (this.state == MachineState.CHOOSE_MAIN_ACTION) {
            setStateFromMainAction(input);
        }

        switch (this.state) {
            case BUY:
                this.state = MachineState.CHOOSE_COFFEE_OPTION;
                break;
            case CHOOSE_COFFEE_OPTION:
                this.makeCoffee(input);
                this.state = MachineState.CHOOSE_MAIN_ACTION;
                break;
            case FILL:
                this.state = MachineState.FILL_WATER;
                break;
            case FILL_WATER:
                int addWater = Integer.parseInt(input);
                this.fillWater(addWater);
                this.state = MachineState.FILL_MILK;
                break;
            case FILL_MILK:
                int addMilk = Integer.parseInt(input);
                this.fillMilk(addMilk);
                this.state = MachineState.FILL_BEANS;
                break;
            case FILL_BEANS:
                int addBeans = Integer.parseInt(input);
                this.fillBeans(addBeans);
                this.state = MachineState.FILL_CUPS;
                break;
            case FILL_CUPS:
                int addCups = Integer.parseInt(input);
                this.fillCups(addCups);
                this.state = MachineState.CHOOSE_MAIN_ACTION;
                break;
            case TAKE_EARNINGS:
                takeEarnings();
                this.state = MachineState.CHOOSE_MAIN_ACTION;
                break;
            case PRINT_STATE:
                printMachineState();
                this.state = MachineState.CHOOSE_MAIN_ACTION;
                break;
            case EXIT:
                break;
            default:
                break;
        }
    }
}
