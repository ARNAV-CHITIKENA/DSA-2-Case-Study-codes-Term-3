class Node {
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Main_BST {

    // 🔹 Insert
    static Node insert(Node root, int key) {
        if (root == null) return new Node(key);

        if (key < root.data)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        return root;
    }

    // 🔹 Search
    static boolean search(Node root, int key) {
        if (root == null) return false;

        if (root.data == key) return true;

        if (key < root.data)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    // 🔹 Find Minimum (used in delete)
    static Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    // 🔹 Delete
    static Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data) {
            root.left = delete(root.left, key);
        } 
        else if (key > root.data) {
            root.right = delete(root.right, key);
        } 
        else {
            // Case 1: No child
            if (root.left == null && root.right == null)
                return null;

            // Case 2: One child
            if (root.left == null)
                return root.right;

            if (root.right == null)
                return root.left;

            // Case 3: Two children
            Node minNode = findMin(root.right);
            root.data = minNode.data;
            root.right = delete(root.right, minNode.data);
        }

        return root;
    }

    // 🔹 Traversals
    static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    // 🔹 Pretty Print
    static void printTree(Node root, String indent, String position) {
        if (root == null) return;

        System.out.println(indent + position + root.data);

        printTree(root.left, indent + "   ", "L: ");
        printTree(root.right, indent + "   ", "R: ");
    }

    public static void main(String[] args) {

        int arr[] = {50, 30, 70, 20, 40, 60, 80};
        Node root = null;

        // Insert elements
        for (int val : arr) {
            root = insert(root, val);
        }

        // Print Tree
        System.out.println("BST Structure:");
        printTree(root, "", "Root: ");

        // Traversals
        System.out.print("\nInorder: ");
        inorder(root);

        System.out.print("\nPreorder: ");
        preorder(root);

        System.out.print("\nPostorder: ");
        postorder(root);

        // Search
        int key = 40;
        System.out.println("\n\nSearch " + key + ": " + (search(root, key) ? "Found" : "Not Found"));

        // Delete
        System.out.println("\nDeleting 30...");
        root = delete(root, 30);

        System.out.println("\nBST after deletion:");
        printTree(root, "", "Root: ");
    }
}