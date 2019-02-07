package com.example.aotuman.network.rxjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.BooleanSupplier;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 参考 https://juejin.im/post/5b17560e6fb9a01e2862246f
 */
public class TestMain {

    public static void main(String[] args) {
        repeat();
        try {
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void method1() {
        // 1.创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("=========================currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        });

        // 2.创建观察者
        Observer observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("======================onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("======================onNext " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("======================onError");
            }

            @Override
            public void onComplete() {
                System.out.println("======================onComplete");
            }
        };

        // 3.订阅
        observable.subscribe(observer);
    }

    /**
     * 链式调用
     */
    public static void method2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("=========================currentThread name: " + Thread.currentThread().getName());
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("======================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("======================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("======================onError");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("======================onComplete");
                    }
                });
    }

    public static void just() {
        Observable.just(1, 2, 3)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("=================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("=================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=================onComplete ");
                    }
                });

    }

    public static void fromArray() {
        Integer array[] = {1, 2, 3, 4};
        Observable.fromArray(array)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("=================onSubscribe");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("=================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=================onComplete ");
                    }
                });
    }

    public static void map() {
        Observable.just(1, 2, 3)
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "I'm " + integer;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("===================onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("===================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("=================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("=================onComplete ");
                    }
                });
    }

    public static List<Person> buildData() {
        List<Person> personList = new ArrayList<>();

        List<Plan> planList01 = new ArrayList<>();
        List<String> actionList1 = new ArrayList<>();
        actionList1.add("写代码");
        actionList1.add("写文章");
        Plan plan1 = new Plan();
        plan1.setTime("星期一");
        plan1.setActionList(actionList1);
        plan1.setContent("星期一要做的事情");

        List<String> actionList2 = new ArrayList<>();
        actionList2.add("打篮球");
        actionList2.add("踢足球");

        Plan plan2 = new Plan();
        plan2.setTime("星期二");
        plan2.setActionList(actionList2);
        plan2.setContent("星期二要做的事情");

        planList01.add(plan2);
        Person xiaoming = new Person("小明", planList01);
        personList.add(xiaoming);


        List<Plan> planList02 = new ArrayList<>();
        List<String> actionList3 = new ArrayList<>();
        actionList3.add("写代码");
        actionList3.add("写文章");
        Plan plan3 = new Plan();
        plan3.setTime("星期三");
        plan3.setActionList(actionList3);
        plan3.setContent("星期三要做的事情");

        List<String> actionList4 = new ArrayList<>();
        actionList4.add("打羽毛球");
        actionList4.add("踢毽子");

        Plan plan4 = new Plan();
        plan4.setTime("星期四");
        plan4.setActionList(actionList4);
        plan4.setContent("星期四要做的事情");

        planList02.add(plan4);
        Person xiaohong = new Person("小红", planList02);
        personList.add(xiaohong);
        return personList;
    }

    /**
     * 现在有一个需求就是要将 Person 集合中的每个元素中的 Plan 的 action 打印出来
     * 可以看到 onNext() 用了嵌套 for 循环来实现，如果代码逻辑复杂起来的话，可能需要多重循环才可以实现。
     */
    public static void map2() {
        List<Person> personList = buildData();

        Observable.fromIterable(personList)
                .map(new Function<Person, List<Plan>>() {
                    @Override
                    public List<Plan> apply(Person person) throws Exception {
                        return person.getPlanList();
                    }
                })
                .subscribe(new Observer<List<Plan>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Plan> plans) {
                        for (Plan plan : plans) {
                            List<String> planActionList = plan.getActionList();
                            for (String action : planActionList) {
                                System.out.println("==================action " + action);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 现在有一个需求就是要将 Person 集合中的每个元素中的 Plan 的 action 打印出来
     * 只需要两个 flatMap() 就可以完成需求，并且代码逻辑非常清晰。
     */
    public static void flatMap() {
        List<Person> personList = buildData();

        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .flatMap(new Function<Plan, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Plan plan) throws Exception {
                        return Observable.fromIterable(plan.getActionList());
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("==================action " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void delayFlatMap() {
        List<Person> personList = buildData();

        Observable.fromIterable(personList)
                .flatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        if ("小明".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {
                        System.out.println("==================plan " + plan.getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * concatMap() 和 flatMap() 基本上是一样的，只不过 concatMap() 转发出来的事件是有序的，而 flatMap() 是无序的。
     */
    public static void concatMap() {
        List<Person> personList = buildData();

        Observable.fromIterable(personList)
                .concatMap(new Function<Person, ObservableSource<Plan>>() {
                    @Override
                    public ObservableSource<Plan> apply(Person person) {
                        if ("小明".equals(person.getName())) {
                            return Observable.fromIterable(person.getPlanList()).delay(10, TimeUnit.MILLISECONDS);
                        }
                        return Observable.fromIterable(person.getPlanList());
                    }
                })
                .subscribe(new Observer<Plan>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Plan plan) {
                        System.out.println("==================plan " + plan.getContent());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 可以将多个观察者组合在一起，然后按照之前发送顺序发送事件。
     * concat() 最多只可以发送4个事件。
     */
    public static void concat() {
        Observable.concat(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 可以将多个观察者组合在一起，然后按照之前发送顺序发送事件。
     * concatArray() 可以发送多于 4 个被观察者
     */
    public static void concatArray() {
        Observable.concatArray(Observable.just(1, 2),
                Observable.just(3, 4),
                Observable.just(5, 6),
                Observable.just(7, 8),
                Observable.just(9, 10),
                Observable.just(11, 12))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 这个方法月 concat() 作用基本一样，
     * concat() 是串行发送事件，而 merge() 并行发送事件。
     */
    public static void merge() {
        Observable.merge(
                Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "A" + aLong;
                    }
                }),
                Observable.interval(1, TimeUnit.SECONDS).map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return "B" + aLong;
                    }
                }))
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("=====================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 在 concatArray() 和 mergeArray() 两个方法当中，如果其中有一个被观察者发送了一个 Error 事件，那么就会停止发送事件，
     * 如果你想 onError() 事件延迟到所有被观察者都发送完事件后再执行的话，
     * 就可以使用  concatArrayDelayError() 和 mergeArrayDelayError()
     */
    public static void concatArrayDelayError() {

        Observable.concatArrayDelayError(
                Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                        e.onNext(1);
                        e.onError(new NumberFormatException());
                    }
                }),
                Observable.just(2, 3, 4))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("===================onError ");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 将数据以一定的逻辑聚合起来。
     * eg:1+2+3+4+5
     */
    public static void scan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        System.out.println("====================apply ");
                        System.out.println("====================integer " + integer);
                        System.out.println("====================integer2 " + integer2);
                        return integer + integer2;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("====================accept " + integer);
                    }
                });

    }

    /**
     * 与 scan() 操作符的作用也是将发送数据以一定逻辑聚合起来，
     * 这两个的区别在于 scan() 每处理一次数据就会将事件发送给观察者，
     * 而 reduce() 会将所有数据聚合在一起才会发送事件给观察者。
     */
    public static void reduce() {
        Observable.just(0, 1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer, Integer integer2) throws Exception {
                        int res = integer + integer2;
                        System.out.println("====================integer " + integer);
                        System.out.println("====================integer2 " + integer2);
                        System.out.println("====================res " + res);
                        return res;
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("==================accept " + integer);
                    }
                });

    }

    /**
     * 在发送事件之前追加事件，startWith() 追加一个事件，startWithArray() 可以追加多个事件。追加的事件会先发出。
     */
    public static void startWithArray() {
        Observable.just(5, 6, 7)
                .startWithArray(2, 3, 4)
                .startWith(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("================accept " + integer);
                    }
                });

    }

    /**
     * Observable 每发送一件事件之前都会先回调这个方法
     */
    public static void doOnEach() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                //      e.onError(new NumberFormatException());
                e.onComplete();
            }
        })
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        System.out.println("==================doOnEach " + integerNotification.getValue());
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });

    }

    /**
     * Observable 每发送 onNext() 之前都会先回调这个方法。
     * 同理，doAfterNext 每发送 onNext() 之后都会先回调这个方法。
     * doOnComplete, doOnError, doOnSubscribe, doOnDispose 也是一样
     */
    public static void doOnNext() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("==================doOnNext " + integer);
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });

    }

    /**
     * 如果出现错误事件，则会重新发送所有事件序列。times 是代表重新发的次数。
     */
    public static void retry() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Exception("404"));
            }
        })
                .retry(2)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });

    }

    private static int i = 0;

    /**
     * 出现错误事件之后，可以通过此方法判断是否继续发送事件。
     */
    public static void retryUntil() {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onError(new Exception("404"));
            }
        })
                .retryUntil(new BooleanSupplier() {
                    @Override
                    public boolean getAsBoolean() throws Exception {
                        if (i == 6) {
                            return true;
                        }
                        return false;
                    }
                })
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        i += integer;
                        System.out.println("==================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError ");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });

    }

    /**
     * 当被观察者接收到异常或者错误事件时会回调该方法，这个方法会返回一个新的被观察者。
     * 如果返回的被观察者发送 Error 事件则之前的被观察者不会继续发送事件，
     * 如果发送正常事件则之前的被观察者会继续不断重试发送事件。
     */
    public static void retryWhen() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("chan");
                e.onNext("ze");
                e.onNext("de");
                e.onError(new Exception("404"));
                e.onNext("haha");
            }
        })
                .retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                                if (!throwable.toString().equals("java.lang.Exception: 404")) {
                                    return Observable.just("可以忽略的异常");
                                } else {
                                    return Observable.error(new Throwable("终止啦"));
                                }
                            }
                        });
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("==================onSubscribe ");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("==================onNext " + s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("==================onError " + e.toString());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("==================onComplete ");
                    }
                });

    }

    public static void repeat() {
        Observable.create(new ObservableOnSubscribe < Integer > () {
            @Override
            public void subscribe(ObservableEmitter < Integer > e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onComplete();
            }
        })
                .repeat(2)
                .subscribe(new Observer < Integer > () {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println( "===================onSubscribe ");
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("===================onNext " + integer);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("===================onComplete ");
                    }
                });

    }
}
