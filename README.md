# IV ASSIGNMENT

[![Build Status](https://travis-ci.org/0x12FD16B/iv-assignment.svg?branch=master)](https://travis-ci.org/0x12FD16B/iv-assignment)
[![codecov](https://codecov.io/gh/0x12FD16B/iv-assignment/branch/master/graph/badge.svg)](https://codecov.io/gh/0x12FD16B/iv-assignment)

## 方法实现入口

`cn.davidliu.iv.assignment.exec.interfaces.IQueryExecutionFacade.query`

## 已经实现功能

- [x] Where 多条件过滤, `AND OR` 逻辑运算符连接表达式, 对象字段和值操作符 ( `=, !=, IN, IS NULL, >=`)
- [x] GroupBy 多个字段, Count 分组函数
- [x] OrderBy 多个字段
- [x] Limit

> 待完善
- [ ] Where 条件中更多对象字段和值操作符, 比如 (`<=, <, >, IS NOT NULL, NOT IN` 等)
- [ ] GroupBy 更多分组函数
- [ ] GroupBy 结果使用 Java 字节码技术 (比如使用 Java assist 库) 生成新对象作为结果集返回
- [ ] OrderBy 排序支持对对象中字段 `null` 作为各个类型的默认值处理
- [ ] 更完善的功能代码测试覆盖率

 

## 执行工程测试

`mvn clean test jacoco:report`

`jacoco` 测试覆盖率报告所在的目录为: maven 生成目录 `target/jacoco-ut/index.html`