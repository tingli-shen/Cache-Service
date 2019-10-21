

public class CacheFactory {
    public Cache getCache(int cacheType,int capacity)
    {
        if(cacheType==2)
            return new LRUCache(capacity);
        else if(cacheType==1)
            return new LFUCache(capacity);
        return  null;
    }
}
