package lexer;

import java.io.*;
import java.util.*;


public class Lexer {
	public int line  = 1;
	private char peek = ' ';
	public Hashtable words = new Hashtable();
	
	void reserve(Word t) {
		words.put(t.lexeme,t);
	}
	
	public Lexer() {
		reserve( new Word(Tag.TRUE, "true"));
		reserve( new Word(Tag.FALSE, "false"));
	}
	
	public Token scan() throws IOException{
		
		for( ; ; peek = (char)System.in.read()) {
			if( peek == ' ' || peek == '\t')
				continue;
			else if( peek == '\n')
				line = line + 1;
			else 
				break;
		}
		
		if(peek == '/') {
			peek = (char)System.in.read();
			int linha_coment = line;
			if(peek == '/') {
				do {
					peek = (char)System.in.read();
					if( peek == '\n')line = line + 1;
				}while(linha_coment == line);
			}else if(peek == '*') {
				while(true){
					peek = (char)System.in.read();
					if( peek == '*') {
						peek = (char)System.in.read();
						if( peek == '/') {
							break;
						}
					}
				}
			}
		}
		
		if( Character.isDigit(peek) || peek == '.') {
			boolean trigger = false;//ativa ao encontrar delimitador decimal
			int v = 0;
			int f = 0;
			
			if(peek == '.') {
				trigger = true;
				peek = (char)System.in.read();
			}
			
			
			do {
				if(!trigger) {
					v = 10*v + Character.digit(peek, 10);
					peek = (char)System.in.read();
				}else {
					f = 10*f + Character.digit(peek, 10);
					peek = (char)System.in.read();
				}				
				
				if(peek == '.' && !trigger ) {
					peek = (char)System.in.read();
					trigger = true;
				}
			}while(Character.isDigit(peek));
			
			
			if( v >= 0 && f == 0) { // caso n e n.
				if(trigger)
					return new FloatNum(Double.valueOf(String.valueOf(v)+"."+ String.valueOf(f)));
				else
					return new Num(v);
			}else if( v > 0 && f > 0) { //caso n.n
				return new FloatNum(Double.valueOf(String.valueOf(v)+"."+ String.valueOf(f)));
			}else if( v == 0 && f > 0) {//caso .n e 0.n
				return new FloatNum(Double.valueOf("."+ String.valueOf(f)));
			}
			
		}
		

		StringBuffer b = new StringBuffer();
		String s;
		Word w;
		
		if( Character.isLetter(peek)) {
		
			do {
				b.append(peek);
				peek = (char)System.in.read();
			}while( Character.isLetterOrDigit(peek));
			
			s = b.toString();
			w = (Word)words.get(s);
			if( w != null) 
				return w;
			w = new Word (Tag.ID, s);
			words.put(s,w);
			return w;
		}
		
		
		if(peek == '<') {
			b.append('<');
			peek = (char)System.in.read();
			if(peek == '=') {
				b.append('=');
			}
			s = b.toString();
			w = (Word)words.get(s);
			if( w != null) 
				return w;
			w = new Word (Tag.ID, s);
			words.put(s,w);
			return w;
		}
		if(peek == '=') {
			peek = (char)System.in.read();
			if(peek == '=') {
				b.append('=');
				b.append('=');
			}
			s = b.toString();
			w = (Word)words.get(s);
			if( w != null) 
				return w;
			w = new Word (Tag.ID, s);
			words.put(s,w);
			return w;
		}
		if(peek == '>') {
			b.append('>');
			peek = (char)System.in.read();
			if(peek == '=') {
				b.append('=');
			}
			s = b.toString();
			w = (Word)words.get(s);
			if( w != null) 
				return w;
			w = new Word (Tag.ID, s);
			words.put(s,w);
			return w;
		}
		if(peek == '!') {
			peek = (char)System.in.read();
			if(peek == '=') {
				b.append('!');
				b.append('=');
				s = b.toString();
				w = (Word)words.get(s);
				if( w != null) 
					return w;
				w = new Word (Tag.ID, s);
				words.put(s,w);
				return w;
			}
		}
		
		Token t = new Token(peek);
		peek = ' ';
		return t;
	}
}
