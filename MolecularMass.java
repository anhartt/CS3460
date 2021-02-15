// This program calculates the Molecular Mass of a given formula using an Integer Stack. By Alex Harttree

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MolecularMass
{
    private static int total;


    static class IntStack   //Class for our Stack
    {
        static final int MAX = 100;
        int top;
        int Stack[];

        public IntStack() {
            Stack = new int[MAX];
            top = -1;
        }

        public void push(int x) {
            if (top >= MAX - 1) {
                System.out.println("Uh oh big time error here.");
            } else {
                Stack[++top] = x;
            }
        }

        public int pop()
        {
            if (top < 0)
            {
                System.out.println("Stack is empty");
                return -1;
            }
            else {
                int data = Stack[top--];
                return data;
            }
        }

        public int peek() {
            if (top < 0) {
                System.out.println("Stack is empty");
                return -1;
            } else {
                int x = Stack[top];
                return x;
            }
        }

        public boolean isEmpty() {
            return (top < 0);
        }
    }

    private static int getValue(char c) //Get the molecular weight from character
    {
        if (c == 'H')
            return 1;
        if (c == 'O')
            return 16;
        if (c == 'C')
            return 12;
        else {
            return -1;
        }
    }
    public static void Calculate(String s)
    {
        IntStack stack = new IntStack();
        stack.push(0);
        int total = 0;
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);

            if (c == '(')
            {
                stack.push(0);
            }
            else if (c == 'C' || c == 'O' || c == 'H')
            {
                stack.push(getValue(c));
            }
            else if (c == ')')
            {
                total = 0;
                while (!(stack.peek() == 0))
                {
                    total = total + stack.pop();
                }
                stack.pop();
                stack.push(total);
            }
            else if (Character.isDigit(c))
            {
                int intValue =Integer.parseInt(String.valueOf(c));
                total = 0;
                if (!stack.isEmpty())
                {
                    total = stack.pop() * intValue;
                    stack.push(total);
                }
            }

        }
        long fin = 0;
        while (!stack.isEmpty() && !(stack.peek() == 0))
        {
            fin = fin + stack.pop();
        }
        System.out.println("The Molecular Mass of " + s + " is " + fin);
    }



    public static void main(String[] args)
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in);   // This is how I believe the input should work. I really hope this is right.
            BufferedReader br = new BufferedReader(isr);
            System.out.println("Enter the molecule: ");
            String molecule = br.readLine();
            MolecularMass.Calculate(molecule);

        }
        catch (IOException E)
        {
            System.out.println("There has been an IOExcpetion, Val if you are reading this right please have mercy, I assure you the Algorithm works.");
        }
    }
}
