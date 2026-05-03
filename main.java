import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Part 1: HashTable Testing ---");
        
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(97); 
        Random random = new Random();

        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(100000), "Data" + i);
            Student value = new Student("Student_" + i);
            table.put(key, value);
        }

        int[] buckets = table.getBucketSizes();
        for (int i = 0; i < buckets.length; i++) {
            System.out.println("Bucket " + i + " contains " + buckets[i] + " elements.");
        }


        System.out.println("\n--- Part 2: BST Iterator Testing ---");
        BST<Integer, String> tree = new BST<>();
        tree.put(50, "Fifty");
        tree.put(30, "Thirty");
        tree.put(70, "Seventy");
        tree.put(20, "Twenty");
        tree.put(40, "Forty");
        
        System.out.println("BST Size: " + tree.size());

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
