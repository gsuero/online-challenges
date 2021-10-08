package com.vmware;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeLists {


    //(1,3,5,7,9)
// (2,4,6,8,10)

// (1,2,3,4,5,6,7,8,9,10)


    public static List<Integer> mergeAndSort(List<Integer> listA, List<Integer> listB) {

        if ((listA == null || listA.isEmpty()) && (listB == null || listB.isEmpty())) {
            return Collections.emptyList();
        } else if (listA == null || listA.isEmpty()) {
            return listB;
        } else if (listB == null || listB.isEmpty()) {
            return listA;
        }

        int aSize = listA.size();
        int bSize = listB.size();
        List<Integer> mergedList = new ArrayList<>(aSize + bSize);

        int biggerList = (aSize > bSize ? aSize : bSize);
        for (int index = 0, a = 0, b = 0 ; index < biggerList; index++) {

            Integer aValue = listA.get(a);
            Integer bValue = listB.get(b);
            if (aValue > bValue) {
                mergedList.add(aValue);
                a++;
            } else if (aValue < bValue) {
                mergedList.add(bValue);
                b++;
            } // if they are both equal values
            // add both values to the merged list
            // increase both a and b indexes.

            // check for both list length, and if one

        }

        return mergedList;
    }
}
