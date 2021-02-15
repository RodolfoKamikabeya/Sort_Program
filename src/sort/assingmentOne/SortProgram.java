package sort.assingmentOne;

import java.util.Random;

public class SortProgram {

    /*Project: Sorting Arrays

    * Name: Rodolfo Kamikabeya

    */

    private static void PrintArray(int[] arr) {

        for(int i=0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static int BubbleSort(int[] Barr) {
        int counterSwap= 0;
        for (int i = 0; i < Barr.length-1; i++)
            for (int j = 1; j < Barr.length-i; j++)
                if (Barr[j-1] > Barr[j])
                {
                    // swap arr[j+1] and arr[j]
                    int temp = Barr[j-1];
                    Barr[j-1] = Barr[j];
                    Barr[j] = temp;
                    counterSwap++;
                }
        return counterSwap;
    }
    //  We can reduce the time complexity to O(n) if the array is already sorted.
    private static int BubbleSortFlag (int[] arr){
        int counterSwap= 0;
        boolean flag = true;
        for (int i=0; i<arr.length;i++){

            for (int j=1; j<arr.length-i;j++){
                if(arr[j-1]>arr[j]){
                    int temp = arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = temp;
                    flag=false;
                    counterSwap++;
                }
            }
            if (flag ==true){

                break;
            }
        }
        return counterSwap;
    }

    private static int SelectionSort(int[] arr) {
        int counterSwap =0;

        for (int i = 0; i < arr.length; i++) {
            // min is the index of the smallest element with an index greater or equal to i
            int min = i;
            //sort search for the smallest element of the unsorted array
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                    counterSwap++;
                }
            }
            // Swapping i-th and min-th elements
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        return counterSwap;
    }

    private static int InsertionSort(int[] arr) {
        int countSwap =0;
        //base case
        if (arr.length <= 1) {
            return 0;
        }
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;

            while (j > 0 && temp < arr[j-1]) {
                arr[j] = arr[j-1];
                --j;
                //increase the count as element swap is happened
                countSwap++;
            }
            arr[j] = temp;

        }
        return countSwap;
    }

    private static int ShellSort(int[] arr) {
        int counterSwap =0;
        // Check and reduce the gap
        for (int gap = arr.length/2; gap > 0; gap /= 2) {
            // keep adding one more element until the entire array is
            // gap sorted
            for (int i = gap; i < arr.length; i+= 1) {
                // save a[i] in temp and make a hole at position i
                int temp = arr[i];

                // shift earlier gap-sorted elements up until the correct location for a[i] is found
                int j;
                for (j = i; j >= gap && arr[j-gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                    counterSwap++;
                }
                //  put temp (the original a[i]) in its correct location
                arr[j] =temp;

            }
        }
        return counterSwap;
    }

    private static int MergeSort(int[]arr, int len){
        //recursive control
        if(len<2){
            return 0;
        }
        int midpoint = len/2;
        int[] left = new int[midpoint];
        int[] right = new int[len-midpoint];

        //Dividing array into two and copying into two separate arrays
        int k = 0;
        for(int i = 0;i< arr.length;++i){
            if(i<midpoint){
                left[i] = arr[i];
            }
            else{
                right[k] = arr[i];
                k = k+1;
            }
        }
        // Recursively calling the function to divide the subarrays further
        MergeSort(left,midpoint);
        MergeSort(right,len-midpoint);
        // Calling the merge method on each subdivision
      return merge(arr,left,right, midpoint, len-midpoint);

    }

    //to get the result array to merge the left and right array
    private static int merge(int arr[], int[] left, int[] right, int left_size, int right_size){

        int counterSwap=0;
//        int[] result = new int[left.length +right.length];
        int resultPointer=0,leftPointer=0,rightPointer = 0;

        //check if has elements in the arrays
        while(leftPointer<left_size&& rightPointer<right_size){

            if(left[leftPointer]<right[rightPointer]){
                arr[resultPointer++] = left[leftPointer++];
            }
            else{
                arr[resultPointer++] = right[rightPointer++];
                counterSwap ++;
            }
        }
        while(leftPointer<left_size){
            arr[resultPointer++] = left[leftPointer++];
        }
        while(rightPointer<right_size){
            arr[resultPointer++] = right[rightPointer++];
        }
    return counterSwap;
    }


    public static int quickSort(int[] arr, int low, int high) {
        //check for empty or null array
        if (arr == null || arr.length == 0){
            return 0;
        }

        if (low >= high){
            return 0;
        }

        //Get the pivot element from the middle of the list
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];
        int countSwap =0;
        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j)
        {
            //Check until all values on left side array are lower than pivot
            while (arr[i] < pivot)
            {
                i++;

            }
            //Check until all values on left side array are greater than pivot
            while (arr[j] > pivot)
            {
                j--;
            }
            //Now compare values from both side of lists to see if they need swapping
            //After swapping move the iterator on both lists
            if (i <= j)
            {
                if(i==j){countSwap--;}

                swap (arr, i, j);
                i++;
                j--;
                countSwap++;

            }

        }
        //Do same operation as above recursively to sort two sub arrays
        if (low < j){
            quickSort(arr, low, j);
        }
        if (high > i){
            quickSort(arr, i, high);
        }
        return countSwap;
    }

    public static void swap (int array[], int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    public static void main (String[] args){

        int indexes = 100;
        int[]arr = new int [indexes]; // Creates an array with 1000 elements
        int[]BestCaseArr = new int[indexes];
        int[]WorstCaseArr = new int[indexes];
        // initialize each value at index i to the index limit (just positive numbers)
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = i; // [0,1,2,3,...,ind]
            BestCaseArr[i]=i;
        }
        int x =0;
        // initialize each value at index k = 1000 and invert to descending order
        for (int k = arr.length-1; k > 0; k--)
        {
            WorstCaseArr[x]=k;
            x++;
        }

        Random randomNumber = new Random (); //Creates a Random Object
        int randomIndexLocation; // variable to store the index each time the loop is called
        int randomIndexValue; // variable to store the true value from the array each time the loop is called

        for(int j =0; j<arr.length;j++){
            randomIndexLocation= randomNumber.nextInt(indexes); // stores random integer in our variable

            //swap values (it can be done by Collections.shuffle(Arrays.asList(arr))
            randomIndexValue =arr[randomIndexLocation];
            arr[randomIndexLocation]= arr[j];
            arr[j] = randomIndexValue;

        }
        System.out.println("Best Case Array");
        PrintArray(BestCaseArr);

        System.out.println("\n\nWorst Case Array");
        PrintArray(WorstCaseArr);

        System.out.println("\n\nRandom Array");
        PrintArray(arr);

        // Creating Worst Case and Random Case arrays for Bubble Sort
        int[] BWCarr =new int [arr.length], BACarr =new int [arr.length];

        // Creating Worst Case and Random Case arrays for Bubble Sort With Flag
        int[] BFWCarr =new int [arr.length], BFACarr =new int [arr.length];

        //  Creating Worst Case and Random Case arrays for Selection Sort
        int[] SEWCarr =new int [arr.length], SEACarr =new int [arr.length];

        //  Creating Worst Case and Random Case arrays for Insertion Sort
        int[] ISWCarr =new int [arr.length], ISACarr =new int [arr.length];

        //  Creating Worst Case and Random Case arrays for Shell Sort
        int[] SSWCarr =new int [arr.length], SSACarr =new int [arr.length];

        //  Creating Worst Case and Random Case arrays for Merge Sort
        int[] MEWCarr =new int [arr.length], MEACarr =new int [arr.length];

        //  Creating Worst Case and Random Case arrays for Quick Sort
        int[] QSWCarr =new int [arr.length], QSACarr =new int [arr.length], QSACarr2 =new int [arr.length];


        for( int y=0;y< arr.length;y++){
            BWCarr[y] = BFWCarr[y] = SEWCarr[y] = ISWCarr[y] = SSWCarr[y] = MEWCarr[y] = QSWCarr[y] = WorstCaseArr[y];
            BACarr[y] = BFACarr[y] = SEACarr[y] = ISACarr[y] = SSACarr[y] = MEACarr[y] = QSACarr[y] = QSACarr2[y]=arr[y];
        }
        int swap_BPoor_WC = BubbleSort(BWCarr);
        int swap_BPoor_BC = BubbleSort(BestCaseArr);
        int swap_BPoor_AC = BubbleSort(BACarr);

        int swap_BF_WC = BubbleSortFlag(BFWCarr);
        int swap_BF_BC = BubbleSortFlag(BestCaseArr);
        int swap_BF_AC = BubbleSortFlag(BFACarr);

        int swap_SE_WC = SelectionSort(SEWCarr);
        int swap_SE_BC = SelectionSort(BestCaseArr);
        int swap_SE_AC = SelectionSort(SEACarr);

        int swap_IS_WC = InsertionSort(ISWCarr);
        int swap_IS_BC = InsertionSort(BestCaseArr);
        int swap_IS_AC = InsertionSort(ISACarr);

        int swap_SS_WC = ShellSort(SSWCarr);
        int swap_SS_BC = ShellSort(BestCaseArr);
        int swap_SS_AC = ShellSort(SSACarr);

        int swap_ME_WC = MergeSort(MEWCarr,MEWCarr.length);
        int swap_ME_BC = MergeSort(BestCaseArr,BestCaseArr.length);
        int swap_ME_AC = MergeSort(MEACarr,MEACarr.length);

        int swap_QS_WC = quickSort(QSWCarr,0,QSWCarr.length-1);
        int swap_QS_BC = quickSort(BestCaseArr,0,BestCaseArr.length-1);
        int swap_QS_AC = quickSort(QSACarr,0,QSACarr.length-1);

        System.out.println("\n\nArray Size: " +indexes);
        System.out.println("\t\t\t\t\tTable\t\tOF\t\tResults");
        System.out.println("\n\t\t\t\t\t     \t\t#of\t      ");
        System.out.println("\n\t\t\t\t\t     \t\tswaps\t      ");
        System.out.println("\n\t\t\t\t\tWC   \t\t BC\t\t\t\t AC");

        Object[][] table = new String[7][];
        table[0] = new String[] { "Bubble_sort_poor   ",Integer.toString(swap_BPoor_WC), Integer.toString(swap_BPoor_BC),Integer.toString(swap_BPoor_AC) };
        table[1] = new String[] { "Bubble_sort_smart  ",Integer.toString(swap_BF_WC), Integer.toString(swap_BF_BC),Integer.toString(swap_BF_AC)};
        table[2] = new String[] { "Insertion Sort     ",Integer.toString(swap_IS_WC), Integer.toString(swap_IS_BC),Integer.toString(swap_IS_AC) };
        table[3] = new String[] { "Selection Sort     ",Integer.toString(swap_SE_WC), Integer.toString(swap_SE_BC),Integer.toString(swap_SE_AC) };
        table[4] = new String[] { "Shell Sort         ",Integer.toString(swap_SS_WC), Integer.toString(swap_SS_BC),Integer.toString(swap_SS_AC)};
        table[5] = new String[] { "Merge Sort         ",Integer.toString(swap_ME_WC), Integer.toString(swap_ME_BC),Integer.toString(swap_ME_AC) };
        table[6] = new String[] { "Quick Sort         ",Integer.toString(swap_QS_WC), Integer.toString(swap_QS_BC),Integer.toString(swap_QS_AC) };

        //System.out.format("%-15s%-15s%-15s%-15s\n", "","WC", "BC", "AC");
        for (int i = 0; i < table.length; i++) {
            Object[] row = table[i];
            System.out.format("%-15s%-15s%-15s%-15s\n",row[0], row[1], row[2], row[3]);
        }

    }
}
