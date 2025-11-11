package resources.lesson10;

public class RBTTest {
    public static void main(String[] args) {
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        rbt.insert(10);
        rbt.insert(20);
        rbt.insert(30);
        rbt.insert(15);

        System.out.println(rbt);
    }
}
