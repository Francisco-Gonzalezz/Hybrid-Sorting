# Hybrid Sorting Algorithms
##### Project By: Brian Vu, Francisco Gonzalez, Ethan Rees
###### Version 1.0
<br>

Our programming portion of the project allows a user to sort a dataset in one of two ways.
1. Timsort
2. Advanced Quicksort

### <ins>How to run SortingHub</ins>
1. Collect the following files into a single directory
    - SortingHub.java
    - Array.java
    - Timsort.java
    - SortingHelpers.java
    - AdvancedQuickSort.java
    - stringTest.txt
2. Within Terminal/Powershell run 
    ~~~ 
    javac *.java 
    ~~~
3. You are now able to run the follwing command
    ~~~
    java SortingHub
    ~~~
    This will start the command line interface for the sorting algorithms.

### <ins>Interacting with the SortingHub CLI</ins>
After starting the CLI for SortingHub you will be greeted and asked to type which sorting algorithm you would like to see.
1. Timsort
2. Advanced Quicksort
<!-- -->
The user will then be prompted to pick a type of data to be used while sorting and will need to select either:
1. Integer
>📝 You will then be asked how many data points you would like to be sorted and they will be randomly generated for you
>
2. String 
>📝 When using String you will need to specify the file needed for getting the data from with a text file. There is one provided call stringTest.txt 
>
The console will then output what the original array was and what the array looks like after the sorting is complete. You will also be able to see how many times the array was accessed while sorting.


