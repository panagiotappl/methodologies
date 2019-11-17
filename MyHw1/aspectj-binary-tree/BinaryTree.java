class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }
    
    public BinaryTree(Node root) {
        this.root = root;
    }

    private Node insertNode(int value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insertNode(value, node.left);
        } else if (value > node.value) {
            node.right = insertNode(value, node.right);
        } else {
            return node;
        }
        return node;
    }

    public void insert(int value) {
        this.root = insertNode(value, root);
    }

    private Boolean lookUpNode(int value, Node node) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return value < node.value ? lookUpNode(value, node.left) : lookUpNode(value, node.right);

    }

    public Boolean lookUp(int value) {
        return lookUpNode(value, root);
    }

    private int findMinimumValue(Node node) {
        return node.left == null ? node.value : findMinimumValue(node.left);
    }

    private Node removeNode(int value, Node node) {
        if (node == null) {
            return null;
        }
        if (value == node.value) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            } else {
                int minValue = findMinimumValue(node.right);
                node.value = minValue;
                node.right = removeNode(minValue, node.right);
                return node;
            }
        }
        if (value < node.value) {
            node.left = removeNode(value, node.left);
        } else {
            node.right = removeNode(value, node.right);
        }
        return node;

    }

    public void remove(int value) {
        this.root = removeNode(value, root);
    }
}