import java.util.*;   

public class ContactList {
	
	// Add instance variables here
    private BST contactList = new BST<>();


    public boolean createContact(Person person) {
        return this.contactList.insert(person);
    }

    public boolean lookupContact(String name) {
        Person temp = new Person(name, new ArrayList<>());
        return this.contactList.getPerson(temp) != null;
    }

    public Person getContact(String name) {
        Person temp = new Person(name.toLowerCase(), new ArrayList<>());
        return this.contactList.getPerson(temp);
    }

    public Person[] getContactByRange(String start, String end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException();
        }
        if (start.compareTo(end) >= 0) {
            throw new IllegalArgumentException();
        }
        Person startP = new Person(start.toLowerCase(), new ArrayList<>());
        Person endP = new Person(end.toLowerCase(), new ArrayList<>());
        return this.contactList.getPersonByRange(this.contactList.getRoot(), startP, endP);
        // use DFS on the tree and make sure to return once you've found the end bc it's exclusive
    }

    public boolean deleteContact(String name) {
        Person temp = new Person(name, new ArrayList<>());
        return this.contactList.delete(temp);
    }

    public int size() {
        return this.contactList.size();
    }

    public String[] fetchAllNames() {
        return this.contactList.fetchAllNames();
    }

    public String[] fetchAllPhoneNumbers() {
        return this.contactList.fetchAllNumbers();
    }
}
