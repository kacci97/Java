
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.net.*;
import java.lang.*;

class Product  {

    protected String name;
    protected float price;
    protected boolean domestic;
    protected int weight;
    protected String description;


    public Product(String name, float price, boolean domestic, int weight , String description) {
        this.name=name;
        this.price=price;
        this.domestic=domestic;
        this.weight=weight;
        this.description=description;

    }
    public Product(String name, float price, boolean domestic, String description) {
        this.name=name;
        this.price=price;
        this.domestic=domestic;
        this.weight=0;
        this.description=description;

    }

    public Product(){}

    public void copy(Product p){
        this.setName(p.getName());
        this.setPrice(p.getPrice());
        this.setdomestic(p.getDomestic());
        this.setWeight(p.getWeight());
        this.setDesc(p.getDesc());
    }

    public void setName(String name){
        this.name=name;
    }
    public void setPrice(Float price){
        this.price=price;
    }
    public void setdomestic(boolean domestic){
        this.domestic=domestic;
    }
    public void setWeight(int weight){
        this.weight=weight;
    }
    public void setDesc(String description){
        this.description=description;
    }
    public String getName(){
        return name;
    }
    public String getDesc(){
        return description;
    }
    public float getPrice(){
        return price;
    }
    public int getWeight(){
        return weight;
    }
    public boolean getDomestic(){
        return domestic;
    }




}

class Cart {

    static Collection<Product>collection=new ArrayList<>();

    public static  void addProduct(Product product) {

        collection.add(product);
    }

    public static void sortList(List<Product> list, int n) {


        for(int i=0;i<n-1;i++){
        Product smallest=new Product();
        smallest.copy(list.get(i));
         for(int j=i+1;j<n;j++){
            Product smaller=new Product();
            int compare=list.get(i).name.compareTo(list.get(j).name);
            if(compare<0){
                 smaller.copy(list.get(i));}
            else{
                smaller.copy(list.get(j));}

            if (smaller.name.compareTo(smallest.name)<0){
                    smallest.copy(smaller);}



            }
         list.add(i,smallest);


    }}

public static void print(List<Product> list, int numD){

    for(int i=0;i<numD;i++) {
        System.out.println("... "+list.get(i).name);
        System.out.println("    Price: $"+list.get(i).price);
        System.out.println("    "+list.get(i).description.substring(0,10)+". . .");
        if (list.get(i).weight!=0)
            System.out.println("    Weight: " + list.get(i).weight + "g");
         else
            System.out.println("    Weight: N/A");




    }
}


    public static void printReceipt(){



        List<Product> list=new ArrayList<>(collection);

        int n=list.size();
        int domesticProduct=0;
        float domesticPrice=0f;
        int importedProduct=0;
        float importedPrice=0f;
        for(int i=0;i<n;i++) {
            if(list.get(i).domestic) {
                domesticProduct++;
                domesticPrice+=list.get(i).price;

            }
            else {

                importedProduct++;
                importedPrice+=list.get(i).price;


            }

        }

        List<Product>domestic=new ArrayList<>();
        List<Product>imported=new ArrayList<>();

        for(int i=0;i<n;i++) {
            if(list.get(i).domestic)
                domestic.add(list.get(i));
            else
                imported.add(list.get(i));
            }

        int numD=domestic.size();
        int numI=imported.size();

        sortList(domestic,numD);
        sortList(imported,numI);

        System.out.println(". Domestic");
        print(domestic,numD);
        System.out.println(". Imported");
        print(imported,numI);

        System.out.println("Domestic cost: $"+domesticPrice);
        System.out.println("Imported cost: $"+importedPrice);
        System.out.println("Domestic count: "+domesticProduct);
        System.out.println("Imported count: "+importedProduct);

    }

}





public class shop{

    public static void main(String[] args)throws Exception {


        URL input = new URL("https://interview-task-api.mca.dev/qr-scanner-codes/alpha-qr-gFpwhsQ8fkY1");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(input.openStream()));
        List<String> list=new ArrayList<>();
        Map<String ,String >map=new HashMap<>();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.equals("  {") || inputLine.equals("  },") || inputLine.equals("[") || inputLine.equals("]") || inputLine.equals("  }"))
                continue;
            else {
                String[] parts = inputLine.split(": ");
                map.put(parts[0],parts[1]);
               /* StringBuilder sb=new StringBuilder(parts[1]);
                int len=parts[1].length();
                if(sb.charAt(len-1)==',')
                    sb.deleteCharAt(len-1);
                if(sb.charAt(0)=='"')
                    sb.deleteCharAt(0);
                if (sb.charAt(sb.length()-1)=='"')
                    sb.deleteCharAt(sb.length()-1);

                String value=sb.toString();

                list.add(value);*/
                 }



        }





        in.close();




        Product tomato=new Product(list.get(0),Float.parseFloat(list.get(2)),Boolean.parseBoolean(list.get(1)),Integer.parseInt(list.get(3)),list.get(4));
        Product apple=new Product(list.get(5),Float.parseFloat(list.get(7)),Boolean.parseBoolean(list.get(6)),list.get(8));
        Product banana=new Product(list.get(9),Float.parseFloat(list.get(11)),Boolean.parseBoolean(list.get(10)),list.get(12));
        Cart.addProduct(tomato);
        Cart.addProduct(apple);
        Cart.addProduct(banana);
        Cart.printReceipt();


    }
}