package xiongxoy.util;

public class DBG {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static void println(String class_name, String info) { 
		println(class_name, info, 0);
	}
	public static void println(String class_name, String info, int level) {
		System.out.println(class_name+": "+info);
	}

}
