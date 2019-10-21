# Cache-Service
This project implements cache service by sucket programming in Java.  
The server provdie two cache service([LRU cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)) and [LFU Cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least-frequently_used_(LFU)))
for the client to use.  
## Connection
To connect to other machine we need a socket connection. A socket connection means the two machines have information about each other’s network location (IP Address) and TCP port.  

`Socket socket = new Socket(“127.0.0.1”, 5000)`

First argument – IP address of Server. ( 127.0.0.1  is the IP address of localhost, where code will run on single stand-alone machine).  
Second argument – TCP Port. (Just a number representing which application to run on a server. For example, HTTP runs on port 80. Port number can be from 0 to 65535)

## How to run
***Compile***  
`javac -d ./bin/ *.java`  
***Run***   
1. run  `cd bin`
2. open two terminal windows.  
3. `java Server` for the first window.  
Server window  
```
Server started
Waiting for a client ...
```
4. `java Client` for the first window.  
Client window  
```
Connected
```
Server window
```
Client accepted
```
