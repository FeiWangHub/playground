// Import the `io` module from the standard library for handling input and output
use std::io;

// Define a simple struct
struct Rectangle {
    width: u32,
    height: u32,
}

// Implement methods for the `Rectangle` struct
impl Rectangle {
    // Method to calculate the area of the rectangle
    fn area(&self) -> u32 {
        self.width * self.height
    }
}

// Define a generic function that returns the larger of two values
fn max<T: PartialOrd>(a: T, b: T) -> T {
    if a >= b {
        a
    } else {
        b
    }
}

fn main() {
    // Variable declaration and initialization
    let num = 42;
    println!("The number is: {}", num);

    // Mutable variable
    let mut message = String::from("Hello");
    message.push_str(", Rust!");
    println!("The message is: {}", message);

    // Using the struct
    let rect = Rectangle {
        width: 10,
        height: 20,
    };
    println!("The area of the rectangle is: {}", rect.area());

    // Using the generic function
    let max_num = max(10, 20);
    println!("The larger number is: {}", max_num);

    // Handling user input
    println!("Please enter your name:");
    let mut name = String::new();
    io::stdin()
       .read_line(&mut name)
       .expect("Failed to read input");
    println!("Hello, {}!", name.trim());

    // Using a loop
    let mut counter = 0;
    while counter < 5 {
        println!("Counter: {}", counter);
        counter += 1;
    }

    // Using an enum
    enum Color {
        Red,
        Green,
        Blue,
    }
    let favorite_color = Color::Blue;
    match favorite_color {
        Color::Red => println!("You like red"),
        Color::Green => println!("You like green"),
        Color::Blue => println!("You like blue"),
    }
}
