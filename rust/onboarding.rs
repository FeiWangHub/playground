// rust onboarding
fn main() {
    println!("*** Hello Rust!!");

    // 1 variables
    let x = 5; // 不可变
    let mut y: i32 = 5; // 可变
    y = 3; // leagle
    let MAX_POINTS: u32 = 100_100;

    // 2 data type
    // integer: i32(default)|u64 etc
    // float: f32|f64(default)
    // bool: true|false(default)
    let integer_default = 100;
    let integer_signed: (i8, i16, i32, i64, i128) = (-1, 2, 3, 4, i128::MAX);
    let integer_unsigned: (u8, u16, u32, u64, u128) = (1, 2, 3, 4, u128::MAX);
    let float_tup: (f32, f64) = (1.0, 2.0);
    let boolean_var: bool = true;

    println!("***Test integer_signed");
    println!("{:?}", integer_signed);

    // 3 Tuple and Array
    println!("***Test Tuple");
    let (a, b, c, d, e) = integer_signed;
    println!("a: {}, b: {}, c: {}, d: {}, e: {}", a, b, c, d, e);
    
    println!("***Test Array");
    let arr = [1, 2, 3, 4, 5];
    let arrFloat :[f32; 5] = [1.0, 2.0, 3.0, 4.0, 5.0]; // implicit type and length
    println!("arrFloat: {:?}", arrFloat);

    // 4 function
    println!("***Test function");
    fn print_hello() {
        println!("Hello, Rust!");
    }
    print_hello();

    fn add(a: i32, b: i32) -> i32 {
        a + b
    }
    println!("test function add: {:?}", add(1, 2));

    // 5 test if else
    println!("***Test if else controll");
    let three = 3;
    if three < 5 {
        println!("smaller")
    } else {
        println!("bigger")
    }

    // 6 test for loop
    println!("***Test for loop");
    let loopArr = ["one", "two", "three"];
    for it in loopArr {
        println!("loop item: {:?}", it)
    }

    // 7 test while loop
    println!("***Test while loop");
    let mut counter = 0;
    while counter < 5 {
        println!("Counter: {}", counter);
        counter += 1;
    }

    // 8 TODO test owner
    
}
