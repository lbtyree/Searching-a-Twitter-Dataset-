import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.TreeMap; 
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.io.*; 
import java.util.*;
import java.util.LinkedList;

public class TweetDB {
   
    
    HashMap<String, List<Tweet>> tweetsPerUser;
    HashMap<String, List<Tweet>> tweetsPerKeyword;  
    TreeMap<DateTime, List<Tweet>> tweetsByTime;
    ArrayList<String> allTtweetslist;
    ArrayList<DateTime> datetimearr;
    ArrayList<ArrayList<Tweet> > aList =  
                  new ArrayList<ArrayList<Tweet>>();
    
    public TweetDB(String pathToFile) throws FileNotFoundException, IOException {
        tweetsPerUser = new HashMap<>();               
        tweetsPerKeyword = new HashMap<>();   
        tweetsByTime = new TreeMap<>(); 
        allTtweetslist = new ArrayList<String>();
        datetimearr= new ArrayList<DateTime>();
        //ArrayList<Tweet> alltweets= new ArrayList<Tweet>(); // for tweet indexing 
        
         //Using a CsvReader, read through the input file
        //Input file which needs to be parsed
        BufferedReader fileReader = null;
        try{
        fileReader = new BufferedReader(new FileReader(pathToFile));
        }catch(FileNotFoundException G){
               System.out.println("Exception! File Not Found.");
        }
        
       //Convert each row into a Tweet instance.
       CsvReader r= new CsvReader(fileReader);

       int x=0;
       while (x<=3882){          
            
           String stringTweet= Arrays.toString(r.nextLine());          
           String[] partsofTweet = stringTweet.split(",");        
           String user= partsofTweet[0];            
           String content= partsofTweet[1];          
           String thedatetime= partsofTweet[2];
           
          
           if(!thedatetime.contains("/") && !thedatetime.contains(":") 
            &&thedatetime.contains("http")&&thedatetime.contains("#") ||
                thedatetime.length()==0 || thedatetime.equals("")){
           break; 
           }
           else if(thedatetime.contains("/") && thedatetime.contains(":") 
              && !thedatetime.contains("http")&& !thedatetime.contains("#") &&
                      thedatetime.length()>=1 && !thedatetime.equals("")){
            thedatetime = thedatetime.trim(); // trim to remove all spaces
           try{
           thedatetime = thedatetime.substring(0, thedatetime.length()-1 );      
           }catch(IndexOutOfBoundsException E){
               System.out.println("Exception! There is nothing to trim.");    
           }
  
           DateTime datetime= new DateTime(thedatetime.trim());
           datetimearr.add(datetime); 
           Collections.sort(datetimearr);//returns from most recent 1/7 to oldest 1/6
       
           Tweet tweet=new Tweet(user, datetime, content);   
            
           ArrayList<Tweet> alltweets= new ArrayList<Tweet>(); // for tweet indexing  
           //alltweets.add(tweet); 
               
           List<Tweet> newList=  new ArrayList<>();
           //Convert each row into a Tweet instance.
          // List<Tweet> theuser= getTweetsByUser(user);
           // insert each Tweet into the tweetsPerUser hash map
                for(int j=0; j<datetimearr.size();j++){
             tweetsByTime.put( datetimearr.get(j),alltweets );
         }          
           alltweets.add(tweet);
           tweetsPerUser.put(user,alltweets);
         
           // Update the constructor TweetDB so that each Tweet is additionally 
           // indexed by the the words that appear in the tweet content.
           // add the tweets into the tweetsPerKeyword hashmap
           //List<Tweet> thecontent= getTweetsByKeyword(content);
           tweetsPerKeyword.put(content, alltweets);
             
          aList.add(alltweets);
        //Update the constructor TweetDB so that each Tweet is additionally indexed 
           //by its DateTime object. 
           //You should add the tweets into the tweetsByTime tree map. 
           //To use the DateTime instances as keys, you need to modify that class 
           //to implement Comparable. 
           //tweetsByTime.put(datetime, aList.get(x));
         
           allTtweetslist.add(stringTweet);    
           }}
         x++; 
      }
      
    //write the method. public List<Tweet> getTweetsByUser(String user) that 
    //returns all tweets written by a user.
    public List<Tweet> getTweetsByUser(String user) {
             
        ArrayList usertweets2 = new ArrayList();
        List<Tweet> usertweets= new ArrayList<Tweet>();  
        //convert map to string so user be searched for
        // then turn the string tweet back into a tweet and return    
        String keyword="";
        StringBuilder mapAsString = new StringBuilder("{");   
        for(String key: tweetsPerUser.keySet()){   
           // System.out.println(tweetsPerKeyword.get(key));
         mapAsString.append(key + "=" + tweetsPerKeyword.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        String m= mapAsString.toString(); 
          String[] partsofTweet2 = m.split("=");
           for(String g:partsofTweet2){
             if(g.contains(user)){ // if the tweet contains said keyword 
                for(int x=0; x<allTtweetslist.size(); x++){
                 if(allTtweetslist.get(x).contains(user)){ // if Keyword present  
                     //then get index of kw and add to kw tweets
                       int userIndex = x;
                       String tweet= allTtweetslist.get(userIndex);
                       String[] partsofTweet = tweet.split(",");
                       String alsouser= partsofTweet[0];                
                       String content= partsofTweet[1];
                       String thedatetime= partsofTweet[2];
                       thedatetime = thedatetime.substring(0, thedatetime.length()-1 );
                       DateTime datetime= new DateTime(thedatetime.trim());
                       Tweet thetweet=new Tweet(alsouser, datetime, content);               
                       if(!usertweets.contains(thetweet)) //override in equals 
                        usertweets.add(thetweet);
                 }}}}  
        // get rid of duplicates using sets 
        Set<Tweet> hashSet = new HashSet<Tweet>(); 
        for (Tweet j : usertweets) 
                hashSet.add(j);
        for (Tweet h : hashSet)    
            usertweets2.add(h);  
   
        return usertweets2; // replace this
  
  } 
    
    public List<Tweet> getTweetsByKeyword(String kw) {
       List<Tweet> kwtweets = new ArrayList<Tweet>();
       ArrayList kwtweets2 = new ArrayList();     
       kw= assistgetTweetsByKeyword(kw); // replace punctuation  
       String[] partsofkw = kw.split(" "); //split at whitespace 
        
        // have to convert map to string so each word can be searched
        // individally for the kw
        // then turn the string tweet back into a tweet and print   
        String keyword="";
        StringBuilder mapAsString = new StringBuilder("{");   
        for(String key: tweetsPerKeyword.keySet()){   
           // System.out.println(tweetsPerKeyword.get(key));
         mapAsString.append(key + "=" + tweetsPerKeyword.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        String m= mapAsString.toString(); 
          String[] partsofTweet2 = m.split("=");
           for(String g:partsofTweet2){
             for(int p=0; p<partsofkw.length;p++){
             if(g.contains(partsofkw[p])){ // if the tweet contains said keyword 
                for(int x=0; x<allTtweetslist.size(); x++){
                 if(allTtweetslist.get(x).contains(kw)){ // if Keyword present  
                     //then get index of kw and add to kw tweets
                       int kwIndex = x;
                       String tweet= allTtweetslist.get(kwIndex);
                       String[] partsofTweet = tweet.split(",");
                       String alsouser= partsofTweet[0];                
                       String content= partsofTweet[1];
                       String thedatetime= partsofTweet[2];
                       thedatetime = thedatetime.substring(0, thedatetime.length()-1 );
                       DateTime datetime= new DateTime(thedatetime.trim());
                       Tweet thetweet=new Tweet(alsouser, datetime, content);               
                       if(!kwtweets.contains(thetweet)) //override in equals 
                        kwtweets.add(thetweet);
                 }}}}}  
        // add elements to hs, including duplicates
        Set<Tweet> hs = new HashSet<>();      
        hs.addAll(kwtweets);
        kwtweets.clear();
        kwtweets.addAll(hs);   
        return  kwtweets;    
    }
    
       //helper method 
       public String assistgetTweetsByKeyword(String kw) {
        // stripping out common punctuation symbols
        // white space split above 
       kw.replaceAll("_","");    
       return kw.replaceAll("\\p{Punct}", ""); 
       }
    
     public List<Tweet> getTweetsInRange(DateTime start, DateTime end) {
         //ArrayList<Tweet> tweetsInRange = new ArrayList<Tweet>();
         
         
         //create a map of the qualifying date times
         SortedMap<DateTime, List<Tweet>> map = tweetsByTime.subMap(start, end);
         List<Tweet> tweetsInRange = new ArrayList<Tweet>();
         // map should still be date time, tweet
        
         for(List<Tweet> r: map.values())
              for( Tweet f: r)
                if(!tweetsInRange.contains(f)) //override in equals 
                  tweetsInRange.add(f);
        
        // get rid of duplicates 
        Set<Tweet> hs = new HashSet<>();      
        hs.addAll(tweetsInRange);
        tweetsInRange.clear();
        tweetsInRange.addAll(hs);   
         
        
        return tweetsInRange; // return all tweets in range 
    }  
    
    public static void main(String[] args) {
        
        //Tweet t; 
        
          
        try {
            TweetDB tdb = new TweetDB("coachella_tweets.csv");
          
           
           // System.out.println("range :");
          //System.out.println(tdb.getTweetsInRange(new DateTime("1/6/15 00:00"), new DateTime("1/6/15 5:00")));
             
           // Part 1: Search by username 
             System.out.println("username:"); 
            for(Tweet x : tdb.getTweetsByUser("TheExperiencexX")){
                System.out.println( x);  
            }
              
           // Part 2: Search by keyword 
            System.out.println("keyword:");
            for(Tweet t : tdb.getTweetsByKeyword("hann")){
                System.out.println(t);
                }

            System.out.println("range :");
            
           /** Part 3: Search by date/time interval*/ 
            
            for(Tweet t : tdb.getTweetsInRange(new DateTime("1/7/15  15:02"), new DateTime("1/7/15  15:03"))){
                   System.out.println(t);
        }
            
        } catch (FileNotFoundException e) {
            System.out.println(".csv File not found.");
        } catch (IOException e) {
            System.out.println("Error reading from .csv file.");
        }
        
        
        
        
    }
    
}