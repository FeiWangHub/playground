# Fei's CSS Kitchen Solution

### Build Environment Requirement
For further reference, please consider the following sections:

* Java JDK version 1.8.0_73
* Apache Maven Version 3.3.3

### Build & Run Commands
* Build with Tests: `mvn clean package`
* Run Tests only: `mvn test`
* Run FIFO strategy: `java -jar feisolution-1.0.jar fifo`
* Run Matched strategy: `java -jar feisolution-1.0.jar matched`

### Result Samples
For interviewer's convenience, I run each strategy 4 times and result as follows:

#### FIFO Results
| # | Average Food Wait Time | Average Courier Wait Time |
| :-----: | :----: | :----: |
| 1 | 0.36363637 | 0.8484849 |
| 2 | 0.6060606 | 0.45454547 |
| 3 | 0.41666666 | 0.97727275 |
| 4 | 0.75757575 | 0.50757575 |

#### Match Results
| # | Average Food Wait Time | Average Courier Wait Time |
| :-----: | :----: | :----: |
| 1 | 1.8636364 | 2.3181818 |
| 2 | 2.371212 | 1.8333334 |
| 3 | 1.7575758 | 2.3863637 |
| 4 | 2.1969697 | 2.2878788 |
