package des;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Phat
 */
public class DES {

    public static int PC1CD(int K1, int K2, int chiso1, int chiso2) {
        int pc1[] = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
        int pc1k1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            if (pc1[i] > 32) {
                vitri = pc1[i] - 32;
                bit = getBit(K2, vitri);
            } else {
                vitri = pc1[i];
                bit = getBit(K1, vitri);
            }
            int b = bit & 0x01;
            pc1k1 = (pc1k1 << 1) | b;
        }
        return pc1k1;
    }

    public static int IPM(int M1, int M2, int chiso1, int chiso2) {
        int IP[] = {58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7};
        int ipm1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            if (IP[i] > 32) {
                vitri = IP[i] - 32;
                bit = getBit(M2, vitri);
            } else {
                vitri = IP[i];
                bit = getBit(M1, vitri);
            }
            int b = bit & 0x01;
            ipm1 = (ipm1 << 1) | b;
        }
        return ipm1;
    }

    public static int hoanViIP_1(int M1, int M2, int chiso1, int chiso2) {
        int IP1[] = {40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25};
        int ipm1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            if (IP1[i] > 32) {
                vitri = IP1[i] - 32;
                bit = getBit(M2, vitri);
            } else {
                vitri = IP1[i];
                bit = getBit(M1, vitri);
            }
            int b = bit & 0x01;
            ipm1 = (ipm1 << 1) | b;
        }
        return ipm1;
    }

    public static int ER0(int R0, int chiso1, int chiso2) {
        int E[] = {32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1};
        int er1 = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            vitri = E[i];
            bit = getBit(R0, vitri);
            int b = bit & 0x01;
            er1 = (er1 << 1) | b;
        }
        return er1;
    }

    public static int KPC2(int C1, int D1, int chiso1, int chiso2) {
        int pc2[] = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
        int pc2k = 0;
        for (int i = chiso1; i < chiso2; i++) {
            int vitri;
            int bit;
            if (pc2[i] > 28) {
                vitri = pc2[i] - 28;
                bit = getBit28(D1, vitri);
            } else {
                vitri = pc2[i];
                bit = getBit28(C1, vitri);
            }
            int b = bit & 0x01;
            pc2k = (pc2k << 1) | b;
        }
        return pc2k;
    }

    //lấy bit tại vị trí thứ i của khoá
    public static int getBit(int K1, int i) {
        int b = K1 >> (32 - i);
        int bit = b & 0x01;
        return bit;
    }

    public static int getBit28(int K1, int i) {
        int b = K1 >> (28 - i);
        int bit = b & 0x01;
        return bit;
    }

    public static int shiftLeft(int C0, int s) {
        int C1;
        int sbit, bit28s;
        sbit = (C0 >> (28 - s));
        bit28s = (C0 << s) & 0xFFFFFFF;
        C1 = bit28s | sbit;
        return C1;
    }

    public static void genKey(int K1, int K2, int[] key1, int[] key2) {
        int C0, D0;
        C0 = PC1CD(K1, K2, 0, 28);
        D0 = PC1CD(K1, K2, 28, 56);

        //System.out.printf("C0 = %#X\n", C0);
        //System.out.printf("D0 = %#X\n", D0);
        int s[] = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

        int C1, D1;

        for (int i = 0; i < 16; i++) {
            C1 = shiftLeft(C0, s[i]);
            D1 = shiftLeft(D0, s[i]);

            key1[i] = KPC2(C1, D1, 0, 24);
            key2[i] = KPC2(C1, D1, 24, 48);
            C0 = C1;
            D0 = D1;
            //System.out.printf("C1 = %#X\n", C1);
            //System.out.printf("D1 = %#X\n", D1);

            //int KC1, KD1;
            //KC1 = KPC2(C1, D1, 0, 24);
            //KD1 = KPC2(C1, D1, 24, 48);
            //System.out.printf("KC1 = %#X\n", KC1);
            //System.out.printf("KD1 = %#X\n", KD1);
        }

    }

    public static int subByte(int A1, int A2) {
        int S1[] = {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7, 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8, 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0, 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13};
        int S2[] = {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10, 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5, 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15, 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9};
        int S3[] = {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8, 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1, 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7, 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12};
        int S4[] = {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15, 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9, 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4, 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14};
        int S5[] = {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9, 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6, 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14, 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3};
        int S6[] = {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11, 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8, 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6, 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13};
        int S7[] = {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1, 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6, 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2, 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12};
        int S8[] = {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7, 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2, 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8, 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11};
        int B;
        int chiso[] = new int[9];
        int S[] = new int[9];
        //4 cap 6 bit ben trai thuoc A1
        for (int i = 1; i <= 4; i++) {
            int b6i = (A1 >> (24 - 6 * i)) & 0x3F;
            int bit1 = (b6i >> 5) & 0x01;
            int bit6 = b6i & 0x01;
            int hang = bit1 << 1 | bit6;
            int cot = (b6i >> 1) & 0xF;
            chiso[i] = (hang << 4) | cot;
        }
        //4 cap 6 bit ben phai thuoc A2
        for (int i = 5; i <= 8; i++) {
            int b6i = (A2 >> (24 - 6 * (i - 4))) & 0x3F;
            int bit1 = (b6i >> 5) & 0x01;
            int bit6 = b6i & 0x01;
            int hang = bit1 << 1 | bit6;
            int cot = (b6i >> 1) & 0xF;
            chiso[i] = (hang << 4) | cot;

        }
        //Tra bang S
        S[1] = S1[chiso[1]];
        S[2] = S2[chiso[2]];
        S[3] = S3[chiso[3]];
        S[4] = S4[chiso[4]];
        S[5] = S5[chiso[5]];
        S[6] = S6[chiso[6]];
        S[7] = S7[chiso[7]];
        S[8] = S8[chiso[8]];
        //Ghep 8 cap 4 bit tao thanh B (32 bit)
        B = 0;
        for (int i = 1; i <= 8; i++) {
            B = (B << 4) | S[i];
        }
        return B;
    }

    public static int HoanviP(int B) {
        int P[] = {16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 30, 6, 22, 11, 4, 25};
        int fp = 0;
        for (int i = 0; i < 32; i++) {
            int vitri;
            int bit;
            vitri = P[i];
            bit = getBit(B, vitri);
            int b = bit & 0x01;
            fp = (fp << 1) | b;
        }
        return fp;
    }

    public static int F(int L0, int R0, int key1, int key2) {
        int ER01, ER02;
        ER01 = ER0(R0, 0, 24);
        ER02 = ER0(R0, 24, 48);
//            System.out.println("Ket qua mo rong E(R0):");
//            System.out.printf("ER01 = %#X\n", ER01);
//            System.out.printf("ER02 = %#X\n", ER02);

        int A1, A2;
        A1 = key1 ^ ER01;
        A2 = key2 ^ ER02;

        //System.out.println("Ket qua phep XOR : A = E[R0] + K1");
        //System.out.printf("A1 = %#X\n", A1);
        //System.out.printf("A2 = %#X\n", A2);
        int B;
        B = subByte(A1, A2);
        //System.out.printf("Ket qua phep the B = %#X\n", B);

        int FP = HoanviP(B);
        //System.out.printf("Ket qua phep hoan vi P = %#X\n", FP);
        return FP;
    }

    public static void ShowByte(int C) {
        for (int i = 1; i <= 8; i++) {
            int b = (C >> (32 - 4 * i)) & 0xF;
            System.out.printf("%X", b);

        }
    }

    public static String intHexToString(int C) {
        return Integer.toHexString(C);

//       String hex = "";
//       for (int i = 1; i <= 8; i++) {
//            int b = (C >> (32 - 4 * i)) & 0xF;
//            hex += hex.toString();
//        }
//       
//       return hex;
    }

    public static int convertString2Hex(String numberHexString) {
        char[] ChaArray = numberHexString.toCharArray();
        int HexSum = 0;
        int cChar = 0;

        for (int i = 0; i < numberHexString.length(); i++) {
            if ((ChaArray[i] >= '0') && (ChaArray[i] <= '9')) {
                cChar = ChaArray[i] - '0';
            } else {
                cChar = ChaArray[i] - 'A' + 10;
            }
            HexSum = 16 * HexSum + cChar;
        }
        return HexSum;
    }

    public static String maHoaDES(int M1, int M2, int K1, int K2, int C1, int C2) {
        int[] key1 = new int[16];
        int[] key2 = new int[16];
        genKey(K1, K2, key1, key2);

//        for (int i = 0; i < 16; i++) {
//            System.out.printf("\nKhoa thu %d :  %#X %#X", i + 1, key1[i], key2[i]);
//        }
//        int M1 = 0x1234567;
//        int M2 = 0x89ABCDEF;
        int L0, R0;
        L0 = IPM(M1, M2, 0, 32);
        R0 = IPM(M1, M2, 32, 64);
//        System.out.println("Ket qua hoan vi IP:");
//        System.out.printf("L0 = %#X\n", L0);
//        System.out.printf("R0 = %#X\n", R0);
        int L1 = 0, R1 = 0;
        int FP;

        for (int i = 0; i < 16; i++) {
            FP = F(L0, R0, key1[i], key2[i]);
            R1 = FP ^ L0;
            L1 = R0;
            R0 = R1;
            L0 = L1;

            //System.out.printf("L[%d] = %#X\n", i, L1);
            //System.out.printf("R[%d] = %#X\n", i, R1);
        }

        //System.out.println("Ket thuc vong lap");
        //System.out.printf("L16 = %#X\n", L1);
        //System.out.printf("R16 = %#X\n", R1);
        //int C1, C2;
        C1 = hoanViIP_1(R1, L1, 0, 32);
        C2 = hoanViIP_1(R1, L1, 32, 64);

        //System.out.printf("Ban ma C1, C2 = %#X %#X\n", C1, C2);
//        System.out.println("Ban ma:");
//        ShowByte(C1);
//        ShowByte(C2);
//        System.out.println("");
        //int MC1 = 0, MC2 = 0;
        //giaiMaDES(C1, C2, K1, K2, MC1, MC2);
        return Integer.toHexString(C1) + "" + Integer.toHexString(C2);
    }

    public static String giaiMaDES(int M1, int M2, int K1, int K2, int C1, int C2) {
        int[] key1 = new int[16];
        int[] key2 = new int[16];
        genKey(K1, K2, key1, key2);

//        for (int i = 0; i < 16; i++) {
//            System.out.printf("\nKhoa thu %d :  %#X %#X", i + 1, key1[i], key2[i]);
//        }
//        int M1 = 0x1234567;
//        int M2 = 0x89ABCDEF;
        int L0, R0;
        L0 = IPM(M1, M2, 0, 32);
        R0 = IPM(M1, M2, 32, 64);
        //System.out.println("Ket qua hoan vi IP:");
        //System.out.printf("L0 = %#X\n", L0);
        //System.out.printf("R0 = %#X\n", R0);
        int L1 = 0, R1 = 0;
        int FP;

        for (int i = 0; i < 16; i++) {
            FP = F(L0, R0, key1[15 - i], key2[15 - i]);
            R1 = FP ^ L0;
            L1 = R0;
            R0 = R1;
            L0 = L1;

            //System.out.printf("L[%d] = %#X\n", i, L1);
            //System.out.printf("R[%d] = %#X\n", i, R1);
        }

        //System.out.println("Ket thuc vong lap");
        //System.out.printf("L16 = %#X\n", L1);
        //System.out.printf("R16 = %#X\n", R1);
        //int C1, C2;
        C1 = hoanViIP_1(R1, L1, 0, 32);
        C2 = hoanViIP_1(R1, L1, 32, 64);

        //System.out.print("Ban ro:");
        //System.out.printf("C = %#X %#X\n", C1, C2);
        //System.out.println(hexToText(intHexToString(C1) + intHexToString(C2)));
        //ShowByte(C1);
        //ShowByte(C2);
        //System.out.println("");
        return hexToText(Integer.toHexString(C1)) + hexToText(Integer.toHexString(C2));
    }

    // Global Charset Encoding
    public static Charset encodingType = StandardCharsets.UTF_8;

// Text To Hex
    public static String textToHex(String text) {
        byte[] buf = null;
        buf = text.getBytes(encodingType);
        char[] HEX_CHARS = "0123456789abcdef".toCharArray();
        char[] chars = new char[2 * buf.length];
        for (int i = 0; i < buf.length; ++i) {
            chars[2 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[2 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
        }
        return new String(chars);
        //return String.format("%040x", new BigInteger(1, text.getBytes(encodingType)));
    }

// Hex To Text
    public static String hexToText(String hex) {
        int l = hex.length();
        byte[] data = new byte[l / 2];
        for (int i = 0; i < l; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        String st = new String(data, encodingType);
        return st;
    }

    public static List<Integer> getHexIntAray(String text) {
        if ((text.length() % 8) != 0) {
            while ((text.length() % 8) != 0) {
                text += "0";
            }
        }
        
        System.out.println(text);
        
        String M = textToHex(text);

        System.out.println("String to hex: " + M);
        List<String> array = new ArrayList<>();
        for (int i = 0; i < M.length(); i += 8) {

            String cut = "";
            if ((i + 8) > M.length()) {
                cut = M.substring(i, M.length());
            } else {
                cut = M.substring(i, i + 8);
            }

            array.add(cut);
        }
        //System.out.println(array.toString());
        List<Integer> mArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            mArray.add(Integer.parseUnsignedInt(array.get(i), 16));
            //System.out.printf("%#x\t", mArray.get(i));
        }
        //System.out.println(mArray.toString());
        return mArray;
    }

    public static String encrypt(String text, String K) {
        int K1 = Integer.parseUnsignedInt(K.substring(0, 8), 16);
        int K2 = Integer.parseUnsignedInt(K.substring(8, K.length()), 16);

        List<Integer> mArray = getHexIntAray(text);
        int C1 = 0, C2 = 0;
        String cypherText = "";
        for (int i = 0; i < mArray.size() - 1; i += 2) {
            cypherText += maHoaDES(mArray.get(i), mArray.get(i + 1), K1, K2, C1, C2);
        }

        return cypherText;
    }

    public static String decrypt(String text, String K) {
        int K1 = Integer.parseUnsignedInt(K.substring(0, 8), 16);
        int K2 = Integer.parseUnsignedInt(K.substring(8, K.length()), 16);
        List<String> array = new ArrayList<>();
        for (int i = 0; i < text.length(); i += 8) {

            String cut = "";
            if ((i + 8) > text.length()) {
                cut = text.substring(i, text.length());
            } else {
                cut = text.substring(i, i + 8);
            }

            array.add(cut);
        }

        List<Integer> mArray = new ArrayList<>();
        for (int i = 0; i < array.size(); i++) {
            mArray.add(Integer.parseUnsignedInt(array.get(i), 16));
        }

        int MC1 = 0, MC2 = 0;
        String plainText = "";
        for (int i = 0; i < array.size() - 1; i += 2) {
            plainText += giaiMaDES(mArray.get(i), mArray.get(i + 1), K1, K2, MC1, MC2);
        }
        return plainText;
    }

    public static void main(String[] args) {
//        int K1 = 0xAABB0918; //4 byte
//        int K2 = 0x2736CCDD;
//        System.out.println("Khoa K: ");
//        ShowByte(K1);
//        ShowByte(K2);
//        System.out.println("");

        String text = "I am trying to convert a string like testing123 into hexadecimal form in java. I am currently using BlueJ.";
        String key = "3132333435363738";

        String cipher = encrypt(text, key);
        System.out.println("Ban ma day du: " + cipher);
        String result = decrypt(cipher, key);
        System.out.println("Ket qua: " + result.trim());

//        StringBuilder sb = new StringBuilder(result.trim().toUpperCase());
//        String text2 = sb.toString();
//        System.out.println(text2);
//        String cipher2 = encrypt(text2, key);
//        System.out.println("Ban ma day du: " + cipher2);
//        String result2 = decrypt(cipher2, key);
//        System.out.println("Ket qua2: " + result2.trim());
//        int M1 = 0x123456AB; 
//        int M2 = 0xCD132536;
//        System.out.println("Ban tin M = ");
//        ShowByte(M1);
//        ShowByte(M2);
//        System.out.println("");
//        int C1 = 0, C2 = 0;
//        String MC = maHoaDES(M1, M2, K1, K2, C1, C2);
//        System.out.println(MC);
//        String text = "ma hoa thanh cong =))";
//        String M = textToHex(text);
//        System.out.println(hexToText(M));
//
//        int length = M.length();
//        System.out.println("chieu dai ban M: " + length);
//        if ((length % 16) != 0) {
//            while ((length % 16) != 0) {
//                M += "0";
//                length = M.length();
//            }
//        }
//
//        System.out.println("M: " + M);
//        List<String> array = new ArrayList<>();
//        for (int i = 0; i < M.length(); i += 8) {
//
//            String cut = "";
//            if ((i + 8) > M.length()) {
//                cut = M.substring(i, M.length());
//            } else {
//                cut = M.substring(i, i + 8);
//            }
//
//            array.add(cut);
//        }
//
//        List<Integer> mArray = new ArrayList<>();
//        for (int i = 0; i < array.size(); i++) {
//            mArray.add(Integer.parseInt(array.get(i), 16));
//            System.out.printf("%#x\t", mArray.get(i));
//        }
//
//        int C1 = 0, C2 = 0;
//        String cypherText = "";
//        for (int i = 0; i < mArray.size() - 1; i += 2) {
//            cypherText += maHoaDES(mArray.get(i), mArray.get(i + 1), K1, K2, C1, C2);
//        }
//        ShowByte(M1);
//        ShowByte(M2);
//        System.out.println("");
        //maHoaDES(M1, M2, K1, K2, C1, C2);
        //System.out.println("Ban ma C");
//        ShowByte(C1);
//        ShowByte(C2);
//        System.out.println("");
        //int MC1 = 0,MC2 = 0;
        //giaiMaDES(C1, C2, K1, K2, MC1, MC2);
    }
}
