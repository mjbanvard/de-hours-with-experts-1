package com.labs1904;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        int strLength = iString.length();
        int[] buildArray = new int[strLength];

        for (int i = 0; i < strLength; i++) {
            buildArray[i] = iString.charAt( i ) - '0';
        }
        Integer rebuiltInt = 0;


        // Now that the array is created, we need to make a method that juggles the indeces, eliminates the
        // values less than the original number, and narrows down the remaining numbers to find the one that is
        // "just greater than" the given input.

        // Values of each index, inverted into alphabetical order.

        for (int i = 1; i < strLength; i++) {
            int[] ordArr;
            if (buildArray[(strLength - i)] > buildArray[(strLength - i - 1)]) {
                ordArr = Arrays.copyOfRange( buildArray, (strLength - i - 1), strLength );
                int ordLength = ordArr.length;
                List<Integer> preOrdList = IntStream.of( ordArr ).boxed().collect( Collectors.toList() );
                preOrdList.remove( ordLength - i );
                ordArr = preOrdList.stream().mapToInt( Integer::intValue ).toArray();
                buildArray[strLength - i - 1] = buildArray[strLength - i];
//                }
                int oLength = ordArr.length;
                if (oLength == 3) {
                    wipe3( buildArray, ordArr );
                }
                if (oLength == 4) {
                    wipe4( buildArray, ordArr );
                }
                if (oLength == 5) {
                    wipe5( buildArray, ordArr );
                }

                // This rebuilds the appropriate array into an Integer to return.
                for (int k = 0; k < strLength; k++) {
                    rebuiltInt *= 10;
                    rebuiltInt += buildArray[k];
                }

                if (rebuiltInt <= integ) {
                    rebuiltInt = -1;
                }
            }
        }

        int finalInt = rebuiltInt;
        return finalInt;
    }

    private static void wipe3 ( int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 3] = orderArr[0];
        buildArr[lnth - 2] = orderArr[1];
        buildArr[lnth - 1] = orderArr[2];
    }

    public static void wipe4 ( int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 4] = orderArr[0];
        buildArr[lnth - 3] = orderArr[1];
        buildArr[lnth - 2] = orderArr[2];
        buildArr[lnth - 1] = orderArr[3];
    }

    public static void wipe5 ( int[] buildArr, int[] orderArr){

        int lnth = buildArr.length;

        Arrays.sort( orderArr );
        buildArr[lnth - 5] = orderArr[0];
        buildArr[lnth - 4] = orderArr[1];
        buildArr[lnth - 3] = orderArr[2];
        buildArr[lnth - 2] = orderArr[3];
        buildArr[lnth - 1] = orderArr[4];
    }
}
