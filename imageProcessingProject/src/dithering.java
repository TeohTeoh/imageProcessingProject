
import java.io.*;

public class dithering extends rawShop {

//    private String dPath1 = "";
//    private String dPath2 = "";
    private String name1 = "";
    private String name2 = "";

    public void method3(String path, int width, int height, int stripOffsets) throws IOException {

        try {
            File file = new File(path);
            FileInputStream myInputFile = new FileInputStream(file);
            String name = file.getName();
            for (int i = 0; i < name.length() - 4; i++) {
                name1 += name.charAt(i);
                name2 += name.charAt(i);
            }
            name1 += "_d1.raw";
//            dPath1 = "C:\\Users\\pc\\Downloads\\" + name1;
            name2 += "_d2.raw";
//            dPath2 = "C:\\Users\\pc\\Downloads\\" + name2;
            FileOutputStream myOutputFile = new FileOutputStream(name1);
            FileOutputStream myOutputFile1 = new FileOutputStream(name2);
            int value;
            int k = 0;
            int l = 0;
            int m = 0;
            int n = 0;
            int[][] d1 = {{0, 128}, {192, 64}};
            int[][] d2 = {
                {0, 128, 32, 160},
                {192, 64, 224, 96},
                {48, 176, 16, 144},
                {240, 112, 208, 80}};
            int[] arr0 = new int[myInputFile.available()];
            int[] arr3 = new int[arr0.length - stripOffsets];
            int[][] arr1 = new int[height][width];
            int[][] arr2 = new int[height][width];

            //store value into 1D array(int)
            while ((value = myInputFile.read()) != -1) {
                arr0[k++] = value;
            }
            //start from strip offsets(data raw)
            for (int i = stripOffsets; i < arr0.length; i++) {
                arr3[n++] = arr0[i];
            }
            for (int i = 0; i < arr1.length; i++) {
                for (int j = 0; j < arr1[i].length; j++) {
                    //store value into 2D array(int)
                    arr1[i][j] = arr3[l++];
                    arr2[i][j] = arr3[m++];
                    //d1
                    if (arr1[i][j] > d1[i % 2][j % 2]) {
                        arr1[i][j] = 255;
                    } else {
                        arr1[i][j] = 0;
                    }
                    //d2
                    if (arr2[i][j] > d2[i % 4][j % 4]) {
                        arr2[i][j] = 255;
                    } else {
                        arr2[i][j] = 0;
                    }
                    myOutputFile.write(arr1[i][j]);
                    myOutputFile1.write(arr2[i][j]);
                }
            }

            System.out.println(name1 + " is created.");
            System.out.println(name2 + " is created.");
            myOutputFile.close();
            myOutputFile1.close();
            myInputFile.close();
        } catch (IOException ex) {
            System.out.println("File error!");
        }
    }

//    public String getdPath1() {
//        return dPath1;
//    }
//
//    public String getdPath2() {
//        return dPath2;
//    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }
}
