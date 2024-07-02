//import the respective packages
import java.io.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
//driver class
public class LabTest0724
{
    //Driver method
    public static void main(String[]args) throws IOException
    {
        //Instantiate the object of DecimalFormat
        DecimalFormat dF = new DecimalFormat("0.00");

        try
        {
            //Set the input/output file
            //input file
            BufferedReader inputFile = new BufferedReader(new FileReader("flightBookings.txt"));

            //2 output files
            PrintWriter withDisc = new PrintWriter(new 
                    FileWriter("discountedBookings.txt"));
            PrintWriter withoutDisc = new PrintWriter(new 
                    FileWriter("noDiscountBookings.txt"));

            //Declare the variables
            String inputData = null;
            String custName,custDestination;
            double custPrice,totPrice, priceAftDisc, priceAftDisc2;
            double discRate;
            int custQty;

            //Write the title to the withDiscount.txt
            withDisc.println("+-----------------+--------------------+--------------------------+----------------------+");
            withDisc.printf("| %-80s |\n", "List of Customer Entitled for Discount");
            withDisc.println("+-----------------+--------------------+--------------------------+----------------------+");
            withDisc.printf("| %-15s | %-18s | %-24s | %-20s |\n", "Customer Name", "Flt Destination", "Total Price Before Disc", "Total Disc. Price");
            withDisc.println("+-----------------+--------------------+--------------------------+----------------------+");            

            //Write the title the withoutDiscount.txt
            withoutDisc.println("+-----------------+--------------------+--------------------------+");
            withoutDisc.printf("| %-62s |\n", "List of Customer NOT Entitled for Discount");
            withoutDisc.println("+-----------------+--------------------+--------------------------+");
            withoutDisc.printf("| %-15s | %-18s | %-24s |\n", "Customer Name", "Flt Destination", "Total Price");
            withoutDisc.println("+-----------------+--------------------+--------------------------+");   
            int countWithDisc = 0;
            int countWithoutDisc = 0;

            while((inputData = inputFile.readLine()) != null)
            {
                //Instantiate the object reference of the StringTokenizer class

                //to pass the string line (input data) & to set the delimeter
                StringTokenizer sT = new StringTokenizer(inputData,",");
                //to pass the string line &delimeter

                //Break into tokens and assign to the appropriate variables
                custName = sT.nextToken().trim();
                custDestination = sT.nextToken().trim();

                //or
                custPrice =Double.parseDouble(sT.nextToken().trim());
                custQty =Integer.parseInt(sT.nextToken().trim());

                //String str = sT.nextToken();
                //custQty = Integer.parseInt(str);

                //To test for the negative number
                if(custPrice < 0)
                    throw new IllegalArgumentException();

                //Calculate the total price
                totPrice = custPrice * custQty;

                //Initialize the category to null
                String category =null;
                if(totPrice < 300)
                {
                    //Calculate discount
                    discRate = 0.0;
                    priceAftDisc = totPrice;
                    withoutDisc.printf("| %-15s | %-18s | %-24s |\n", custName, custDestination, dF.format(totPrice));
                }
                else if (totPrice >= 300 && totPrice <= 600)
                {
                    if (custQty > 2){//extra 5% discount if more than 2
                        discRate = 0.15;
                        priceAftDisc = totPrice * (1 - 0.15);
                        withDisc.printf("| %-15s | %-18s | %-24s | %-20s |\n", custName, custDestination, dF.format(totPrice),dF.format(priceAftDisc));
                    } else {
                        discRate = 0.1;
                        priceAftDisc = totPrice * (1 - discRate);
                        withDisc.printf("| %-15s | %-18s | %-24s | %-20s |\n", custName, custDestination, dF.format(totPrice),dF.format(priceAftDisc));
                    }
                }
                else
                {
                    if (custQty > 2){
                        discRate = 0.25;
                        priceAftDisc = totPrice * (1 - discRate);
                        withDisc.printf("| %-15s | %-18s | %-24s | %-20s |\n", custName, custDestination, dF.format(totPrice),dF.format(priceAftDisc));
                    } else {
                        discRate = 0.2;
                        priceAftDisc = totPrice * (1 - discRate);
                        withDisc.printf("| %-15s | %-18s | %-24s | %-20s |\n", custName, custDestination, dF.format(totPrice),dF.format(priceAftDisc));

                    }
                }
            }//end of while loop

            //close all the input/output files
            //Close the input/output file
            inputFile.close();
            withDisc.close();
            withoutDisc.close();
        }//end of try block
        catch(FileNotFoundException ex)
        {
            String output="File not found";
            JOptionPane.showMessageDialog(null, output);
        }
        catch(IllegalArgumentException iae)
        {
            String output="Invalid input! The price must be a positive number \n" + iae;
            JOptionPane.showMessageDialog(null, output);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error: \n "+ e);
        }//end of try-catch statement
    }//end of main
}//end of class