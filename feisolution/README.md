# Fei's CSS Kitchen Solution

### 更新改版日志
* 重新梳理KitchenDispatcher的线程安全
* 输出的统计时间单位 从秒改为毫秒
* 改进策略模式的代码结构，将KitchenStrategy从抽象类改为接口
* 修复UT中assertEquals(expected, actual)的参数前后顺序
* pickup的时间记录更加精准，不在受测试运行的interval影响
* FIFO模式时，现在会正确的匹配"最早到来的courier"或"随机的order"
* 使用Timer，让order-ready和courier-arrived等事件，在相对准确的时间点上发生

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
