package resources.lesson10;

public class RedBlackTree <T extends Comparable<T>>{
    private enum Color {RED, BLACK}
    private Node root;

    public RedBlackTree() {this.root = null;}

    private class Node {
        T key;
        Color color;
        Node left, right, parent;

        Node(T key, Color color, Node parent) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = null;
            this.right = null;
        }

        boolean isRed() {
            return this.color == Color.RED;
        }

        boolean isBlack() {
            return this.color == Color.BLACK;
        }
    }

    private void rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != null) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }

        y.left = x;
        x.parent = y;
    }

    private void rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }

        y.right = x;
        x.parent = y;
    }

    public void insert(T key) {
        Node inserted = bstInsert(key);
        fixInsert(inserted);
    }

    private Node bstInsert(T key) {
        if (root == null) {
            root = new Node(key, Color.RED, null);
            return root;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) {
                current = current.left;
            } else if (cmp > 0) {
                current = current.right;
            } else {
                // chave já existe – dependendo da aplicação, você decide o que fazer
                return current;
            }
        }

        Node newNode = new Node(key, Color.RED, parent);
        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        return newNode;
    }

    private void fixInsert(Node z) {
        // enquanto pai é vermelho, temos conflito "vermelho-vermelho"
        while (z.parent != null && z.parent.isRed()) {
            Node parent = z.parent;
            Node grandparent = parent.parent;

            // pai é filho esquerdo do avô
            if (parent == grandparent.left) {
                Node uncle = grandparent.right;

                // Caso 1: tio é vermelho -> só recolore
                if (uncle != null && uncle.isRed()) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    z = grandparent; // sobe o problema
                } else {
                    // Caso 2: z é filho direito -> rotação esquerda no pai
                    if (z == parent.right) {
                        z = parent;
                        rotateLeft(z);
                        parent = z.parent;
                        grandparent = parent.parent;
                    }
                    // Caso 3: z é filho esquerdo -> rotação direita no avô
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    rotateRight(grandparent);
                }
            } else {
                // simétrico: pai é filho direito do avô
                Node uncle = grandparent.left;

                // Caso 1 simétrico: tio vermelho
                if (uncle != null && uncle.isRed()) {
                    parent.color = Color.BLACK;
                    uncle.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    z = grandparent;
                } else {
                    // Caso 2 simétrico: z é filho esquerdo
                    if (z == parent.left) {
                        z = parent;
                        rotateRight(z);
                        parent = z.parent;
                        grandparent = parent.parent;
                    }
                    // Caso 3 simétrico: z é filho direito
                    parent.color = Color.BLACK;
                    grandparent.color = Color.RED;
                    rotateLeft(grandparent);
                }
            }
        }

        // regra: raiz sempre preta
        root.color = Color.BLACK;
    }

    @Override
    public String toString() {
        return traverseInOrder(root);
    }

    private String traverseInOrder(Node node) {
        if (node == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        // Esquerda
        sb.append(traverseInOrder(node.left));

        // Nó atual (Imprime Chave e Cor)
        String color = node.isRed() ? "R" : "B"; // Assume que isRed() existe no Node
        sb.append(node.key).append("(").append(color).append(") ");

        // Direita
        sb.append(traverseInOrder(node.right));

        return sb.toString();
    }
}
