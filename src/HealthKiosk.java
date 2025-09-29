import java.util.Scanner;

public class HealthKiosk {
    //this program simulates a self-service intake health kiosk
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //declaring and initialising constant
        int DISPENSABLE = 250;

        //declaring and initialising variables
        int metric = 0;
        double finalBMI = 0;
        int tablet = 0;
        double angleSine = 0;
        String id = "";
        String code = "";

        String service;
        double weight;
        double height;
        double rawBMI;
        double dosage;
        double angleDegrees;
        double angleRadians;
        double angleCosine;
        char letter;
        int number;
        String name;
        char firstLetter;
        char codeLetter;
        char num1;
        char num2;

        System.out.println("Welcome to the Ashesi Health Kiosk!");

        //task 1 - service router
        System.out.print("Enter service code (P/L/T/C): ");
        char serviceCode = scanner.next().trim().toUpperCase().charAt(0);

        switch (serviceCode) {
            case 'P' -> service = "Pharmacy Desk";
            case 'L' -> service = "Lab Desk";
            case 'T' -> service = "Triage Desk";
            case 'C' -> service = "Counseling Desk";
            default -> service = "Invalid service code";
        }

        System.out.println("Go to: " + service);

        //task 2 - mini health metric
        if (service.equalsIgnoreCase("triage desk")) {
            System.out.println("\nMetric\n1. BMI\n2. Dosage round-up\n3. Simple trig helper\n");
            System.out.print("Enter health metric (number): ");
            metric = scanner.nextInt();

            if (metric == 1) {
                //case 1 (calculating bmi)
                System.out.print("Weight (kg): ");
                weight = scanner.nextDouble();
                System.out.print("Height (m): ");
                height = scanner.nextDouble();

                rawBMI = weight / Math.pow(height, 2);
                finalBMI = Math.round(rawBMI * 10) / 10.0;

                if (finalBMI < 18.5) System.out.println("BMI: " + finalBMI +" (Underweight)");
                else if (finalBMI <= 24.9) System.out.println("BMI: " + finalBMI +" (Normal)");
                else if (finalBMI <= 29.9) System.out.println("BMI: " + finalBMI +" (Overweight)");
                else System.out.println("BMI: " + finalBMI +" (Obese)");

            }   else if (metric == 2) {
                //case 2 (dosage round-up)
                System.out.print("Enter the required dosage (mg): ");
                dosage = scanner.nextDouble();
                tablet = (int) Math.ceil(dosage/DISPENSABLE);
                System.out.println("Number of tablets: " + tablet);

            }   else if (metric == 3) {
                //case 3 (simple trig helper)
                System.out.print("Enter angle (in degrees): ");
                angleDegrees = scanner.nextDouble();
                angleRadians = Math.toRadians(angleDegrees);
                angleSine = Math.round(Math.sin(angleRadians) * 1000) / 1000.0;
                angleCosine = Math.round(Math.cos(angleRadians) * 1000) / 1000.0;

                System.out.println("Sin = " + angleSine + "\nCos = " + angleCosine);
            }

        }

        //task 3 - ID sanity check
        letter = (char) ('A' + (int) (Math.random() * 26));
        id += letter;

        for (int i = 1; i < 5; i++) {
            number = (int) (Math.random() * 7) + 3;
            id += number;
        }

        //validating id
        if (id.length() != 5) {
            System.out.println("Invalid length");
        } else if (!Character.isLetter(id.charAt(0))) {
            System.out.println("Invalid: First character must be a letter");
        } else if (!(Character.isDigit(id.charAt(1)) || Character.isDigit(id.charAt(2)) ||
                Character.isDigit(id.charAt(3)) || Character.isDigit(id.charAt(4)))) {
            System.out.println("Invalid: Last 4 characters must be digits");
        } else {
            System.out.println("ID: " + id + "\nID OK");
        }


        //task 4 - secure display code
        System.out.print("\nEnter your first name: ");
        name = scanner.next().toUpperCase().trim();
        firstLetter = name.charAt(0);
        codeLetter = (char) ('A' + (firstLetter - 'A' + 2) % 26);
        code += codeLetter;
        num1 = id.charAt(3);
        code += num1;
        num2 = id.charAt(4);
        code += num2;


        if (service.equalsIgnoreCase("triage desk")) {
            code += "-";
            if (metric == 1) {
                code += Math.round(finalBMI);
            } else if (metric == 2) {
                code += tablet;
            } else if (metric == 3) {
                code += (int) (angleSine * 100);
            }
        }

        System.out.println("Display code: " + code);

        //service summary
        if (serviceCode == 'P') {
            System.out.println("Summary: PHARMACY | ID=" + id + " | Code=" + code);
        } else if (serviceCode == 'T') {
            if (metric == 1) {
                System.out.println("Summary: TRIAGE | ID=" + id + " | BMI=" + finalBMI + " | Code=" + code);
            }   else if (metric == 2) {
                System.out.println("Summary: TRIAGE | ID=" + id + " | TABLETS=" + tablet + " | Code=" + code);
            }   else if (metric == 3) {
                System.out.println("Summary: TRIAGE | ID=" + id + " | ANGLE(SIN)=" + angleSine + " | Code=" + code);
            }
        } else if (serviceCode == 'L') {
            System.out.println("Summary: LAB | ID=" + id + " | Code=" + code);
        } else if (serviceCode == 'C') {
            System.out.println("Summary: COUNSELING | ID=" + id + " | Code=" + code);
        }

        scanner.close();
    }
}
