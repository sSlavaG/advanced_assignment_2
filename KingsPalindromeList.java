import java.util.*;


/**
 * Reads a list of numbers, and can reconstruct the corresponding list of Palindromes,
 * produce the size of the largest magic set, and the content of that magic set.
 * 
 * Usage:
 * TODO: Documentation
 * 
 * 
 * 
 * 
 * END TODO
 * 
 * @author
 * @ID
 * @author
 * @ID 
 * 
 */
class KingsPalindromeList {
      
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int taskNumber = scanner.nextInt();

        int listLength = scanner.nextInt();

        int index = 0;
        long[] wrongList = new long[listLength]; // Creating list for the input elements

        while (index < listLength) {
            long wrongPalindrome = scanner.nextLong();
            wrongList[index] = wrongPalindrome;
            index++;
        }

        // Switch statement that executes appropriate task depending on variable taskNumber
        switch (taskNumber) {
            case 1:
                /*
                 * For-loop to display the result of function
                 * restorePalindromes on parameter wrongList
                 */
                for (long numToDisplay : restorePalindromes(wrongList)) {
                    System.out.print(numToDisplay + " ");
                }
                break;

            case 2: 
                // Displaying the number of elements of the largest magic set

                System.out.println(findMagicSet(restorePalindromes(wrongList)).size());
                break;

            case 3:
                /*
                 * For-loop to display the result of function findMagicSet
                 * on correct list that is obtained from restorePalindromes(wrongList)
                 */

                for (Long numToDisplay : findMagicSet(restorePalindromes(wrongList))) {
                    System.out.print(numToDisplay + " ");

                }
                break;

            default:
                break;

        }

        // Closing the scanner
        scanner.close();


    }


    /*
     * Function that checks whether the given parameter of type long is palindrome.
     * Returns true if it is palindrome, false if it is not a palindrome
     * 
     */
    private static boolean isPalindrome(long num) {
        /*
         * Converting the given number from type Long to type String to 
         * make use of .charAt method
         */
        String numToString = Long.toString(num); 

        int frontPointer = 0;
        int rearPointer = numToString.length() - 1;

        for (int i = 0; i < numToString.length() / 2; i++) {
            if (numToString.charAt(frontPointer) != numToString.charAt(rearPointer)) {
                return false;
            }

            frontPointer++;
            rearPointer--;
            

        }

        return true;
        
    }

    /*
     * Function that obtains the correct list of palindromes from the given list by
     * replacing each number in the list with the smallest palindrome greater than or equal
     * to that number.
     * 
     */

    private static long[] restorePalindromes(long[] array) {
        for (int i = 0; i < array.length; i++) {
            // Variable to store the element of the list in the current iteration
            long currentNumber = array[i];
            
            while (!isPalindrome(currentNumber)) {
                currentNumber++;
            }

            array[i] = currentNumber;


        }

        return array;

    }

    private static ArrayList<Long> findMagicSet(long[] correctList) {

        // Creating set from correctList of palindromes

        Set<Long> setOfPalindromes = new HashSet<>();

        for (long num : correctList) {
            setOfPalindromes.add(num);
        }
        
        // Sorting the correctList by BubbleSort
        for (int i = 0; i < correctList.length; i++) {
            for (int j = 1; j < correctList.length; j++) {
                if (correctList[j] > correctList[j - 1]) {
                    long tempVar = correctList[j];
                    correctList[j] = correctList[j - 1];
                    correctList[j - 1] = tempVar;
                }
            }
        }

        // Creating the ArrayList that will store palindromes of the magic set

        ArrayList<Long> magicSet = new ArrayList<>();


        
        // Iterating over the sorted correct list of palindromes starting with the largest
        for (int i = 0; i < correctList.length; i++) {

            /*
            * Converting current iteration of the palindrome from type Long to type String to 
            * make use of .substring method
            */

            String strFromLong = Long.toString(correctList[i]);


            // Iterating over the substrings of the current largest palindrome
            for (int digitIndex = 1; digitIndex <= strFromLong.length() / 2; digitIndex++) {

                /* 
                 * Variable used to store the current substring of the palindrome that 
                 * needs to be checked on whether it is in the set of correct palindromes 
                 */
                String substringToCheck = strFromLong.substring(
                    digitIndex, strFromLong.length() - digitIndex);

                /*
                 * If substring of the largest palindrome is in the set, add it to the magic set
                 * converting back from type String to Long
                 */

                if (setOfPalindromes.contains(Long.parseLong(substringToCheck))) {
                    
                    magicSet.add(Long.parseLong(substringToCheck));


                }
            }

            /*
             * If magicSet has at leas one element, it means we found the magic set
             * We add the largest palindrome (from which other palindromes were found) to magicSet
             * and break the loop, since we are not interested by magic sets obtained from smaller
             * palindrome
             */
            if (magicSet.size() > 0) {
                magicSet.add(correctList[i]);
                break;
            }
            
        }
        /*
         * If no magic set was found add the largest palindrome to the list
         */
        if (magicSet.size() == 0) {
            magicSet.add(correctList[0]);
        }

        //Sorting the magic set in ascending order
        Collections.sort(magicSet);

        return magicSet;


    }
    

}
