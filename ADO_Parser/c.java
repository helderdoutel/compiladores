import java.io.*;

class Parser {

    static int lookahead;
    static boolean response = true;

    public Parser() throws IOException{
        lookahead = System.in.read();
    }

    void S() throws IOException{
        if(lookahead == '0'){
            match('0');
            A();
            matchLast('1');
        }
        else {
            response = false;
        }
        System.out.println(response);
    }
  
    void A() throws IOException{
        if(lookahead == '0'){
            match('0');
            A();
            match('1');
        }
    } 
   
    void match(int t) throws IOException{
        if(lookahead ==t) {
            lookahead = System.in.read();
        }
        else response = false;
    }
    
    void matchLast(int t) throws IOException{
        if(lookahead ==t) {
            lookahead = System.in.read();
            if(!(lookahead==10)){
                response = false;
            }
        }
        else response = false;
    }
}

public class Main{
    public static void main(String[] args)throws IOException{
        Parser parse = new Parser();
        parse.S();
    }
}