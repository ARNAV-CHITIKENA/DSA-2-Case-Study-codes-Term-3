class RankNode {
// C01
    int key;
    int size;
    RankNode left, right;

    // Constructor
    RankNode(int key) {
        this.key = key;
        this.size = 1;
    }
}

public class RankAVL {

    // Function to get size
    static int size(RankNode n) {
        if (n == null)
            return 0;

        return n.size;
    }

    // Update size of node
    static void updateSize(RankNode n) {
        if (n != null) {
            n.size = 1 + size(n.left) + size(n.right);
        }
    }

    // Insert node in DESCENDING order
    static RankNode insert(RankNode root, int key) {

        if (root == null)
            return new RankNode(key);

        // DESCENDING ORDER
        if (key > root.key)
            root.left = insert(root.left, key);

        else if (key < root.key)
            root.right = insert(root.right, key);

        // Update subtree size
        updateSize(root);

        return root;
    }

    // Rank Function
    static int rankOf(RankNode root, int key) {

        int rank = 1;

        while (root != null) {

            // Key found
            if (key == root.key) {
                rank += size(root.left);
                return rank;
            }

            // Move LEFT
            else if (key > root.key) {
                root = root.left;
            }

            // Move RIGHT
            else {
                rank += size(root.left) + 1;
                root = root.right;
            }
        }

        return -1;
    }

    // Inorder Display
    static void inorder(RankNode root) {

        if (root == null)
            return;

        inorder(root.left);

        System.out.println(
            "Key = " + root.key +
            "  Size = " + root.size
        );

        inorder(root.right);
    }

    public static void main(String[] args) {

        int arr[] = {
            820, 540, 910, 770, 880,
            460, 990, 600, 730, 950, 510
        };

        RankNode root = null;

        // Insert all nodes
        for (int x : arr) {
            root = insert(root, x);
        }

        // Display Tree Nodes
        System.out.println("Nodes with Size Fields:\n");

        inorder(root);

        // Find Rank
        int playerScore = 770;

        int rank = rankOf(root, playerScore);

        System.out.println(
            "\nRank of " + playerScore + " = " + rank
        );
    }
}