package com.mesmerize.mesmerize;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

/**
 * @author yangyunpeng
 */

public class SimpleStudy {

//    public static final String TAG = SimpleStudy.class.getSimpleName();

    /**
     */
    public static void main(String[] args) {

        createObserver();
        createObservable();
    }
    /**
     * 创建观察者
     */
    private static void createObserver(){

//        testObserver();
//        testSubscriber();
    }

    /**
     * 创建被观察者
     */
    private static void createObservable(){

        testCreateObservable();

        testJustObservable();

        testFromObservable();
    }

    private static void testCreateObservable() {

        Observable.create(new Observable.OnSubscribe<Test>() {
            @Override
            public void call(Subscriber<? super Test> subscriber) {

                subscriber.onNext(new Test());
                subscriber.onNext(new Test());
                subscriber.onCompleted();
            }
        }).subscribe(testObserver());
    }

    /**
     * just() 会依次调用 onNext()  onCompleted()
     */
    private static void testJustObservable(){

       Observable.just(new Test(), new Test()).subscribe(testObserver());
    }

    /**
     * from(T[])  from(Iterable<? extends T>)
     * 传入的数组或Iterable拆分成具体对象后，依次发送出来
     */
    private static void testFromObservable(){

        Test[] s = {new Test(), new Test()};
        Observable.from(s).subscribe(testObserver());
    }


    private static int i = 0;
    /**
     * 内置抽象类 Subscriber。使用方式一样
     * Observer 先转换成 Subscriber 再使用。
     * {@link #testSubscriber()}
     */
    private static Observer<Test> testObserver() {

        return new Observer<Test>() {
            @Override
            public void onCompleted() {

//                Log.d(TAG, "onCompleted: call");
                System.out.println("true = " + true);
            }

            @Override
            public void onError(Throwable e) {

//                Log.d(TAG, "onError: call");
                System.out.println("e = " + e);
            }

            @Override
            public void onNext(Test s) {

                s.print(i == 0 ? "哈哈" : "嘿嘿");
                i++;
            }
        };
    }


    /**
     * {@link #testObserver()}
     *
     * 增加了onStart方法
     * unSubscribe 方法
     */
    private static Subscriber<String> testSubscriber() {

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        };

        return subscriber;
    }
}
