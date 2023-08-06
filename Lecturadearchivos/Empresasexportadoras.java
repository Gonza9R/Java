/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
    public String countryInfo (CSVParser parser, String country){
        for(CSVRecord record : parser){
            if(record.get("Country").equals(country)){
                return record.get("Country")+": "+record.get("Exports")+": "+record.get("Value (dollars)");
            }
        }
        return "Not found";
    }
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2){
        for(CSVRecord record : parser){
            String p=record.get("Exports");
            if(p.contains(exportItem1) && p.contains(exportItem2)){
             System.out.println(record.get("Country"));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count=0;
        for(CSVRecord record : parser){
            String p=record.get("Exports");
            if(p.contains(exportItem)){
                count++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser, String amount){
        for(CSVRecord record : parser){
            
            if(record.get("Value (dollars)").length()>amount.length()){
             System.out.println(record.get("Country")+": "+record.get("Value (dollars)"));
            }
        }
    }
    public void tester(){
       FileResource fr = new FileResource();
       CSVParser parser = fr.getCSVParser();
       System.out.println(countryInfo(parser,"Nauru"));
       parser = fr.getCSVParser();
       System.out.println("Cotoon and flowers");
       listExportersTwoProducts(parser,"cotton","flowers");
       parser = fr.getCSVParser();
       System.out.println("2 Cocoa");
       System.out.println(numberOfExporters(parser,"cocoa"));
       parser = fr.getCSVParser();
       System.out.print("$10000000");
       bigExporters(parser,"$400,000,000,000");
    }
}
