# Chat Application Using Java Sockets

## Project Overview

This project is a real-time client-server chat application developed using Java Socket Programming. It allows multiple users to connect to a server and exchange messages instantly through the command prompt.

The application follows the flow:

Server Starts → Clients Connect → Users Send and Receive Messages → Chat Continues Until Exit

---

## Features

* Real-time messaging between multiple clients
* Client-server communication using sockets
* Supports multiple users at the same time
* Username-based chat messages
* Separate thread for receiving messages
* Exit command to disconnect from the chat
* Runs completely in the command prompt

---

## Technologies Used

* Java
* Socket Programming
* Multithreading
* Input/Output Streams
* Command Prompt / Terminal

---

## Files in the Project

1. ChatServer.java
   Handles client connections and broadcasts messages to all users.

2. ChatClient.java
   Connects to the server, sends messages, and receives messages from other users.

---

## How the Project Works

1. Start the server.
2. Clients connect to the server.
3. Each client enters a username.
4. Users type messages in the command prompt.
5. The server sends the message to all connected clients.
6. Users continue chatting until they type `/exit`.

---

## How to Run the Project

### Step 1: Open Command Prompt

Go to the project folder:

```bash
cd path_to_your_project_folder
```

### Step 2: Compile the Files

```bash
javac ChatServer.java
javac ChatClient.java
```

### Step 3: Run the Server

```bash
java ChatServer
```

Output:

```text
[SERVER] Chat Server Started on port 5000
```

### Step 4: Run the Clients

Open another command prompt window and run:

```bash
java ChatClient
```

Enter a username when prompted.

Example:

```text
Enter your username:
Rohini
```

Open another client window and repeat.

---

## Sample Output

Client 1:

```text
Enter your username:
Rohini
[JOIN] Rohini joined the chat.
hi
Rohini: hi
```

Client 2:

```text
Enter your username:
Anu
[JOIN] Anu joined the chat.
hello
Anu: hello
```

Client 1 then receives:

```text
Anu: hello
```

---

## Exit the Chat

To leave the chat, type:

```text
/exit
```

---

## Future Enhancements

* Graphical User Interface using Java Swing
* Private messaging between users
* User login and password
* Chat history using MySQL
* File sharing feature
* Multiple chat rooms

---

## Conclusion

This project demonstrates the use of Java sockets and multithreading to create a simple real-time chat system. It is a good beginner project for learning networking concepts in Java and can be further enhanced with GUI and database features.
