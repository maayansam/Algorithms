package com.example.HomeTest;

import java.io.Serializable;

public class Operator implements Serializable {


    private String token;
    private int precedence;
    private int associativity;

    public Operator (String tk, int pre, int assoc) {

        token = tk;
        precedence = pre;
        associativity = assoc;
    }

    public String getToken() {
        return token;
    }

    public int getPrecedence() {
        return precedence;
    }

    public int getAssociativity() {
        return associativity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator)) return false;

        Operator operator = (Operator) o;

        if (precedence != operator.precedence) return false;
        if (associativity != operator.associativity) return false;
        return token != null ? token.equals(operator.token) : operator.token == null;
    }

    @Override
    public int hashCode() {

        return token.hashCode() * 17;
    }

}
