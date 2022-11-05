import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int numberToTest = 30;
        List<Integer> primes = new ArrayList<>();

        int indexOfSquareRoot = findPrimesLessThanN(numberToTest, primes);
        System.out.println("Primes:");
        for(int i : primes) {
            System.out.println(i);
        }

        int numberOfTwoFactors = 0;
        for(int i=0;i<primes.size();i++) {
            for(int j=indexOfSquareRoot+1;j<primes.size();j++) {
                int testNumber = primes.get(i) * primes.get(j);
                if(testNumber < numberToTest) {
                    System.out.println("Two Factor Composite: " + testNumber);
                    numberOfTwoFactors++;
                }
            }
        }
        long twoFactorCompositesLessThanSqrt = (factorial(indexOfSquareRoot+1) / (factorial(indexOfSquareRoot-1) * 2L)) + indexOfSquareRoot+1L;
        System.out.println("Final count of two factors: " + (numberOfTwoFactors + twoFactorCompositesLessThanSqrt));
    }

    //TODO: Switch to a sieve
    private static int findPrimesLessThanN(int n, List<Integer> retVal) {
        int squareRootIndex = -1;
        int root = (int) Math.sqrt( n);
        System.out.println("Root: " + root);

        for(int i=2; i<n; i++) {
            if(isPrime(i)) {
                retVal.add(i);
                if(i<=root) {
                    squareRootIndex++;
                }
            }
        }
        System.out.println("largest index >= square root of test number: " + squareRootIndex);
        return squareRootIndex;
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

    private static long factorial(int n) {
        long retVal = 1;
        for(int i=2; i<=n; i++) {
            retVal = retVal * i;
        }
        return retVal;
    }
}