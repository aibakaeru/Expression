package hw2;


import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main<E> extends LinkedBinaryTree<E> {
	protected StringTokenizer tokens;
	protected static LinkedBinaryTree<String> Maintree;
	static Scanner scanner = new Scanner(System.in);
 public static void main (String[] arg) throws EmptyTreeException, InvalidPositionException, BoundaryViolationException,
                    EmptyStackException, FullStackException, NonEmptyTreeException 
 {
	 
		 
	 int begin=0;
	 String enterValue = null;

	 
	 begin = menu();
		while(begin != 3){
			switch(begin){
			
			case 1:
				System.out.print("enter a expression:");
				enterValue = scanner.next();
			    Main<String> et = new Main<String>();
	    	    System.out.println("Building expression tree of: " + enterValue);
	    	    et.buildExpression(enterValue);
	    	    System.out.println("=================================");
	    	    System.out.println("The value of " + enterValue + "=" + et.evaluate());
			    System.out.println("=================================");
				break;
			case 2:
				inOrder(Maintree.root(),0);
				
				break;
			}
			
			System.out.println("");
			begin = menu();
			}
	 
 }
private static int menu() {
		System.out.println("=======================");
		System.out.println("1.enterExpression");
		System.out.println("2.printTree");
		System.out.println("=======================");
		
		System.out.println("select==>");
		Scanner  scanner = new Scanner(System.in);
		
		return scanner.nextInt();
	}

/**
 * buildExpresion() takes a fully-parenthesized arithmetic expression and
 *  builds a binary expression tree.
 * @param s is a fully parenthesized arithmetic expression of the form
 *  E = e_0, e_1, ..., e_n-1 where each e_i is either a variable, value,
 *  operator, or parenthetic symbol.
 * @return a binary tree storing the expression 
 * @throws EmptyStackException 
 * @throws EmptyTreeException 
 * @throws InvalidPositionException 
 * @throws FullStackException 
 * @throws NonEmptyTreeException 
 */
public BinaryTree<String> buildExpression(String s) throws EmptyStackException, InvalidPositionException, EmptyTreeException, FullStackException, NonEmptyTreeException {
	
	// Use a stack to store partial trees
	ArrayStack<LinkedBinaryTree<String>> stack = new ArrayStack<LinkedBinaryTree<String>>(1000); 
	
	 // Use this statement to tokenize expression
	tokens = new StringTokenizer(s,")(+-*/",true); 
	
	String str;
	
	while(tokens.hasMoreTokens())
	{
		str = tokens.nextToken();
		
		char operator = str.charAt(0);
		
		switch(operator){
		
		case ')':
			LinkedBinaryTree<String> t2 = stack.pop();
			Maintree = stack.pop();
			LinkedBinaryTree<String> t1 = stack.pop();
			Maintree.attach(Maintree.root(), t1, t2);
			stack.push(Maintree);
			break;
		
		case '(':
			break;
		
		case ' ':
			break;
			
		default:
			LinkedBinaryTree<String> newnode = new LinkedBinaryTree<String>();
			newnode.addRoot(str);
			stack.push(newnode);
			break;
		}
	}
	System.out.println("Done with building Expression Tree.");
	return Maintree;//and return it
}

/**
 * evaluate() performs an evaluation of the (non-empty) expression tree
 *  by passing the root of the tree to the recursive version.
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException 
 */
public Double evaluate() throws EmptyTreeException, InvalidPositionException, BoundaryViolationException {
if (Maintree == null)
    throw new EmptyTreeException("Cannot evaluate empty tree.");
return evaluate(Maintree.root());
}

/**
 * evaluate() performs a postOrder traversal of the expression tree
 *  recursively evaluating the operators stored at each internal node.
 *  @param position represents a Position (i.e., Node) of the tree.
 * @throws InvalidPositionException 
 * @throws BoundaryViolationException 
 */
// calculates the value of the expression
private Double evaluate(Position<String> position) throws InvalidPositionException, BoundaryViolationException {
	
	Double x,y;
	
	if(Maintree.isInternal(position))
	{
		x = evaluate(Maintree.left(position));
		y = evaluate(Maintree.right(position));
	    
		switch(position.element().toString()){
		case "+":
			return x+y;
		case "-":
			return x-y;
		case "*":
			return x*y;
		case "/":
			return x/y;
		}
	}
	else
		position.element();
return Double.parseDouble(position.element());
};

/** display tree content 
 * @throws BoundaryViolationException 
 * @throws InvalidPositionException **/
private static void inOrder(Position<String> position,int indent) throws InvalidPositionException, BoundaryViolationException {
  
	if (Maintree == null) return;
  
	if(Maintree.hasLeft(position))
		try {
			inOrder(Maintree.left(position),indent+5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
  
	for (int i=1;i<=indent;i++) System.out.print(' ');      
  
	System.out.println(position.element().toString());//output the element     
  
	if(Maintree.hasRight(position))inOrder(Maintree.right(position),indent+5);
}

//print preorder expression
private static void Preorder(Position<String> position){
	
}

//print postorder expression
private static void Postorder(Position<String> position){
	
}
}