package cs211ch17exercises;

/**
 * BJP, reference tree #2
 * page 1029, for exercise #17
 */
public class RefTree2
{
    public static void main(String[] args) {

        IntTreeNode six = new IntTreeNode(6,null,new IntTreeNode(9,null,null));
        IntTreeNode seven = new IntTreeNode(7,new IntTreeNode(4,null,null),null);
        IntTreeNode eight = new IntTreeNode(8,new IntTreeNode(0,null,null),null);
        IntTreeNode two = new IntTreeNode(2, new IntTreeNode(4, null, null), new IntTreeNode(6, null, null));
        IntTreeNode five = new IntTreeNode(5, new IntTreeNode(1, null, null), null);
        IntTreeNode three = new IntTreeNode(3, new IntTreeNode(8, null, null), new IntTreeNode(7, null, null));
        
        IntTree tree1 = new IntTree(new IntTreeNode(3, five, two));
        IntTree tree2 = new IntTree(new IntTreeNode(2,eight,new IntTreeNode(1,seven,six)));
        IntTree tree3 = new IntTree(new IntTreeNode(2, three, new IntTreeNode(1, null, null)));
        IntTree treeEmpty = new IntTree();
        
        tree2.printSideways();
        
        // Exercise 4
        System.out.println("Even Branches: " + tree2.countEvenBranches());
        // Exercise 5
        System.out.println("Printing all data at level 3: ");
        tree2.printLevel(3);
        System.out.println();
        tree2.printLeaves();
        System.out.println();
        System.out.println(tree2);
        
        System.out.println(tree2.equals(tree2)); // true
        System.out.println(treeEmpty.equals(tree2));    // false
        System.out.println(treeEmpty.equals(treeEmpty));    // true
        tree1.printSideways();
        System.out.println("-------tree1 with leaves removed----------------------");
        tree1.removeLeaves();
        tree1.printSideways();
        System.out.println("-------tree2 tightened----------------------");
        tree2.tighten();
        tree2.printSideways();
        System.out.println("------------------------------------");
        System.out.println("Tree3 as a list: " + tree3.inOrderList());
        System.out.println("------------------------------------");
        tree3.printSideways();
        System.out.println("------------------------------------");
        IntTree tree50 = tree3.copy();
        tree50.printSideways();
        System.out.println("------------------------------------");
        tree3.removeLeaves();
        System.out.println("Removed leaves from tree3...");
        tree3.printSideways();
        System.out.println("------------------------------------");
        System.out.println("Tree50 for reference...");
        tree50.printSideways();
        System.out.println("------------------------------------");
        System.out.println("CombineWith(tree3, tree2)");
        IntTree tree500 = tree3.combineWith(tree2);
        tree500.printSideways();
    }
}
