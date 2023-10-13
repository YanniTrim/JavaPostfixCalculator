import java.util.*;

public class Converter {

    private final List<String> parsedInput;

    public Converter(String input) {
        char[] input1 = input.toCharArray();
        this.parsedInput = ParserHelper.parse(input1);
        //System.out.println(parsedInput);
    }

    public int precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            case "^":
                return 3;
            default:
                return -1;
        }
    }

    public String toPostFix() {
        ArrayStack<Object> conversionStack = new ArrayStack<>(); // stack to save operators to be added later
        String postfix = "";

        for (String item: this.parsedInput) {
            boolean numeric;
            try {                                   // check if numeric by attempting to make an integer
                Integer.parseInt(item);
                numeric = true;
            } catch (NumberFormatException e){
                numeric = false;
            }
            if (numeric) {                          // instant add to postfix if number
                postfix += item + " ";
            } else {
                if (conversionStack.isEmpty() || item.equals("(") || conversionStack.top().equals("(")) { // if empty stack or parentheses
                    conversionStack.push(item); // add to stack
                }
                else if (item.equals(")")) {       // close priority on operators
                    while (!conversionStack.top().equals("(")) {        // push rest of parentheses
                        postfix += conversionStack.pop() + " ";
                    }
                    conversionStack.pop();
                }
                else {
                    if (item.equals("^")) {             // exponents act differently as the order is right to left not left to right
                        while (precedence((String) conversionStack.top()) > precedence(item)) {
                            postfix += conversionStack.pop() + " ";
                            if (conversionStack.isEmpty()) {
                                break;
                            }
                        }
                    } else {
                        while (precedence((String) conversionStack.top()) >= precedence(item)) {        // pop operators until lower precedence is met
                            postfix += conversionStack.pop() + " ";
                            if (conversionStack.isEmpty()) {
                                break;
                            }
                        }
                    }
                    conversionStack.push(item); // push current item into stack
                }
            }
        }
        while (!conversionStack.isEmpty()) {
            postfix += conversionStack.pop() + " ";
        }
        return postfix;
    }

}
