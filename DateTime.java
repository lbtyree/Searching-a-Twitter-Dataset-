/**
 * Represent a timestamp consisting of a date (day/month/year) and time 
 * in hours and minutes (24h format.
 */
public class DateTime  implements Comparable<DateTime> { //For part 4
    
    public Integer year;//change to Integer from int to support compareTo
    public Integer month;
    public Integer day; 
    public Integer hours;
    public Integer minutes;    
    public Integer seconds;
    public boolean pm; 

    
    public DateTime(int year, int day, int month, int h, int m) {        
        this.year = year; 
        this.month = month; 
        this.day = day;     
        this.hours = h; 
        this.minutes = m;                
    }
    
    public DateTime(String datetime) {
        String[] fields = datetime.split(" ");
        String[] dateFields = fields[0].split("/");
        month = Integer.parseInt(dateFields[0]);
        day = Integer.parseInt(dateFields[1]);
        year = Integer.parseInt(dateFields[2]);
        
        String[] timeFields = fields[1].split(":"); 
        hours = Integer.parseInt(timeFields[0]);
        minutes = Integer.parseInt(timeFields[1]);;
    }
    
    
    public int compareTo(DateTime x) {
         if((this.year.compareTo(x.year) + this.day.compareTo(x.day) 
                +  this.hours.compareTo(x.hours) + 
                this.minutes.compareTo(x.minutes))>3 ){
             return 1;
         }else{
             return 0;
         }
    }
    
    
    public String toString() {
        return Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year)+"  "+
            String.format("%02d" , hours)+":"+String.format("%02d", minutes);
    }   
      @Override
    public boolean equals(Object x) {
             if (x instanceof DateTime) {
            return (((DateTime) x).day.equals(day) && 
                    ((DateTime) x).hours.equals(hours)&& ((DateTime) x).minutes.equals(minutes)); //if same, datetime is same 
        }
        return false;
}
    
     public int hashCode() {
        int result = 17;
        result = 31 * result + year.hashCode();
        result = 31 * result + month.hashCode();
        result = 31 * result + day.hashCode();
        result = 31 * result + hours.hashCode();
        result = 31 * result + minutes.hashCode();
         
        return result;
    }
    
}