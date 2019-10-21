

public class ListNode {
    int key;
    int val;
    int cnt;
    ListNode prev;
    ListNode next;
    ListNode(int key,int val){
        this.key=key;
        this.val=val;
        this.cnt=0;
    }
    public void connectNext(ListNode node)
    {
        this.next=node;
        node.prev=this;
    }
}
