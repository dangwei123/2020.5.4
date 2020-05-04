public class ListParation {

    public Node listPar(Node head,int pivot){
        Node leftHead=null;
        Node leftTail=null;
        Node midHead=null;
        Node midTail=null;
        Node rightHead=null;
        Node rightTail=null;
        while(head!=null){
            if(head.val<pivot){
                if(leftHead==null){
                    leftHead=head;
                    leftTail=head;
                }else{
                    leftTail.next=head;
                    leftTail=leftTail.next;
                }
            }else if(head.val>pivot){
                if(rightHead==null){
                    rightHead=head;
                    rightTail=head;
                }else{
                    rightTail.next=head;
                    rightTail=rightTail.next;
                }
            }else{
                if(midHead==null){
                    midHead=head;
                    midTail=head;
                }else{
                    midTail.next=head;
                    midTail=midTail.next;
                }
            }
            head=head.next;
        }

        if(leftTail!=null){
            leftTail.next=midHead;
            midTail=midTail==null?leftTail:midTail;
        }
        if(midTail!=null){
            midTail.next=rightHead;
        }
        return leftHead!=null?leftHead:midHead!=null?midHead:rightHead;
    }
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}


public class CopyRandom {
    public Node copyRandomList(Node head) {
        if(head==null) return head;
        Node cur=head;
        while(cur!=null){
            Node node=new Node(cur.val);
            node.next=cur.next;
            cur.next=node;
            cur=node.next;
        }
        cur=head;
        while(cur!=null){
            if(cur.random!=null){
                cur.next.random=cur.random.next;
            }
            cur=cur.next.next;
        }

        cur=head;
        Node newHead=cur.next;
        while(cur.next!=null){
            Node next=cur.next;
            cur.next=next.next;
            cur=next;
        }
        return newHead;
    }

    private static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}


public class Palindrome {
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&&fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode cur=slow.next;
        while(cur!=null){
            ListNode next=cur.next;
            cur.next=slow;
            slow=cur;
            cur=next;
        }
        fast=head;
        while(fast!=slow){
            if(fast.val!=slow.val){
                return false;
            }
            if(fast.next==slow){
                return true;
            }
            fast=fast.next;
            slow=slow.next;
        }
        return true;
    }

    private static class ListNode{
        private int val;
        private ListNode next;
        public ListNode(int val){
            this.val=val;
        }
    }
}


public class PrintCommonPart {
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }

    public void printCommon(Node head1,Node head2){
        while(head1!=null&&head2!=null){
            if(head1.val<head2.val){
                head1=head1.next;
            }else if(head2.val<head1.val){
                head2=head2.next;
            }else{
                System.out.println(head1.val+" ");
                head1=head1.next;
                head2=head2.next;
            }
        }
    }
}


public class RemoveKth {
    //单链表
    private static class SimpleNode{
        private int val;
        private SimpleNode next;
        public SimpleNode(int val){
            this.val=val;
        }
    }
    public SimpleNode removeLstKth(SimpleNode head,int k){
        if(head==null||k<1) return head;
        SimpleNode cur=head;
        while(cur!=null){
            k--;
            cur=cur.next;
        }
        if(k==0){
            head=head.next;
        }
        if(k<0){
            cur=head;
            while(++k!=0){
                cur=cur.next;
            }
            cur.next=cur.next.next;
        }
        return head;
    }

    //双链表
    private static class DoubleNode{
        private int val;
        private DoubleNode pre;
        private DoubleNode next;
        public DoubleNode(int val){
            this.val=val;
        }
    }
    public DoubleNode removeLastKthDouble(DoubleNode head,int k){
        if(head==null||k<1) return head;
        DoubleNode cur=head;
        while(cur!=null){
            k--;
            cur=cur.next;
        }
        if(k==0){
            head=head.next;
            head.pre=null;
        }
        if(k<0){
            cur=head;
            while(++k!=0){
                cur=cur.next;
            }
            cur.next=cur.next.next;
            DoubleNode next=cur.next.next;
            if(next!=null){
                next.pre=cur;
            }
        }
        return head;
    }

}


public class RemoveMid {

    //删除中间节点
    public Node removeMid(Node head){
        if(head==null||head.next==null) return head;
        if(head.next.next==null) return head.next;
        Node fast=head.next.next;
        Node slow=head;
        while(fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        slow.next=slow.next.next;
        return head;
    }

    //删除第a/b个节点
    public Node removeByRatio(Node head,int a,int b){
        if(a>b||a<=0||head==null) return head;
        Node cur=head;
        int n=0;
        while(cur!=null){
            cur=cur.next;
            n++;
        }
        int index=(int)Math.ceil((double)a*n/b);
        if(index==1){
            head=head.next;
        }else{
            cur=head;
            while(index!=1){
                cur=cur.next;
                index--;
            }
            cur.next=cur.next.next;
        }
        return head;
    }
    private static class Node{
        private int val;
        private Node next;
        public Node(int val){
            this.val=val;
        }
    }
}
