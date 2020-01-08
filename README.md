# Searching Twitter DataSet
Prompt and Source Code Given In Data Structures Courses at Columbia University 

File TweetDB.java contains my original code. 

The file `coachella_tweets.csv` contains 3882 tweets discussing the Coachella 2015 music festival. This dataset was originally createdfor the purpose of training and testing sentiment analysis models, identifying if a users view of the festival is positive or negative. 

The goal of this problem is to read the complete data into memory and index it using different maps. Specifically, you will index the tweets using

* A [`java.util.HashMap`](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) in which the keys are usernames (of type String), which allows you to efficiently find all tweets sent by a user. 
* A `java.util.HashMap` in which the keys are keywords (of type String) appearing in the tweets, which allows you to efficiently find all tweets mentioning a specific term. 
* A [`java.util.TreeMap`](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html) in which the keys are dates and times (represented as instances of the class DateTime. The `TreeMap` allows you to find tweets by a specific date and time and also efficiently retrieve tweets in a certain time range. A TreeMap is a balanced Binary Search Tree. (see below)



#### Part 1 - Reading the CSVfile and Indexing by Username. 
#### Part 2 - Indexing by Keyword. 
#### Part 3 - Indexing by Date/Time 
#### Part 4 - Removing Duplicates 
