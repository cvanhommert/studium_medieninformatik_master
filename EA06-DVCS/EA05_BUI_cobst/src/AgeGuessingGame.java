/**
 *
 * @author cobst
 * @version 1.1
 * @since 2014.10.21
 * @param args the command line arguments
 * 
 * This game guesses the players age based on comand line inputs
 * Game idea orginates in http://www.philognosie.net/index.php/fun/funview/174/
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AgeGuessingGame {
    
    private String name = null;
    private String hasHadBirthdayInActYear = null;
    private String result = null;
    private Boolean testrun = false;
   
    public static void main(String[] args) {
        try {
            (new AgeGuessingGame()).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setName (String name) {
        this.name = name;
    }
    public void setBirthdayInActYear (String birthdayInActYear) {
        this.hasHadBirthdayInActYear = birthdayInActYear;
    }
    public void setResult (String result) {
        this.result = result;
    }
    public void setTestrun () {
        this.testrun = true;
    }
    public void unsetTestrun () {
        this.testrun = false;
    }
    
    
    public void run() throws IOException{
        
        Boolean stopTest = false;
        
        if (this.testrun) {
            String missingParameter = null;
            if (this.name == null) {
                missingParameter = "Name\n";
                stopTest = true;
            }
            if (this.hasHadBirthdayInActYear == null) {
                missingParameter+= "hasHadBirthdayInActYear\n";
                stopTest = true;
            }
            if (this.result == null) {
                missingParameter+= "result\n";
                stopTest = true;
            } 
            if (stopTest) {
                System.out.println("Missing Parameter!\n"+missingParameter);
            } else {
                System.out.println("Testing!\n\n");
            }
            
        }
        
        if (!stopTest) {
            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
            String tmpConsoleUserInput;

            String userName;
            if (!this.testrun) {
                do {
                System.out.println(
                      "Ich errate Ihr Alter!\n"
                    + "Bitte geben Sie Ihren Namen an und bestätigen mit Enter:");

                    userName = console.readLine();

              } while (userName.equals(""));
            } else {
                userName = this.name;
            }


            System.out.println(
                    "Vielen Dank "+ userName + "\n"
                    + "\n"
                    + "Bitte fo1gen Sie nun den folgenden Anweisungen (jeweils weiter mit Enter)");

            console.readLine();

            System.out.println("Liebe/r " + userName + ", bitte denken Sie sich eine Geheimzahl (n ∈ N) zwischen 1 und 9 aus (weiter mit Enter). \n   "
                    + "Hinweis: das Programm liest die kommenden Eingaben NICHT mit (es sei denn, es ist explizit angegeben). \n"
                    + "Sie können also die Eingabezeile als Platz um \"Zwischenergebnisse\" zu notieren nutzen. ");

            console.readLine();

            System.out.println("Multiplizieren Sie Ihre Geheimzahl mit 2 (weiter mit Enter).");

            console.readLine();

            System.out.println("Addieren Sie 2 zum Ergebnis (weiter mit Enter).");

            console.readLine();

            System.out.println("Multiplizieren Sie das Ergebnis mit 100 (weiter mit Enter).");

            console.readLine();

            System.out.println("Dividieren Sie nun durch 2 (weiter mit Enter).");

            console.readLine();

            System.out.println("Subtrahieren Sie die letzten beiden Ziffern Ihres Geburtsjahres vom Zwischenergebnis (weiter mit Enter).");

            console.readLine();

            Boolean bDayInActYear = true;
            String  bDayInActYearInput;
            String  inputIsYesRegEx = "^[jJyY]$";
            String  inputIsNoRegEx = "^[nN]$";
            Boolean bDayInActYearInputValid;
            
            do {
                System.out.println("Hatten Sie im aktuellen/laufenden Jahr bereits Geburtstag? (J)a (N)ein (weiter mit Enter):");

                if (this.testrun) {
                    bDayInActYearInput = this.hasHadBirthdayInActYear;
                } else {
                    bDayInActYearInput = console.readLine();
                }

                if (bDayInActYearInput.matches(inputIsYesRegEx) ) {
                    bDayInActYearInputValid = true;
                    bDayInActYear = true;
                } else if (bDayInActYearInput.matches(inputIsNoRegEx) ) {
                    bDayInActYearInputValid = true;
                    bDayInActYear = false;
                } else {
                    bDayInActYearInputValid = false;
                }

            } while (!bDayInActYearInputValid);

            Calendar cal = new GregorianCalendar();
            int thisYear = cal.get(Calendar.YEAR);

            // Calculation will be wrong if Program not used within the 2nd Milennium
            // Check if in 2nd Milennium
            if (thisYear > 3000 || thisYear < 2000) {
                System.out.println("Fehler im Jahr, setze 2014");
                thisYear = 2014;
            }

            String tmpYear = Integer.toString(thisYear);
            // birthday of user can be seen in last two digits of his result
            tmpYear = tmpYear.substring(2,4);
            int addYear;
            // Because your age depends not only on difference between actual year and
            // your year of birth but also on weather or not you have had birthday this
            // year, the calculation has to take this into account.
            if (bDayInActYear) {
                addYear = Integer.parseInt(tmpYear);
            } else {
                addYear = Integer.parseInt(tmpYear) - 1;
            }

            System.out.println("Bitte addieren Sie nun " + addYear + "(weiter mit Enter).");

            console.readLine();

            System.out.println("Geben Sie nun Ihr Ergebnis an (weiter mit Enter):");

            Boolean incorrectResultInput;
            int userCalculationResult = 0;
            do {
                
                if (this.testrun) {
                    tmpConsoleUserInput = this.result;
                } else {
                    tmpConsoleUserInput = console.readLine();
                }
                String regExOnlyExactThreeDigits = "^\\d{3}$";

                // If user has calculated correctly, his answer will be a 3 digits long number
                if (tmpConsoleUserInput.matches(regExOnlyExactThreeDigits)) {
                    userCalculationResult = Integer.parseInt(tmpConsoleUserInput);
                    incorrectResultInput = false;
                } else {
                    incorrectResultInput = true;
                    System.out.println("Bitte überprüfen Sie Ihre Eingabe (weiter mit Enter):");
                }
            } while (incorrectResultInput);

            String userCalculationResultTemp = Integer.toString(userCalculationResult);

            // If the user has calculated correctly, his secret number can be found as first digit 
            // and his age can be found as last two digits of his result
            String userSecretNumber = userCalculationResultTemp.substring(0,1);
            String userAge = userCalculationResultTemp.substring(1,3);

            System.out.println("Sie sind " + userAge + " Jahre alt ... (geht's noch weiter mit Enter?).");

            console.readLine();

            System.out.println("Ach ja und Ihre Geheimzahl ist " + userSecretNumber + "\n\n");     

            System.out.println("Möchten Sie noch einmal spielen? (J)a: ");     

            tmpConsoleUserInput = console.readLine();
            if (tmpConsoleUserInput.matches(inputIsYesRegEx)) {
                this.run();
            } else {
               System.out.println("Auf Wiedersehen!");
            }
        }
    }    
}

