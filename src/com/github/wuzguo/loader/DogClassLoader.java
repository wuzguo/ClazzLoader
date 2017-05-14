package com.github.wuzguo.loader;

import com.github.wuzguo.utils.EncryptHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo.loader
 */
public class DogClassLoader extends ClassLoader {

    private String clazzPath;

    public DogClassLoader(String clazzPath) {
        this.clazzPath = clazzPath;
        System.out.println("DogClassLoader的类加载器为： " + DogClassLoader.class.getClassLoader().getClass().getName());
    }

    public DogClassLoader(ClassLoader parent, String clazzPath) {
        super(parent);
        this.clazzPath = clazzPath;
        System.out.println("DogClassLoader的类加载器为： " + DogClassLoader.class.getClassLoader().getClass().getName());
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        StringBuilder classFileName = new StringBuilder(this.clazzPath);
        String className = name.substring(name.lastIndexOf('.') + 1);
        classFileName.append("\\" + className);
        classFileName.append(".class");

        // 如果目标路径不存在就创建路径
        File file = new File(classFileName.toString());
        if (!file.exists()) {
            System.out.println("dest file not find: " + classFileName);
        }

        System.out.println("classFileName: " + classFileName.toString());

        try {
            FileInputStream fileStrean = new FileInputStream(classFileName.toString());
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();

            if (EncryptHelper.encHelper(fileStrean, byteOutput)) {
                byte[] bytes = byteOutput.toByteArray();
                return defineClass(bytes, 0, bytes.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return super.findClass(name);
    }
}
