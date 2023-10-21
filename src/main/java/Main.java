public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bts = new IterativeBinarySearchTree<>();

        bts.insert(60);
        bts.insert(30);
        bts.insert(90);
        bts.insert(35);
        bts.insert(25);
        bts.insert(70);
        bts.insert(59);
        bts.insert(15);
        bts.insert(7);
        bts.insert(5);
        bts.insert(28);
        bts.insert(62);
        bts.insert(89);
        bts.insert(29);
        bts.insert(1);

        System.out.println("True: ");
        System.out.println(bts.find(60));
        System.out.println(bts.find(30));
        System.out.println(bts.find(59));
        System.out.println(bts.find(7));

        System.out.println();

        System.out.println("False: ");
        System.out.println(bts.find(100));
        System.out.println(bts.find(200));
        System.out.println(bts.find(-1));
        System.out.println(bts.find(37));


        bts.remove(1); // No children
        bts.remove(90); // Only left child
        bts.remove(35); // Only right child
        bts.remove(60); // Both children
    }
}
