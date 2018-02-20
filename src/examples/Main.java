/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
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
    static String kword4 = "";
    static String arg1 = "";
    static String arg2 = "";
    static String arg3 = "";
    static String arg4 = "";
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
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains removeWatermark
                arg1 = args[1]; //file
                
                if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("removewatermark") && kword2.toLowerCase().contains("newfile"))
                {
                  String aux = arg1;
                  String outputFile = aux.replace(".html", "_nowatermark.html");
                  outputFile        = aux.replace(".htm", "_nowatermark.htm");
                  outputFile        = aux.replace(".xhtml", "_nowatermark.xhtml");
                  boolean result = replaceLineInFile(arg1, arg2, arg3.toLowerCase(), outputFile);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("removewatermark")&& !kword2.toLowerCase().contains("newfile"))
                {
                  boolean result = replaceLineInFile(arg1, arg2, arg3.toLowerCase(), null);
                  System.out.println("result: " + result);
                }
                break;
            case 4:
                // -file c:\bat.bat -contains error
                // -file c:\bat.bat -countLines error
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains or countLines or existAt
                
                arg1 = args[1]; //file
                arg2 = args[3]; //string
                
                if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("countlines"))
                {
                    int result = getCountLinesFoundInFile(arg2, arg1);
                    System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("contains"))
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
                kword2 = args[2]; //keyWORD: contains or replace
                kword3 = args[4]; //keyWORD: existAt/atLine or at-Number/position/line-FromLast or > >> or WITH
                kword4 = args[6]; //keyWORD: file xxx replace yyy with zzz > output
                
                arg1 = args[1]; //file
                arg2 = args[3]; //string
                arg3 = args[5]; //num
                arg4 = args[7]; //num
                if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains("at") && !kword3.toLowerCase().contains("last"))//atLine
                {
                  boolean result = isStringInFileLine(arg2, arg1, arg3);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains("at") && kword3.toLowerCase().contains("last"))
                {
                  boolean result = isStringInFileLineFromLast(arg2, arg1, arg3);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains(">"))
                {
                  int result = printMatchingStringsInFile(arg2, arg1, arg3, null);
                  System.out.println("result: " + result);
                }
                else if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("replace") && kword3.toLowerCase().contains("with"))
                {
                  boolean result = replaceLineInFile(arg1, arg2, arg3.toLowerCase(), null);
                  System.out.println("result: " + result);
                }
                break;
            case 8:
                kword1 = args[0]; //keyWORD: file
                kword2 = args[2]; //keyWORD: contains or replace
                kword3 = args[4]; //keyWORD: existAt/atLine or at-Number/position/line-FromLast or > >> or WITH
                kword4 = args[6]; //keyWORD: file xxx replace yyy with zzz > output
                
                arg1 = args[1]; //file
                arg2 = args[3]; //string
                arg3 = args[5]; //num
                arg4 = args[7]; //num
                if(kword1.toLowerCase().contains("file") &&kword2.toLowerCase().contains("replace") && kword3.toLowerCase().contains("with") && kword4.toLowerCase().contains(">"))
                {
                  boolean result = replaceLineInFile(arg1, arg2, arg3.toLowerCase(), arg4);
                  System.out.println("result: " + result);
                }
                //-file C:\\file -contains 'error' >> outputFileErrorLines except 'string'
                else if(kword1.toLowerCase().contains("file") && kword2.toLowerCase().contains("contains") && kword3.toLowerCase().contains(">") && kword4.toLowerCase().contains("except"))
                {
                  boolean result = printMatchingStringsInFile(arg2, arg1, arg3, arg4);
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
     public static boolean replaceMatchingStringInFile(String fileName, String toUpdate, String updated)
     {
        boolean result = updateLineInFile(fileName,toUpdate,updated);
        return result;
     }
    private static boolean updateLineInFile(String fileName, String toUpdate, String updated) 
    {
        /**
         * If you read and modify line by line this has the advantage, 
         * that you dont need to fit the whole file in memory. 
         * Not sure if this is possible in your case, but it is generally 
         * a good thing to aim for streaming. In your case this would 
         * in addition remove the need for concatenate the string and 
         * you don't need to select a line terminator, because you can 
         * write each single transformed line with println(). 
         * It requires to write to a different file, which is generally 
         * a good thing as it is crash safe. You would lose data if you 
         * rewrite a file and get aborted.
         * 
         * In this case, it assumes that you need to do the replace only on complete lines, 
         * not multiple lines. I also added an explicit encoding and use a writer, as you have a string to output.
         */
        try
        {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new File(fileName), "UTF-8"); // orig PrintWriter writer = new PrintWriter(new File(file+".out"), "UTF-8");
            String line;

            while ((line = br.readLine()) != null)
            {
                line = line.replace(toUpdate, updated);
                writer.println(line);
            }
            br.close();
            if (writer.checkError())
                throw new IOException("cannot write");
            writer.close();
            return true;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{return false;}
    }
    private static boolean replaceLineInFile(String fileName, String toUpdate, String updated, String outputFile)
    {
        try
        {
            String tobeUpdated;
            toUpdate = toUpdate.replace("\"", "");
            if(updated.contains("empty"))
            {
                tobeUpdated="";
            }
            else
            {
                tobeUpdated = updated;
            }
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            String input = "";

            while ((line = br.readLine()) != null)
                input += line + System.lineSeparator();  // OLD NAD BAD input += line + "\n";    
            
            /**
             * The issue is that on Unix systems, the line separator is \n while on Windows systems, it's \r\n.
             * In Java versions older then Java 7, you would have to use System.getProperty("line.separator") instead.
             */
                
            input = input.replace(toUpdate, tobeUpdated);
            if(outputFile!=null)
            {
                file = new File(outputFile);
            }
            FileOutputStream os = new FileOutputStream(file);
            os.write(input.getBytes());

            br.close();
            os.close();
            
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        //return true;
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
     public static int printMatchingStringsInFile(String cadena, String filename, String outputFilename, String excludeString)
     {
         try
         {
            int countMatches = 0;
            List<String> lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
            int i = 0; 
            for (String line: lines)
             {
                 i++;
                 if(line.toLowerCase().contains(cadena.toLowerCase()))
                 {
                     if(excludeString!=null)
                     {                        
                        if(!line.toLowerCase().contains(excludeString))
                        {
                            String printLine = "Result (With exclusion) printMatchingStringsInFile: String found at line: " + i + "/" + lines.size();
                            System.out.println(printLine);  
                            String outputLine = line + " @L"+i+"/"+lines.size();
                            writeToFile(outputFilename,outputLine); 
                            countMatches++;
                        }
                        else
                        {
                            System.out.println("Found error, excluded line.");  
                        }
                     }
                     else
                     {
                        String printLine = "Result printMatchingStringsInFile: String found at line: " + i + "/" + lines.size();
                        System.out.println(printLine);
                        String outputLine = line + " @L"+i+"/"+lines.size();
                        writeToFile(outputFilename,outputLine);  
                        countMatches++;
                     }
                 }
             }
            return countMatches;
         }
         catch(Exception e)
         {
            System.out.println("Exception msg: " + e.getMessage());
            System.out.println("Exception cause: " + e.getCause());
            return -1;
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
        System.out.println("*     Commands: ");
        System.out.println("* ");
        System.out.println("* [  ] -help (after param error or empty param)");
        System.out.println("* [ 1] -file c:\\file -contains error");
        System.out.println("* [ 2] -file c:\\file -countLines error");
        System.out.println("* [ 3] -file c:\\file -contains 'error' atLine 7");
        System.out.println("* [ 4] -file c:\\file -contains 'error' atLast 3");
        System.out.println("* [ 5] -file C:\\file -contains 'error' >> outputFileErrorLines "); //outputErrorLines
        System.out.println("* [ 6] -file C:\\file -contains 'error' >> outputFileErrorLines except 'string' "); //outputErrorLines
        System.out.println("* [ 7] -file D:\\proof.html -replace 'DemoSplashScreen_11_2_0(\"GMC Inspire Demo sample\"),' with emptyString");        
        System.out.println("* [ 8] -file D:\\proof.html -replace 'DemoSplashScreen_11_2_0(\"GMC Inspire Demo sample\"),' with emptyString >> D:\\output.html");
        System.out.println("* [ 9] -file D:\\proof.html -removeWatermark");
        System.out.println("* [10] -file D:\\proof.html -removeWatermarkCreateNewFile");
        System.out.println("* ");
        System.out.println("* [  ] Explained:");
        System.out.println("* [ 1] Returns true/false if a string matches in a file");
        System.out.println("* [ 2] Returns number of lines matching the specified string");
        System.out.println("* [ 3] Returns true/false if a string matches in a line");
        System.out.println("* [ 4] Same as 3 but the line is specified downToUp");
        System.out.println("* [ 5] Returns the number of lines and print these lines in a file               -> -1 is exception, 0 is not found, >0 is number of lines");
        System.out.println("* [ 6] Returns the number of lines and print these lines in a file except string -> -1 is exception, 0 is not found, >0 is number of lines");
        System.out.println("* [ 7] Explicit:Removes the demoSplashScreen function for Designer WATERMARK in the specified file");
        System.out.println("* [ 8] Explicit:Removes the demoSplashScreen function for Designer WATERMARK in the output file");
        System.out.println("* [ 9] Implicit:Removes the demoSplashScreen function for Designer WATERMARK in the specified file");
        System.out.println("* [10] Implicit:Removes the demoSplashScreen function for Designer WATERMARK in the output file");
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
