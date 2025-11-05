import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;


public class CSArrayListTest {
    public static void main(String[] args) {
        CSArrayList<String> testCollection = new CSArrayList<>();
        testCollection.add("A");
        testCollection.add("B");
        testCollection.add("C");
        System.out.println("Initial list: " + testCollection);  // [A,B,C]

        // -- fail-fast check #1 --
        try {
            for (String s : testCollection) {
                System.out.println(s);
                if (s.equals("C")) {
                    testCollection.add("D");  // modification
                }
            }
        }catch (ConcurrentModificationException e) {
            System.out.println("concurrent modification exception caught - fail-fast works!");
        }

        // -- removal (Object) --
        testCollection.remove("B");
        System.out.println("After removing(B): " + testCollection);  // [A,C,D]

        // -- isEmpty() --
        System.out.println("Is Empty? " + "(" + testCollection.isEmpty() + ")");  // false

        // -- clear() --
        testCollection.clear();
        System.out.println("After clear(): " + testCollection + " (empty? " + testCollection.isEmpty() + ")");

        // -- refill for next tests --
        testCollection.add("X");
        testCollection.add("Y");
        testCollection.add("Z");
        System.out.println("Refilled: " + testCollection);

        // -- index and contains test --
        System.out.println("Size: " + testCollection.size());  // 3
        System.out.println("Contains Y? " + testCollection.contains("Y")); // true
        System.out.println("Index of Y: " + testCollection.indexOf("Y")); // 1

        // -- Fail-fast check #2 (modify after iterator created) --
        try {
            Iterator<String> it = testCollection.iterator();
            testCollection.add("W");  // modification after iterator creation
            System.out.println("Next element: " + it.next());  //should throw
            System.out.println("Expected ConcurrentModificationException, but none thrown ");
        } catch (ConcurrentModificationException e) {
            System.out.println("Fail-fast works!");
        }
    }
}
