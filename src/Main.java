import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberToTest = 30;
        List<Integer> primes = findPrimesLessThanN(numberToTest);
        System.out.println("Primes:");
        for(int i : primes) {
            System.out.println(i);
        }
        int root = (int) Math.sqrt( numberToTest);
        System.out.println("Root: " + root);

        for(int i=0;i<primes.size();i++) {
            for(int j=i;j<primes.size();j++) {
                int testNumber = primes.get(i) * primes.get(j);
                if(testNumber < numberToTest) {
                    System.out.println("Two Factor Composite: " + testNumber);
                }
            }
        }
    }

    //TODO: Switch to a sieve
    private static List<Integer> findPrimesLessThanN(int n) {
        List<Integer> retVal = new ArrayList<>();
        for(int i=2; i<n; i++) {
            if(isPrime(i)) {
                retVal.add(i);
            }
        }
        return retVal;
    }

    //TODO: Naive implementation, fix this
    private static boolean isPrime(int n) {
        for (int i=2;i<n;i++) {
            if(n%i==0) {
                return false;
            }
        }
        return true;
    }
}