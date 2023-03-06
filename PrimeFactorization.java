import java.util.ArrayList;
import java.util.List;

public class PrimeFactorization {
    
    public static void main(String[] args) {
        int number = 123456;
        int numThreads = 4;
        List<Integer> factors = new ArrayList<>();
        
        // Step 1: Generating list of primes
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(number); i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        
        // Step 2: Dividing the work among threads
        List<List<Integer>> primeSubsets = new ArrayList<>();
        int step = primes.size() / numThreads;
        int startIndex = 0;
        int endIndex = step;
        for (int i = 0; i < numThreads; i++) {
            if (i == numThreads - 1) {
                endIndex = primes.size();
            }
            List<Integer> subset = primes.subList(startIndex, endIndex);
            primeSubsets.add(subset);
            startIndex = endIndex;
            endIndex += step;
        }
        
        // Step 3: Finding the prime factors
        for (List<Integer> subset : primeSubsets) {
            for (int factor : subset) {
                while (number % factor == 0) {
                    factors.add(factor);
                    number /= factor;
                }
            }
        }
        
        if (number > 1) {
            factors.add(number);
        }
        
        // Print the factors
        for (int factor : factors) {
            System.out.print(factor + " ");
        }
    }
}
