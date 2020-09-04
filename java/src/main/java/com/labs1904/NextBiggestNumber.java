package com.labs1904;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NextBiggestNumber {

    public static void main(String[] args) {
        Integer input = Integer.parseInt(args[0]);
        int nextBiggestNumber = getNextBiggestNumber(input);
        System.out.println("Input: " + input);
        System.out.println("Next biggest number: " + nextBiggestNumber);
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

//        int[] compBuildArray = new int[arrLength];

        for (int i = 0; i < strLength; i++) {
            buildArray[i] = iString.charAt(i) - '0';
        }
        int bldLength = strLength;

        // Now that the array is created, we need to make a method that juggles the indeces, eliminates the
        // values less than the original number, and narrows down the remaining numbers to find the one that is
        // "just greater than" the given input.

        // Values of each index, inverted into alphabetical order.

//        if (bldLength == 2) {
//            twoFer(buildArray);
//        } else if (bldLength == 3) {
//            threeFer(buildArray);
//        } else if (bldLength > 3) {
            for (int i = 1; i < bldLength; i++) {
                int[] ordArr = new int[i];
                if (buildArray[(bldLength - i)] > buildArray[(bldLength - i - 1)]) {
                    ordArr = Arrays.copyOfRange(buildArray, (bldLength - i - 1 ), bldLength);
                    int ordLength = ordArr.length;
                    List<Integer> preOrdList = IntStream.of(ordArr).boxed().collect(Collectors.toList());
                    preOrdList.remove(ordLength - i);
                    ordArr = preOrdList.stream().mapToInt(Integer::intValue).toArray();
                    buildArray[bldLength - i - 1] = buildArray[bldLength - i ];
//                }
                int oLength = ordArr.length;
                if (oLength == 3) {
                    wipe3(buildArray, ordArr);
                }
                if (oLength == 4) {
                    wipe4(buildArray, ordArr);
                }
                if (oLength == 5) {
                    wipe5(buildArray, ordArr);
                }

                //    public static int[] fourFer(int[] bld4Arr) {
//        // Method for 4 digit integer. Requires a sub array to order the remaining
//        // digits, below the digit swapped.
//        //
//        // There should be a way to build in recursion.
//
//        int aLength = bld4Arr.length;
//        int a = bld4Arr[aLength - 1];
//        int b = bld4Arr[aLength - 2];
//        int c = bld4Arr[aLength - 3];
//        int d = bld4Arr[aLength - 4];
//
//        if (a > b) {
//            bld4Arr[2] = a;
//            bld4Arr[3] = b;
//        } else if (a > c) {
//            bld4Arr[1] = a;
//            bld4Arr[2] = c;
//            bld4Arr[3] = b;
//        } else if (b > c) {
//            bld4Arr[1] = b;
//            bld4Arr[2] = a;
//            bld4Arr[3] = c;
//        } else if (a > d) {
//            bld4Arr[0] = a;
//            int[] ordArr = {b, c, d};
//            wipe3(bld4Arr, ordArr);
//        } else if (b > d) {
//            bld4Arr[0] = b;
//            int[] ordArr = {a, c, d};
//            wipe3(bld4Arr, ordArr);
//        } else if (c > d) {
//            bld4Arr[0] = c;
//            int[] ordArr = {a, b, d};
//            wipe3(bld4Arr, ordArr);
//        }
//
//        return bld4Arr;
//    }
//
//    public static int[] fiveFer(int[] bld5Arr) {
//        // Method for 5 digit integer. Requires a sub array to order the remaining
//        // digits, below the digit swapped.
//        //
//        // There should be a way to build in recursion.
//
//        int aLength = bld5Arr.length;
//        int a = bld5Arr[aLength - 1];
//        int b = bld5Arr[aLength - 2];
//        int c = bld5Arr[aLength - 3];
//        int d = bld5Arr[aLength - 4];
//        int e = bld5Arr[aLength - 5];
//
//        // Ideal form of method:
//        // Find element (array[length - x]) that is greater than previous element (array[length - x - 1])
//        // Call method with array[e] and array.
//        // array.toArrayList.remove(array[e]).toArray => ordArr
//        //
//        // for (int i = 1; i <= aLength; i++) {
//        //      if (array[aLength - i] > array[aLength - i - 1]) {
//        //          int[] ordArr = array.copyOfRange((aLength - i -1), aLength).toArrayList.remove(array[array.length - i]).toArray
//        //          array[aLength - i - 1] = array[aLength - i]
//        //          }
//        //      int oLength = ordArr.length
//        //      if (oLength == 3) {wipe3(array, ordArr)}
//        //      if (oLength == 4) {wipe4(array, ordArr)}
//        //      if (oLength == 5) {wipe5(array, ordArr)}
//
//        if (a > b) {
//            bld5Arr[3] = a;
//            bld5Arr[4] = b;
//        } else if (a > c) {
//            bld5Arr[2] = a;
//            bld5Arr[3] = c;
//            bld5Arr[4] = b;
//        } else if (b > c) {
//            bld5Arr[2] = b;
//            bld5Arr[3] = a;
//            bld5Arr[4] = c;
//        } else if (a > d) {
//            bld5Arr[1] = a;
//            int[] ordArr = {b, c, d};
//            wipe3(bld5Arr, ordArr);
//        } else if (b > d) {
//            bld5Arr[1] = b;
//            int[] ordArr = {a, c, d};
//            wipe3(bld5Arr, ordArr);
//        } else if (c > d) {
//            bld5Arr[1] = c;
//            int[] ordArr = {b, a, d};
//            wipe3(bld5Arr, ordArr);
//        } else if (a > e) {
//            bld5Arr[0] = a;
//            int[] ordArr = {b, c, d, e};
//            wipe4(bld5Arr, ordArr);
//        } else if (b > e) {
//            bld5Arr[0] = b;
//            int[] ordArr = {a, c, d, e};
//            Arrays.sort(ordArr);
//            wipe4(bld5Arr, ordArr);
//        } else if (c > e) {
//            bld5Arr[0] = c;
//            int[] ordArr = {a, b, d, e};
//            wipe4(bld5Arr, ordArr);
//        } else if (d > e) {
//            bld5Arr[0] = d;
//            int[] ordArr = {b, c, a, e};
//            wipe4(bld5Arr, ordArr);
//        }
//
//        return bld5Arr;
//    }

                private static void wipe3( int[] buildArr, int[] orderArr) {

                    int lnth = buildArr.length;

                    Arrays.sort(orderArr);
                    buildArr[lnth - 3] = orderArr[0];
                    buildArr[lnth - 2] = orderArr[1];
                    buildArr[lnth - 1] = orderArr[2];
                    //  return buildArr;
                }

                public static void wipe4(int[] buildArr, int[] orderArr) {

                    int lnth = buildArr.length;

                    Arrays.sort(orderArr);
                    buildArr[lnth - 4] = orderArr[0];
                    buildArr[lnth - 3] = orderArr[1];
                    buildArr[lnth - 2] = orderArr[2];
                    buildArr[lnth - 1] = orderArr[3];
                    //   return buildArr;
                }

                public static void wipe5(int[] buildArr, int[] orderArr) {

                    int lnth = buildArr.length;

                    Arrays.sort(orderArr);
                    buildArr[lnth - 5] = orderArr[0];
                    buildArr[lnth - 4] = orderArr[1];
                    buildArr[lnth - 3] = orderArr[2];
                    buildArr[lnth - 2] = orderArr[3];
                    buildArr[lnth - 1] = orderArr[4];
                    //   return buildArr;
                }
//            }
//        }
//        if (arrLength == 4) {
//            compBuildArray = fourFer(buildArray);
//        }
//        if (arrLength == 5) {
//            compBuildArray = fiveFer(buildArray);
//        }

            // This rebuilds the appropriate array into an Integer to return.
            Integer rebuiltInt = 0;
            for (int k = 0; k < bldLength; k++) {
                rebuiltInt *= 10;
                rebuiltInt += buildArray[k];
            }

            if (rebuiltInt <= integ) {
                rebuiltInt = -1;
            }
            return rebuiltInt;
        }

    public static int[] twoFer(int[] bld2Arr) {
        // Method for 2 digit integer.

        int a2Length = bld2Arr.length;
        int a = bld2Arr[a2Length - 1];
        int b = bld2Arr[a2Length - 2];

        if (a > b) {
            bld2Arr[0] = a;
            bld2Arr[1] = b;
        }
//        return bld2Arr;
    }

    private static int[] threeFer(int[] bld3Arr) {
        //Simple method for 3 digit integer.

        int aLength = bld3Arr.length;
        int a = bld3Arr[aLength - 1];
        int b = bld3Arr[aLength - 2];
        int c = bld3Arr[aLength - 3];

        if (a > b) {
            bld3Arr[1] = a;
            bld3Arr[2] = b;
        } else if (b > c){
            bld3Arr[0] = b;
            bld3Arr[1] = a;
            bld3Arr[2] = c;
        }

//        return bld3Arr;
    }


}

