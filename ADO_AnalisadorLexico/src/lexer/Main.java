package lexer;

public class Main {
	
	public static void main(String[] args) {
		try {
			Lexer lexer = new Lexer();
			Token t ;
			while(true) {
				t = lexer.scan();				
				if(t instanceof Word) {
					if(!((Word) t).lexeme.equals("")) {
						System.out.println("Token "+((Word) t).lexeme);
					}
				}else if(t instanceof Num) {
					System.out.println("Token " + ((Num)t).value);
				}else if(t instanceof FloatNum) {
					System.out.println("Token " + ((FloatNum)t).value);
				}

			}


		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		
		
//		System.out.println(Double.valueOf("12."));

	}
}
