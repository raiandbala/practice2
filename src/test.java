class new1{
	int a;
	int b;
	public void add(int i, int j) {
		a =i;
		b=j;
		int c = a+b;
		System.out.println("addition is :"+c);
	}	
}
public class test{
	public static void main(String[]args) {
		new1 obj = new new1();
		obj.add(4, 7);
		System.out.println("shubham");
	}
}