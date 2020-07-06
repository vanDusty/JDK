# Map.merge()

## 背景

`Map` 中`ConcurrentHashMap`是线程安全的，但不是所有操作都是，例如`get()`之后再`put()`就不是了，这时使用`merge()`确保没有更新会丢失。

**因为`Map.merge()`意味着我们可以原子地执行插入或更新操作，它是线程安全的。**


## 一、源码解析

```java
default V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
    Objects.requireNonNull(remappingFunction);
    Objects.requireNonNull(value);
    V oldValue = get(key);
    V newValue = (oldValue == null) ? value :
               remappingFunction.apply(oldValue, value);
    if(newValue == null) {
        remove(key);
    } else {
        put(key, newValue);
    }
    return newValue;
}
```

该方法接收三个参数，一个 `key` 值，一个 `value`，一个 `remappingFunction` 。如果给定的`key`不存在，它就变成了`put(key, value)`；但是，如果`key`已经存在一些值，我们 `remappingFunction` 可以选择合并的方式:

1. 只返回新值即可覆盖旧值： `(old, new) -> new`;
1. 只需返回旧值即可保留旧值：`(old, new) -> old`;
1. 合并两者，例如：`(old, new) -> old + new`;
1. 删除旧值：`(old, new) -> null`。

## 二、使用场景

`merge()`方法在统计时用的场景比较多，例如：有一个学生成绩对象的列表，对象包含学生姓名、科目、科目分数三个属性，求得每个学生的总成绩。

### 2.1 准备数据

- 学生对象`StudentEntity.java`

```java
@Data
public class StudentEntity {
    /**
     * 学生姓名
     */
    private String studentName;
    /**
     * 学科
     */
    private String subject;
    /**
     * 分数
     */
    private Integer score;
}
```

- 学生成绩数据

```java
private List<StudentEntity> buildATestList() {
    List<StudentEntity> studentEntityList = new ArrayList<>();
    StudentEntity studentEntity1 = new StudentEntity() {{
        setStudentName("张三");
        setSubject("语文");
        setScore(60);
    }};
    StudentEntity studentEntity2 = new StudentEntity() {{
        setStudentName("张三");
        setSubject("数学");
        setScore(70);
    }};
    StudentEntity studentEntity3 = new StudentEntity() {{
        setStudentName("张三");
        setSubject("英语");
        setScore(80);
    }};
    StudentEntity studentEntity4 = new StudentEntity() {{
        setStudentName("李四");
        setSubject("语文");
        setScore(85);
    }};
    StudentEntity studentEntity5 = new StudentEntity() {{
        setStudentName("李四");
        setSubject("数学");
        setScore(75);
    }};
    StudentEntity studentEntity6 = new StudentEntity() {{
        setStudentName("李四");
        setSubject("英语");
        setScore(65);
    }};
    StudentEntity studentEntity7 = new StudentEntity() {{
        setStudentName("王五");
        setSubject("语文");
        setScore(80);
    }};
    StudentEntity studentEntity8 = new StudentEntity() {{
        setStudentName("王五");
        setSubject("数学");
        setScore(85);
    }};
    StudentEntity studentEntity9 = new StudentEntity() {{
        setStudentName("王五");
        setSubject("英语");
        setScore(90);
    }};

    studentEntityList.add(studentEntity1);
    studentEntityList.add(studentEntity2);
    studentEntityList.add(studentEntity3);
    studentEntityList.add(studentEntity4);
    studentEntityList.add(studentEntity5);
    studentEntityList.add(studentEntity6);
    studentEntityList.add(studentEntity7);
    studentEntityList.add(studentEntity8);
    studentEntityList.add(studentEntity9);

    return studentEntityList;
}
```

### 2.2 一般方案

思路：用`Map`的一组`key/value`存储一个学生的总成绩(学生姓名作为`key`,总成绩为`value`)

1. `Map`中不存在指定的`key`时，将传入的`value`设置为`key`的值；
2. 当`key`存在值时,取出存在的值与当前值相加，然后放入`Map`中。

```java
public void normalMethod() {
    Long startTime = System.currentTimeMillis();
    // 造一个学生成绩列表
    List<StudentEntity> studentEntityList = buildATestList();

    Map<String, Integer> studentScore = new HashMap<>();
    studentEntityList.forEach(studentEntity -> {
        if (studentScore.containsKey(studentEntity.getStudentName())) {
            studentScore.put(studentEntity.getStudentName(),
                    studentScore.get(studentEntity.getStudentName()) + studentEntity.getScore());
        } else {
            studentScore.put(studentEntity.getStudentName(), studentEntity.getScore());
        }
    });
    log.info("各个学生成绩:{},耗时：{}ms",studentScore, System.currentTimeMillis() - startTime);
}
```

### 2.3 `Map.merge()`

> 很明显，这里需要采用`remappingFunction`的合并方式。

```java
public void mergeMethod() {
    Long startTime = System.currentTimeMillis();
    // 造一个学生成绩列表
    List<StudentEntity> studentEntityList = buildATestList();
    Map<String, Integer> studentScore = new HashMap<>();
    studentEntityList.forEach(studentEntity -> studentScore.merge(
            studentEntity.getStudentName(),
            studentEntity.getScore(),
            Integer::sum));
    log.info("各个学生成绩:{},耗时：{}ms",studentScore, System.currentTimeMillis() - startTime);
}
```

### 2.4 测试及小结

- 测试方法

```java
@Test
public void testAll() {
    // 一般写法
    normalMethod();
    // merge()方法
    mergeMethod();
}
```

- 测试结果

```xml
00:21:28.305 [main] INFO cn.van.jdk.eight.map.merge.MapOfMergeTest - 各个学生成绩:{李四=225, 张三=210, 王五=255},耗时：75ms
00:21:28.310 [main] INFO cn.van.jdk.eight.map.merge.MapOfMergeTest - 各个学生成绩:{李四=225, 张三=210, 王五=255},耗时：2ms
```

- 结果小结

1. `merger()`方法使用起来在一定程度上减少了代码量，使得代码更加简洁。同时，通过打印的方法耗时可以看出，`merge()`方法效率更高。
1. `Map.merge()`的出现，和`ConcurrentHashMap`的结合，完美处理那些自动执行插入或者更新操作的单线程安全的逻辑.

## 三、总结
