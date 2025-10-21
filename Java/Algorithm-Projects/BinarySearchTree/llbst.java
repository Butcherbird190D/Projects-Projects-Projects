package Projects.Assignment4;

import java.util.*;

public class llbst {
    class bstNode {
        private Comparable data;
        private bstNode left, right;
        private bstNode(Comparable x) {
            data = x;
            left = right = null;
        }
    }

    private bstNode root;

    public llbst() {
        root = null;
    }

    public void insert(Comparable x) {
        root = bstNodeInsert(root, x);
    }

    public void inorder() {
        bstNodeInorder(root);
    }

    private bstNode bstNodeInsert(bstNode t, Comparable x) {
        if (t == null)
            t = new bstNode(x);
        else if (x.compareTo(t.data) < 0)
            t.left = bstNodeInsert(t.left, x);
        else if (x.compareTo(t.data) > 0)
            t.right = bstNodeInsert(t.right, x);
        else {
            System.out.println("DUPLICATE - " + x + " REJECTED");
        }
        return t;
    }

    private void bstNodeInorder(bstNode t) {
        if (t == null)
            return;
        bstNodeInorder(t.left);
        System.out.println(t.data);
        bstNodeInorder(t.right);
    }

    public void delete(Comparable x) {
        root = bstNodeDelete(root, x);
    }

    private bstNode bstNodeDelete(bstNode t, Comparable x) {
        if (t == null) return null;

        if (x.compareTo(t.data) < 0) {
            t.left = bstNodeDelete(t.left, x);
        } else if (x.compareTo(t.data) > 0) {
            t.right = bstNodeDelete(t.right, x);
        } else {
            // Node found
            if (t.left == null)
                return t.right;
            else if (t.right == null)
                return t.left;
            else {
                // Node with two children
                bstNode successor = findMin(t.right);
                t.data = successor.data;
                t.right = bstNodeDelete(t.right, successor.data);
            }
        }
        return t;
    }

    private bstNode findMin(bstNode t) {
        while (t.left != null)
            t = t.left;
        return t;
    }
}

