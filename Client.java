
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class Client {
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private DataInputStream in       =  null;


    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);


            // takes input from terminal
            input  = new DataInputStream(System.in);

            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());


            in = new DataInputStream(
            new BufferedInputStream(socket.getInputStream()));
            System.out.println("Connected");
            System.out.println("Wellcome to Cache Service");
            System.out.println("Least Frequently Used Cache: 1");
            System.out.println("Least Recently Used Cache: 2");
            System.out.print("Please select your Cache Service(1 or 2):");




        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        // string to read message from input
        String line = "";

        // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
            try
            {
                line = in.readUTF();
                System.out.println(line);

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("127.0.0.1", 5000);
    }
}
