import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class BST<T extends Comparable<? super T>> {
        private BSTNode root;
        private int nelems;

    protected class BSTNode {

        BSTNode parent;
        Person person;
        BSTNode left;
        BSTNode right;

        public BSTNode(BSTNode left, BSTNode right, Person person) {
            /* A constructor that initializes a BST Node with its instance variables */
            this.left = left;
            this.right = right;
            this.person = person;
            this.parent = null;
        }


        public String getName() {
            /* Getter that returns the key of the BST Node */
            return this.person.name;
        }

        public BSTNode getLeft() {
            /* Getter that returns the left Node of the current BST Node */
            return this.left;
        }


        public BSTNode getRight() {
            /* Getter that returns the right Node of the current BST Node */
            return this.right;
        }


        public ArrayList<String> getnumbersList() {
            /* Getter that returns the data (LL) stored within the BST Node */
            return this.person.getPhoneNumbers();
        }

        public void setleft(BSTNode newleft) {
            /* Setter for the left pointer for the BST Node */
            this.left = newleft;
            newleft.parent = this;
        }

        public void setright(BSTNode newright) {
            /* Setter for the right pointer for the BST Node */
            this.right = newright;
            newright.parent = this;
        }

        /**
         * Setter for the linked list of the node
         *
         * @param newData New linked list
         */
        public void setnumbersList(ArrayList<String> newData) {
            /* Setter for the data (LL) inside the BST Node */
            this.person.numbersList = newData;
        }

        public void addNumber(String number) {
            /* Append data to the end of the Linked List in the current node */
            this.person.addPhoneNumber(number);
        }

        public boolean removeNumber(String number) {
            /* Remove data from the LL in the current node and return true if the data was
             * found, returns false otherwise*/
            return this.person.deletePhoneNumber(number);
        }

        public void traverseInOrder() {
            if (this.left != null) {
                this.left.traverseInOrder();
            }
            System.out.println(this.person.name);
            if (this.right != null) {
                this.right.traverseInOrder();
            }
        }
    }


    public BST() {
            this.root = null;
            this.nelems = 0;
        }

        public BSTNode getRoot() {
            /* Returns the root of the BST, returns null if the tree is empty */
            if (this.nelems < 1) {
                return null;
            } else {
                return this.root;
            }
        }

        public boolean insert(Person person) {
            /* insert a Node with a given key and an empty LL for its data
             * returns false if there exists a previous node with the same key
             * throws an exception if the key is null*/
            if (person == null) {
                throw new NullPointerException();
            }
            BSTNode newNode = new BSTNode(null, null, person);
            if (this.root == null) {
                this.root = newNode;
                this.nelems++;
                return true;
            }
            return addHelp(this.root, person);
        }
        public boolean addHelp(BSTNode currRoot, Person person) {
            int value = person.name.compareTo(currRoot.getName());
            if (value == 0) { // Duplicate
                return false;
            }
            if (value < 0) {
                if (currRoot.getLeft() == null) {
                    currRoot.setleft(new BSTNode(null, null, person));
                    this.nelems++;
                    return true;
                } else {
                    addHelp(currRoot.getLeft(), person);
                }
            } else {
                if (currRoot.getRight() == null) {
                    currRoot.setright(new BSTNode(null, null, person));
                    this.nelems++;
                    return true;
                } else {
                    addHelp(currRoot.getRight(), person);
                }
            }
            return false;
        }

        // use this for lookupContact, uses DFS
        public Person getPerson(Person person) {
            Stack<BSTNode> stack = new Stack<>();
            stack.push(this.getRoot());
            while (!stack.isEmpty()) {
                BSTNode currentP = stack.pop();
                if (currentP.getName().equalsIgnoreCase(person.name)) {
                    return currentP.person;
                } else {
                    if (currentP.getLeft() != null) {
                        stack.push(currentP.getLeft());
                    }
                    if (currentP.getRight() != null) {
                        stack.push(currentP.getRight());
                    }
                }
            }
            return null;
        }

        public Person[] getPersonByRange(BSTNode currNode, Person startP, Person endP) {
            LinkedList holdList = new LinkedList<Person>();
            getRangeHelper(currNode, holdList, startP, endP);
            Person[] output = new Person[holdList.size() - 1];
            for (int i = 0; i < holdList.size() - 1; i++) {
                output[i] = (Person) holdList.get(i);
            }
            return output;
        }

        private void getRangeHelper(BSTNode currNode, LinkedList<Person> outList, Person startP, Person endP) {
            if (currNode == null) {
                return;
            }
            int value1 = startP.getName().compareTo(currNode.getName());
            if (value1 < 0) {
                getRangeHelper(currNode.getLeft(), outList, startP, endP);
            }
            int value2 = endP.getName().compareTo(currNode.getName());
            if (value1 <= 0 && value2 >= 0 ) {
                outList.add(currNode.person);
            }
            getRangeHelper(currNode.getRight(), outList, startP, endP);
        }

        public int size() {
            return this.nelems;
        }

        public boolean delete(Person toDelete) {
            //find the node first
            Stack<BSTNode> stack = new Stack<>();
            stack.push(this.getRoot());
            while (!stack.isEmpty()) {
                BSTNode currentP = stack.pop();
                if (currentP.getName().equals(toDelete.name)) {
                    //delete it now
                    deleteHelp(currentP);
                    return true;
                } else {
                    if (currentP.getLeft() != null) {
                        stack.push(currentP.getLeft());
                    }
                    if (currentP.getRight() != null) {
                        stack.push(currentP.getRight());
                    }
                }
            }
            return false;
        }
    private BSTNode deleteHelp(BSTNode currNode) {
//         node with 2 kids
        if (currNode.left != null && currNode.getRight() != null) {
            BSTNode temp = currNode;
            BSTNode minForRight = minVal(temp.getRight());
            //replace current node with min node from right
            currNode.person = minForRight.person;
            //delete min
            currNode.right = deleteHelp(currNode.getRight());
        } else if (currNode.getRight() == null) {  // node with just left
            if (currNode.parent == null){
                this.root = currNode.left;
            } else if (currNode.parent.getLeft() == currNode) {
                currNode.parent.setleft(currNode.getLeft());
            } else {
                currNode.parent.setright(currNode.getLeft());
            }
        } else if (currNode.getLeft() == null) { // just right
            if (currNode.parent == null){
                this.root = currNode.right;
            } else if (currNode.parent.getLeft() == currNode) {
                currNode.parent.setleft(currNode.getRight());
            } else {
                currNode.parent.setright(currNode.getRight());
            }
        } else { // leaf nodes
            currNode = null;
        }
        this.nelems--;
        return currNode;
    }

    private BSTNode minVal(BSTNode newCurrRoot) {
        while (newCurrRoot.getLeft() !=  null) {
            newCurrRoot = newCurrRoot.getLeft();
        }
        return newCurrRoot;
    }
    public String[] fetchAllNames() {
        LinkedList<Person> temp = new LinkedList<>();
        String[] output = new String[this.nelems];
        fetchAllHelper(temp, this.root);
        for (int i = 0; i < temp.size(); i++) {
            output[i] = temp.get(i).getName();
        }
        return output;
    }
    public String[] fetchAllNumbers() {
        LinkedList<Person> personList = new LinkedList<>();
        LinkedList<String> numList = new LinkedList<>();
        fetchAllHelper(personList, this.root);
        for (int i = 0; i < personList.size(); i++) {
            for (int j = 0 ; j < personList.get(i).getPhoneNumbers().size(); j++) {
                String currNum = personList.get(i).getPhoneNumbers().get(j);
                if (!numList.contains(currNum)) {
                    numList.add(currNum);
                }
            }
        }
        String[] output = new String[numList.size()];
        for (int i = 0; i < numList.size(); i++) {
            output[i] = numList.get(i);
        }
        return output;
    }

    private void fetchAllHelper(LinkedList<Person> outList, BSTNode currRoot) {
        if (currRoot.left != null) {
            fetchAllHelper(outList, currRoot.getLeft());
        }
        outList.add(currRoot.person);
        if (currRoot.right != null) {
            fetchAllHelper(outList, currRoot.getRight());
        }
    }
}
