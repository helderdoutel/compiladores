import java.io.*;

class Parser{
    static int lookahead;
    
    public Parser() throws IOException{
        lookahead = System.in.read();
    }
    
    void expr() throws IOException {
        if(lookahead == '('){
            match('(');expr(); match(')'); expr(); expr();
        }else return;
    
    }
    
    void match(int t) throws IOException{
        if(lookahead == t) lookahead = System.in.read();
        else throw new Error("Syntax error");
    }
    
}

public class Main{
    public static void main(String[] args) throws IOException{
        Parser parser = new Parser();
        parser.expr(); System.out.println("True");
    }
}