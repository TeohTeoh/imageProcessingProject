
import java.io.*;
import java.text.DecimalFormat;

public class histogram extends rawShop {

//    private String dPath = "";
    private String name1 = "";

    public void method4(String path, int stripOffsets) throws IOException {

        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String name = file.getName();
            for (int i = 0; i < name.length() - 4; i++) {
                name1 += name.charAt(i);
            }
            name1 += "_histogram.raw";
//            dPath = "C:\\Users\\pc\\Downloads\\" + name1;
            FileOutputStream myOutputFile = new FileOutputStream(name1);
            int value;
            int k = 0;//count arr0
            int m = 0;
            int runSum1 = 0;//for increment use and get the last value of runSum(array)
            int[] arr0 = new int[myInputFile.available()];
            int[] arr2 = new int[arr0.length - stripOffsets];
            int[] noOfPixel = new int[256];//num of pixels of original image
//            int[] noOfPixel1 = new int[256];//num of pixels of histogram image
            int[] runSum = new int[256];//run sum
            double[] normalized = new double[256];//normalized(run sum / 7626)
            double[] mul255 = new double[256];//multiply 255(double)
            int[] map = new int[256];//histogram-equalized value

            //store value into 1D array(int)
            while ((value = myInputFile.read()) != -1) {
                arr0[k++] = value;
            }
            //start from strip offsets(data raw)
            for (int i = stripOffsets; i < arr0.length; i++) {
                arr2[m++] = arr0[i];
                noOfPixel[arr0[i]]++;//store total number of each value into array
            }
            //run sum
            for (int i = 0; i < noOfPixel.length; i++) {
                runSum[i] = runSum1 += noOfPixel[i];
            }
            //normalized
            DecimalFormat df = new DecimalFormat("0.00");
            for (int i = 0; i < runSum.length; i++) {
                normalized[i] = runSum[i] / 1.0 / runSum1;
            }
            //multiply255
            for (int i = 0; i < normalized.length; i++) {
                mul255[i] = Math.round(normalized[i] * 255);
            }
            for (int i = 0; i < arr2.length; i++) {
                myOutputFile.write((int) mul255[arr2[i]]);
            }
            System.out.println(name1 + " is created.");
            myOutputFile.close();
            myInputFile.close();
        } catch (IOException ex) {
            System.out.println("File error!");
        }
    }

//    public String getdPath() {
//        return dPath;
//    }

    public String getName1() {
        return name1;
    }
}
