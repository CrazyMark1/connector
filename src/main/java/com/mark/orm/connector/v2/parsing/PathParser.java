package com.mark.orm.connector.v2.parsing;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 帅气的Mark
 * @Description: Mark行行好，给点注释吧！
 * @Date: Create in 2018/4/7 19:48
 * @QQ: 85104982
 */
public class PathParser {
    private void checkPath(String ScanPath){
        if (null == ScanPath || ScanPath.trim().equals(""))
            throw new RuntimeException("扫描路径不能为空");
    }
    public List<String> scannerClass(String scanPath) throws ClassNotFoundException {
        List<String> list=new ArrayList<String>();
        checkPath(scanPath);
        URL url=this.getClass().getClassLoader().getResource(scanPath.replaceAll("\\.","/"));
        File dir=new File(url.getFile());
        for (File file:dir.listFiles()){
            if (file.isDirectory())
                scannerClass(scanPath+"."+file.getName());
            else{
                String className=scanPath+"."+file.getName();
                if (className.endsWith("Mapper.class")){
                    list.add(className);
                }
            }
        }
        return list;
    }
}
