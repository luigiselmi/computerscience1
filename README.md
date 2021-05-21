Computer Science 1
==================
Java programs developed for the course [Computer Science: Programming with a Purpose](https://www.coursera.org/learn/cs-programming-java/), 
taught by Kevin Wayne and Robert Sedgewick and offered by Princeton University on Coursera. **The Java files that have been developed as solutions to the assignments are encrypted in order 
to comply with the Coursera Honor’s Code** (see [Source Code Encryption](#Source-Code-Encryption) section).

## Compile and execute
In order to compile the Java source files you have first to create a folder that will contain all the compiled classes. You 
can organize the folder as with Maven, with a *target* folder and a *classes* sub-folder. From the project root folder execute

```
$ mkdir -p target/classes 
```
In order to compile the Java code you need the [algs4.jar](https://algs4.cs.princeton.edu/code/) Java library to be added to the classpath. In 
the example the library is in the *lib* folder. Since the Java compiler cannot find recursively all the Java files by itself, you need to use a 
bash *find* command

```
$ javac -cp "lib/algs4.jar" -d target/classes $(find src/main/java -name '*.java')
```

In order to execute a Java class, from the project root folder, you need to add the compiled classes and the algs4.jar library to the classpath. 
As an example the command 

```
$ java -cp "lib/algs4.jar;target/classes" examples.week1.cs1.PlotFilter < resources/data/USA.txt
```

executes the PlotFilter Java class, in the examples.week1.cs1 package, that takes in input a stream from a file containing 
the coordinates of 13,509 cities in the United States.

## Compile and execute using Maven
Since the software follows the Maven conventions it can be compiled and executed using Maven

```
$ mvn compile
```

When the command returns, a new *target* folder is created, as Maven's convention, with a sub-folder *classes* containing all the compiled java 
classes. The previous example can be executed using Maven as follows

```
$ mvn exec:java -Dexec.mainClass="examples.week1.cs1.PlotFilter" < resources/data/USA.txt
```
## Assignments
The course has 10 assignments, one per week. The solutions are included in the assignment package.
### Week 1
The 1st week assignment is about some basic Java programming, such as passing arguments to a Java class, computing mathematical functions and 
data conversion.

### Week 2
The 2nd week assignment is about loops and conditionals that are used to compute a numerical series (generalized harmonic numbers) or the Manhattan
distance in a random walk.
 
### Week 3
The 3rd week assignment is about arrays. Among other things, they are used to present the birthday paradox.

### Week 4
The purpose of the 4th week is to learn how to use the standard input and output and how to pass data as input to a program and get the output and 
print data to the standard drawings. An interesting application is the plot of a n-by-n checkerboard where n is an integer passed by argument.

### Week 5
The 5th week assignment is about functions that in Java can be defined as static methods.

## Abstract Data Types and Algorithms
This repository contains the implementations of some abstract data types and algorithms. The abstract data type implementations are included in
the package datastructures.

## Source Code Encryption
The Java source code of the solution of the assignments has been encrypted to comply with the [Coursera Honor's Code](https://learner.coursera.help/hc/en-us/articles/209818863-Coursera-Honor-Code) using [OpenSSL](https://www.openssl.org/)
and the Advanced Encryption Standard (AES) symmetric cipher with a 256 bits long key in CBC mode. The command for the encryption is like in the example

```
$ openssl enc -e -aes-256-cbc -in HelloWorld.java -out HelloWorld.java.enc -pass file:secret
```

where "secret" is the name of the file containing the pass-phrase. Use the next command for decryption 
  
```
$ openssl enc -d -aes-256-cbc -in HelloWorld.java.enc -out HelloWorld.java -pass file:secret
```