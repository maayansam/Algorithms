package com.example.BST;


public class execute {

    public static void main (String[] args){

        Throwable a = new Throwable();
        StackTraceElement[] elems = a.getStackTrace();
        StringBuffer e = new StringBuffer();
        for(StackTraceElement elem: elems){
            e.append(String.format("%d declaringClass: %s methodName: %s, fileName: %s", elem.getLineNumber(), elem.getClassName(), elem.getMethodName(), elem.getFileName()));
        }


        System.exit(0);
    }
}
