package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Nicholas Caesar
 */
public class IntegerSet{

    private ArrayList<Integer> set = new ArrayList<>();

    public boolean isEmpty(){
        // Returns true if the set is empty, false otherwise.
        return set.isEmpty();
    }

    public void clear(){
        // Removes all elements from the set.
        set.clear();
    }


    public int length(){
        // Returns the number of elements in the set.
        return set.size();
    }

    public boolean contains(int value){
        // returns either true if the value is in the set and false if the value is not in the set
        return set.contains(value);
    }

    public void remove(int item){
        // checks if the number is in the list and if it is then it removes it but if its not then it skips 
        set.remove(Integer.valueOf(item));
    }

    public void add(int item){
        // checks to see if the item is already in the set and if not, adds it. skip duplicates
        if (!set.contains(item)) {
            set.add(item);
        }

    }


    public int largest(){
        // Returns the largest element in the set and throws the IllegalStateException if the set is empty
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.max(set);
    }

    public int smallest(){
        // returns the samllest element in the set and throws the IllegalStateException if the set is empty
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty");
        }
        return Collections.min(set);
    }

    
    public boolean equals(IntegerSet b){

        // equals(IntegerSet b)
        // Returns true if both sets contain exactly the same elements.
        //     Examples:
        //     [1, 2, 3] and [3, 2, 1] -> true
        //     [1, 2, 3] and [1, 2] -> false

        if (!(b instanceof IntegerSet)) {
            return false;
        }

        if (set.size() != b.set.size()) {
            return false;
        }
        for (int item : set) {
            if (!b.set.contains(item)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        //You must override the default toString() implementation defined in Object. Use the @Override annotation.
        //Required output format:
        //  [1, 2, 3]
        //  •	Values must appear in ascending order.
        //  •	Values must be separated by a comma and a single space.
        //  •	No extra spaces are allowed.
        //  •	An empty set must return [].
        //  •	You may use Collections.sort(yourList) if needed.

        Collections.sort(set);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < set.size(); i++) {
            sb.append(set.get(i));
            if (i < set.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }





    public IntegerSet union(IntegerSet intSetb){
        // union(IntegerSet b)
        //     Returns a new set containing all elements that appear in either set.
        //     Example:
        //     Set1 = [1, 2, 3]
        //     Set2 = [2, 3, 4]
        //     Result = [1, 2, 3, 4]
        IntegerSet result = new IntegerSet();
        for (int item : set) {
            result.add(item);
        }
        for (int item : intSetb.set) {
            result.add(item);
        }
        return result;

    }

    public IntegerSet intersect(IntegerSet intSetb){
        /*intersect(IntegerSet b)
            Returns a new set containing only elements common to both sets.
            Example:
            Set1 = [1, 2, 3]
            Set2 = [2, 3, 4]
            Result = [2, 3]*/
        IntegerSet result = new IntegerSet();
        for (int item : set) {
            if (intSetb.set.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }
    
    public IntegerSet diff(IntegerSet intSetb){
        // diff(IntegerSet b)
        //     Returns a new set containing elements in the current set but not in b.
        //     Example:
        //     Set1 = [1, 2, 3]
        //     Set2 = [2, 3, 4]
        //     Result = [1]
        IntegerSet result = new IntegerSet();
        for (int item : set) {
            if (!intSetb.set.contains(item)) {
                result.add(item);
            }
        }
        return result;
        
    }

    public IntegerSet complement(IntegerSet intSetb){
        // complement(IntegerSet b)
        //     Returns a new set containing elements in b but not in the current set.
        //     Example:
        //     Set1 = [1, 2, 3]
        //     Set2 = [2, 3, 4]
        //     Result = [4]

        IntegerSet result = new IntegerSet();
        for (int item : intSetb.set) {
            if (!set.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

}
