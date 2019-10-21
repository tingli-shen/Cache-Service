
import java.util.*;
import java.lang.String;
public class LFUCache implements Cache {
    private int capacity;
    private Map<Integer, ListNode> cntMap;
    private Map<Integer, ListNode> nodeMap;
    ListNode head;
    ListNode tail;
    private String name="LFUCache";
    public LFUCache(int capacity)
    {
        this.capacity=capacity;
        head=new ListNode(-1,-1);
        tail=new ListNode(-1,-1);
        cntMap=new HashMap<>();
        nodeMap=new HashMap<>();
        head.next=tail;
        tail.prev=head;
        cntMap.put(0,tail);
    }
    public String getName()
    {
      return this.name;
    }
    public void set(int key,int value)
    {
        if(nodeMap.containsKey(key))
        {
            ListNode node=nodeMap.get(key);
            remove(key);
            node.cnt++;
            node.val=value;
            insert(node);
            return;
        }
        if(nodeMap.size()==this.capacity)
            remove(this.tail.prev.key);
        ListNode node=new ListNode(key,value);
        insert(node);
    }
    public int get(int key)
    {
        if(!nodeMap.containsKey(key))
            return -1;
        ListNode node=nodeMap.get(key);
        remove(key);
        node.cnt++;
        insert(node);
        return node.val;
    }
    private void remove(int key)
    {
        ListNode node=nodeMap.get(key);
        int cnt=node.cnt;
        node.prev.connectNext(node.next);
        if(cntMap.get(cnt)==node)
        {
            if(node.next.cnt==cnt)
                cntMap.put(cnt,node.next);
            else
                cntMap.remove(cnt);
        }
        nodeMap.remove(key);
    }
    private void insert(ListNode node)
    {
        int cnt=node.cnt;
        int key=node.key;
        nodeMap.put(key,node);
        if(cntMap.containsKey(cnt))
        {
            ListNode firstNode=cntMap.get(cnt);
            ListNode prev=firstNode.prev;
            prev.connectNext(node);
            node.connectNext(firstNode);
            cntMap.put(cnt,node);
        }
        else
        {
            int cnt1=cnt-1;
            while(!cntMap.containsKey(cnt1))
                cnt1--;
            ListNode next=cntMap.get(cnt1);
            next.prev.connectNext(node);
            node.connectNext(next);
            cntMap.put(cnt,node);
        }
    }
}
