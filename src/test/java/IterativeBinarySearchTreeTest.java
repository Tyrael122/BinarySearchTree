import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IterativeBinarySearchTreeTest {

    @Test
    public void testInsertAndFind() {
        BinarySearchTree<Integer> tree = new IterativeBinarySearchTree<>();
        tree.insert(5);
        assertTrue(tree.find(5));
        tree.insert(3);
        tree.insert(7);
        assertTrue(tree.find(3));
        assertTrue(tree.find(7));
        assertFalse(tree.find(4));
        assertFalse(tree.find(8));
    }

    @Test
    public void testRemoveNonExistent() {
        BinarySearchTree<Integer> tree = new IterativeBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        assertFalse(tree.find(4));
        assertFalse(tree.find(8));
        tree.remove(4); // Removing a non-existent element should not affect the tree
        assertFalse(tree.find(4));
        tree.remove(8); // Removing a non-existent element should not affect the tree
        assertFalse(tree.find(8));
    }

    @Test
    public void testInsertDuplicate() {
        BinarySearchTree<Integer> tree = new IterativeBinarySearchTree<>();
        tree.insert(5);
        tree.insert(5); // Adding a duplicate value should not change the tree
        assertTrue(tree.find(5));
    }

    @Test
    public void testRemoveRoot() {
        BinarySearchTree<Integer> tree = new IterativeBinarySearchTree<>();
        tree.insert(5);
        tree.insert(3);
        tree.insert(7);
        tree.remove(5);
        assertFalse(tree.find(5));
        assertTrue(tree.find(3));
        assertTrue(tree.find(7));
    }
}