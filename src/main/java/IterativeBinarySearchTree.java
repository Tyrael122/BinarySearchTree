public class IterativeBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private final Node<T> rootNode = new Node<>();

    @Override
    public boolean find(T valueToLookFor) {
        return findNodeWithValue(valueToLookFor) != null;
    }

    @Override
    public void insert(T valueToInsert) {
        if (isTreeEmpty()) {
            rootNode.setValue(valueToInsert);

            return;
        }

        Node<T> node = rootNode;

        while (node.getValue() != valueToInsert) {
            Node<T> successorNode = findSuccessorNodeForGivenValue(node, valueToInsert);

            if (successorNode == null) {
                createNewChildNode(node, valueToInsert);
                break;
            }

            node = successorNode;
        }
    }

    @Override
    public void remove(T value) {
        Node<T> nodeToRemove = findNodeWithValue(value);
        if (nodeToRemove == null) {
            return;
        }

        removeNode(nodeToRemove);
    }

    private void removeNode(Node<T> nodeToRemove) {
        int howManyChildren = calculateHowManyChildren(nodeToRemove);
        if (howManyChildren == 0) {
            removeNodeWithNoChild(nodeToRemove);

            return;
        }

        if (howManyChildren == 1) {
            copyChildNodeToParentNode(nodeToRemove);
            return;
        }

        if (howManyChildren == 2) {
            replaceWithInOrderSuccessor(nodeToRemove);
            return;
        }
    }

    private void removeNodeWithNoChild(Node<T> nodeToRemove) {
        Node<T> parent = nodeToRemove.getParent();
        NodeDirection nodeDirection = calculateChildNodeDirection(parent, nodeToRemove);

        switch (nodeDirection) {
            case LEFT -> parent.setLeftNode(null);
            case RIGHT -> parent.setRightNode(null);
        }
    }

    private NodeDirection calculateChildNodeDirection(Node<T> parent, Node<T> childNode) {
        if (parent == childNode) {
            return NodeDirection.EQUAL;
        }

        if (parent.getLeftNode() == childNode) {
            return NodeDirection.LEFT;
        }

        if (parent.getRightNode() == childNode) {
            return NodeDirection.RIGHT;
        }

        throw new IllegalStateException("The specified child doesn't have the specified parent as it's parent.");
    }

    private void replaceWithInOrderSuccessor(Node<T> nodeToRemove) {
        Node<T> largestNodeInLeftSubtree = findLargestNodeInLeftSubtree(nodeToRemove);

        nodeToRemove.setValue(largestNodeInLeftSubtree.getValue());

        removeNodeWithNoChild(largestNodeInLeftSubtree);
    }

    private void copyChildNodeToParentNode(Node<T> parentNode) {
        if (parentNode.getLeftNode() != null && parentNode.getRightNode() != null) {
            throw new IllegalStateException("The specified parent node has two children, only one was expected.");
        }

        if (parentNode.getLeftNode() == null && parentNode.getRightNode() == null) {
            throw new IllegalStateException("The specified parent node has no children, at least one was expected.");
        }

        Node<T> child = null;
        if (parentNode.getLeftNode() != null) {
            child = parentNode.getLeftNode();
        }

        if (parentNode.getRightNode() != null) {
            child = parentNode.getRightNode();
        }

        parentNode.setValue(child.getValue());
        parentNode.setLeftNode(child.getLeftNode());
        parentNode.setRightNode(child.getRightNode());
    }

    private int calculateHowManyChildren(Node<T> nodeToRemove) {
        int numberOfChildren = 0;

        if (nodeToRemove.getLeftNode() != null) {
            numberOfChildren++;
        }

        if (nodeToRemove.getRightNode() != null) {
            numberOfChildren++;
        }

        return numberOfChildren;
    }

    private Node<T> findLargestNodeInLeftSubtree(Node<T> targetNode) {
        Node<T> firstLeftNode = targetNode.getLeftNode();
        if (firstLeftNode == null) {
            return targetNode;
        }

        Node<T> rightmostNode = firstLeftNode;
        while (rightmostNode.getRightNode() != null) {
            rightmostNode = rightmostNode.getRightNode();
        }

        return rightmostNode;
    }


    private Node<T> findNodeWithValue(T valueToLookFor) {
        if (isTreeEmpty()) return null;

        Node<T> node = rootNode;

        while (node.getValue() != valueToLookFor) {
            node = findSuccessorNodeForGivenValue(node, valueToLookFor);

            boolean hasReachedSearchEndAndDidntFoundValue = node == null;
            if (hasReachedSearchEndAndDidntFoundValue) {
                return null;
            }
        }

        return node;
    }

    private boolean isTreeEmpty() {
        return rootNode.getValue() == null;
    }

    private void createNewChildNode(Node<T> node, T valueToInsert) {
        Node<T> newNode = new Node<>();
        newNode.setValue(valueToInsert);
        newNode.setParent(node);

        NodeDirection direction = findNextNodeDirection(node, valueToInsert);
        switch (direction) {
            case LEFT -> node.setLeftNode(newNode);
            case RIGHT -> node.setRightNode(newNode);
        }
    }

    private Node<T> findSuccessorNodeForGivenValue(Node<T> node, T value) {
        NodeDirection direction = findNextNodeDirection(node, value);
        switch (direction) {
            case LEFT -> {
                return node.getLeftNode();
            }
            case RIGHT -> {
                return node.getRightNode();
            }
            default-> {
                return node;
            }
        }
    }

    private NodeDirection findNextNodeDirection(Node<T> node, T value) {
        // Esquerda é menor, direita é maior
        int comparison = value.compareTo(node.getValue());

        if (comparison < 0) {
            return NodeDirection.LEFT;
        }

        if (comparison > 0) {
            return NodeDirection.RIGHT;
        }

        return NodeDirection.EQUAL;
    }
}
