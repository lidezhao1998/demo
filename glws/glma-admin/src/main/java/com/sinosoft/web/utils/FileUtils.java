package com.sinosoft.web.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Enumeration;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author sunlei
 * @description shp文件上传到linux上
 * @date 2020/09/10/14:31
 */
public class FileUtils {
    /**
     * 实现服务器连接
     *
     * @param host
     * @param port
     * @param username
     * @param password //     * @param privateKeyFile
     * @return
     * @throws IOException
     */

    public static Connection getSSHConnection(String host, int port, String username, String password) throws IOException {
        Connection connection = new Connection(host, port);
        connection.connect();

//        File file=new File(privateKeyFile);
//        System.out.println(file);
        boolean b = connection.authenticateWithPassword(username, password);
        System.out.println(b);
        if (b) {
            return connection;
        } else {
            System.out.println("登录连接失败，请检查用户名、密码、私钥文件");
            return null;
        }
    }

    /**
     * 实现下载服务器上的文件到本地指定目录
     *
     * @param conn      SSH连接信息
     * @param fileName  服务器上的文件地址/opt/rpc/log/order/all.log
     * @param localPath 本地路径：D:\
     * @throws IOException
     */

    public void getFile(Connection conn, String fileName, String localPath) throws IOException {

        SCPClient scpClient = conn.createSCPClient();
        //String logPath="cd /opt/rpc/log/order";
        //Session session = conn.openSession();
        //session.execCommand(logPath);//执行shell命令
        scpClient.get(fileName, localPath);

    }

    /**
     * 实现上传本地文件到服务器上指定目录
     *
     * @param conn      SSH连接信息
     * @param filepath  D:\a.txt
     * @param localPath 服务器地址路径：/opt/rpc/log/order
     * @throws IOException
     */
    public void putFile(Connection conn, String fileName, String localPath) throws IOException {

        SCPClient scpClient = conn.createSCPClient();
        scpClient.put(fileName, localPath);

    }

    /**
     * 解压文件
     *
     * @param zipPath 要解压的目标文件
     * @param descDir 指定解压目录
     * @return 解压结果：成功，失败
     */
    @SuppressWarnings("rawtypes")
    public boolean decompressZip(String zipPath, String descDir) {
        File zipFile = new File(zipPath);
        boolean flag = false;
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = null;
        try {
            zip = new ZipFile(zipFile, Charset.forName("gbk"));//防止中文目录，乱码
            for (Enumeration entries = zip.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                String zipEntryName = entry.getName();
                InputStream in = zip.getInputStream(entry);
                //指定解压后的文件夹+当前zip文件的名称
                String outPath = (descDir + zipEntryName).replace("/", File.separator);
                //判断路径是否存在,不存在则创建文件路径
                File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
                if (!file.exists()) {
                    file.mkdirs();
                }
                //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
                if (new File(outPath).isDirectory()) {
                    continue;
                }
                //保存文件路径信息（可利用md5.zip名称的唯一性，来判断是否已经解压）
                System.err.println("当前zip解压之后的路径为：" + outPath);
                OutputStream out = new FileOutputStream(outPath);
                byte[] buf1 = new byte[2048];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
            }
            flag = true;
            //必须关闭，要不然这个zip文件一直被占用着，要删删不掉，改名也不可以，移动也不行，整多了，系统还崩了。
            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
     *
     * @param sourceFilePath :待压缩的文件路径
     * @param zipFilePath    :压缩后存放路径
     * @param fileName       :压缩后文件的名称
     * @return
     */
    public static boolean fileToZip(String sourceFilePath, String zipFilePath, String fileName) {
        boolean flag = false;
        File sourceFile = new File(sourceFilePath);
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;

        if (sourceFile.exists() == false) {
            System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
            sourceFile.mkdir(); // 新建目录
        }
        try {
            File zipFile = new File(zipFilePath + "/" + fileName + ".zip");
            if (zipFile.exists()) {
                System.out.println(zipFilePath + "目录下存在名字为:" + fileName + ".zip" + "打包文件.");
            } else {
                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFiles || sourceFiles.length < 1) {
                    System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
                } else {
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024 * 10];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 10);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                    }
                    flag = true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //关闭流
            try {
                if (null != bis) bis.close();
                if (null != zos) zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return flag;
    }
    static void gaiFileName(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        if (Objects.nonNull(files) && files.length > 0) {
            for (File file1 : files) {
                if (file1.isDirectory()){
                    continue;
                }else {
                    String fileName = file1.getName();
                    File parentPath = file1.getParentFile();
                    String[] params = fileName.split("\\.");
                    String newName = "";//初始值
                    if (params.length>=2){
                        newName = "123." +params[1];
                    }
                    File newDir = new File(parentPath + "/" + newName);
                    file1.renameTo(newDir);
                }
            }
        }
    }

    public static void newName(String path, String name) {
        File file = new File(path);   //指定文件名及路径
        String filename = file.getAbsolutePath();
        if (filename.indexOf(".") >= 0) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }
        file.renameTo(new File(name + ".shp"));   //改名

    }
    public static  void traverseFolder(String path) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            if (null == files || files.length == 0) {
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        traverseFolder(file2.getAbsolutePath());
                    } else {
                        if(file2.getName().contains("shp"))
                        {
                            try {
                                generateFile(file2.getPath(),"zip");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("文件:" + file2.getAbsolutePath());
                        }
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }
    }
    /**
     * @param path   要压缩的文件路径
     * @param format 生成的格式（zip、rar）d
     */
    public static void generateFile(String path, String format) throws Exception {

        File file = new File(path);
        // 压缩文件的路径不存在
        if (!file.exists()) {
            throw new Exception("路径 " + path + " 不存在文件，无法进行压缩...");
        }
        // 用于存放压缩文件的文件夹
        String generateFile = file.getParent()+ File.separator;
        File compress = new File(generateFile);
        // 如果文件夹不存在，进行创建
        if( !compress.exists() ){
            compress.mkdirs();
        }

        // 目的压缩文件
        String generateFileName = compress.getAbsolutePath()+ File.separator +file.getName().replace(".shp","") + "." + format;

        // 输入流 表示从一个源读取数据
        // 输出流 表示向一个目标写入数据

        // 输出流
        FileOutputStream outputStream = new FileOutputStream(generateFileName);

        // 压缩输出流
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));

        generateFile(zipOutputStream,file,"");

        System.out.println("源文件位置：" + file.getAbsolutePath() + "，目的压缩文件生成位置：" + generateFileName);
        // 关闭 输出流
        zipOutputStream.close();
    }
    /**
     * @param out  输出流
     * @param file 目标文件
     * @param dir  文件夹
     * @throws Exception
     */
    private static void generateFile(ZipOutputStream out, File file, String dir) throws Exception {

        // 当前的是文件夹，则进行一步处理
        if (file.isDirectory()) {
            //得到文件列表信息
            File[] files = file.listFiles();

            //将文件夹添加到下一级打包目录
            out.putNextEntry(new ZipEntry(dir + "/"));

            dir = dir.length() == 0 ? "" : dir + "/";

            //循环将文件夹中的文件打包
            for (int i = 0; i < files.length; i++) {
                generateFile(out, files[i], dir + files[i].getName());
            }

        } else { // 当前是文件

            // 输入流
            FileInputStream inputStream = new FileInputStream(file);
            // 标记要打包的条目
            out.putNextEntry(new ZipEntry(dir));
            // 进行写操作
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            // 关闭输入流
            inputStream.close();
        }

    }
    public static void main(String[] args) throws IOException {
//        File file = new File("D:\\demoshp\\高寒生态系统\\a89713ef1f6efee5.shp");//也可以是文件夹路径
////待生成的zip包名
//        //待生成的zip保存路径
//        String zipFilePath = "D:\\demoshp\\高寒生态系统\\";
////压缩
//        FileUtils.fileToZip("D:\\demoshp\\高寒生态系统\\", zipFilePath, "a89713ef1f6efee5.zip");
////        System.out.println(s.substring(s.lastIndexOf("\\")+1));
////        boolean b = fileUtils.decompressZip("D:\\demoshp\\data\\sun\\data.zip","D:\\demoshp\\data\\sun\\");
//        FileUtils fileUtils = new FileUtils();
        String dir="C:\\Users\\Administrator\\Desktop\\文档\\gis\\123\\sss.text";
        int i = dir.lastIndexOf("\\");
        String substring = dir.substring(0,dir.lastIndexOf("\\"));
//        Connection sshConnection = FileUtils.getSSHConnection("47.94.105.131", 22, "root", "Qaz123wsX");
        FileUtils fileUtils = new FileUtils();
        String s1 = "d:\\opt\\" + "a89713ef1f6efee5";
        File file1 = new File(s1);
        if (!file1.exists()){
            file1.mkdirs();
        }

//        fileUtils.putFile(sshConnection,"D:\\demoshp\\高寒生态系统\\a89713ef1f6efee5.shp",s1);
        fileUtils.fileToZip("D:\\demoshp\\高寒生态系统\\",s1,"a89713ef1f6efee5");



    }
}
