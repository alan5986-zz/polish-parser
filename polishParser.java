import java.util.Scanner;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Stack;


class polishParser{
  public static void main(String args[]){

    //initialise variables
    ArrayList<String> formula = new ArrayList<>(); // = s.nextLine().split("\\s+");
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      formula.add(s.next()); 
      
    }
    Stack<String> stack = new Stack<>();
    
    int result = 0;
    
    

   

    try {

      //check first element is acceptable operator
      if(!formula.get(0).contains("+") && !formula.get(0).contains("-") && !formula.get(0).contains("x") ){
        throw new InputMismatchException();
      }

      //read array backwards. if element is not an operator, assume operand and add to stack otherwise carry out operation
      for(int i = formula.size() -1; i >= 0; i--){
        if(formula.get(i).equals("+") || formula.get(i).equals("-") || formula.get(i).equals("x")  ){
          switch(formula.get(i)){
            case "+":
              result = Math.addExact(Integer.parseInt((String)stack.pop()), Integer.parseInt((String)stack.pop()));
              stack.push(Integer.toString(result));
              break;
            case "-":
              result = Math.subtractExact(Integer.parseInt((String)stack.pop()), Integer.parseInt((String)stack.pop()));
              stack.push(Integer.toString(result));
              break;
            case "x":
              result = Math.multiplyExact(Integer.parseInt((String)stack.pop()), Integer.parseInt((String)stack.pop()));
              stack.push(Integer.toString(result));
              break;
          }
        }
        else
        {
          stack.push(formula.get(i));
        }
      }
      System.out.println(result);
    }
    catch (EmptyStackException | InputMismatchException e){
      System.out.println("Error: Use +, -, or x in Normal Polish Notation e.g + 5 6. \n " + e);
    }
    catch (ArithmeticException e){
      System.out.println("Error: The result may have been too large or small. \n " + e);
    }
    finally {
      if(s != null){
        s.close();
      }
    }
  }
}
