package com.labs1904;

import java.util.Arrays;

public class NextBiggestNumber {

        // This class will successfully run for a positive of any length, not just the 6 digits within
        // the 150 test cases. Added one test case to TestNextBiggestNumberTest.java to check. Not an
        // exhaustive testing strategy, but a great proof of concept.

    public static void main(String[] args) {
        Integer input = Integer.parseInt( args[0] );
        int nextBiggestNumber = getNextBiggestNumber( input );
        System.out.println( "Input: " + input );
        System.out.println( "Next biggest number: " + nextBiggestNumber );
    }

    public static int getNextBiggestNumber(Integer integ) {

        //Break apart Integer.
        //Run a loop to compare relative values of different configurations.
        //nBN > i, but < all other orientations.
        //Focus on last two digits, then last three digits, the last four digits. etc...
        //Return out of loop, once nBN is found.

        String iString = integ.toString();
        int numElements = iString.length();
        int rebuiltInt = 0;
        int[] buildArray = new int[numElements];
        for (int i = 0; i < numElements; i++) {
            buildArray[i] = iString.charAt( i ) - '0';
        }

        // Now that the array is created, we need to make a method that juggles the indeces, eliminates the
        // values less than the original number, and narrows down the remaining numbers to find the one that is
        // "just greater than" the given input.

        outer: for (int i = 2; i <= numElements; i += 1)
            for (int j = 1; j < i; j += 1) {
                int low = numElements - j;
                int high = numElements - i;
                if (buildArray[low] > buildArray[high]) {
        // Swap compared values
                    int temp;
                    temp = buildArray[high];
                    buildArray[high] = buildArray[low];
                    buildArray[low] = temp;

        // Create list of elements, stored in indeces above 'high'.
                    int[] ordArr;
                    ordArr = Arrays.copyOfRange( buildArray, (high + 1), numElements );
                    wipeAll( buildArray, ordArr );

        // Once the match is found, end both loops by breaking outer loop
                    break outer;
                }
            }


        // This rebuilds the appropriate array into an int to return.
            for (int k = 0; k < numElements; k++) {
                rebuiltInt *= 10;
                rebuiltInt += buildArray[k];
            }
        if (integ < rebuiltInt) {
            return rebuiltInt;
        } else {
            return -1;
        }
    }

    public static void wipeAll (int[] buildArr, int [] orderArr) {
        int lnth = buildArr.length;
        int oLnth = orderArr.length;

        Arrays.sort(orderArr);
        for (int m = 0; m < oLnth; m++) {
            buildArr[lnth - (oLnth - m)] = orderArr[m];
        }
    }
}
