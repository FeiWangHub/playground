#!/bin/bash
echo "Hello Bash! 2020.01.29"

#vairable
name="Fei"
# list all name : for file in `ls /etc`
echo $name
echo ${name}

for skill in Salsa Bachata Kizomba; do
    echo "I am master at ${skill} dancing!"
done

#connect string
title="bachata king ${name}"
echo $title
echo "length of title: ${#title}"
echo "substring: ${title:9:15}"

#search_string="where is bachata king?"
#echo `expr index "$search_string" king`

#array
array1=(1 2 3 4)
array1[4]=5
echo "print array all ${array1[@]}"
echo "print array all ${array1[1]}"
echo "lenth of array ${#array1[*]}"

#pass variable
echo "/nShell passing variable"
echo "Script filename: $0";
echo "Total num of passed param: $#";
echo "First Param: $1";
echo "Second Param: $2";
echo "Script PID is: $$";
# $* is one whole string; $@  as array
echo "Show passed params as string: $*"
echo "-- \$@ deomo pring each var --"
for i in "$@"; do
    echo $i
done

#calcualtion in shell
result=`expr 2 + 2`
echo "两数之和: $result" 
a=10
b=20
val=`expr $b % $a`
echo "a % b is: $val"
if [ $a > $b ]
then
    echo "a > b"
else
    echo "a < b"
fi
echo "could also use -eq -ne -gt -lt -ge -le, -o is ||, -a is &&"

if [ $a < $b -a $a == 10 ]
then
    echo "a<b and a==10 is true"
fi

if [[ $a -lt 100 && $b -gt 100 ]]
then
   echo "返回 true"
else
   echo "返回 false"
fi

echo ""
echo "now string operation = != -z -n str"
a="abc"
b="efg"
if [ $a != $b ]
then
    echo "string a != b"
fi

if [ -z $a ]
then
   echo "-z $a : 字符串长度为 0"
else
   echo "-z $a : 字符串长度不为 0"
fi

file="/Users/Fei/Dev/SVNRepos/FeiGitBucket/playground/hellorReact.html"
echo "file operation to detect file permissions"
if test -e $file 
then
  echo "./helloReact.html is readable"
fi

echo -e " \nchange line before and after\n"

echo run command date:
echo `date`

#printf
printf "%-10s %-8s %-4s\n" 姓名 性别 体重kg  
printf "%-10s %-8s %-4.2f\n" 郭靖 男 66.1234 
printf "%-10s %-8s %-4.2f\n" 杨过 男 48.6543 
printf "%-10s %-8s %-4.2f\n" 郭芙 女 47.9876 

echo `pwd`
if test -e ./helloReact.html
then
    echo "hellorReact.html file existed"
else
    echo helloReact.html fild not exist
fi

echo -e "\nNow is function in shell"
demoFun(){
  echo first param is $1
  echo second param is $2
  echo total number of params is: $*
  echo sum of number is `expr $1 + $2` 
}
demoFun 1 3 5

#input output
echo -e \n export output of demoFun into shell_output.txt
demoFun 1 3 5 > shell_output.txt
echo count words of output file: `wc < shell_output.txt` 
rm shell_output.txt

echo -e \nshell could run/import another shell!
#. ./xxx.sh then you can use the other shell

