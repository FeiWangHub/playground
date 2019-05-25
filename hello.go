package main

import (
	"errors"
	"fmt"
	"math"
	"time"
	"unsafe"
)

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

const usr = "abc"

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

	//常量const test http://www.runoob.com/go/go-constants.html, 和函数表达式
	const usr, pwd, isAdmin = 123, "password", false
	const (
		mix     = 0
		female  = 1
		male    = 2
		str4    = "12345"
		strLen  = len(str4)
		strSize = unsafe.Sizeof(str4)
	)
	fmt.Println("strLen:", strLen, "strSize:", strSize)

	//特殊常量iota, const 中每新增一行常量声明将使 iota 计数一次(从0开始)
	const (
		a1 = iota //0
		a2 = iota //1
		a3 = iota //2
		a4 = "a4" //iota= 3
		a5        //iota = 4
		a6 = iota // iota =5
	)
	fmt.Println(a1, a2, a3, a4, a5, a6)

	//常量左移实验
	const (
		ii = 1 << iota //左移0位
		j  = 3 << iota //左移1位, 110
		k              //左移2位, 1100
		l              ////左移3位11000
	)
	fmt.Println("i=", ii, "j=", j, "k=", k, "l=", l)

	//算数运算符 +-*/% ++ --
	//关系运算符 == != > < >= <=
	//逻辑运算符 && || !
	//位运算符 &与 |或 ^异或 << >>
	//赋值运算(全都是先计算再赋值) = += -= *= /= %= <<= >>= &= ^= |=
	//其他 &返回变量实际地址 *指针变量

	//条件语句 if, else if, switch, select

	//switch 从上至下, 匹配成功后就不会执行其他 case，如果我们需要执行后面的 case，可以使用 fallthrough
	grade := "B"
	marks := 90
	switch marks {
	case 80:
		grade = "B"
	case 50, 60, 70:
		grade = "C"
	case 90:
		grade = "A"
	default:
		grade = "D"
	}
	fmt.Println("swith语句grade结果:", grade)

	//switch: type swith
	var x interface{}

	switch i := x.(type) {
	case nil:
		fmt.Println("x的类型是", i)
	case int:
		fmt.Println("x是int型")
	case bool, string, func(int):
		fmt.Println("x是bool, string, func(int)")
	default:
		fmt.Println("未知")
	}

	//switch: fallthrough 即使匹配了，也会接着向下执行
	switch true {
	case true:
		fmt.Println("1 case 为true")
		fallthrough
	case false:
		fmt.Println("2 case 为false")
		fallthrough
	default:
		fmt.Println("default case")
	}

	/* loop 循环语句 for, break, continue, goto
		类似for循环：for init; condition; post { }
		类似while循环: for condition {}
		类似迭代list：for {}
	  执行循序：先init，再check condition，true则执行body；然后再contidon -> body循环
	*/
	for a := 0; a < 5; a++ {
		fmt.Println("for循环,a=", a)
	}

	var loop1 int
	var loop2 = 5
	for loop1 < loop2 {
		loop1++
		if loop1 > 4 {
			break //跳出循环直接终止
		}
		fmt.Printf("while循环,loop1=%d", loop1)
	}

	numbers := [6]int{1, 2, 3, 5}
	for i, x := range numbers {
		if i == 3 {
			continue //跳过i=3
		}
		fmt.Printf("第 %d 位 x 的值 = %d\n", i, x)
	}

	//goto label 无条件到指定label位置，不建议使用，代码不好调试理解
	for i := 0; i < 5; i++ {
		if i == 3 {
			goto LABEL
		}
		fmt.Printf("i=%d测试go to label\n", i)
	}
LABEL:
	fmt.Println("这是一个label")

	//函数调用(默认值传递)
	fmt.Printf("函数调用max结果：%d\n", max(7, 9))
	result1, result2 := swap("first", "second")
	fmt.Println("多返还函数swap:", result1, result2)

	//函数引用传递
	var x2, y2 = 100, 200
	swapIntRef(&x2, &y2)
	fmt.Println("函数引用传递，交换后x2,y2分别为", x2, y2)

	//函数变量
	getSquareRoot := func(x float64) float64 {
		return math.Sqrt(x)
	}
	fmt.Println("调用函数变量getSquareRoot", getSquareRoot(9))

	//调用go方法
	var c1 Circle
	c1.radius = 10
	fmt.Println("调用go的方法，圆面积为:", c1.getArea())

	//调用闭包
	nextNum := getSequence()
	fmt.Println("打印闭包getSequence的输出", nextNum(), nextNum(), nextNum())
	nextNum1 := getSequence()
	fmt.Println("打印new闭包getSequence的输出", nextNum1(), nextNum1(), nextNum1())

	//数组练习 var variable_name [SIZE] variable_type
	var balance [10]float32 //单纯初始化
	fmt.Println("单纯初始化数组：", balance)
	balance = [10]float32{1000.0, 2.0, 3.4, 7.0, 50.0}
	fmt.Println("赋值后的数组：", balance)
	var autoArray = [...]int{1, 2, 3}
	fmt.Println("自动指定size的数组：", autoArray)
	fmt.Println("随机访问数组index1：", autoArray[1])
	for index := 0; index < 10; index++ {
		balance[index] = float32(index) + float32(100) /* 设置元素为 i + 100 */
	}
	fmt.Println(balance)

	//向函数传递数组练习
	var arr = []int{100, 2, 3, 14, 7, 50}
	var avg = getAverage(arr, len(arr))
	fmt.Println("向函数传递数组练习, getAverage:", avg)

	//pointer指针练习
	var ip *int /* 指向整型*/
	var intValue = 3
	ip = &intValue //指向intValue地址
	fmt.Printf("原始int数据地址:%d, pointer地址:%d, pointer的值为:%d\n",
		&intValue, ip, *ip)
	fmt.Println("判断ip是否是空指针:", ip == nil)

	//指针的数组练习
	var arr5 = [5]int{100, 2, 3, 14, 7}
	var ptr [len(arr5)]*int
	fmt.Println("指针数组的练习:")
	for index := 0; index < len(arr5); index++ {
		ptr[index] = &arr5[index] /* 整数地址赋值给指针数组 */
		fmt.Printf("a[%d] = %d\n", index, *ptr[index])
	}

	//结构体struct练习
	book1 := Books{"Go语言", "www.go.org", "trainning", 142521}
	book2 := Books{title: "title", author: "author", subject: "subject"}
	fmt.Println("结构体练习", book1)
	fmt.Println("结构体练习K-V，忽略字段为0或空", book2)
	book1.author = "修改过的author"
	fmt.Printf("修改过的book1的author为%s\n", book1.author)
	//结构体做参数
	printBook(book1)

	//切片slice练习，对数组的抽象
	var capacity = 10
	var slice1 []int = make([]int, 5, capacity)
	slice3 := []int{1, 2, 3}
	var arr6 = [6]int{1, 2, 3, 4, 5, 6}
	slice4 := arr6[0:1] //slice的end index
	printSlice(slice1)
	printSlice(slice3)
	printSlice(slice4)
	//append & copy slice
	slice4 = append(slice4, 999)
	printSlice(slice4)
	slice5big := make([]int, len(slice4), cap(slice4)*3)
	copy(slice5big, slice4)
	printSlice(slice5big)

	//range练习
	nums := []int{2, 3, 4}
	sum := 0
	for _, num := range nums {
		sum += num
	}
	fmt.Println("用range循环slice", sum)

	kvs := map[string]string{"a": "apple", "b": "banana"}
	for k, v := range kvs {
		fmt.Printf("用range循环map, k=%s, v=%s\n", k, v)
	}

	//map练习
	var countryCapMap map[string]string     //初始化是nil map
	countryCapMap = make(map[string]string) //make之后才可以使用
	countryCapMap["France"] = "paris"
	countryCapMap["Italy"] = "罗马"
	countryCapMap["Japan"] = "Tokyo"
	for k, v := range countryCapMap {
		fmt.Println("循环map,", k, "首都是", v)
	}
	//查看元素是否存在
	fmt.Println("尝试去一个map中没有的key：", countryCapMap["美国"])
	//delete 元素
	delete(countryCapMap, "Italy")
	fmt.Println("delete删除italy之后的map:", countryCapMap)

	//语言递归练习http://www.runoob.com/go/go-recursion.html
	fmt.Println("递归练习.fibonacci of 10", fibonacci(10))
	for i := 0; i < 10; i++ {
		fmt.Printf("%d\t", fibonacci(i))
	}
	fmt.Println()

	//interface 接口
	var phone Phone
	phone = new(IPhone)
	phone.call()
	phone = new(NokiaPhone)
	phone.call()

	//exception 错误处理
	if _, err := sqrt(-1); err.Error() != "" {
		fmt.Println("errMsg is: ", err)
	}

	//并发 goroutine : go 函数名 {参数列表} 同一个程序中的所有 goroutine 共享同一个地址空间
	fmt.Println("test go routine")
	go say("hello") //一个新的线程
	say("world")
	//channel 通道 用来传递数据的一个数据结构，用于两个goroutine之间通讯
	chanArr := []int{7, 2, 8, -9, 4, 0}
	channel := make(chan int)
	go sumChan(chanArr[:len(chanArr)/2], channel)
	go sumChan(chanArr[len(chanArr)/2:], channel)
	chanx, chany := <-channel, <-channel
	fmt.Println("sumChan通道sum输出结果:", chanx, chany, chanx+chany)

	//通道缓冲区域，如果缓存满了，发送方被阻塞
	chanBuffer := make(chan int, 5)
	chanBuffer <- 2 //因为 ch 是带缓冲的通道，我们可以同时发送两个数据,不用立刻同步读取数据
	chanBuffer <- 1
	fmt.Println("通道缓存数据读取", <-chanBuffer, <-chanBuffer)

	//遍历通道 & 关闭通道 v, ok := <-ch, close(chan)
	// chanBuffer <- 5
	// chanBuffer <- 5
	// chanBuffer <- 5
	// for index := range chanBuffer {
	// 	fmt.Println("channel buffer loop:", index)
	// }
	// close(chanBuffer)

	// 测试struct的父子类pointer
	type Human struct {
		name string
		age  int
	}

	type Dev struct {
		Human
		lang string
	}
}

//函数func function_name( [parameter list] ) [return_types] {函数体}
func max(num1, num2 int) int {
	var result int
	if num1 > num2 {
		result = num1
	} else {
		result = num2
	}
	return result
}

//函数多返还
func swap(a, b string) (string, string) {
	return b, a
}

//函数引用传递
func swapIntRef(a *int, b *int) {
	var tmp int
	tmp = *a //a是一个pointer，*a是指向的数值
	*a = *b
	*b = tmp
}

/*Circle go语言方法：
一个方法就是一个包含了接受者的函数，接受者可以是命名类型或者结构体类型的一个值或者是一个指针
一个定义在a对象之外的，a对象的函数*/
type Circle struct {
	radius float64
}

//Books 结构体练习
type Books struct {
	title   string
	author  string
	subject string
	bookID  int
}

func (c Circle) getArea() float64 {
	//c.radius 即为 Circle 类型对象中的属性
	return 3.14 * c.radius * c.radius
}

//闭包函数
func getSequence() func() int {
	i := 0
	return func() int {
		i++
		return i
	}
}

//向函数传递数组
func getAverage(arr []int, size int) float32 {
	var i int
	var avg, sum float32
	for i = 0; i < size; i++ {
		sum += float32(arr[i])
	}
	avg = sum / float32(size)
	return avg
}

//结构体做参数
func printBook(book Books) {
	var ptr = &book
	fmt.Println("结构体做参数printBook测试，指针地址：", ptr)
	fmt.Printf("Book title : %s\n", book.title)
	fmt.Printf("Book author : %s\n", book.author)
	fmt.Printf("Book subject : %s\n", book.subject)
	fmt.Printf("Book bookID : %d\n", book.bookID)
}

//slice
func printSlice(x []int) {
	fmt.Printf("len=%d, cap=%d, slice=%v\n", len(x), cap(x), x)
}

//递归 recursion 斐波那契数列
func fibonacci(n int) int {
	if n < 2 {
		return n
	}
	return fibonacci(n-2) + fibonacci(n-1)
}

//interface
type Phone interface {
	call()
}

type NokiaPhone struct{}

func (nokiaPhone NokiaPhone) call() {
	fmt.Println("Calling from Nokia Phone")
}

type IPhone struct{}

func (iphone IPhone) call() {
	fmt.Println("Calling from iphone")
}

//error 错误处理
func sqrt(f float32) (float32, error) {
	if f < 0 {
		return 0, errors.New("math square root of negative number")
	}
	return f, errors.New("")
}

//go goroutine
func say(s string) {
	for i := 0; i < 5; i++ {
		time.Sleep(10 * time.Millisecond)
		fmt.Println(s)
	}
}

//channel <-用于追定通道方向
func sumChan(s []int, c chan int) {
	sum := 0
	for _, v := range s {
		sum += v
	}
	c <- sum //把sum发送到通道c
}
