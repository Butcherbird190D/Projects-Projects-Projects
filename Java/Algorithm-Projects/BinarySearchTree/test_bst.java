package Projects.Assignment4;

import java.util.*;

public class test_bst {
    public static void main(String[] args) {
        llbst b1 = new llbst();
        llbst b2 = new llbst();
        Random rand = new Random();

        ArrayList<Integer> insertedIntegers = new ArrayList<>();

        // Insert 10 random integers and track them
        for (int i = 0; i < 10; i++) {
            int x = rand.nextInt(100); // Keep range small for testing
            insertedIntegers.add(x);
            b1.insert(x);
        }

        System.out.println("\nIn order traversal of b1 before deletion:");
        b1.inorder();

        // Find and delete the 4th inserted integer
        int fourthInserted = insertedIntegers.get(3);
        System.out.println("\nThe 4th integer inserted is: " + fourthInserted);

        b1.delete(fourthInserted);

        System.out.println("\nIn order traversal of b1 after deleting the 4th integer:");
        b1.inorder();

        // Insert strings into b2
        b2.insert("JANE");
        b2.insert("MIKE");
        b2.insert("APPLE");
        b2.insert("GREEN");
        b2.insert("YELLOW");
        b2.insert("RED");
        b2.insert("BLACK");
        b2.insert("WHITE");
        b2.insert("ORANGE");
        b2.insert("APPLE"); // Duplicate

        System.out.println("\nInorder traversal of b2:");
        b2.inorder();
    }
}

