import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int[] bitIndexes= {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int numberToTest = 100000000;
        List<Long> primes = new ArrayList<>(numberToTest/10);

        int indexOfSquareRoot = findPrimesLessThanNSieve(numberToTest, primes);
        int numberOfTwoFactors = 0;
        for(int i=0;i<=indexOfSquareRoot;i++) {
            for(int j=indexOfSquareRoot+1;j<primes.size();j++) {
                long testNumber = primes.get(i) * primes.get(j);
                if(testNumber < numberToTest) {
                    numberOfTwoFactors++;
                } else {
                    break;
                }
            }
        }
        long twoFactorCompositesLessThanSqrt = (((indexOfSquareRoot+1) * indexOfSquareRoot)/2L ) + indexOfSquareRoot+1L;
        long finish =System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        System.out.println("Final count of two factors: " + (numberOfTwoFactors + twoFactorCompositesLessThanSqrt));
    }

    private static int findPrimesLessThanNSieve(int n, List<Long> retVal) {
        byte[] isComposite = new byte[(n>>4)+1];
        retVal.add(2L);
        int squareRootIndex = 0;
        int root = (int) Math.sqrt(n);
        //predeclare loop variables so we don't have them being put on and off of the stack.
        int i;
        int j;
        int k;
        int kind;

        for(i=3; i<=n/2; i=i+2) {
            if((isComposite[i>>4] & bitIndexes[(i&0xF)>>1]) == 0) {
                retVal.add((long)i);
                if(i<=root) {
                    squareRootIndex++;
                }
                for(j=3;j<=n/i;j=j+2) {
                    k=j*i;
                    kind=k>>4;
                    isComposite[kind]=(byte) (isComposite[kind] | bitIndexes[(k&0xF)>>1]);
                }
            }
        }
        return squareRootIndex;
    }
}