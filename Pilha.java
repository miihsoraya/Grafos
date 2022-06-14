import java.util.*;

public class Pilha {
    static final int MAX = 10;
    int top;
    int a[] = new int[MAX]; // Define tamanho máximo da pilha   
 
    // Construtor
    Pilha() {
       top = -1;
    }
 
    // Métodos da pilha
    boolean isEmpty() {
      return (top < 0);
    }
    boolean push(int x) {
       if (top >= (MAX-1)) {
          System.out.println("Estouro de Pilha!");
          return false;
       }
       else {
          a[++top] = x;
          return true;
       }
    }
    int pop() {
       if (top < 0) {
          System.out.println("Pilha Vazia!");
          return 0;
       }
       else {
          int x = a[top--];
          return x;
       }
    }
    int peek() {
       if (top < 0) {
          System.out.println("Pilha Vazia!");
          return 0;
       }
       else {
          return a[top];
       }
    }

    public void prt(){
      System.out.print("\n"); 
      for(int b : a) System.out.print(b + " ");
    }

    public boolean contains(int chave) {

      for(int b : a){
         if(b == chave)
            return true;
      }
      return false;
    }
 }