import java.io.*;

class Parser{
    static int lookahead;
    static int contador;
    
    public Parser() throws IOException{
        lookahead = System.in.read();
        contador = 0;
    }
    
    void expr() throws IOException {
        if(lookahead == '('){
            match('(');expr(); match(')'); expr(); expr();
        }else return;
    
    }
    
    void match(int t) throws IOException{
        if(lookahead == t){
            if(lookahead == '('){
                contador++;
            }else if(lookahead == ')'){
                contador--;
            }
            lookahead = System.in.read();
        }
        else throw new Error("Syntax error");
    }
    
}

public class Main{
    public static void main(String[] args) throws IOException{
        Parser parser = new Parser();
        parser.expr();
        if(parser.contador != 0){
            throw new Error("Syntax error");
        }
        if(parser.lookahead != 10 && parser.lookahead != 13 && parser.lookahead != -1){
            throw new Error("Syntax error");
        }
        System.out.println("True");
    }
}