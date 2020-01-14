package cn.van.jdk.five.generic.method;

/**
 * Copyright (C), 2015-2020, 风尘博客
 * 公众号 : 风尘博客
 * FileName: Generic
 *
 * @author: Van
 * Date:     2019-12-23 00:17
 * Description: 泛型方法
 * Version： V1.0
 */
public class GenericMethodDemo {

    /**
     * 泛型类
     * @param <T>
     */
    public class Generic<T> {
        private T key;

        public Generic(T key) {
            this.key = key;
        }

        /**
         * 这里虽然在方法中使用了泛型，但是这并不是一个泛型方法，
         * 这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型，
         * 所以在这个方法中才可以继续使用 T 这个泛型。
         * @return
         */
        public T getKey() {
            return key;
        }

        /**
         * 这才是一个真正的泛型方法
         * @param container
         * @param <T>
         * @return
         */
        public <T> T keyName(Generic<T> container){
            T test = container.getKey();
            return test;        }

        /**
         * 这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
         * @param obj
         */
        public void showKeyValue1(Generic<Number> obj){

        }

        /**
         * 这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
         * @param obj
         */
        public void showKeyValue2(Generic<?> obj){

        }


        /**
         * 该方法编译器会报错
         * 虽然我们声明了<T>,也表明了这是一个可以处理泛型的类型的泛型方法。
         * 但是只声明了泛型类型T，并未声明泛型类型E，因此编译器并不知道该如何处理E这个类型。
         * @param container
         * @param <T>
         * @return
         */
        /*public <T> T showKeyName(Generic<E> container){
            return null;
        }*/

    }
}
