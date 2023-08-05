import edu.duke.*;
import java.io.File;
/**
 * Write a description of class Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
       int currentPos= dna.indexOf(stopCodon,startIndex+3);
       while(currentPos!=-1){
           
           if((currentPos-startIndex)%3==0){
               return currentPos;
            }
            currentPos=dna.indexOf(stopCodon,currentPos+1);
        }
       
       return dna.length();
       
    }
    public String findGene(String dna, int where){
        int startIndex= dna.indexOf("ATG", where);
        if( startIndex<0){
            return"";
        }
        int gTAA= findStopCodon(dna, startIndex,"TAA");
        int gTAG= findStopCodon(dna, startIndex,"TAG");
        int gTGA= findStopCodon(dna, startIndex,"TGA");
        int minIndex= Math.min(gTAA,Math.min(gTAG,gTGA));
        if(minIndex==dna.length()){
            return "";
        }
        return dna.substring(startIndex, minIndex+3);
    }
    public void printAllGenes(String dna){
        int startIndex=0;
        while(true){
            String currentGene= findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            System.out.println(currentGene);
            startIndex=dna.indexOf(currentGene,startIndex)+currentGene.length();
            
        }
    }
    public StorageResource getAllGenes(String dna){
        StorageResource geneList= new StorageResource();
        int startIndex=0;
        while(true){
            String currentGene= findGene(dna, startIndex);
            if(currentGene.isEmpty()){
                break;
            }
            geneList.add(currentGene);
            startIndex=dna.indexOf(currentGene,startIndex)+currentGene.length();
            
        }
        return geneList;
    }
    public double cgRatio(String dna){
        int nC=0;
        double nG=0;
        for(int n=0; n<dna.length();n++){
            String e=dna.substring(n,n+1);
            if(e.equals("C")){
                nC++;
            }
            if(e.equals("G")){
                nG++;
            }
        }
        
        return ((double) nC + nG) / dna.length();
    }
    public int countCTG(String dna){
        int startIndex=0;
        int count=0;
        while(startIndex>=0){
            startIndex=dna.indexOf("CTG",startIndex+3);
            if(startIndex>=0){
                count++;
            }
        }
        return count;
    }
    public void processGenes(StorageResource sr){
        int count=0;
        System.out.println("Cadenas con más de 60 caracteres");
        for (String item : sr.data()){
            if(item.length()>60){
                System.out.println(item);
                count++;
            }
        }
        System.out.println("Hay "+count+" con mas de 9 caracteres");
        count=0;
        System.out.println("Cadenas cuya relación C-G sea superior a 0,35");
        for (String item : sr.data()){
            if(cgRatio(item)>0.35){
                System.out.println(item);
                count++;
            }
        }
        System.out.println("Hay "+count+" cuya relación C-G sea superior a 0,35");
        String large="";
        System.out.println("Cadenas más larga");
        for (String item : sr.data()){
            if(item.length()>large.length()){
                large=item;
            }
        }
        System.out.println("La longitud con el length mas largo es: "+ large.length());
    }   
    public void  testProccesGenes(){
        System.out.println(findStopCodon("ATGaTAAdcDdcTAA",0,"TAA"));
        //Para tomar los datos desde un documento o pagina web de la cadena de ADN 
        URLResource fr = new URLResource("https://users.cs.duke.edu/~rodger/GRch38dnapart.fa");
        //FileResource fr = new FileResource("brca1line.fa"); //Descomente o comente la linea segun sea elcaso
        
        String dna = fr.asString();
        dna=dna.toUpperCase();
        
        StorageResource genes=getAllGenes(dna);
        System.out.println("Num de genes"+genes.size());
        processGenes(genes);
        System.out.println("Hay "+countCTG(dna)+" condons");
        
    }
}
