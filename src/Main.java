import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static byte[] isComposite;
    private static final int[] bitIndexes= {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

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
        isComposite = new byte[(n>>4)+1];
        retVal.add(2);
        int squareRootIndex = 0;
        int root = (int) Math.sqrt(n);

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
        int index=i>>4;
        int bitIndex=(i&0xF)>>1;
        return (isComposite[index] & bitIndexes[bitIndex]) > 0;
    }

    private static void setComposite(int i) {
        int index=i>>4;
        int bitIndex=(i&0xF)>>1;
        isComposite[index]=(byte) (isComposite[index] | bitIndexes[bitIndex]);
    }
}