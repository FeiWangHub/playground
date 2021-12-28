# Fei's CSS Kitchen Solution

### 2021-12-27 Update Log
* Improved KitchenDispatcher's thread safe
* Refactor KitchenStrategy from abstract class to interface
* Record PickUp time more accurately
* For FIFO strategy, it now correctly find "earliest arrived courier" and "arbitrary order" 
* In running simulation, use Timer to make sure "Order Ready" and "Courier Arrived" event triggered more precisely
* In final statistics, use milliseconds instead of seconds
* Improved UT

### Build Environment Requirement
For further reference, please consider the following sections:

* Java JDK version 1.8.0_73
* Apache Maven Version 3.3.3

### Build & Run Commands
* Build with Tests: `mvn clean package`
* Run Tests only: `mvn test`
* Run FIFO strategy: `java -jar feisolution-1.0.jar fifo`
* Run Matched strategy: `java -jar feisolution-1.0.jar matched`
* (Main entry class: `FeiSolutionMain.java`)
* (Note: all datetime logged in the console, is "the time when event actually happened", it doesn't print in the order of time for now)

### Result Samples
For interviewer's convenience, I run each strategy 4 times and result as follows:

#### FIFO Results
| # | Average Food Wait Time(ms) | Average Courier Wait Time(ms) |
| :-----: | :----: | :----: |
| 1 | 493 | 766 |
| 2 | 515 | 757 |
| 3 | 728 | 456 |
| 4 | 720 | 470 |

#### Match Results
| # | Average Food Wait Time(ms) | Average Courier Wait Time(ms) |
| :-----: | :----: | :----: |
| 1 | 2303 | 2022 |
| 2 | 2393 | 2166 |
| 3 | 1924 | 2825 |
| 4 | 2212 | 1969 |
