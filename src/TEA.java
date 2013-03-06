
public class TEA {
	private static int[] key = new int[4];

	public static void main (String[] args) {
		key[0] = 0xA56BABCD;
		key[1] = 0x00000000;
		key[2] = 0xFFFFFFFF;
		key[3] = 0xABCDEF01;
		int temp[] = {0x01234567,0x89ABCDEF};
		int temp2[] = {0,0};
		System.out.println(temp[0] + " " + temp[1]);
		temp2 = encipher(temp);
		temp = temp2;
		System.out.println(temp[0] + " " + temp[1]);
		int con; String concat;
		concat = Integer.toHexString(temp[0]) + Integer.toHexString(temp[1]);
		System.out.println(concat);
		temp2 = decipher(temp);
		System.out.println(temp2[0] + " " + temp2[1]);
	}
	
	private static int[] encipher(final int v[]) {
		int L = v[0];
		int R = v[1];
		int delta = 0x9e3779B9;
		int sum = 0;
		for (int i = 1; i <= 32; i++) {
			sum += delta;
			L += ((R<<4)+key[0]) ^ (R + sum) ^ ((R>>5)+key[1]);
			R += ((L<<4)+key[2]) ^ (L + sum) ^ ((L>>5)+key[3]);
		}
		v[0] = L;
		v[1] = R;
		return v;
		
	}
	
	private static int[] decipher(final int v[]) {
		int L = v[0];
		int R = v[1];
		int delta = 0x9e3779b9;
		int sum = delta << 5;
		for (int i = 1; i <= 32; i++) {
			R -= ((L<<4)+key[2]) ^ (L + sum) ^ ((L>>5) + key[3]);
			L -= ((R<<4)+key[0]) ^ (R + sum) ^ ((R>>5) + key[1]);
			sum -= delta;
		}
		v[0] = L;
		v[1] = R;
		return v;
	}

}
