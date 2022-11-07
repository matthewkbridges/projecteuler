import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Date());
        int numberToTest = 100000000;
        List<Integer> primes = new ArrayList<>();

        int indexOfSquareRoot = findPrimesLessThanNSieve(numberToTest, primes);

        int numberOfTwoFactors = 0;
        for(int i=0;i<primes.size();i++) {
            for(int j=indexOfSquareRoot+1;j<primes.size();j++) {
                long testNumber = (long)(primes.get(i)) * (long)(primes.get(j));
                if(testNumber < numberToTest) {
                    numberOfTwoFactors++;
                } else {
                    break;
                }
            }
        }
        long twoFactorCompositesLessThanSqrt = (((indexOfSquareRoot+1) * indexOfSquareRoot)/2L ) + (long)indexOfSquareRoot+1L;
        System.out.println(new Date());
        System.out.println("Final count of two factors: " + (numberOfTwoFactors + twoFactorCompositesLessThanSqrt));
    }

    private static int findPrimesLessThanNSieve(int n, List<Integer> retVal) {
        boolean[] isComposite = new boolean[n+1];
        int squareRootIndex = -1;
        int root = (int) Math.sqrt( n);

        for(int i=2; i<=n; i++) {
            if(!isComposite[i]) {
                retVal.add(i);
                if(i<=root) {
                    squareRootIndex++;
                }
                for(int j=2;j<=n/i;j++) {
                    isComposite[j*i]=true;
                }
            }
        }
        return squareRootIndex;
    }
}