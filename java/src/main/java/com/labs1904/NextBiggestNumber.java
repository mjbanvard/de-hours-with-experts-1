package com.labs1904;

import java.util.Arrays;

public class NextBiggestNumber {

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
                    System.out.println("Yup, if statement matched");
                    int temp;
                    temp = buildArray[high];
                    buildArray[high] = buildArray[low];
                    buildArray[low] = temp;

        // Create list of elements, stored in indeces above 'i'.
                    int[] ordArr;
                    ordArr = Arrays.copyOfRange( buildArray, (high + 1), numElements );
                    int oLength = ordArr.length;

                    // Call appropriate method to reorder portion of 'buildArray'.
                   if (oLength == 2) {
                        wipe2( buildArray, ordArr );
                    } else if (oLength == 3) {
                        wipe3( buildArray, ordArr );
                    } else if (oLength == 4) {
                        wipe4( buildArray, ordArr );
                    } else if (oLength == 5) {
                        wipe5( buildArray, ordArr );
                    }
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

    private static void wipe2 (int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 2] = orderArr[0];
        buildArr[lnth - 1] = orderArr[1];

    }

    private static void wipe3 (int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 3] = orderArr[0];
        buildArr[lnth - 2] = orderArr[1];
        buildArr[lnth - 1] = orderArr[2];

    }

    public static void wipe4 (int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 4] = orderArr[0];
        buildArr[lnth - 3] = orderArr[1];
        buildArr[lnth - 2] = orderArr[2];
        buildArr[lnth - 1] = orderArr[3];

    }

    public static void wipe5 (int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 5] = orderArr[0];
        buildArr[lnth - 4] = orderArr[1];
        buildArr[lnth - 3] = orderArr[2];
        buildArr[lnth - 2] = orderArr[3];
        buildArr[lnth - 1] = orderArr[4];

    }
}
