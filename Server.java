
import java.net.*;
import java.io.*;
public class Server {
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;

    // constructor with port
    public Server(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());

            String line = "";
            //LFUCache lru=new LFUCache(3);
            boolean setCap=true;
            int cnt=0;
            int choice=-1;
            CacheFactory cacheFactory=new CacheFactory();
            Cache cache=cacheFactory.getCache(1,1);
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    if(cnt==0)
                    {
                        choice=Integer.parseInt(line);
                        cnt++;
                        out.writeUTF("Please set up the capacity: ");
                    }
                    else if(cnt==1)
                    {
                        cnt++;
                        int capacity = Integer.parseInt(line);
                        cache=cacheFactory.getCache(choice,capacity);
                        out.writeUTF(cache.getName()+" is set up");

                    }
                    else
                    {
                        String method=line.substring(0, 3);
                        //out.writeUTF(line);
                        if(method.equals("set"))
                        {
                            set(cache,line);
                            out.writeUTF("data stored");
                        }
                        else if(method.equals("get"))
                        {
                            String ans=get(cache,line);
                            out.writeUTF(ans);
                        }
                    }

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }


            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }
    static public void set(Cache cache, String s)
    {
        int left=s.indexOf("(");
        int right=s.indexOf(")");
        int comma=s.indexOf(",");
        int key= Integer.parseInt(s.substring(left+1,comma));
        int val=Integer.parseInt(s.substring(comma+1,right));
        cache.set(key,val);
    }
    static public String get(Cache cache, String s)
    {
        int left=s.indexOf("(");
        int right=s.indexOf(")");
        int key=Integer.parseInt(s.substring(left+1,right));
        int val=cache.get(key);
        String res="Key: "+key+" has Value: "+val+" in the cache";
        if(val==-1)
            res="Sorry. That data with Key: "+key+" has been evicted by "+cache.getName()+" policy because the cache was full.";
        return res;

    }

    public static void main(String args[])
    {
        Server server = new Server(5000);
    }
}
