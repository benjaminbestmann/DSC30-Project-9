import java.util.*;

/**
 * Node class to answer problem 1 -  huffman tree
 */
class HuffmanNode{
    char c;
    int freq;
    String path;

    public HuffmanNode(char c, int freq, String path){
        this.c = c;
        this.freq = freq;
        this.path = path;
    }
}

/**
 * Node class for B-tree
 */
class BNode{
    List data;
    List<BNode> children;

    public BNode(List data){
        this.data = data;
        this.children = new ArrayList<BNode>();
    }
    public void setChildren(List<BNode> children){
        this.children = children;
    }

    public void addChildren(BNode node){
        this.children.add(node);
    }
}




/**
 * Java file that stores answers to problem 1(huffman encoding) and problem 2(B-tree)
 */
public class Worksheet {


    /**
     * Answer to q1(a)
     * @return arrayList consisting HuffmanNodes, where each node stores info about an encoded character
     */
    public static ArrayList<HuffmanNode> q1aEncodeChar(){
        /*TODO: insert nodes into an ArrayList, where each node stores the frequency and path of a character */
        ArrayList<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();
        //Example code on adding nodes: nodeList.add(new HuffmanNode('p', 2, "0001"));
        nodeList.add(new HuffmanNode('d', 1, "0000"));
        nodeList.add(new HuffmanNode('s', 1, "0001"));
        nodeList.add(new HuffmanNode('c', 1, "10111"));
        nodeList.add(new HuffmanNode('3', 2, "0010"));
        nodeList.add(new HuffmanNode('0', 7, "01"));
        nodeList.add(new HuffmanNode('p', 6, "111"));
        nodeList.add(new HuffmanNode('a', 6, "110"));
        nodeList.add(new HuffmanNode('1', 1, "00110"));
        nodeList.add(new HuffmanNode('\n', 5, "100"));
        nodeList.add(new HuffmanNode('2', 1, "00111"));
        nodeList.add(new HuffmanNode('4', 1, "10100"));
        nodeList.add(new HuffmanNode('5', 1, "10101"));
        nodeList.add(new HuffmanNode('6', 1, "10110"));
        return nodeList;
    }

    /**
     * Answer to q1(b)
     * @return arrayList consisting HuffmanNodes, where each node stores info about an encoded character
     */
    public static ArrayList<HuffmanNode> q1bEncodeChar(){
        /*TODO: insert nodes into an ArrayList, where each node stores the frequency and path of a character */
        ArrayList<HuffmanNode> nodeList = new ArrayList<HuffmanNode>();
        //Example code on adding nodes: nodeList.add(new HuffmanNode('p', 2, "0001"));
        nodeList.add(new HuffmanNode('T', 1, "11110"));
        nodeList.add(new HuffmanNode('h', 2, "1101"));
        nodeList.add(new HuffmanNode('i', 3, "010"));
        nodeList.add(new HuffmanNode('s', 4, "101"));
        nodeList.add(new HuffmanNode(' ', 4, "100"));
        nodeList.add(new HuffmanNode('c', 1, "11111"));
        nodeList.add(new HuffmanNode('l', 1, "0001"));
        nodeList.add(new HuffmanNode('a', 2, "0111"));
        nodeList.add(new HuffmanNode('e', 2, "1100"));
        nodeList.add(new HuffmanNode('r', 2, "1110"));
        nodeList.add(new HuffmanNode('f', 1, "0000"));
        nodeList.add(new HuffmanNode('v', 1, "0110"));
        nodeList.add(new HuffmanNode('o', 1, "0010"));
        nodeList.add(new HuffmanNode('t', 1, "0011"));
        return nodeList;
    }

    /**
     * Answer to 2(a)
     * @return a list of BNodes
     */
    public static ArrayList<BNode> q2EncodeTree(){
        /*TODO: insert BNodes into a nodeList, where each node stores the data and path of a character */
        ArrayList<BNode> nodeList = new ArrayList<BNode>();
        //example of adding nodes to nodeList : nodeList.add(new BNode(Arrays.asList(17,24)));
        //add leaf nodes
        nodeList.add(new BNode(Arrays.asList(16, 17, 23)));
        nodeList.add(new BNode(Arrays.asList(30, 32)));
        nodeList.add(new BNode(Arrays.asList(42)));
        nodeList.add(new BNode(Arrays.asList(50, 53)));
        nodeList.add(new BNode(Arrays.asList(67)));
        nodeList.add(new BNode(Arrays.asList(78)));
        //add internal nodes
        nodeList.add(new BNode(Arrays.asList(24, 35, 43)));
        nodeList.add(new BNode(Arrays.asList(70)));
        nodeList.add(new BNode(Arrays.asList(60))); // root - pos 8
        //connect root nodes with its children
        nodeList.get(8).addChildren(nodeList.get(6));
        nodeList.get(8).addChildren(nodeList.get(7));
        //connect non-root, internal nodes
        nodeList.get(6).addChildren(nodeList.get(0));
        nodeList.get(6).addChildren(nodeList.get(1));
        nodeList.get(6).addChildren(nodeList.get(2));
        nodeList.get(6).addChildren(nodeList.get(3));
        nodeList.get(7).addChildren(nodeList.get(4));
        nodeList.get(7).addChildren(nodeList.get(5));
        return nodeList;

    }

    public static void main(String[] args) {
        ArrayList<BNode> nodes = q2EncodeTree();
        System.out.println("number of nodes  = "+nodes.size());
        for(int i = nodes.size()-1;i>=0;i--){
            List<BNode> children = nodes.get(i).children;
            String output = "";
            output+="current data = "+nodes.get(i).data+"\n";
            output+="children = ";
            if(children.size()==0){
                output+="None";
            }
            for(int j=0;j<children.size();j++){
                output+=children.get(j).data;
            }
            System.out.println(output);
            System.out.println("____________________");
        }
//        ArrayList<String> testArr = new ArrayList<>();
//        testArr.add("8587748778");
//        ArrayList<String> testArr2 = new ArrayList<>();
//        testArr2.add("9112456711");
//        ArrayList<String> testArr3 = new ArrayList<>();
//        testArr3.add("911");
//        testArr3.add("21");
//        testArr3.add("223445");
//        Person myTestPerson = new Person("Arjun", testArr);
//        Person Benny = new Person("Benny", testArr3);
//        System.out.println(Arrays.toString(Benny.getPhoneNumbers().toArray()));
//        System.out.println(Benny.deletePhoneNumber("911"));
//        System.out.println(Arrays.toString(Benny.getPhoneNumbers().toArray()));
//        ContactList firstContact = new ContactList();
//        System.out.println("test person added? :" + firstContact.createContact(myTestPerson));
//        System.out.println("Benchod added? :" + firstContact.createContact(new Person("Benchod", testArr)));
//        System.out.println("IbinaldO added? :" + firstContact.createContact(new Person("IbinaldO", testArr2)));
//        System.out.println(firstContact.createContact(new Person("Hillary", testArr2)));
//        firstContact.createContact(Benny);
//        System.out.println("Duplicate test: " + firstContact.createContact(Benny));
//        System.out.println(Arrays.toString(firstContact.fetchAllNames()));
//        System.out.println("Delete test: "+ firstContact.deleteContact("Benny"));
//        System.out.println(Arrays.toString(firstContact.fetchAllNames()));
//        System.out.println("Look up ibInaldo: " + firstContact.lookupContact("ibInaldo"));
//        System.out.println("Look up IbinaldO: " + firstContact.lookupContact("IbinaldO"));
//        System.out.println("Range test [Arjun - IbinaldO]: ");
//        System.out.println(Arrays.toString(firstContact.getContactByRange("arjun", "ibinaldo")));
//        System.out.println("Get all phone #s: ");
//        System.out.println(Arrays.toString(firstContact.fetchAllPhoneNumbers()));
//        System.out.println("Delete Ibinaldo: " + firstContact.deleteContact("Ibinaldo"));
//        System.out.println("Delete Ibinaldo again: " + firstContact.deleteContact("Ibinaldo"));
//        System.out.println("Delete Ibi: " + firstContact.deleteContact("Ibi"));
//        System.out.println("Delete Benchod: " + firstContact.deleteContact("Benchod"));
//        System.out.println("Leftover Names: " + Arrays.toString(firstContact.fetchAllNames()));
    }
}
