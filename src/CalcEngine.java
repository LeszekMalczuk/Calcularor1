import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class CalcEngine
{

	String total = "";
	String operator;
	Double piValue;
	Double root;
	double displayValue;
	double operand1;
	int op1, op2, size;
	Double value2 =0.0;
	Double value1=0.0;
	static boolean done = false;
	/**
	 * A linear collection that supports element insertion and removal at both ends. 
	 * The name deque is short for "double ended queue" and is usually pronounced "deck". 
	 * Most Deque implementations place no fixed limits on the number of elements they may contain, 
	 * but this interface supports capacity-restricted deques as well as those with no fixed size limit. 
	 */
	Deque<Character> stack = new ArrayDeque<Character>();
	ArrayList<Double> numbers = new ArrayList<Double>();

	/**
	 * Create a CalcEngine instance. Initialise its state so that it is ready 
	 * for use.
	 */
	public CalcEngine()
	{
		operator =" ";
		displayValue=0;
		operand1 = 0;
		piValue =  Math.PI;
		root =  Math.sqrt(operand1);
	}

	/**
	 * the value should by displayed in the screan of calc.
	 * @return
	 */
	public String getDisplayValue()
	{
		return(total);
	}

	/**
	 * A number button was pressed. Do whatever you have to do to handle it.
	 * The number value of the button is given as a parameter.
	 */
	public void numberPressed(int number)
	{
		//displayValue = displayValue *10 + number;
		total+= number;
		piValue =  Math.PI;
		root =  Math.sqrt(number);
	}

	/**
	 * The 'plus' button was pressed. 
	 */
	public void plus()
	{
		operand1 = displayValue;
		displayValue = 0;
		operator = "+";
		total += " + ";
	}

	/**
	 * The 'minus' button was pressed.
	 */
	public void minus()
	{
		operand1 = displayValue;
		displayValue = 0;
		operator = "-"; 
		total += " - ";
	}
	/**
	 * The 'multiply' button was pressed.
	 */
	public void multiply()
	{
		operand1 = displayValue;
		displayValue = 0;
		operator = "*"; 
		total += " * ";
	}
	/**
	 * The 'divide' button was pressed.
	 */
	public void divide()
	{
		operand1 = displayValue;
		displayValue = 0;
		operator = "/"; 
		total += " / ";

	}
	/**
	 * The 'left bracket' button was pressed.
	 */
	public void parenthesisL()
	{
		operand1 = displayValue;
		displayValue = operand1;
		operator = "("; 
		total += " ( ";
	}
	/**
	 * The 'right bracket' button was pressed.
	 */
	public void parenthesisR()
	{
		operand1 = displayValue;	
		operator = ")"; 
		total += " ) ";
	}
	/**
	 * The 'power to' button was pressed.
	 */
	public void power()
	{
		operand1 = displayValue;
		operator = "^"; 
		total += " ^ ";
	}
	/**
	 * The 'decimal point' button was pressed.
	 */
	public void decimalPoint() {

		operand1 = displayValue;
		displayValue = operand1 / 10; 
		operator = "."; 
		total += ".";

	}
	/**
	 * The 'negative for numbers' button was pressed.
	 */
	public void minusButton() {

		operand1 = displayValue;
		displayValue = operand1 * (- 1); 
		operator = "-"; 
		total += " - " ;
	}
	/**
	 * The 'PI number' button was pressed.
	 */
	public void Pi() {

		operand1 = displayValue;
		displayValue = operand1 / 10; 
		operator = "P"; 
		total += piValue;
	}
	/**
	 * The 'root' button was pressed.
	 */
	public void sqrt() {

		operand1 = displayValue;
		operator = "S"; 
		total += " = " + root;
	}

	/**
	 * The 'C' (clear) button was pressed.
	 */
	public void clear() {
		displayValue = 0;
		operand1 = 0;
		total = " ";
	}



	/**
	 * The '=' button was pressed.
	 */
	public void equals() 
	{
		String postfix = convertToPostfix(total);


		System.out.println(convertToPostfix(total));
		System.out.println(total);
		System.out.println(equals(stack));
		System.out.println(isNumber(postfix));
		System.out.println(stack);

		//
		StringTokenizer st = new StringTokenizer(total);

		while (st.hasMoreTokens()) {
			String c = st.nextToken();

			if (isNumber(c)) {
				numbers.add(Double.parseDouble(c));
			} else {
				stack.addFirst(c.charAt(0));
			}

		}

		System.out.println(stack + "Stack");


		while (stack.size() != 0) {

			switch (stack.getFirst()) {
			case '*':
				System.out.println(numbers.size());
				size = numbers.size();
				value1 = numbers.get(size - 1);
				value2 = numbers.get(size - 2);

				numbers.set(size - 2, value1 * value2);
				stack.pop();
				numbers.remove(size - 1);
				continue;

			case '/':
				size = numbers.size();
				value1 = numbers.get(size - 1);
				value2 = numbers.get(size - 2);

				numbers.set(size - 2, value2 / value1);
				stack.pop();
				numbers.remove(size - 1);
				continue;

			case '+':
				size = numbers.size();
				value1 = numbers.get(size - 1);
				value2 = numbers.get(size - 2);
				numbers.set(size - 2, value1 + value2);
				stack.pop();
				numbers.remove(size - 1);
				continue;

			case '-':
				size = numbers.size();
				value1 = numbers.get(size - 1);
				value2 = numbers.get(size - 2);
				numbers.set(size - 2, value2 - value1);
				stack.pop();
				numbers.remove(size - 1);
				continue;

			case '^':

				size = numbers.size();
				value1 = numbers.get(size - 1);
				value2 = numbers.get(size - 2);
				numbers.set(size - 2,  (double)  Math.pow(value2, value1));
				stack.pop();
				numbers.remove(size - 1);
				continue;	
			}

		}
		total += " = " + numbers.get(size-2);

	}

	//				class PostFixConverter { 
	//				boolean isOperand(char ch1) {


	public  String convertToPostfix(String infix)
	{

		// Temporary string to hold the number
		StringBuffer postfix = new StringBuffer(infix.length());
		Stack<String> stack = new Stack<String>();
		StringTokenizer st = new StringTokenizer(infix);

		while (st.hasMoreTokens()) {
			String c = st.nextToken();

			if (!isOperator(c))
			{
				postfix.append(c);
			}

			else
			{
				if (c == ")")   
				{

					while (!stack.isEmpty() && stack.peek() != "(")
					{
						postfix.append(stack.pop());
					}
					if (!stack.isEmpty())
					{
						stack.pop();
					}
				}

				else
				{
					if (!stack.isEmpty() && (getPrecedence(c) - getPrecedence(stack.peek()) >= 0))
					{
						stack.push(c);
					}
					else
					{
						while (!stack.isEmpty() && (getPrecedence(c) - getPrecedence(stack.peek()) < 0))
						{
							String pop = stack.pop();
							if (c != "(")	
									{
								postfix.append(pop);
									} else {
										c = pop;
									}
						}
						stack.push(c);
					}

				}
			}
		}
		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}
		return postfix.toString();
	}


	public boolean isOperator(String c) {

		if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("^") || c.equals("(") || c.equals(")"))
		{
			return true;
		}
		else
			return false;

	}

	public int getPrecedence(String ch) {
		switch (ch) {
		//		case  "+":
		//		case "-":
		//			return 1;
		//
		//		case "*":
		//		case "/":
		//			return 2;
		//
		//		case "^":
		//			return 3;
		//			
		//		case "(":
		//			return 4;
		//			
		//		case ")":
		//			return 5;
		//		}
		//		return -1;


		/// example for RPN					
		///  (  ( 4 + 5 )  * 6 ^ 2 - 3 )  * 9   ||| before convert
		/// 45+62^*3-9*))((						||| after convert

		case  "+":
		case "-":
			return    2;   //3

		case "*":
		case "/":
			return     3;   //4

		case "^":
			return    4 ;   //2

		case "(":
		case ")":
			return 1;



		}
		return -1;
	}



	@SuppressWarnings("unused")
	private int getPrecedence(Character peek) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isNumber(String total) {
		// TODO Auto-generated method stub
		try {
			@SuppressWarnings("unused")
			double y = Double.parseDouble(total);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	/**
	 * Return the title of this calculation engine.
	 */
	public String getTitle()
	{
		return("My Calculator");
	}


	/**
	 * Return the author of this engine. This string is displayed as it is,
	 * so it should say something like "Written by H. Simpson".
	 */
	public String getAuthor()
	{
		return("Leszek");
	}

	/**
	 * Return the version number of this engine. This string is displayed as 
	 * it is, so it should say something like "Version 1.1".
	 */
	public String getVersion()
	{
		return("Ver. 1.1");
	}
}
