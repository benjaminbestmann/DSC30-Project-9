import java.util.*;   

public class Person {
	
    // Add instance variables here
    public ArrayList<String> numbersList;
    public String name;
	
	public Person(String name, ArrayList<String> pnArray) {
        this.numbersList = pnArray;
        this.name = name;
	}
	
    public String getName() {
        return this.name;
    }

    public boolean addPhoneNumber(String pn) {
        if (this.numbersList.contains(pn)) {
            return false;
        }
        numbersList.add(pn);
        return true;
    }

    public ArrayList<String> getPhoneNumbers() {
        // Return an array of all phone numbers in ascending order
        ArrayList<String> temp = this.numbersList;
        Collections.sort(temp);
        return temp;
    }

    public boolean deletePhoneNumber(String pn) {
        if (numbersList.contains(pn)) {
            if (numbersList.size() <= 1) {
                throw new IllegalArgumentException();
            } else {
                numbersList.remove(pn);
                return true;
            }
        } else {
            return false;
        }
    }
}
