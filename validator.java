import java.util.*;
import java.lang.Math;

class validator {
    //The following function will check it the IP address is valid or not
    static boolean validIP(String[] arr) {
        int x;
        for (int i = 0; i < 4; i++) {
            //parsing the incoming string to integer
            x = Integer.parseInt(arr[i]);
            //Checking if the IP address is in the range of 0 to 255
            if (x < 0 || x > 255)
                return false;
            else if(i==4 && (x<0 || x>=255))
                return false;
        }
        return true;
    }
    //This function converts each of the octets of the IP address a string
    static String generator(int[] arr) {
        int val = 0;
        String bottom = "";
        int i = 0;
        for (int j = 7; j >= 0; j--) {
            double temp = Math.pow(2, i);
            int x = (int) temp;
            val += (x * arr[j]);
            i++;
        }
        String f = Integer.toString(val);
        bottom += f;
        bottom += ".";
        val = 0;
        i = 0;
        for (int j = 15; j > 7; j--) {
            double temp = Math.pow(2, i);
            int x = (int) temp;
            val += (x * arr[j]);
            i++;
        }
        f = Integer.toString(val);
        bottom += f;
        bottom += ".";
        val = 0;
        i = 0;
        for (int j = 23; j > 15; j--) {
            double temp = Math.pow(2, i);
            int x = (int) temp;
            val += (x * arr[j]);
            i++;
        }
        f = Integer.toString(val);
        bottom += f;
        bottom += ".";
        val = 0;
        i = 0;
        for (int j = 31; j > 23; j--) {
            double temp = Math.pow(2, i);
            int x = (int) temp;
            val += (x * arr[j]);
            i++;
        }
        f = Integer.toString(val);
        bottom += f;
        val = 0;
        i = 0;
        return bottom;
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter IP-");
        String str1;
        str1 = scan.nextLine();
        System.out.println("\nEnter MASK-");
        int mask;
        mask = scan.nextInt();
        String[] valid = str1.split("\\.");
        if (validIP(valid)) {
            System.out.println("Entered IP is VALID\n");
        } else {
            System.out.println("Entered IP is INVALID!!!\n");
            System.exit(0);
        }
        int[] arr1 = new int[32];
        for (int i = 0; i < 4; i++) {
            int temp = Integer.parseInt(valid[i]);
            String binary = Integer.toBinaryString(temp);
            if (i == 0) {
                int j = 7;
                int x = binary.length();
                x--;
                while (x >= 0) {
                    char temp2 = binary.charAt(x);
                    if (temp2 == '0')
                        arr1[j--] = 0;
                    else
                        arr1[j--] = 1;
                    x--;
                }
            } else if (i == 1) {
                int j = 15;
                int x = binary.length();
                x--;
                while (x >= 0 && j > 7) {
                    char temp2 = binary.charAt(x);
                    if (temp2 == '0')
                        arr1[j--] = 0;
                    else
                        arr1[j--] = 1;
                    x--;
                }
            } else if (i == 2) {
                int j = 23;
                int x = binary.length();
                x--;
                while (x >= 0 && j > 15) {
                    char temp2 = binary.charAt(x);
                    if (temp2 == '0')
                        arr1[j--] = 0;
                    else
                        arr1[j--] = 1;
                    x--;
                }
            } else if (i == 3) {
                int j = 31;
                int x = binary.length();
                x--;
                while (x >= 0 && j > 23) {
                    char temp2 = binary.charAt(x);
                    if (temp2 == '0')
                        arr1[j--] = 0;
                    else
                        arr1[j--] = 1;
                    x--;
                }
            }
        }
        int[] first = new int[32];
        int[] last = new int[32];
        for (int i = 0; i < mask; i++) {
            first[i] = arr1[i];
            last[i] = arr1[i];
        }
        for (int i = mask; i < 32; i++) {
            first[i] = 0;
            last[i] = 1;
        }
        int[] firstRandomAdd = new int[32];
        int[] lastRandomAdd = new int[32];
        String first_address = generator(first);
        System.out.println("FIRST ADDRESS- " + first_address);
        String last_address = generator(last);
        System.out.println("\nLAST ADDRESS- " + last_address);
        double range = Math.pow(2, 32 - mask);
        int r = (int) range;
        System.out.println("\nTotal number of addresses in the block- " +
            range + "\n");
        //generating three blocks with the same block size
        System.out.println("\n3 Random Addresses of the same block size are:- \n ");
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 7; i++) {
                firstRandomAdd[i] = lastRandomAdd[i] =
                    (int) Math.floor(Math.random() * (1 - 0 + 1) + 0);
            }
            for (int i = 8; i < 32; i++) {
                firstRandomAdd[i] = first[i];
                lastRandomAdd[i] = last[i];
            }
            String firstRand = generator(firstRandomAdd);
            String lastRand = generator(lastRandomAdd);
            System.out.print("\tFIRST ADDRESS: ");
            System.out.println(firstRand);
            System.out.print("\tLAST ADDRESS: ");
            System.out.println(lastRand + "\n");
        }
    }
}
