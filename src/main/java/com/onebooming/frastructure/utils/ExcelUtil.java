package com.onebooming.frastructure.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-04-18 10:14
 * @ApiNote
 */
public class ExcelUtil {
    public static boolean exportToExcet(Workbook workbook, String fileName){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String savePath = fileName + sdf.format(new Date()) + ".xls";
        workbook.setSheetName(0, fileName);
        workbook.getSheetAt(0).setDefaultRowHeight((short) 21);
        String localPathPrefix = "file";

        try {
            File filePath = new File(localPathPrefix);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }

            File localFile = new File(localPathPrefix + File.separator + savePath);
            OutputStream os = new FileOutputStream(localFile);

            workbook.write(os);
            os.flush();
            os.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
