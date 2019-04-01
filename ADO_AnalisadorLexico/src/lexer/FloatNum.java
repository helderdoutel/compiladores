package lexer;

public class FloatNum extends Token{
	public final double value;
	
	public FloatNum(double v) {
		super(Tag.FLOATNUM);
		value = v;
	}
}
