# Cache-Service
This project implements cache service by sucket programming in Java.  
The server provdie two cache service([LRU cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU)) and [LFU Cache](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least-frequently_used_(LFU)))
for the client to use.  
The service uses [factory pattern](https://en.wikipedia.org/wiki/Factory_method_pattern) to create and encapsulate  cache object.
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
3. run `java Server` for the first window.  
Server window:  
```
Server started
Waiting for a client ...
```
4. run `java Client` for the first window.  
Client window:  
```
Connected
```
&emsp; Server window:
```
Client accepted
```

## Methods
* **set(key, value)**  
Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.  
* **get(key)**  
Get the value (will always be positive) of the key if the key exists in the cache, otherwise return message about why the key dose not exist.
