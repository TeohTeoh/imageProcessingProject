
import java.io.*;

public class patterning extends rawShop {

//    private String dPath = "";
    private String name1 = "";

    public void method2(String path, int width, int height, int stripOffsets) throws IOException {
        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String name = file.getName();

            for (int i = 0; i < name.length() - 4; i++) {
                name1 += name.charAt(i);
            }
            name1 += "_patterning.raw";
//            dPath = "C:\\Users\\pc\\Downloads\\" + name1;
            FileOutputStream myOutputFile = new FileOutputStream(name1);
            int value;
            int k = 0;
            int l = 0;
            int m = 0;
            int count = 1; //count column of image data
            //width=123*3=369(yoda)
            //height=62*3=186(yoda) 
            int[] arr0 = new int[myInputFile.available()];
            int[] arr2 = new int[arr0.length - stripOffsets];
            int[][] arr = new int[height][width];
            int[][] arr1 = new int[height * 3][width * 3];

            //store value into 1D array(int)
            while ((value = myInputFile.read()) != -1) {
                arr0[k++] = value;
            }
            //start from strip offsets(data raw)
            for (int i = stripOffsets; i < arr0.length; i++) {
                arr2[m++] = arr0[i];
            }
            //store value into 2D array(int)
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr[i].length; j++) {
                    arr[i][j] = arr2[l++];
                }
            }
            //patterning
            for (int j = 0; j < arr.length; j++) {
                for (int i = 0; i < arr[j].length; i++) {
                    if (arr[j][i] >= 0 && arr[j][i] <= 25) {//pattern0
                        arr1[j * 3 + 0][i * 3 + 0] = 0;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 0;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 0;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 0;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 0;//9
                    }
                    if (arr[j][i] >= 26 && arr[j][i] <= 50) {//pattern1
                        arr1[j * 3 + 0][i * 3 + 0] = 0;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 0;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 0;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 0;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 51 && arr[j][i] <= 75) {//pattern2
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 0;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 0;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 0;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 76 && arr[j][i] <= 100) {//pattern3
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 0;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 0;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 101 && arr[j][i] <= 125) {//pattern4
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 0;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 126 && arr[j][i] <= 150) {//pattern5
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 0;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 255;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 151 && arr[j][i] <= 175) {//pattern6
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 0;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 255;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 255;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 176 && arr[j][i] <= 200) {//pattern7
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 255;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 255;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 0;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 255;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 201 && arr[j][i] <= 225) {//pattern8
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 255;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 255;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 0;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 255;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 255;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                    if (arr[j][i] >= 226 && arr[j][i] <= 255) {//pattern9
                        arr1[j * 3 + 0][i * 3 + 0] = 255;//1
                        arr1[j * 3 + 0][i * 3 + 1] = 255;//2
                        arr1[j * 3 + 0][i * 3 + 2] = 255;//3
                        arr1[j * 3 + 1][i * 3 + 0] = 255;//4
                        arr1[j * 3 + 1][i * 3 + 1] = 255;//5
                        arr1[j * 3 + 1][i * 3 + 2] = 255;//6
                        arr1[j * 3 + 2][i * 3 + 0] = 255;//7
                        arr1[j * 3 + 2][i * 3 + 1] = 255;//8
                        arr1[j * 3 + 2][i * 3 + 2] = 255;//9
                    }
                }
            }

            for (int j = 0; j < arr1.length; j++) {
                for (int i = 0; i < arr1[j].length; i++) {
                    myOutputFile.write(arr1[j][i]);
                }
            }
            System.out.println(name1 + " is created.");
            myOutputFile.close();
            myInputFile.close();
        } catch (IOException ex) {
            System.out.println("File error!");
        }
    }

    public String getName1() {
        return name1;
    }

//    public String getdPath() {
//        return dPath;
//    }
}
