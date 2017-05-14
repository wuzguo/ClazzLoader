package com.github.wuzguo;

import com.github.wuzguo.loader.DogClassLoader;
import com.github.wuzguo.loader.SonClassLoader;
import com.github.wuzguo.pojo.Animal;
import com.github.wuzguo.utils.EncryptHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.StringTokenizer;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo
 */
public class ClazzLoaderTest {
    // 源目录（绝对路径）
    private String srcPath;
    // 目标目录（相对路径）
    private String destPath;

    public ClazzLoaderTest(final String srcPath, final String destPath) {
        this.srcPath = srcPath;
        this.destPath = destPath;
    }

    private void encryptClassFile(final String className) {
        // 获取文件名
        String srcFileName = srcPath + className;
        System.out.println("srcFileName: " + srcFileName);

        // 判断源文件是否存在
        File srcFile = new File(srcFileName);
        if (!srcFile.exists()) {
            System.out.println("src file not find: " + srcFileName);
        }
        // 如果目标路径不存在就创建路径
        File file = new File(destPath);
        if (!file.exists()) {
            file.mkdir();
        }
        String destDir = destPath + "\\" + className;
        System.out.println("destDir: " + destDir);

        try {
            FileInputStream fileInput = new FileInputStream(srcFileName);
            FileOutputStream fileOutput = new FileOutputStream(destDir);
            EncryptHelper.encHelper(fileInput, fileOutput);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void testClassLoader(final String className) {
        // 设置ClassLoader的加载路径
        // DogClassLoader clazzLoader = new DogClassLoader(srcPath.substring(0, srcPath.lastIndexOf('/')));
        DogClassLoader clazzLoader = new DogClassLoader(destPath);
        SonClassLoader sonClassLoader = new SonClassLoader(clazzLoader);
        try {
            Class clazz = clazzLoader.loadClass(className);
            // DogClassLoader 默认挂在 AppClassLoader下面，用户自定义的ClassLoader的parent是appClassLoaader
            ClassLoader classLoader = clazz.getClassLoader();
            System.out.println("加载" + className + "类的加载器的层次结构为: ");
            while (classLoader != null) {
                System.out.println(classLoader.getClass().getName());
                classLoader = classLoader.getParent();
            }
            System.out.println(classLoader);
            // 这里不能出现Dog类名（在Dog.class未加密的文件缺失的情况下），否则编译器会报错
            Animal animal = (Animal) clazz.newInstance();
            System.out.println(animal);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    // 打印AppClassLoader加载目录
    private static void printAppClassLoaderPath() {
        // Java的入口程序sun.misc.Launcher中定义
        final String strPath = System.getProperty("java.class.path");

        File[] dirs;
        if (strPath != null) {
            StringTokenizer tokenizer = new StringTokenizer(strPath, File.pathSeparator);
            int count = tokenizer.countTokens();
            dirs = new File[count];
            for (int i = 0; i < count; i++) {
                dirs[i] = new File(tokenizer.nextToken());
            }
        } else {
            dirs = new File[0];
        }

        for (File f : dirs) {
            System.out.println(f.getAbsolutePath());
        }
    }

    public static void main(String[] args) {
        // 打印AppClassLoader加载类的目录结构
        printAppClassLoaderPath();
        //获取Dog类所在工程的路径
        String srcPath = Animal.class.getResource("").getPath();
        System.out.println("srcPath： " + srcPath);

        // 获取当前类加载器加载的根路径
        String basePath = ClazzLoaderTest.class.getClass().getResource("/").getPath();
        System.out.println("basePath： " + basePath);

        // 利用System.getProperty()函数获取当前路径：
        //user.dir指定了当前的路径
        String destPath = System.getProperty("user.dir");

        ClazzLoaderTest classLoder = new ClazzLoaderTest(srcPath, basePath + "enc");
        // 测试类名
        String className = "Dog.class";
        // 加密Dog.class文件
        classLoder.encryptClassFile(className);
        // 要加载的类名，这里要带上包名，否则AppClassLoader加载不到
        classLoder.testClassLoader("com.github.wuzguo.pojo.Dog");
    }
}