/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author XTO
 */

public class Main
{
    static String kword1 = "";   
    static String kword2 = "";
    static String kword3 = "";
    static String arg1 = "";
    static String arg2 = "";
    static String arg3 = "";
    public static void printReceivedArgs(String args[])
    {
        String commandLine = "";
        for(int i=0; i<args.length; i++)
        {
            commandLine+=args[i]+" ";
        }
        System.out.println(commandLine);
    }
     public static void main(String args[]) 
     {
        printReceivedArgs(args);
        switch (args.length)
        {
            case 0:
                //help Case
                showHelp();
                break;
            case 1:
                //help Case
                showHelp();
                break;
            case 2:
                //help Case
                showHelp();
                break;
            case 3:
                break;
            case 4:
                // -file c:\bat.bat -contains error
                // -file c:\bat.bat -countLines error
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains or countLines or existAt
                
                arg1 = args[1]; //file
                arg2 = args[3]; //string
                
                if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("countlines"))
                {
                    int result = getCountLinesFoundInFile(arg2, arg1);
                    System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("contains"))
                {
                    boolean result = isStringInFile(arg2, arg1);  
                    System.out.println("result: " + result);
                }
            break;
            case 5:
                break;
            case 6:
                //-file c:\bat.bat -contains error atLine 7
                //-file c:\bat.bat -contains error atLast 3
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains
                kword3 = args[4]; //keyWORD: existAt/atLine or at-Number/position/line-FromLast or > >>
                
                arg1 = args[1]; //file
                arg2 = args[3]; //string
                arg3 = args[5]; //num
                if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains("at") && !kword3.toLowerCase().contains("last"))
                {
                  boolean result = isStringInFileLine(arg2, arg1, arg3);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains("at") && kword3.toLowerCase().contains("last"))
                {
                  boolean result = isStringInFileLineFromLast(arg2, arg1, arg3);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains(">"))
                {
                  boolean result = printMatchingStringsInFile(arg2, arg1, arg3);
                  System.out.println("result: " + result);
                }
                break;
            default:
                System.out.println("Unhandled number or params, please check the help privided below ");
                showHelp();
            break;
        }
         System.out.println("End of process");
     }
     public static boolean isStringInFileLine(String cadena, String filename, String lineNumber)
     {
         try
         {
            int lineNum = Integer.valueOf(lineNumber);
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            int i = 0; 
            for (String line: lines)
             {
                 i++;
                 if(i==lineNum)
                 {
                     if(line.toLowerCase().contains(cadena.toLowerCase()))
                     {
                        String printLine = "Result isStringInFileLine: String found at line: " + i + "/" + lines.size();
                        System.out.println(printLine);
                        return true;
                     }
                 }
                 else if(i>lineNum) 
                 {
                     return false;
                 }
             }
            return false;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return false;
         }
     }
     public static boolean isStringInFileLineFromLast(String cadena, String filename, String lineNumberFromlast)
     {
         try
         {
            int lineNumFromlast = Integer.valueOf(lineNumberFromlast);
            
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            
            int lineNum = lines.size()-lineNumFromlast;
            
            int i = 0; 
            for (String line: lines)
             {
                 i++;
                 if(i==lineNum+1)
                 {System.out.println("+1 :"+line);}
                 if(i==lineNum-1)
                 {System.out.println("-1 :"+line);}
                 if(i==lineNum)
                 {
                     System.out.println(" 0 :"+line);
                     if(line.toLowerCase().contains(cadena.toLowerCase()))
                     {
                        String printLine = "Result isStringInFileLineFromLast: String found at line: " + i + "/" + lines.size();
                        System.out.println(printLine);
                        return true;
                     }
                 }
                 else if(i>lineNum) 
                 {
                     return false;
                 }
             }
            return false;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return false;
         }
     }
     public static boolean isStringInFile(String cadena, String filename)
     {
         try
         {
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            int i = 0; 
            for (String line: lines)
             {
                 i++;
                 if(line.toLowerCase().contains(cadena.toLowerCase()))
                 {
                     String printLine = "Result isStringInFile: String found at line: " + i + "/" + lines.size();
                     System.out.println(printLine);
                     return true;
                 }
             }
            return false;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return false;
         }
     }
     public static int getCountLinesFoundInFile(String cadena, String filename)
     {
         try
         {
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            int i = 0; 
            for (String line: lines)
             {
                 if(line.toLowerCase().contains(cadena.toLowerCase()))
                 {
                     i++;
                 }
             }
             String printLine = "Result getCountLinesFoundInFile: " + cadena + " Found: " + i + " times in " + lines.size() + " lines.";
             System.out.println(printLine);
             return i;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return 0;
         }
     }
     public static boolean printMatchingStringsInFile(String cadena, String filename, String outputFilename)
     {
         try
         {
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            int i = 0; 
            for (String line: lines)
             {
                 i++;
                 if(line.toLowerCase().contains(cadena.toLowerCase()))
                 {
                     String printLine = "Result printMatchingStringsInFile: String found at line: " + i + "/" + lines.size();
                     System.out.println(printLine);
                     String outputLine = line + " @L"+i+"/"+lines.size();
                     writeToFile(outputFilename,outputLine);
                     return true;
                 }
             }
            return false;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return false;
         }
     }
     public static void showInsufficientArgs()
     {
        System.out.println("WOW: Insufficient params");
        showHelp();
     }
     public static void showHelp()
     {
        System.out.println("*****************************************************");
        System.out.println("*   .•°¤*(¯`★´¯)*¤° LogTracker °¤*(¯´★`¯)*¤°•. start");
        System.out.println("* ");
        System.out.println("*                    Commands: ");
        System.out.println("* ");
        System.out.println("* [ ] -help (after param error or empty param)");
        System.out.println("* [1] -file c:\\file -contains error");
        System.out.println("* [2] -file c:\\file -countLines error");
        System.out.println("* [3] -file c:\\file -contains error atLine 7");
        System.out.println("* [4] -file c:\\file -contains error atLast 3");
        System.out.println("* [5] -file c:\\file -contains error > outputFilename");
        System.out.println("* ");
        System.out.println("* [ ] Explained:");
        System.out.println("* [1] Returns true/false if a string matches in a file");
        System.out.println("* [2] Returns number of lines matching the specified string");
        System.out.println("* [3] Returns true/false if a string matches in a line");
        System.out.println("* [4] Same as 3 but the line is specified downToUp");
        System.out.println("* [5] Returns the number of lines and print these lines in a file");
        System.out.println("* ");
        System.out.println("*   .•°¤*(¯`★´¯)*¤° LogTracker °¤*(¯´★`¯)*¤°•. end");
        System.out.println("***************************************************");
     }
     private static final String newLine = System.getProperty("line.separator");

private static synchronized void writeToFile(String fileName, String msg)  
{
    PrintWriter printWriter = null;
    File file = new File(fileName);
    try 
    {
        if (!file.exists()) file.createNewFile();
        printWriter = new PrintWriter(new FileOutputStream(fileName, true));
        printWriter.write(newLine + msg);
    } 
    catch (IOException ioex) 
    {
        ioex.printStackTrace();
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
    finally 
    {
        if (printWriter != null) {
            printWriter.flush();
            printWriter.close();
        }
    }
}

}
