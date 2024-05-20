import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
class Airline {
    private static class Passenger {
        String passport;
        String name;
        String email;
        String destination;
        int seatNum;
        Passenger following;
        Passenger(String passport, String name, String email, String destination) {
            this.passport = passport;
            this.name = name;
            this.email = email;
            this.destination = destination;
            this.seatNum = 0;
            this.following = null;
        }
    }
    private static Passenger begin = null;
    private static Passenger stream = null; 
    private static Passenger dummy = null;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        int num = 1;
        do {
            System.out.println("\n\n\t\t ********************************************************************");
            System.out.println("\n\t\t                   Welcome to team's Airline System                   ");
            System.out.println("\n\t\t   *******************************************************************");
            System.out.println("\n\n\n\t\t Please enter your choice from below (1-4):");
            System.out.println("\n\n\t\t 1. Reservation");
            System.out.println("\n\n\t\t 2. Cancel");
            System.out.println("\n\n\t\t 3. Display Records");
            System.out.println("\n\n\t\t 4. Exit");
            System.out.println("\n\n\t\t Feel free to contact ");
            System.out.println("\n\n\t\t Enter your choice :");

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    reserve(num);
                    num++;
                    break;
                case 2:
                    cancel();
                    break;
                case 3:
                    display();
                    break;
                case 4:
                    saveFile();
                    break;
                default:
                    System.out.println("\n\n\t SORRY INVALID CHOICE!");
                    System.out.println("\n\n\t PLEASE CHOOSE FROM 1-4");
                    System.out.println("\n\n\t Do not forget to chose from 1-4");
            }
        } while (choice != 4);
        scanner.close();
    }
    private static void details() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\t Enter your passport number: ");
        String passport = scanner.nextLine();

        System.out.print("\n\t Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("\n\t Enter your email address: ");
        String email = scanner.nextLine();

        System.out.print("\n\t Enter the Destination from given options:\n\t \"Dehli\"\n\t  \"Jaipur\"\n\t  \"Kolkata\"\n\t  \"Mumbai\"\n\t \"Udaypur\"\n\t \"Banglor\"\n\t  \"Masuri\"\n\t  \"Hadrabad\"\n\t  \"Aamhmdaaad\"\n");
                
        
        String destination = scanner.nextLine();
        //System.out.println("\n\t Thank you!");
        //String dream=destination;
        if(destination=="Dehli"||destination=="Jaipur"||destination=="Kolkata"||destination=="Mumbai"||
        destination=="Udaypur"||destination=="Banglor"||destination=="Masuri"||
        destination=="Hadrabad"||destination=="Aamhmdaaad"){
            
            System.out.println("\n\t Thank you!");
        }
        else{
           System.out.println("\n\t Its not available \n\t you can choose your destination from above options");
        }
        

        stream.passport = passport;
        stream.name = name;
        stream.email = email;
        stream.destination = destination;
    }
    private static void reserve(int x) {
        stream = begin;
        if (begin == null) {
            // First user
            begin = stream = new Passenger("", "", "", "");
            details();
            stream.seatNum = x;
            System.out.println("\n\t Seat booking successful!");
            System.out.println("\n\t Your seat number is: Seat A-" + x);
            return;
        } else if (x > 15) {
            System.out.println("\n\t\t Seat Full.");
            return;
        } else {
            // Next user
            while (stream.following != null)
                stream = stream.following;
            stream.following = new Passenger("", "", "", "");
            stream = stream.following;
            details();
            stream.seatNum = x;
            System.out.println("\n\t Seat booking successful!");
            System.out.println("\n\t Your seat number is: Seat A-" + x);
        }
    }
    private static void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("team_records.txt"));

            stream = begin;
            while (stream != null) {
                writer.write(String.format("%-6s%-15s%-15s%-15s\n", stream.passport, stream.name, stream.email, stream.destination));
                stream = stream.following;
            }

            writer.close();
            System.out.println("\n\n\t Details have been saved to a file (team_records.txt)");
        } catch (IOException e) {
            System.err.println("\n Error in opening file!");
        }
    }
    private static void display() {
        stream = begin;
        while (stream != null) {
            System.out.println("\n\n Passport Number : " + stream.passport);
            System.out.println("         Name : " + stream.name);
            System.out.println("      Email Address: " + stream.email);
            System.out.println("      Seat number: A-" + stream.seatNum);
            System.out.println("     Destination: " + stream.destination);
            System.out.println("\n++=====================================================++");
            stream = stream.following;
        }
    }
    private static void cancel() {
        stream = begin;
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n\n Enter passport number to delete record: ");
        String passport = scanner.nextLine();

        if (begin.passport.equals(passport)) {
            dummy = begin;
            begin = begin.following;
            System.out.println(" Booking has been deleted");
            return;
        }
        while (stream.following != null) {
            if (stream.following.passport.equals(passport)) {
                dummy = stream.following;
                stream.following = stream.following.following;
                System.out.println(" Booking has been deleted ");
                return;
            }
            stream = stream.following;
        }
        System.out.println(" Passport number is wrong, please check your passport");
    }
}