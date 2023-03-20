package caogao;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.lang.annotation.Target;
import java.util.*;
import java.util.List;



public class draft {
    public static void main(String[] args) {
        System.out.println("fuck o");
    }
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

//    public static void main(String[] args) {
//        draft d = new draft();
//        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
//        priorityQueue.add(1);
//        priorityQueue.add(0);
//        System.out.println(priorityQueue.peek());
//    }

    public String largestNumber(int[] nums) {
        StringBuilder ans = new StringBuilder();

        for(int i :nums) ans.append(i);
        return ans.toString();
    }
}
