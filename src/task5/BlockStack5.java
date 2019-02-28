package task5;

import common.StackEmptyException;
import common.StackFullException;
import common.StackOutOfBoundsAccessException;

/**
 * Class original.BlockStack5
 * Implements character block stack and operations upon it.
 * <p>
 * $Revision: 1.4 $
 * $Last Revision Date: 2019/02/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst
 */
class BlockStack5 {
    /**
     * # of letters in the English alphabet + 2
     */
    public static final int MAX_SIZE = 28;

    /**
     * Default stack size
     */
    public static final int DEFAULT_SIZE = 6;

    /**
     * Current size of the stack
     */
    private int iSize = DEFAULT_SIZE;

    /**
     * Current top of the stack
     */
    private int iTop = 3;

    /**
     * stack[0:5] with four defined values
     */
    private char acStack[] = new char[]{'a', 'b', 'c', 'd', '*', '*'};

    private static int stackAccesCounter = 0;

    /**
     * Default constructor
     */
    public BlockStack5() {
    }

    /**
     * Supplied size
     */
    public BlockStack5(final int piSize) {
        if (piSize != DEFAULT_SIZE) {
            this.acStack = new char[piSize];

            // Fill in with letters of the alphabet and keep
            // 2 free blocks
            for (int i = 0; i < piSize - 2; i++)
                this.acStack[i] = (char) ('a' + i);

            this.acStack[piSize - 2] = this.acStack[piSize - 1] = '*';

            this.iTop = piSize - 3;
            this.iSize = piSize;
        }
    }

    /**
     * Picks a value from the top without modifying the stack
     *
     * @return top element of the stack, char
     */
    public char pick() {
        try {
            if (isEmpty()) {
                throw new StackEmptyException();
            }
            stackAccesCounter++;
            return this.acStack[this.iTop];
        }
        catch(StackEmptyException e){
            System.out.println(e.getMessage());
            return '*';
        }
    }

    /**
     * Returns arbitrary value from the stack array
     *
     * @return the element, char
     */
    public char getAt(final int piPosition) {
        try {
            if (piPosition < 0 || piPosition >= acStack.length) {
                throw new StackOutOfBoundsAccessException();
            }
            stackAccesCounter++;
            return this.acStack[piPosition];
        }
        catch(StackOutOfBoundsAccessException e){
            System.out.println(e.getMessage());
            return '*';
        }
    }

    /**
     * Standard push operation
     */
    public void push(final char pcBlock) {
        try {
            if (acStack[acStack.length-1] != '*') {
                throw new StackFullException();
            }
            stackAccesCounter++;
            if (iSize == 0) {
                this.acStack[++this.iTop] = 'a';
            } else {
                this.acStack[++this.iTop] = pcBlock;
            }
        }
        catch(StackFullException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Standard pop operation
     *
     * @return ex-top element of the stack, char
     */
    public char pop() {
        try {
            if (isEmpty()) {
                throw new StackEmptyException();
            }
            stackAccesCounter++;
            char cBlock = this.acStack[this.iTop];
            this.acStack[this.iTop--] = '*'; // Leave prev. value undefined
            return cBlock;
        }
        catch(StackEmptyException e){
            System.out.println(e.getMessage());
            return '*';
        }
    }

    public int getiTop() {
        return iTop;
    }

    public static int getStackAccesCounter() {
        return stackAccesCounter;
    }

    public int getiSize() {
        return iSize;
    }

    public boolean isEmpty() {
        return (this.iTop == -1);
    }
}

// EOF
