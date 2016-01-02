import rx.Observable;
import rx.observables.ConnectableObservable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
//        f8();
//        f1();
//        f2();
        f3();
//        f4();
//        f5();
//        f6();
//        f7();
    }

    private static void f1() {
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.subscribe(tweet -> System.out.println(tweet));
    }

    public static void f2() {
        List<String> tweets = Arrays.asList("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        Observable<String> obs = Observable.from(tweets);
        obs.subscribe(System.out::println);
    }

    public static Observable<Integer> naturalNumbers(int n) {
        return Observable.create(subscriber -> {
            IntStream.rangeClosed(1, n).forEach(number -> subscriber.onNext(number));
            subscriber.onCompleted();
        });
    }

    public static void f3() {
        Observable<Integer> obs1 = naturalNumbers(10);
        obs1.subscribe(System.out::println);
    }

    public static void f4() {
        List<String> tweets = Arrays.asList("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        Observable<String> observable = Observable.from(tweets);
        observable.subscribe(tweet -> System.out.println("Subscriber 1 >> " + tweet));
        observable.subscribe(tweet -> System.out.println("Subscriber 2 >> " + tweet));
        observable.subscribe(tweet -> System.out.println("Subscriber 3 >> " + tweet));
    }

    public static void f5() throws InterruptedException {
        ConnectableObservable<Long> hotObservable = Observable.interval(1, TimeUnit.SECONDS).publish();
        hotObservable.subscribe(val -> System.out.println("Subscriber 1 >> " + val));
        hotObservable.connect();

        Thread.sleep(5000);

        hotObservable.subscribe(val -> System.out.println("Subscriber 2 >> " + val));

        Thread.sleep(5000);
    }

    public static void f6() {
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.map(tweet -> tweet.length())
                .forEach(System.out::println);
    }

    public static void f7() {
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.flatMap(tweet -> Observable.<String>from(tweet.split("")))
                .forEach(System.out::println);
    }

    public static void f8() {
        Observable<String> tweets = Observable.just("learning RxJava", "Writing blog about RxJava", "RxJava rocks!!");
        tweets.filter(tweet -> tweet.startsWith("RxJava")).forEach(System.out::println);
    }
}
