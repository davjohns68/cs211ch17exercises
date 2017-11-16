package cs211ch17exercises;

// Simple binary tree class that includes methods to construct a
// tree of ints, to print the structure, and to print the data
// using a preorder, inorder or postorder traversal.  The trees
// built have nodes numbered starting with 1 and numbered
// sequentially level by level with no gaps in the tree.  The
// documentation refers to these as "sequential trees."
//
// from buildingjavaprograms.com


import java.util.*;

public class IntTree {
    private IntTreeNode overallRoot;

    // pre : max > 0
    // post: constructs a sequential tree with given number of
    //       nodes
    public IntTree(int max) {
        if (max <= 0) {
            throw new IllegalArgumentException("max: " + max);
        }
        overallRoot = buildTree(1, max);
    }
    
    public IntTree() {
        overallRoot = null;
    }
    
    // constructor added so we can build page 1029 reference trees
    public IntTree(IntTreeNode given) {
        overallRoot = given;
    }

    // Exercise 4
    public int countEvenBranches() {
        return countEvenBranches(overallRoot);
    }

    private int countEvenBranches(IntTreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        } else {    // There is at least one child node
            if (root.data % 2 == 0) {
                return 1 + countEvenBranches(root.left) + countEvenBranches(root.right);
            } else {    // Data is odd
                return countEvenBranches(root.left) + countEvenBranches(root.right);
            }
        }
    }

    // Exercise 5
    public void printLevel(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Please supply an integer larger than 0: " + n);
        }
        
        printLevel(overallRoot, n);
    }
    
    private void printLevel(IntTreeNode root, int n) {
        if (root != null) {
            if (n == 1) {
                System.out.println(root.data);
            } else {
                printLevel(root.left, n - 1);
                printLevel(root.right, n - 1);
            }
        }
    }
    
    // Exercise 6
    public void printLeaves() {
        if (overallRoot == null) {
            System.out.println("No leaves");
        }
        System.out.print("Leaves:");
        printLeaves(overallRoot);
        System.out.println();
    }
    
    private void printLeaves(IntTreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                System.out.print(" " + root.data);
            } else {
                printLeaves(root.right);
                printLeaves(root.left);
            }
        }
    }
    
    //Exercise #7, Chapter 17
    public boolean isFull() {
        return (overallRoot == null || isFull(overallRoot));
    }

    private boolean isFull(IntTreeNode root) {
        if(root.left == null && root.right == null) {
            return true;
        } else {
            return (root.left != null && root.right != null && isFull(root.left) && isFull(root.right));
        }
    }  

    //Exercise 8
    @Override
    public String toString() {
        return toString(overallRoot);
    }
    
    private String toString(IntTreeNode root) {
        String result = "";
        
        if (root == null) {
            result += "empty";
        } else if (root.left != null || root.right != null) {
            result += "(" + root.data + ", " + toString(root.left) + ", " + toString(root.right) + ")";
        } else {
            result += root.data;
        }
        
        return result;
    }
    
    // Exercise 9
    public boolean equals(IntTree other) {
        return equals(this.overallRoot, other.overallRoot);
    }

    private boolean equals(IntTreeNode myRoot, IntTreeNode otherRoot) {
        if (myRoot == null && otherRoot == null) {
            return true;
        } else if (myRoot == null || otherRoot == null) {
            return false;
        } else if (myRoot.data != otherRoot.data) {
            return false;
        } else {
            return (equals(myRoot.left, otherRoot.left) && equals(myRoot.right, otherRoot.right));
        }
    }

    // Exercise 12
    public void removeLeaves() {
        removeLeaves(overallRoot, overallRoot);
    }
    
    private void removeLeaves(IntTreeNode prev, IntTreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                // Remove it
                if (prev.left == root) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
            }
            removeLeaves(root, root.left);
            removeLeaves(root, root.right);
        }
    }
    
    // Exercise 13
    public IntTree copy() {
        IntTree newCopy = new IntTree();
        newCopy.overallRoot = new IntTreeNode(this.overallRoot.data);
        
        copy(this.overallRoot, newCopy.overallRoot);
        
        return newCopy;
    }
    
    private void copy(IntTreeNode thisRoot, IntTreeNode newRoot) {
        if (thisRoot != null) {
            if (thisRoot.left != null) {
                newRoot.left = new IntTreeNode(thisRoot.left.data);
                copy(thisRoot.left, newRoot.left);
            }
            if (thisRoot.right != null) {
                newRoot.right = new IntTreeNode(thisRoot.right.data);
                copy(thisRoot.right, newRoot.right);
            }
       }        
    }
    
    // Exercise 16
    public void tighten() {
        tighten(overallRoot, overallRoot);
    }
    
    private void tighten(IntTreeNode prev, IntTreeNode root) {
        if (root != null) {
            if ((root.left == null && root.right != null) || (root.left != null && root.right == null)) {
                if (prev.left == root) {
                    if (root.left != null) {
                        prev.left = root.left;
                    } else {
                        prev.left = root.right;
                    }
                } else {
                    if (root.left != null) {
                        prev.right = root.left;
                    } else {
                        prev.right = root.right;
                    }
                }
            } else {
                tighten(root, root.left);
                tighten(root, root.right);
            }
        }
    }
    
    // Exercise 17
    public IntTree combineWith(IntTree other) {
        IntTree combo = new IntTree();
        combo.overallRoot = new IntTreeNode(0);
        
        combineWith(this.overallRoot, other.overallRoot, combo.overallRoot);
        
        
        return combo;
    }
    
    private void combineWith(IntTreeNode thisRoot, IntTreeNode otherRoot, IntTreeNode comboRoot) {
        if (thisRoot != null && otherRoot != null) {
            comboRoot.data = 3;
            comboRoot.left = new IntTreeNode(0);
            combineWith(thisRoot.left, otherRoot.left, comboRoot.left);
            comboRoot.right = new IntTreeNode(0);
            combineWith(thisRoot.right, otherRoot.right, comboRoot.right);
        } else if (thisRoot != null) {
            comboRoot.data = 1;
            comboRoot.left = new IntTreeNode(0);
            combineWith(thisRoot.left, null, comboRoot.left);
            comboRoot.right = new IntTreeNode(0);
            combineWith(thisRoot.right, null, comboRoot.right);
        } else if (otherRoot != null) {
            comboRoot.data = 2;
            comboRoot.left = new IntTreeNode(0);
            combineWith(null, otherRoot.left, comboRoot.left);
            comboRoot.right = new IntTreeNode(0);
            combineWith(null, otherRoot.right, comboRoot.right);
        }    
     }
    
    // Exercise 18
    public ArrayList<Integer> inOrderList() {
        ArrayList<Integer> result = new ArrayList<>();
        
        inOrderList(overallRoot, result);
        
        return result;
    }
    
    private void inOrderList(IntTreeNode root, ArrayList<Integer> newList) {
        if (root != null) {
            inOrderList(root.left, newList);
            newList.add(root.data);
            inOrderList(root.right, newList);
        }
    }

    // post: returns a sequential tree with n as its root unless
    //       n is greater than max, in which case it returns an
    //       empty tree
    private IntTreeNode buildTree(int n, int max) {
        if (n > max) {
            return null;
        } else {
            return new IntTreeNode(n, buildTree(2 * n, max),
                                   buildTree(2 * n + 1, max));
        }
    }

    // post: prints the tree contents using a preorder traversal
    public void printPreorder() {
        System.out.print("preorder:");
        printPreorder(overallRoot);
        System.out.println();
    }

    // post: prints the tree contents using a preorder traversal
    // post: prints in preorder the tree with given root
    private void printPreorder(IntTreeNode root) {
        if (root != null) {
            System.out.print(" " + root.data);
            printPreorder(root.left);
            printPreorder(root.right);
        }
    }

    // post: prints the tree contents using a inorder traversal
    public void printInorder() {
        System.out.print("inorder:");
        printInorder(overallRoot);
        System.out.println();
    }

    // post: prints in inorder the tree with given root
    private void printInorder(IntTreeNode root) {
        if (root != null) {
            printInorder(root.left);
            System.out.print(" " + root.data);
            printInorder(root.right);
        }
    }

    // post: prints the tree contents using a postorder traversal
    public void printPostorder() {
        System.out.print("postorder:");
        printPostorder(overallRoot);
        System.out.println();
    }

    // post: prints in postorder the tree with given root
    private void printPostorder(IntTreeNode root) {
        if (root != null) {
            printPostorder(root.left);
            printPostorder(root.right);
            System.out.print(" " + root.data);
        }
    }

    // post: prints the tree contents, one per line, following an
    //       inorder traversal and using indentation to indicate
    //       node depth; prints right to left so that it looks
    //       correct when the output is rotated.
    public void printSideways() {
        printSideways(overallRoot, 0);
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(IntTreeNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }
}