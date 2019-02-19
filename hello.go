package main

import "fmt"

//PublicVar comment
var PublicVar string = "public var"
var age int
var name = 1

//多变量声明
var x, y int
var ( // 这种因式分解关键字的写法一般用于声明全局变量
	a int
	b bool
)
var c, d int = 1, 2
var e, f = 123, "hello"

func main() {
	/* 多行
	注释 */
	// 单行注释
	name := 1 //这种不带声明格式的只能在函数体中出现
	d, e := 1, 2
	var c bool
	fmt.Println("Hello FreeWheel")
	fmt.Println("单变量声明:", name, age, c)
	fmt.Println("多变量声明:", c, d, e, f)
	fmt.Println("取内存地址:", &c)

	var str1 = "测试字符串"
	var str2 = "测试字符串"
	fmt.Println("两个分别初始化的，相同字符串的地址", &str1, &str2)

	var str3 = str1
	fmt.Println("第二个字符串，直接来源于第一个字符串，相同字符串的地址", &str1, &str3)

	//常量const test http://www.runoob.com/go/go-constants.html
	const pwd = "password"

}
