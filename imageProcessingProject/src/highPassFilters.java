
import java.io.*;

public class highPassFilters extends rawShop {

//    private String dPath = "";
    private String name1 = "";

    public void method7(String path, int width, int height, int stripOffsets) throws IOException {

        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String name = file.getName();
            for (int i = 0; i < name.length() - 4; i++) {
                name1 += name.charAt(i);
            }
            name1 += "_highPassFiltering.raw";
//            dPath = "C:\\Users\\pc\\Downloads\\" + name1;
            FileOutputStream myOutputFile = new FileOutputStream(name1);
            int value;
            int k = 0;
            int l = 0;
            int m = 0;
            int sum1 = 0;
            int[][] kernel1 = {{-1, -1, -1}, {-1, 8, -1}, {-1, -1, -1}};//high pass
            int[] arr0 = new int[myInputFile.available()];
            int[] arr2 = new int[arr0.length - stripOffsets];
            int[][] arr1 = new int[height][width];//store f(y,x) 
            int[][] arr3 = new int[height][width];//store g1(y,x) =>high pass filtering

            //store value into 1D array(int)
            while ((value = myInputFile.read()) != -1) {
                arr0[k++] = value;
            }
            //start from strip offsets(data raw)
            for (int i = stripOffsets; i < arr0.length; i++) {
                arr2[m++] = arr0[i];
            }
            //store value into 2D array(int)
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    arr1[i][j] = arr2[l++];
                }
            }
            //low pass, high pass
            for (int i = 0; i < arr3.length; i++) {
                for (int j = 0; j < arr3[i].length; j++) {
                    sum1 = 0;
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum1 += kernel1[0][0] * arr1[i + 1][j + 1];
                    }
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum1 += kernel1[0][1] * arr1[i + 1][j + 0];
                    }
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum1 += kernel1[0][2] * arr1[i + 1][j - 1];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum1 += kernel1[1][0] * arr1[i + 0][j + 1];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum1 += kernel1[1][1] * arr1[i + 0][j + 0];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum1 += kernel1[1][2] * arr1[i + 0][j - 1];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum1 += kernel1[2][0] * arr1[i - 1][j + 1];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum1 += kernel1[2][1] * arr1[i - 1][j + 0];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum1 += kernel1[2][2] * arr1[i - 1][j - 1];
                    }
                    if (sum1 < 0) {
                        sum1 = 0;
                    }
                    if (sum1 > 255) {
                        sum1 = 255;
                    }
                    arr3[i][j] = sum1;//high pass
                }
            }

            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    myOutputFile.write(arr3[i][j]);
                }
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
