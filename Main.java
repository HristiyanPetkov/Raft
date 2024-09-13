import java.util.ArrayList;
import java.util.Iterator;


public class Main {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int K = Integer.parseInt(args[1]);
        int sum = 0;
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 0; i < N; ++i) {
            arr.add(Integer.parseInt(args[i + 2]));
            sum += Integer.parseInt(args[i + 2]);
        }

        arr.sort((a, b) -> b - a);

        int startSize = ((sum / K) > arr.get(0)) ? (sum / K) : arr.get(0);

        System.out.println(findRaftSize(arr, K, startSize));
    }

    public static int findRaftSize(ArrayList<Integer> arr, int K, int size) {
        int occupied = 0;
        ArrayList<Integer> temp = new ArrayList<>(arr);
        Iterator<Integer> it = temp.iterator();

        for(int i = 0; i < K; ++i) {
            while(it.hasNext()) {
                int val = it.next();
                if(val <= (size - occupied)) {
                    occupied += val;
                    it.remove();
                }
            }
            it = temp.iterator();
            occupied = 0;
        }

        if(temp.isEmpty()) {
            return size;
        }

        return  findRaftSize(arr, K, size + 1);
    }
}