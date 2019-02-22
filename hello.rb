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
