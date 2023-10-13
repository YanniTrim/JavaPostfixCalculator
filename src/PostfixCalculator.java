public class PostfixCalculator {


    public static double evaluate(String postfix) {
        ArrayStack<Object> numberStack = new ArrayStack<>(); // stack numbers until one left
        String[] postfixArray = postfix.split(" ");
        for (int i = 0; i < postfixArray.length; i++) { //iterate through
            switch (postfixArray[i]) {
                case "+":
                    numberStack.push((double) numberStack.pop() + (double) numberStack.pop()); // add then push
                    break;
                case "-":
                    numberStack.push((-1*(double) numberStack.pop()) + (double) numberStack.pop()); // subtract then push (use-1 to keep order)
                    break;
                case "*":
                    numberStack.push((double) numberStack.pop() * (double) numberStack.pop()); // multiply then push
                    break;
                case "/":
                    numberStack.push((1/((double) numberStack.pop())) * (double) numberStack.pop()); // divide then push (actually multiplying the reciprocal to keep order right)
                    break;
                case "^":
                    double num1 = (double) numberStack.pop();
                    double num2 = (double) numberStack.pop();
                    numberStack.push(Math.pow(num2, num1));
                    break;
                default:
                    numberStack.push(Double.parseDouble(postfixArray[i])); // push number to stack for future use
            }
        }
        return (double)numberStack.pop(); // pop result
    }
}
