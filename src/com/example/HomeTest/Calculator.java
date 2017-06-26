package com.example.HomeTest;

/**
 * Created by maayan.s on 1/9/17.
 */
public class Calculator {

    public static final String UNARY_DECREMENT = "--";
    public static final String UNARY_INCREMENT = "++";

    public static int run(String num1, String num2, String operation) throws WrongCalculatorInputStreamException {

        int num1_ = Integer.valueOf(num1);
        int num2_ = Integer.valueOf(num2);
        switch (operation) {
            case "+":
                return num1_ + num2_;

            case "-":
                return num1_ - num2_;

            case "*":
                return num1_ * num2_;

            case "/":
                return num1_ / num2_;

            case "%":
                return num1_ % num2_;

            case "^":
                return (int)Math.pow(num1_, num2_);

            default: //exception expected
                throw new WrongCalculatorInputStreamException(String.format("Calculator.run: %s is not a supported operation", operation));
        }
    }

    public static int unary(String unary, boolean pre, int value) throws WrongCalculatorInputStreamException {
        switch(unary) {
            case UNARY_DECREMENT:
            {
                if (pre) {
                    return value - 1;
                }
                else {
                    return value;
                }
            }
            case UNARY_INCREMENT: {
                if(pre) {
                    return value + 1;
                }
                else {
                    return value;
                }
            }
            default: //exception expected
                throw new WrongCalculatorInputStreamException(String.format("Calculator.unary: %s is not a supported operation", unary));
        }
    }

}
