import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean[] isComposite;
    public static void main(String[] args) {
        long start =System.currentTimeMillis();

        int numberToTest = 100000000;
        List<Integer> primes = new ArrayList<>();

        int indexOfSquareRoot = findPrimesLessThanNSieve(numberToTest, primes);

        int numberOfTwoFactors = 0;
        for(int i=0;i<=indexOfSquareRoot;i++) {
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
        long finish =System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        System.out.println("Final count of two factors: " + (numberOfTwoFactors + twoFactorCompositesLessThanSqrt));
    }

    private static int findPrimesLessThanNSieve(int n, List<Integer> retVal) {
        isComposite = new boolean[(n/2)+1];
        retVal.add(2);
        int squareRootIndex = 0;
        int root = (int) Math.sqrt( n);

        for(int i=3; i<=n/2; i=i+2) {
            if(!checkComposite(i)) {
                retVal.add(i);
                if(i<=root) {
                    squareRootIndex++;
                }
                for(int j=3;j<=n/i;j=j+2) {
                    setComposite(j*i);
                }
            }
        }
        return squareRootIndex;
    }

    private static boolean checkComposite(int i) {
        return isComposite[i>>1];
    }

    private static void setComposite(int i) {
        isComposite[i>>1]=true;
    }
}