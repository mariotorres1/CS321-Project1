# Project #: Project 1 Two-Level Cache

* Author: Mario Torres
* Class: CS321 Section #003
* Semester: Fall 2021

## Overview

Implements a cache that can be either a one-level or two-level cache.
Reads in a text file given through command line arguments and creates 
and modifies the implemented cache, while keeping track of data to be
printed at end of program.

## Reflection

I wouldn't say this project kicked my butt but it was definitely 
messing with my head. It was fairly easy to read in the file and
run through it for the most part. I couldn't get the Encyclopedia.txt
file to give me the correct results, I was off by 1 on the references
I believe and my cache1 and cache2 hit rates were off by 1 or 2 but
were correct overall. I tried implementing this thing it feels like
50 different ways and I couldn't get Encyclopedia.txt to be correct. 

I tried with both Scanner and String Tokenizer, I also tried making 
multiple constructors and doing in Cache.java and doing all the work
in those constructors. I tried doing the work in the methods in 
Cache.java. The one that seemed to work best and make the most sense
to me mentally was how I ended up doing it with the methods and a 
constructor in Cache.java and some private methods in CacheTest.java
that did most of the work. I feel like the solution is fairly easy 
but I may have overworked my brain and could be causing me to overlook
simple things which would lead to the correct solution. Overall, it
was a good project and it was nice to jump back into Java after not 
using it for a couple months. Looking forward to learning how to
implement better algorithms to make my life easier when creating
programs.

## Compiling and Using

This program takes in command line arguments to be run successfully.
The user should input 1 or 2 for the level of cache, along with the
size(s) of the cache(s) and the text file to be read. 

java CacheTest [1] [<cache size>] [<input textfile name>] or
java CacheTest [2] [<1st-level cache size>] [<2nd-level cache size>] 
[<input textfilename>]

## Results 

I couldn't figure out how to copy my screenshot over to my Readme,
so I'm going to discuss the results instead. My program was taking
at least 4 times the amount of time in milliseconds compared to 
the result examples of each .txt file. To me this shows that the 
way we wrote this program was not the best possible way to do so, 
there are far more efficient ways to write a program to implement
a cache. 

## Sources used

I used my old projects from CS 221 as a reference and a refresher
since I haven't used Java in awhile. I also looked up String 
Tokenizer which sent me to some examples on StackOverflow and 
the java api since I haven't used a String Tokenizer before. 
Other than that, I didn't research much which is maybe why I 
couldn't figure out how to get the right solution! 

----------
This README template is using Markdown. To preview your README output,
you can copy your file contents to a Markdown editor/previewer such
as [https://stackedit.io/editor](https://stackedit.io/editor).
