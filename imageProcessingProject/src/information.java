
import java.io.*;

public class information extends rawShop {

    private String order;
    private String version;
    private String offset;
    private int totalDE;
    private int sizeIFD;
    private int offset1;
    private int width;
    private int height;
    private int stripOffsets;
    private int stripByteCounts;

    public void method1(String path) throws IOException {
        int value;
        int count = 1; //count column of image data
        int k = 0;

        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String[] arr = new String[myInputFile.available()];

            //store value into array(string)
            while ((value = myInputFile.read()) != -1) {
                arr[k++] = Integer.toHexString(value);
            }

            //make each byte two digit
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].length() == 1) {
                    arr[i] = 0 + arr[i];
                }
            }

            //header info
            order = arr[1] + arr[0];
            version = arr[3] + arr[2];
            offset = arr[7] + arr[6] + arr[5] + arr[4];

            //ifd data
            String m = arr[9] + arr[8];//m=10(hex)
            int m1 = Integer.parseInt(m, 16);//hex to dec  //m1=16(dec)
            totalDE = m1 * 12;
            sizeIFD = totalDE + 2;
            offset1 = Integer.parseInt(arr[8 + sizeIFD + 6 - 1] + arr[8 + sizeIFD + 5 - 1]);

            //data entry
            String[] arrDE = new String[totalDE];//array of DE
            int u = 0; //count arrDE

            //array of data entry
            for (int i = 10; i < totalDE + 10; i++) {
                arrDE[u++] = arr[i];
            }
            String[] arrTag = new String[m1];
            String[] arrValue = new String[m1];
            stripOffsets = 0;
            stripByteCounts = 0;
            width = 0;
            height = 0;

            for (int i = 0; i < m1; i++) {
                arrTag[i] = arrDE[1 + 12 * i] + arrDE[0 + 12 * i];
                arrValue[i] = arrDE[11 + 12 * i] + arrDE[10 + 12 * i] + arrDE[9 + 12 * i] + arrDE[8 + 12 * i];
            }

            for (int i = 0; i < arrTag.length; i++) {
                //Strip offsets=273
                //Strip byte counts=279
                //width=256
                //height=257
                if (Integer.parseInt(arrTag[i], 16) == 273) {
                    stripOffsets = Integer.parseInt(arrValue[i], 16);
                }
                if (Integer.parseInt(arrTag[i], 16) == 279) {
                    stripByteCounts = Integer.parseInt(arrValue[i], 16);
                }
                if (Integer.parseInt(arrTag[i], 16) == 256) {
                    width = Integer.parseInt(arrValue[i], 16);
                }
                if (Integer.parseInt(arrTag[i], 16) == 257) {
                    height = Integer.parseInt(arrValue[i], 16);
                }
                myInputFile.close();
            }

        } catch (IOException ex) {
            System.out.println("File error!");
        }
    }

    public String getOrder() {
        return order;
    }

    public String getVersion() {
        return version.replaceFirst("^0+(?!$)", "");
    }

    public String getOffset() {
        return offset.replaceFirst("^0+(?!$)", "");
    }

    public int getTotalDE() {
        return totalDE;
    }

    public int getSizeIFD() {
        return sizeIFD;
    }

    public int getOffset1() {
        return offset1;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStripOffsets() {
        return stripOffsets;
    }

    public int getStripByteCounts() {
        return stripByteCounts;
    }
}
