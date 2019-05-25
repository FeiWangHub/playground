#!/usr/bin/ruby
# -*- coding : utf-8 -*-
=begin
多行注释
多行注释
=end

puts "Hello Ruby!";

# 初始化代码块
END{
  puts "---结束程序END代码块---"
}
BEGIN {
  puts "---初始化RUBY代码块Begin---"
}

# 数据类型
123                  # Fixnum 十进制
1_234                # Fixnum 带有下划线的十进制
-500                 # 负的 Fixnum
0377                 # 八进制
0xff                 # 十六进制
0b1011               # 二进制
"a".ord              # "a" 的字符编码
?\n                  # 换行符（0x0a）的编码
12345678901234567890 # 大数
123.4                # 浮点值
1.0e6                # 科学记数法
4E20                 # 不是必需的
4e+20                # 指数前的符号
a1=0
a2=1_000_000 #{带千分符的整形}

# 使用序列 #{ expr } 替换任意 Ruby 表达式的值为一个字符串
name="exp name"
puts name
puts "#{name} ok"

# 数组 array
ary=["---Array练习---:","fred", 10, 3.14, "This is a string", "last element", ]
ary.each do |i|
  puts i
end
names=Array.new(10)
puts names.size
puts names.length

names = Array.new(4, "mac") #重复性赋值
puts "重复性array赋值: #{names}"
nums = Array.new(10){ |e| e=e*2 }
puts "使用代码块给array赋值: #{nums}"
nums = Array.[](1, 2, 3, 4,5)
nums = Array[1, 2, 3, 4,5]
digits = Array(0..9)
puts "使用数字范围给array赋值: #{digits}"
puts "随机访问array位置: #{digits.at(6)}"
digits << 10
digits | [11, 12] #过滤重复元素
digits - [1, 2]
digits.reverse! #反转数组
puts "------------"

# MAP 哈希
months = Hash.new
months = Hash.new("month")
months = Hash.new "month"
puts "map的key不存在返回空 #{months[72]}" #key不存在，返回空
H = Hash["a" => 100, "b" => 200]
H["c"]=300
puts H['a']
puts "#{H['b']}"
keys = H.keys
puts "hash map的keys: #{keys}"
puts H

# 时间 date
time1 = Time.new
puts "---Time时间练习---"
puts "当前时间" + time1.inspect
puts "Time.now:" + Time.now.inspect
puts "把时间输出为array:"
p time1.to_a
# time.year .month .wday .yday .hour .zone
puts "------------"

# range ''..''创建一个包含指定的最高值的范围 和 ''...''三点形式创建一个不包含指定的最高值的范围
puts "---range练习---"
digits = 0..9
puts (1..5)        #==> 1, 2, 3, 4, 5
puts (1...5)       #==> 1, 2, 3, 4
puts ('a'..'d')    #==> 'a', 'b', 'c', 'd'
puts "range转换为array #{(1...10).to_a}"
puts "range内最小值min: #{(1...10).min}"
ret = digits.reject { |i| i<5 }
(1..2).each do |digit|
  puts "在循环中 #{digit}"
end
puts "range内不符合条件的ret有 #{ret}"
# 作为switch语句范围
score = 60
result = case score
when 0..40
  "糟糕分数"
when 41..60
  "快要及格"
else
  "default value"
end
puts result
# 范围检测
if ((1..10)===5)
  puts "5在1..10之间"
end
puts "------------"


# loop 迭代
puts "---loop测试---"
array = [1,2,3,4,5]
array.each do |i|
  puts i
end
# collect迭代器
puts "collect迭代器测试，常用于对数组的每一个元素做小操作（不用于复制数组一般）"
collect = Array.new
collect = array.collect{|x| x*10}
puts collect
puts "------------"

# 文件输入输出
puts "Enter a value:"
# val = gets #输入文件
# put val
print "print"
print "print\n"

# 打开关闭文件
# File.open("filename", "mode") do | afile |
  # process
# end

<<<<<<< HEAD
=======
# 异常处理
begin
  #可能会有异常的代码
  puts "----异常处理测试练习----"
  raise "raise 主动抛出异常"
  puts "raise之后的代码"
rescue #捕获指定类型的异常，默认StandardException
  puts "rescue进入捕捉模块" #$! #表现异常信息 $@ #表示异常出现的代码位置
  # retry #retry语句会从头开始执行begin
else #如果没有异常 执行else
ensure #不管有没有异常，进入此代码块
  puts "ensure模块一定执行block"
end
puts "----异常处理结束----"
puts ""

# Catch Throw异常处理

>>>>>>> 7df56303f0b9df4d3451e544e79928b82aedc42c
def hello
  out = "hello output"
  puts out
end

# here document 多行字符串
print <<EOF
    这是第一种方式创建here document 。
    多行字符串。
EOF

print <<"EOF";                # 与上面相同
    这是第二种方式创建here document 。
    多行字符串。
EOF

print <<`EOC`                 # 执行命令
    echo hi there
    echo lo there
EOC

print <<"foo", <<"bar"          # 您可以把它们进行堆叠
    I said foo.
foo
    I said bar.
bar
