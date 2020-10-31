import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class App{
    private static ArrayList <String> Cashuser;
    private static ArrayList <String> Cashpass;
    private static ArrayList <String> Products;
    private static ArrayList <String> Costs;
    private static ArrayList <Integer> Quantity;
    private static ArrayList <Integer> Subtotal;
    private static ArrayList <String> CurBill;
    private static ArrayList <String> Curprice;
    private static ArrayList <String> oldBill;
    private static ArrayList <String> Members;
    
    public static void header(){
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("                                          Shopping Mall Billing system                                                         ");
        System.out.println("###############################################################################################################################");
        System.out.println("  Balachandar.B(2019PECCS373)     Augustin sham.J(2019PECCS369)       Gokulnath(2019PECCS405)        Amarnath.R(2019PECCS356)  ");
        System.out.println("                                      Arul Vijo(2019PECCS359)     Babin Mon.B(2019PECCS370)                                    ");
        System.out.println("###############################################################################################################################");
    }
    public static void load(){
        try{
        Cashuser.clear();
        Cashpass.clear();
        Products.clear();
        Costs.clear();
        Quantity.clear();
        Subtotal.clear();
        Members.clear();
        CurBill.clear();
        Curprice.clear();
        oldBill.clear();
        Scanner user = new Scanner(new File("usernames.txt"));
        while (user.hasNext()){
            Cashuser.add(user.next());
        }
        user.close();
        Scanner pass = new Scanner(new File("passwords.txt"));
        while (pass.hasNext()){
            Cashpass.add(pass.next());
        }
        pass.close();
        Scanner pro = new Scanner(new File("productname.txt"));
        while (pro.hasNext()){
            Products.add(pro.next());
        }
        pro.close();
        Scanner cost = new Scanner(new File("cost.txt"));
        while (cost.hasNext()){
            Costs.add(cost.next());
        }
        cost.close();
        Scanner memb = new Scanner(new File("members.txt"));
        int i=1;
        while (memb.hasNext()){
            if(i%2!=0){
                Members.add(memb.next());
                i++;
            }
            else{
                i++;
                memb.next();
            }           

        }
        memb.close();        
        return;
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void remove (String s,String f){
        File inputFile = new File(f);
        File tempFile = new File("myTempFile.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
    
            String lineToRemove = s;
            String currentLine;
    
            while((currentLine = reader.readLine()) != null) {
                if(currentLine.equals(lineToRemove)) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close(); 
            reader.close(); 
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    private static void admin() {

        Boolean login=true;
        Console cnsl = System.console();
        while (login) {
            try{
            header();
            System.out.println("                                                   WELCOME ADMIN                                                                   ");
            System.out.println("Welcome Admin...!!!");
            System.out.println("1.Add a user");
            System.out.println("2.remove a user");
            System.out.println("3.Add an new product");
            System.out.println("4.Remove an product");
            System.out.println("5.log out");
            load();
            int ch = Integer.parseInt(cnsl.readLine("Enter choice: "));  
            if(ch==1){
                System.out.println();
                System.out.println();
                System.out.println();
                String new_name=cnsl.readLine("Enter new user's username: ");
                String new_pass =cnsl.readLine("create a password: ");
                Cashuser.add(new_name);
                Cashpass.add(new_pass);
                try { 
                    PrintWriter user = new PrintWriter(new BufferedWriter(new FileWriter("usernames.txt", true)));
                    user.println(new_name);
                    user.flush();
                    user.close();
                    PrintWriter pass = new PrintWriter(new BufferedWriter(new FileWriter("passwords.txt", true)));
                    pass.println(new_pass);
                    pass.flush();
                    pass.close();
                  }
                  catch (IOException e) {  
                    System.out.println(e);}
                System.out.println("New user "+new_name+" has been added..");
            }
            if (ch==2) {
                System.out.println();
                System.out.println();
                System.out.println();
                String rname=cnsl.readLine("Whom you need to remove:");
                int i=Cashuser.indexOf(rname);
                String rpass=Cashpass.get(i);
                Cashuser.remove(i);
                Cashpass.remove(i);
                remove(rname,"usernames.txt");
                remove(rpass,"passwords.txt");
                System.out.println(rname+" has been removed...");
            }
            if(ch==3){
                System.out.println();
                System.out.println();
                System.out.println();
                String new_product=cnsl.readLine("Enter Product's name: ");
                String new_cost =cnsl.readLine("Enter its cost: ");
                Products.add(new_product);
                Costs.add(new_cost);
                try { 
                    PrintWriter produ = new PrintWriter(new BufferedWriter(new FileWriter("productname.txt", true)));
                    produ.println(new_product);
                    produ.flush();
                    produ.close();
                    PrintWriter cost = new PrintWriter(new BufferedWriter(new FileWriter("cost.txt", true)));
                    cost.println(new_cost);
                    cost.flush();
                    cost.close();
                  }
                catch (IOException e){
                    System.out.println(e);}
                System.out.println("New product "+new_product+" has been added..");
            }
            if (ch==4) {
                System.out.println();
                System.out.println();
                System.out.println();
                String rprodu=cnsl.readLine("Which product you need to remove:");
                int i=Products.indexOf(rprodu);
                String rcost=Costs.get(i);
                Products.remove(i);
                Costs.remove(i);
                remove(rprodu,"productname.txt");
                remove(rcost,"cost.txt");
                System.out.println(rprodu+" has been removed...");
            }
            if(ch==5){
                System.out.println("Logging out..");
                return;
            }
        }
            catch(Exception e){
                System.out.println("Wrong entry...");
            }
        }
    }
    private static void cashier(String username) {
        Console cnsl = System.console();
        String curcashier=null;
        curcashier=username;
        while(true){
            try{
            header();
            System.out.println("                                                   WELCOME "+username+"                                                                   ");
            System.out.println("Welcome "+curcashier+"...!!!");
            System.out.println("1.Add a new member");
            System.out.println("2.Generate Bill");
            System.out.println("3.View member's last bill");
            System.out.println("4.log out");
            load();
            int ch = Integer.parseInt(cnsl.readLine("Enter choice: "));
            if(ch==1){
                System.out.println();
                System.out.println();
                System.out.println();
                String new_mem=cnsl.readLine("Enter new members's name: ");
                String new_mobile=cnsl.readLine("Enter "+new_mem+"'s mobile num: ");
                Members.add(new_mem);
                try {
                    PrintWriter user = new PrintWriter(new BufferedWriter(new FileWriter("members.txt", true)));
                    user.println(new_mem);
                    user.println(new_mobile);
                    user.flush();
                    user.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if(ch==2){
                int cost=0;
                int total=0;
                int m=0;
                int sub=0;
                System.out.println();
                System.out.println();
                System.out.println();
                String cus=cnsl.readLine("Enter Customer's name: ");
                for (int i = 0; Members.size() > i; i++) {
                    if (cus.equals(Members.get(i))) {
                        m = 1;
                        break;
                    }
                }
                if(m==0){
                    System.out.println("he is not an registerd member");
                }
                while(true){
                    String product = cnsl.readLine("Enter product name: ");
                    int quan=Integer.parseInt(cnsl.readLine("quantity: "));
                    LocalTime time = LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
                    LocalDate date = LocalDate.now();   
                    int c=0;
                    for (int i = 0; Products.size() > i; i++) {
                        if (product.equals(Products.get(i))) {
                            c = 1;
                            cost=Integer.parseInt(Costs.get(i));
                            CurBill.add(product);
                            Curprice.add(Costs.get(i));
                            sub=cost*quan;
                            Quantity.add(quan);
                            Subtotal.add(sub);
                            total=total+sub;
                            break;
                        }
                    }
                    if(c!=1){
                        System.out.println("Cannot find the product..");
                        cnsl.readLine();
                    }
                    String t = cnsl.readLine("Do you want to continue to add another product to bill(Y/N):");
                    if(t.equals("Y")||t.equals("y")){
                        continue;
                    }
                    String format = "|%1$-36s|%2$-7s|%3$-10s|%4$-10s|\n";
                    System.out.println("####################################################################");
                    System.out.println("#           Products name            # Price # Quantity # Subtotal #");
                    System.out.println("####################################################################");
                    for(int i=0;i<CurBill.size();i++){
                        System.out.format(format, CurBill.get(i), Curprice.get(i), Quantity.get(i),Subtotal.get(i));
                    }
                    System.out.println("####################################################################");
                    System.out.println("                            Date:"+date+" Time:"+time+" Total:"+total);
                    if(m==1){
                        String oldFile=cus+".txt";
                        File old=new File(oldFile);
                        File tempFile = new File("temp.txt");
                        try{
                            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                            for(int i=0;i<CurBill.size();i++){
                                writer.write(CurBill.get(i) +"  "+ Quantity.get(i) +System.getProperty("line.separator"));
                            }
                            writer.close(); 
                            old.delete();
                            tempFile.renameTo(old);
                        } 
                        catch (Exception e) {
                            System.out.println(e);
                        }
                    }
                    cnsl.readLine();
                    break;
                }

            }
            if(ch==3){
                try {
               
                    System.out.println();
                    System.out.println();
                    System.out.println();
                    String mem=cnsl.readLine("Enter members's name: ");
                    BufferedReader br = new BufferedReader(new FileReader(mem+".txt"));
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                    br.close();
                    cnsl.readLine();
                    
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            if(ch==4){
                System.out.println("Logging out");
                return;
            }}
            catch(Exception e){
                System.out.println("Wrong entry...");
            }  

        }
    }

    public static void main(String[] args) {
        Console cnsl = System.console();
        String username = null;
        Cashuser = new ArrayList<String>();
        Cashpass = new ArrayList<String>();
        Products = new ArrayList<String>();
        Costs = new ArrayList<String>();
        Quantity = new ArrayList<Integer>();
        Subtotal = new ArrayList<Integer>();
        Members = new ArrayList<String>();
        Curprice = new ArrayList<String>();
        CurBill = new ArrayList<String>();
        oldBill = new ArrayList<String>();

        while (true) {
            load();
            int c = 0;
            int a = 0;
            header();
            System.out.println("                                                   LOGIN PAGE                                                                   ");


            username = cnsl.readLine("Enter username: ");
            char[] pw = cnsl.readPassword("Enter your password: ");
            String password = new String(pw);
            

            if (username.equals("admin") & password.equals("admin")) {
                a = 1;
                admin();
            } else {
                for (int i = 0; Cashuser.size() > i; i++) {
                    if (username.equals(Cashuser.get(i)) & password.equals(Cashpass.get(i))) {
                        c = 1;
                        break;
                    }
                }
            }
            if (c == 1) {
                cashier(username);
            }
            if(a==0 & c==0){
                System.out.println("Invalid credentials:");
                System.out.println(username);
                System.out.println(password);
            }
        }
    }
}
