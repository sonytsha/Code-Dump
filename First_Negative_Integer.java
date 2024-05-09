/*We have been given an integer array Arr[N] and an integer k, 0<k<=N. Find the first negative integer in every subarray of size k.

Input: Arr[6]  = {6. -2, -1, 7, 9, -3}. k=4
Output: -2, 0, 0, -1

Note: In case a subarray contains no negative element, print zero

Approach:

Brute Force - Create a nested loop to consider all the subarrays of size k and find the negative element in the subarray.
Time complexity: O((N-k+1)k)

Using Queue - Here, the positive numbers are useless for us. Therefore, we can keep storing the negative elements in a queue, such that for each subarray the first element of the queue will be the required answer.
Time complexity: O(N)
Space complexity: O(N)  */

import java.util.ArrayDeque;
import java.util.Deque;

public class First_Negative_Integer {
    public static void main(String[] args) {
        int[] arr = {6, -2, -1, 7, 9, -3};
        int k = 4;
        int[] result = firstNegativeNumber(arr, k);
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println(); // Print a newline at the end
    }

    public static int[] firstNegativeNumber(int[] arr, int k) {
        int n = arr.length;
        Deque<Integer> dq = new ArrayDeque<>();
        int[] result = new int[n - k + 1]; // Results array to hold first negative for each window
        
        // Initialize deque for the first window
        for (int i = 0; i < k; i++) {
            if (arr[i] < 0) {
                dq.offerLast(i); // Add index of negative numbers
            }
        }

        // Slide the window from k to end of array
        for (int i = k; i < n; i++) {
            // The first element in the deque is the first negative number of the previous window
            result[i - k] = dq.isEmpty() ? 0 : arr[dq.peekFirst()];
            
            // Remove elements not within the window
            while (!dq.isEmpty() && dq.peekFirst() < i - k + 1) {
                dq.pollFirst();
            }
            
            // Add current element if it is negative
            if (arr[i] < 0) {
                dq.offerLast(i);
            }
        }

        // For the last window
        result[n - k] = dq.isEmpty() ? 0 : arr[dq.peekFirst()];
        
        return result;
    }
}
