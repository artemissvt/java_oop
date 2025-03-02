package week01hw;

import java.util.Scanner;

public class PetClinic {
    public static void main(String[] args) {
        Pet[] pets = new Pet[10];

        // Assign pet objects manually
        pets[0] = new Pet("dog", "Pluto");
        pets[1] = new Pet("cat", "Whiskers");
        pets[2] = new Pet("bird", "Tweety");
        pets[3] = new Pet("rabbit", "Thumper");
        pets[4] = new Pet("fish", "Nemo");
        pets[5] = new Pet("dog", "Thunder");
        pets[6] = new Pet("cat", "Minie");
        pets[7] = new Pet("dog", "Wesley");
        pets[8] = new Pet("cat", "Lila");
        pets[9] = new Pet("bird", "Bonnie");

        Scanner in = new Scanner(System.in);
        System.out.println("Type the specie of the animal you want to check: ");
        String specieType = in.nextLine();
        //System.out.println("Specie type: " + specieType);

        int count = 0;
        String petNames = "";

        for (int i = 0; i < pets.length; i++) {
            if (pets[i].species.equalsIgnoreCase(specieType)) {
                count++;
                petNames += pets[i].name + "\n";
            }
        }

        if (count > 0) {
            System.out.println();
            System.out.println("Number of these species found: " + count);
            System.out.println();
            System.out.println("The name(s) of the searched species are:\n" + petNames);
        } else {
            System.out.println("No species found.");
        }

    }
}
