# Java TCP Client-Server
  
  This is simple app simualting a Client-Server model.
  I used the DES algorithm to encrypt the data.
 
## Installation
  1. Require JDK 11
  2. Netbeans 12.0 LTS

## Activity Description

  1. Initialize the soket on Server with address and port entered by user. After initailization, the Server is listening for connection from Client.
  2. The Client connects to Server by the address and port.
  3. The data will be encryted by DES and return the cipher text in hexa string.
  4. The Client sends the cipher text and key to Server.
  5. The Server receives and decrypt the cipher text with key then return the plaintext.
  6. The plaintext is converted to uppercase and encrypted again.
  7. The Server send the new cipher text to the Client.
  8. The Client receives and decrypts it then displays the plaintext.

## The DES alogortitm
  
  All the DES code was referenced by https://www.youtube.com/watch?v=3mH6zaSjeGU&t=2628s
  
## Interface

### Server

   ![image](https://user-images.githubusercontent.com/61774212/144468204-fffed634-3a45-484c-8c4b-f143b334cea2.png)


### Client
   1. Client Connection
   
   ![image](https://user-images.githubusercontent.com/61774212/144468407-5ccc5297-5540-4046-af80-dab6ab009e55.png)
    
   2. Client
   
   ![image](https://user-images.githubusercontent.com/61774212/144467958-7474521c-f703-46ea-8a9c-ed22fc4dfdf0.png)

    


  
