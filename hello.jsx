"use strict";
/**
 * Created by Fei on 2019/5/25.
 */

function displayDate() {
    return Date();
}

console.log(displayDate());
console.log(__filename);

//setTimeout(function () {
//    console.log('test timeout');
//    console.log(displayDate());
//}, 100);

console.log(process.version);
console.log(process.cwd());

//常用工具 继承
var util = require('util');

//任意对象转换字符串
function Person() {
    this.name = 'byvoid';
    this.toString = function() {
        return this.name;
    };
}
var person = new Person();
console.log(util.inspect(person));
console.log(util.inspect(person, true));

console.log("util.isArray", util.isArray([]));
console.log("util.isArray", util.isArray({}));
util.isDate("a");
util.isError("a");

// Array
console.log("\n!!!!!!! ARRAY !!!!!!\n");
var cars=new Array();
cars[0]="Saab";
cars[1]="Volvo";
cars[2]="BMW";
console.log("array.slice:", cars.slice(0,2)); //2 not include
console.log("array.some:", cars.some( (i) => i=="BMW"));
var spliceIndex = 2, spliceNumber = 1;
console.log("array.splice:", cars.splice(spliceIndex, spliceNumber, "newcar"));//add or delete, return the deleted
console.log("array after splice:", cars);
cars.unshift("unshift");//向开头增加元素
cars.push("push");//结尾增加元素
console.log("unshift & push:", cars);//起始/END位置添加新元素
console.log("pop结尾删除元素: ", cars.pop());
console.log("shift开头删除元素: ", cars.shift());
console.log("reverse: ", [1,2,3,4,5].reverse());
console.log("Array.isArray(): ", Array.isArray([]));
console.log("concat连接数组: ", ["a"].concat(["b"], ["c"]));
console.log("every()是否每个都符合条件", [1,2,3].every( (i) => i>0 ));
console.log("fill替换所有或部分元素:", [1,2,3,4,5].fill(0, 2, 4));
console.log("filter()返回过滤后元素:", [1,2,3,4].filter( (i, index)=> i>2 ));
console.log("find()找到符合的第一个元素", [1,2,3,4].find( i=> i>2 ));
console.log("findIndex()找到符合的第一个index", [1,2,3,4].findIndex( i=> i>2 ));
var temp=0;
[1,2,3].forEach( (i, index, arrs) => {temp =temp+i; return temp;} );
console.log("用foreach来sum: ", temp);
console.log("Array.from()字符串变char[]: ", Array.from("FEIWANG"));
console.log("Array.from(Array, func)字符串变char[]并小写: ", Array.from("FEIWANG", (i)=> i.toLowerCase()));
console.log("arr.includes(item, index)", [1,2,3].includes(2, 0));//param: search element & index
console.log("arr.indexOf(element):", [1,2,3,4].indexOf(4));
console.log("arr.join(seperator): ", [1,2,3,4].join("-"));
console.log("arr.keys():返还数字的keys() ", [1,2,3,4].keys());
console.log("arr.lastIndexOf(Item), ", [1,2,3,1,1].lastIndexOf(1));
console.log("string.lastindexof()", "aabbcc".lastIndexOf("bb"));//return the begin index of item
console.log("array.map(()=> xx): ", [1,2,3,4].map( i=>i*i ));


//SORT!!数组排序 mutator
var arr = [12, 55, 8];
//es5写法
console.log('默认排序sort ASC:', arr.sort());
arr.sort(function (n1, n2) {
    return n1 - n2;
});
//es6写法
arr.sort((n1, n2) => n1 - n2);
console.log("数组排序", arr);


// !!! JSON object
var human = {
    firstname: "John",
    lastname: "Doe",
    id: 5566,
    isHuman: true
};
console.log("JSON取值: ", human.lastname);
console.log("JSON取值2: ", human["lastname"]);
console.log("JSON.parse字符串转对象: ", JSON.parse('{"json": true}'));
console.log("JSON.stringify对象转字符串:", JSON.stringify(human));

//for loop array 循环 数组
var arr = [1,2,3];
for(var i in arr) {
    console.log(i);      //0 1  2
    console.log(arr[i]);  //12  5  8
}

//循环json数据
var json = {a:12,b:5,c:8};
delete json.a;
for(var name in json){
    console.log("Loop Json ",name, " : ", json[name]);  //a  b c
}

//!!!MAP!!!

var map = new Map();
var m = new Map([['XiaoMing', 99], ['XiaoHong', 66]]);//MAP构造
console.log("MAP.has: ", m.has('XiaoMing'));
map.set('a', 'apple');
map.set('b', 'banana');
map.set('c', 'orange');
console.log(map);
console.log('map.get', map.get('b'));
map.delete('a');
console.log('map.get deleted a', map.get('a'));
console.log('map.size ', map.size);
//console.log('map entries:', map.entries());
for (var i of map){
    console.log('looping map:', i)
};
//遍历map的 key or values
//console.log(map.values());
//console.log(map.keys());
for ( var i of map.values()){
    console.log('looping map values: ', i);
}
for(let [key,value] of map.entries()){
    console.log(key, value);   //a apple     b banana
}

//!!! SET !!!
var s1 = new Set();
s1.add(1); s1.add(1);
s1.add(2);
s1.delete(3);
console.log("Set.has: ", s1.has(1));
console.log('length/size of set', s1.size);
var s1array = [];
for (let item of s1){
    s1array.push(item);
}
console.log("遍历Set: ", s1array);

//Math!!
//SUM!! by reduce
var sumResult = [1,2,3].reduce(function(pv, cv) { return pv + cv; }, 0);
var sumResult2 = [1,2,3].reduce( (total, x) => total+ x, 0 );
console.log("sum:", sumResult);
console.log("sum2:", sumResult2);
//console.log('SUM', sum([1,2,3]));
function sumArr(arr) {
    return arr.reduce( (total, x) => total+ x, 0 );
}
console.log("Math.max(1,2,3,4,5): ", Math.max(1,2,3,4,5));
console.log("Math.min(1,2,3,4,5): ", Math.min(1,2,3,4,5));
console.log("Math.random(): ", Math.random()); // between 1-0
console.log("Math.ceil(): ", Math.ceil(1.5)); //天花板
console.log("Math.floor(): ", Math.floor(1.5)); //floor


// STRING 字符串!
var str = "FEIWANG";
console.log("String.charAt():", "FEIWANG".charAt(0));
str.length;
console.log("string.concat(): ", "Fei".concat(" wang", " concat"));
console.log("string.repeat(n): ", "Fei".repeat(3));
console.log("string.includes(str, startIndex)", "FeiWAng".includes("Fei", 0));
console.log("string.replace(target, replaceTo)单独替换:", "FeiFeiFei".replace("e", "i"));//第一个only
console.log("string.replace(target, replaceTo)delete删除某个字符:", "FeiFeiFei".replace("e", ""));//第一个only
console.log("string.replace(targetReg, replaceTo)全局替换:", "FeiFeiFei".replace(/e/g, "i"));//第一个only
console.log("string.replace(targetReg, replaceTo)全局替换忽略大小写:", "FeiFeiFei".replace(/f/gi, "i"));//第一个only
console.log("string.slice(start, end): ", "12345".slice(1,4));
console.log("string.split(seperator, limit): ", "hello world hi hi".split(" "));
console.log("string.startsWith(str): ", "hey hey hey".startsWith("hello"));
console.log("string.substr(start_index, length)", "123456".substr(1, 3));
console.log("string.substring(start, end): ", "123456".substring(0,3));
console.log("string.trim(): ", " 123456 ".trim());
console.log("string to chars .split()", String("string").split(""));

//ES6的对象 OBJECT 和继承
class Person1{
    constructor(name,age=25){  //可以给属性初始值或默认值,正常es的function函数也可以给默认值
        this.name = name;
        this.age = age;
    }
    showName(){
        return this.name;
    }
    showAge(){
        return this.age;
    }
}

var p1 = new Person1('alice',18);
console.log("ES6 object:", (p1.showAge()));

//类型转换
console.log("num tostring: ", (1).toString(), String(1));
console.log("string to number: ", Number("3.15"));
console.log("string to number: parseInt: ", parseInt("1", 10));
console.log("boolean to string: ", true.toString());
console.log("boolean to number: ", Number(false));

//Random
console.log("typeof array", typeof [1,2,3]);
console.log("typeof 1:", typeof 1);