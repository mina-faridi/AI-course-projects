import java.util.Scanner;

/**
 * Created by ASUS UX360CA on 12/31/2018.
 */
public class Main {

    public static void main(String[] args) {//todo complete
        Scanner sc = new Scanner(System.in);
        System.out.println("which problem? 1)problem1 2)problem2");
        int pNum = sc.nextInt();//problem number
        if (pNum == 1)//problem1
            (new Problem1()).solve();
        else if (pNum == 2){//problem2
            //todo
        }

    }
}
