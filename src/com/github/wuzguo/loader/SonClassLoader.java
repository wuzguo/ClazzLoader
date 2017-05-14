package com.github.wuzguo.loader;

/**
 * IntelliJ IDEA 2016.3
 *
 * @author： admin
 * @date： 五月 13, 2017
 * @version: 1.0.0
 * @desc: ClazzLoader / com.github.wuzguo.loader
 */
public class SonClassLoader extends ClassLoader {

    public SonClassLoader() {
    }

    public SonClassLoader(ClassLoader parent) {
        super(parent);
        System.out.println("SonClassLoader的类加载器为：" + SonClassLoader.class.getClassLoader().getClass().getName());
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
