/**
 * Represent a tweet, including the content, author's username
 * and time when it was posted. 
 */
import java.util.Objects;
public class Tweet {
    
    public String user;
    public DateTime datetime; 
    public String content;
    
    public Tweet(String user, DateTime datetime, String content) {
            this.user = user; 
            this.datetime = datetime;
            this.content = content;       
    }
    
    public String toString(){
        return "@"+this.user+" ["+datetime.toString()+"]: "+content;
    }
    
    @Override
    public boolean equals(Object x) {
             if (x instanceof Tweet) {
            return ((Tweet) x).content.equals(content); //if content same,tweet is same 
        }
        return false;
}
    
     public int hashCode() {
        int result = 17;
        result = 31 * result + user.hashCode();
        result = 31 * result + datetime.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }
    
    
}