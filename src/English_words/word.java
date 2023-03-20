package English_words;

import java.util.Scanner;

public class word {
    void judge(String s)
    {
        Scanner sca = new Scanner(System.in);
        String temp = sca.next();
        if (temp.equals(s))
        {
            System.out.println("you are right");
        }
        else
        {
            System.out.println("you are wrong");
        }
        sca.close();
    }


}
