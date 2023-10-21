import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T> {
    private Node<T> parent;

    private T value;
    private Node<T> rightNode;
    private Node<T> leftNode;
}
