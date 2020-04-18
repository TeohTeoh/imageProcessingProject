
import java.io.*;

public class lowPassFilters extends rawShop {

//    private String dPath = "";
    private String name1 = "";

    public void method6(String path, int width, int height, int stripOffsets) throws IOException {

        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String name = file.getName();
            for (int i = 0; i < name.length() - 4; i++) {
                name1 += name.charAt(i);
            }
            name1 += "_lowPassFiltering.raw";
//            dPath = "C:\\Users\\pc\\Downloads\\" + name1;
            FileOutputStream myOutputFile = new FileOutputStream(name1);
            int value;
            int k = 0;
            int l = 0;
            int m = 0;
            int sum = 0;
            int[][] kernel = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};//low pass
            int[] arr0 = new int[myInputFile.available()];
            int[] arr3 = new int[arr0.length - stripOffsets];
            int[][] arr1 = new int[height][width];//store f(y,x) 
            int[][] arr2 = new int[height][width];//store g(y,x) =>low pass filtering

            //store value into 1D array(int)
            while ((value = myInputFile.read()) != -1) {
                arr0[k++] = value;
            }
            //start from strip offsets(data raw)
            for (int i = stripOffsets; i < arr0.length; i++) {
                arr3[m++] = arr0[i];
            }
            //store value into 2D array(int)
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    arr1[i][j] = arr3[l++];
                }
            }
            //low pass, high pass
            for (int i = 0; i < arr2.length; i++) {
                for (int j = 0; j < arr2[i].length; j++) {
                    sum = 0;
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum += kernel[0][0] * arr1[i + 1][j + 1];
                    }
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum += kernel[0][1] * arr1[i + 1][j + 0];
                    }
                    if (i + 1 >= 0 && i + 1 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum += kernel[0][2] * arr1[i + 1][j - 1];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum += kernel[1][0] * arr1[i + 0][j + 1];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum += kernel[1][1] * arr1[i + 0][j + 0];
                    }
                    if (i + 0 >= 0 && i + 0 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum += kernel[1][2] * arr1[i + 0][j - 1];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j + 1 >= 0 && j + 1 <= (width - 1)) {
                        sum += kernel[2][0] * arr1[i - 1][j + 1];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j + 0 >= 0 && j + 0 <= (width - 1)) {
                        sum += kernel[2][1] * arr1[i - 1][j + 0];
                    }
                    if (i - 1 >= 0 && i - 1 <= (height - 1) && j - 1 >= 0 && j - 1 <= (width - 1)) {
                        sum += kernel[2][2] * arr1[i - 1][j - 1];
                    }
                    sum /= 9;
                    if (sum < 0) {
                        sum = 0;
                    }
                    if (sum > 255) {
                        sum = 255;
                    }
                    arr2[i][j] = sum;//low pass
                }
            }

            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    myOutputFile.write(arr2[i][j]);
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
