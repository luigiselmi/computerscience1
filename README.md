Computer Science 1
==================
Java programs developed for the course "Computer Science: Programming with a Purpose".

### Compile and execute
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

### Compile and execute using Maven
Since the software follows the Maven conventions it can be compiled and executed using Maven

```
$ mvn compile
```

When the command returns, a new *target* folder is created, as Maven's convention, with a sub-folder *classes* containing all the compiled java 
classes. The previous example can be executed using Maven as follows

```
$ mvn exec:java -Dexec.mainClass="examples.week1.cs1.PlotFilter" < resources/data/USA.txt
```