class new1{
	int a;
	int b;
	public void add(int i, int j) {
		a =i;
		b=j;
		int c = a+b;
		System.out.println("addition is :"+c);
	}
	public void multiply(int i, int j) {
		a =i;
		b=j;
		int c = a*b;
		System.out.println("multiplication is :"+c);
	}
}
public class test{
	public static void main(String[]args) {
		new1 obj = new new1();
		obj.add(4, 7);
//<<<<<<< HEAD
		obj.multiply(3, 2);
//=======
////<<<<<<< HEAD
		for(int i=1; i<=3; i++) {
			System.out.println('*');
		}
//=======
		System.out.println("shubham");
		System.out.println("welcome");
		System.out.println("visit again");
		System.out.println("Jay Shree Ram");
		System.out.println("Jay Shree Ram");
		System.out.println("shubham");
		System.out.println("shubham");
	}
}