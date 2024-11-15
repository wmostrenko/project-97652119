package ui;

import model.Simulation;

import java.util.Scanner;

import model.Object;

// Represents a tool that users can use to modify Objects in Simulations
public class ObjectTool {
    private Simulation simulation;
    private Object object; // dummy variable for manipulating objects
    private Scanner scanner;
    private double mass; // in SM
    private double xPosition; // in AU
    private double yPosition; // in AU
    private double xVelocity; // in AU/yr
    private double yVelocity; // in AU/yr

    /**
     * EFFECTS: Initializes this.simulation to simulation, initializes object to
     * null.
     * 
     * @param simulation Simulation to modify with ObjectTool
     */
    public ObjectTool(Simulation simulation) {
        this.simulation = simulation;
        object = null;
        scanner = new Scanner(System.in);
    }

    /*
     * MODIFIES: this, simulation.
     * EFFECTS: A UI to add Objects to a Simulation.
     */
    @SuppressWarnings("methodlength")
    public void addObject() {
        // User enters mass
        System.out.println("What is the mass (in solar masses)?");
        while (true) {
            mass = scanner.nextDouble();
            scanner.nextLine();
            if (mass < 0.0) {
                System.out.println("The mass cannot be negative! Try again.");
            } else {
                break;
            }
        }
        System.out.println("The mass is set to " + mass + "SM");

        // User enters xPosition
        System.out.println("What should the initial horizontal position be (in astronomical units)?");
        xPosition = scanner.nextDouble();
        System.out.println("The horizontal position is set to " + xPosition + "AU");

        // User enters yPosition
        System.out.println("What should the initial vertical position be (in astronomical units)?");
        yPosition = scanner.nextDouble();
        System.out.println("The vertical position is set to " + yPosition + "AU");

        // User enters xVelocity
        System.out.println("What should the initial horizontal velocity be (in astronomical units per year)?");
        xVelocity = scanner.nextDouble();
        System.out.println("The initial horizontal velocity is set to " + xVelocity + "AU/yr");

        // User enters yVelocity
        System.out.println("What should the initial vertical velocity be (in astronomical units per year)?");
        yVelocity = scanner.nextDouble();
        System.out.println("The initial vertical velocity is set to " + yVelocity + "AU/yr");

        // Adds object to simulation
        this.object = new Object(mass, xPosition, yPosition, xVelocity, yVelocity, 0.0, 0.0);
        this.simulation.addObject(this.object);
        System.out.println("Your new object has been added!");
    }

    /*
     * MODIFIES: this, simulation.
     * EFFECTS: A UI to remove Objects from a Simulation
     */
    public void removeObject() {
        // Local variable declaration
        int input;

        // Asks user which object they'd like to remove
        System.out.println("Which object below would you like to remove (#)? ");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println("(" + i + ")" + ". Object " + i);
        }

        // User enters object # to remove
        while (true) {
            input = scanner.nextInt();
            if ((input >= simulation.getNumberOfObjects()) || (input < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }

        simulation.removeObject(input);
    }

    /*
     * MODIFIES: this.
     * EFFECTS: Asks user which object they would like to view. Then prints object
     * properties.
     */
    public void getObjectProperties() {
        // Local variable declaration
        int input;

        // Prints a list of each Object in objects
        System.out.println("The properties of which object below would you like to view?");
        for (int i = 0; i < simulation.getNumberOfObjects(); i++) {
            System.out.println("(" + i + ")" + ". Object " + i);
        }

        // User entrers object # to observe
        while (true) {
            input = scanner.nextInt();
            if ((input >= simulation.getNumberOfObjects()) || (input < 0)) {
                System.out.println("That object isn't in your simulation! Try again.");
            } else {
                break;
            }
        }

        printPropertiesOfChosenObject(simulation.getObjectAt(input));
    }

    /**
     * EFFECTS: Prints properties of given object.
     * 
     * @param object Object for which properties are printed
     */
    public void printPropertiesOfChosenObject(Object object) {
        System.out.println("Mass: " + object.getMass());
        System.out.println("xPosition: " + object.getXPosition());
        System.out.println("yPosition: " + object.getYPosition());
        System.out.println("xVelocity: " + object.getXVelocity());
        System.out.println("yVelocity: " + object.getYVelocity());
        System.out.println("xAcceleration: " + object.getXAcceleration());
        System.out.println("yAcceleration: " + object.getYAcceleration());
    }
}
