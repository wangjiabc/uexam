package com.alvis.exam;

import org.junit.Test;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

/**
 * @author lz
 * @description
 * @date 2019/10/18
 */
public class SomeTest {
    private static final String PATH = "C:\\Users\\Administrator\\Desktop\\uexam\\uexam\\source\\exam\\src";
    private static final File FILE = new File(FileSystemView.getFileSystemView().getHomeDirectory() + File.separator + "aa.txt");
    private static int LineCount = 0;
    private static int LineCountMax = 50 * 60;
    private static BufferedWriter BUFFERED_WRITER = null;


    @Test
    public void test() throws IOException {
        FILE.createNewFile();
        BUFFERED_WRITER = new BufferedWriter(new FileWriter(FILE));
        File file = new File(PATH);
        traverseFile(file);
    }

    private void traverseFile(File file) throws IOException {
        if (file.isFile()) {
            if (file.toString().endsWith(".java") && LineCount < LineCountMax) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line;
                try {
                    while ((line = bufferedReader.readLine()) != null) {
                        //去空行
                        if (line.isEmpty())
                            continue;
                        LineCount++;
                        BUFFERED_WRITER.write(line);
                        BUFFERED_WRITER.newLine();
                    }
                } finally {
                    bufferedReader.close();
                    BUFFERED_WRITER.flush();
                }
            }
        } else {
            for (File listFile : file.listFiles()) {
                traverseFile(listFile);
            }
        }
    }

    @Test
    public void testReduce() {
    }

}
