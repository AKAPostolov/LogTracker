/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

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
     public static void main(String args[]) 
     {
         String argument = "";
         
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
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains
                kword3 = args[4]; //keyWORD: existAt/atLine or at-Number/position/line-FromLast
                
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
            default:
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
     public static void showInsufficientArgs()
     {
        System.out.println("WOW: Insufficient params");
        showHelp();
     }
     public static void showHelp()
     {
        System.out.println("*****************************************************************************");
        System.out.println("HELP:\n (How to use)");
        System.out.println("-help (For help = THIS TEXT)");
        System.out.println("-file c:\file.ext -contains string \n");
        System.out.println("-search youtString -method topdown (For Top-Down search of a string)");
        System.out.println("-search youtString -method downup (For Down-UP search of a string)");
        System.out.println("");
        System.out.println(" '-' in front of a keyWord is not mandatory: search youtString method topdown");
        System.out.println("*****************************************************************************");
     }
}
