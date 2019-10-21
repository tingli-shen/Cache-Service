
import java.util.*;
import java.lang.String;
public class LRUCache implements Cache{
    private ListNode head;
    private ListNode tail;
    private int capacity;
    private Map<Integer, ListNode> map;
    private String name="LRUCache";
    public LRUCache(int capacity)
    {
        this.capacity=capacity;
        head=new ListNode(-1,-1);
        tail=new ListNode(-1,-1);
        map=new HashMap<>();
        head.next=tail;
        tail.prev=head;
    }
    public String getName()
    {
      return this.name;
    }
    public int get(int key)
    {
        if(!map.containsKey(key))
            return -1;
        ListNode node=map.get(key);
        int val=node.val;
        remove(node);
        insert(node,key);
        return val;

    }
    public void set(int key,int value)
    {
        if(map.containsKey(key))
        {
            ListNode node=map.get(key);
            remove(node);
        }
        if(map.size()==capacity)
            remove(head.next);
        ListNode newNode=new ListNode(key,value);
        insert(newNode,key);
    }
    private void insert(ListNode node,int key)
    {
        map.put(key,node);
        ListNode prev=this.tail.prev;
        prev.next=node;
        node.next=this.tail;
        this.tail.prev=node;
        node.prev=prev;
    }
    private void remove(ListNode node)
    {
        int key=node.key;
        ListNode prev=node.prev;
        ListNode next=node.next;
        prev.next=next;
        next.prev=prev;
        map.remove(key);
    }
}
