package jdbc.swing.frame;
/*
public class Vishal {

	 public static void main(String[] args) {
	 	 final int i;
		 i = 20;
		 int j = i+20;
		int k=i+j;
	     System.out.println(i + "<--I " + k+ "<--K "+ j+"<---j");
	 }
 }*/

 

/* class Vishal {
    public static void main(String args[]) {
        System.out.println("1 Main Method");
    }
    public static void main(int[] args){
        System.out.println("Overloaded Integer array Main Method");
    }
    public static void main(char[] args){
        System.out.println("Overloaded Character array Main Method");
    }
    public static void main(double[] args){
        System.out.println("Overloaded Double array Main Method");
    }			
    public static void main(float args){
        System.out.println("Overloaded float Main Method");
    }
}

*/
public class Vishal {
public static void main(String args[]) {
 try {
  int n = 100, x = 0;
  //int arr[] = new int[n];
  for (int i = 1; i <= n; i++) {
  // arr[i] = i / x;
	  System.out.print(i+", ");
  }
 }
 catch (ArrayIndexOutOfBoundsException exception) {
  System.out.println("1st block = ArrayIndexOutOfBoundsException");
 }
 catch (ArithmeticException exception) {
  System.out.println("2nd block = ArithmeticException");
 }
 catch (Exception exception) {
  System.out.println("3rd block = Exception");
 }
}
}


