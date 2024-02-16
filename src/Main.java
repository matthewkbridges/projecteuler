import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int[] bitIndexes= {0x0, 0x1, 0x0, 0x2, 0x0, 0x4, 0x0, 0x8, 0x0, 0x10, 0x0, 0x20, 0x0, 0x40, 0x0, 0x80};

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int numberToTest = 100000000;
        List<Long> primes = findPrimesLessThanNSieve(numberToTest);
        int numberOfTwoFactors = 0;
        int lim=primes.size();
        for(int i=0;i<lim;i++) {
            for(int j=i;j<lim;j++) {
                long testNumber = primes.get(i) * primes.get(j);
                if(testNumber < numberToTest) {
                    numberOfTwoFactors++;
                } else {
                    break;
                }
            }
        }
        long finish =System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);
        System.out.println("Final count of two factors: " + numberOfTwoFactors);
    }

    private static List<Long> findPrimesLessThanNSieve(int n) {
        List<Long> retVal = new ArrayList<>(n/10);
        byte[] isComposite = new byte[(n>>4)+1];
        retVal.add(2L);

        //predeclare loop variables so that we don't have them being put on and off of the stack.
        int i;
        int j;
        int k;
        int ilim=(n>>1)+1;
        int kind;

        for(i=3; i<ilim; i=i+2) {
            if((isComposite[i>>4] & bitIndexes[i&0xF]) == 0) {
                retVal.add((long)i);
                int jlim=(n/i)+1;
                for(j=3;j<jlim;j=j+2) {
                    k=j*i;
                    kind=k>>4;
                    isComposite[kind]=(byte) (isComposite[kind] | bitIndexes[k&0xF]);
                }
            }
        }
        return retVal;
    }
}