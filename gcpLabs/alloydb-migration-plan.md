# AlloyDB Migration Project Plan & Benefit Report (English)

## 1. Executive Summary
This report provides a comprehensive analysis and project plan for migrating an existing Cloud SQL for PostgreSQL instance to AlloyDB for PostgreSQL. Based on current performance metrics (P99 CPU 41.9%, Disk 13.3%) and your specific workload pattern (8 hours/day, 5 days/week), there is an exceptional opportunity for cost optimization through **AlloyDB's Always-On "Low-Power Standby" model**.

## 2. Current State Analysis
### Source Instance: Cloud SQL for PostgreSQL
- **Edition:** Enterprise Plus (High Availability)
- **vCPU:** 96
- **Memory:** 768 GB
- **Storage:** 12 TB SSD (Provisioned)
- **Region:** `europe-west-2` (London)
- **Workload Pattern:** Busy for ~40 hours/week (8h/day, 5d/week); Idle for ~128 hours/week.
- **Current Requirement:** Instance must remain online and responsive 24/7 to handle any incoming traffic immediately.

### Key Metrics Observations (Last 30 Days)
- **CPU Utilization:** P99 is ~41.9%, but P50 is only ~1.18%. This confirms the massive over-provisioning during idle periods.
- **Disk Utilization:** Only ~13.3% used (1.6 TB). Cloud SQL's provisioned storage model charges for the full 12TB.

## 3. Cost Savings Analysis: Low-Power Standby
### Cost Estimation (Region: `europe-west-2`, Currency: USD)

| Component | Cloud SQL (As-Is) | AlloyDB (Fixed 64vCPU) | AlloyDB (Low-Power Standby) |
| :--- | :--- | :--- | :--- |
| **Compute (vCPU/RAM)** | ~$44,290 / Month | ~$36,908 / Month | **~$9,626 / Month** |
| **Storage (2TB Actual)** | ~$4,080 / Month | ~$480 / Month | **~$480 / Month** |
| **Total Cost** | **~$48,370 / Month** | **~$37,388 / Month** | **~$10,106 / Month** |
| **Savings vs. Current** | - | **~$10,982 / Month (23%)** | **~$38,264 / Month (79%)** |

### How "Low-Power Standby" Works (Vertical Resizing)
1. **Vertical Auto-scaling (Instance Resizing):** Instead of stopping the cluster, we dynamically resize the instance. 
    - **Busy Window (9 AM - 6 PM):** Scale up to **64 vCPU / 512 GB RAM**.
    - **Standby Window (Off-hours/Weekends):** Scale down to **2 vCPU / 16 GB RAM**.
2. **Ready for Traffic:** The instance remains **Always-On**. It can process queries immediately, even at 2:00 AM, though at a lower throughput.
3. **Storage Elasticity:** You only pay for the ~1.6TB used. No more paying for "empty" 10TB of SSD space.

### Annual Financial Impact
- **Current Annual Spend:** ~$580,440 USD / Year
- **Projected Annual Spend (Low-Power Standby):** ~$121,272 USD / Year
- **Estimated Annual Savings:** **~$459,168 USD / Year (~79% reduction)**

## 4. Migration Strategy
### Recommended Approach: Database Migration Service (DMS)
1. **Preparation:** Create an AlloyDB Cluster and Instance in `europe-west-2`.
2. **Connectivity:** Use VPC Peering or PSC for secure internal data transfer.
3. **Resizing Automation:** Implement a **Cloud Scheduler + Cloud Function** to call the AlloyDB API (`instances.patch`) to resize the instance:
    - Monday-Friday 08:45 AM: Resize to 64 vCPU.
    - Monday-Friday 06:15 PM: Resize to 2 vCPU.
4. **Cutover:** Once the replication lag is near zero, point the application to the new AlloyDB endpoint.

## 5. Technical Considerations
- **Resizing Downtime:** Resizing an AlloyDB instance typically involves a **2-3 minute period** where connections are dropped as the instance restarts with new hardware. The HA secondary instance helps mitigate this, but expect a brief disruption during the 9 AM/6 PM transition.
- **Standby Performance:** At 2 vCPU, the database can handle background tasks and low-volume queries, but heavy reporting or bulk loads should be avoided until the 9 AM scale-up.
- **Read Pool Scaling:** The Read Pool nodes can also be scaled down to zero during standby to save additional costs.

## 6. Migration Project Plan
- **Phase 1: Discovery & Sizing (Week 1):** Audit extensions. Confirm 2vCPU is sufficient for baseline standby tasks.
- **Phase 2: Resizing Automation Pilot (Week 2):** Test the `instances.patch` API calls and verify application reconnect logic during the 2-minute resize window.
- **Phase 3: Production Sync (Week 3):** Start DMS job. Monitor replication lag.
- **Phase 4: Cutover & Activation (Week 4):** Final cutover. Activate the daily resizing schedule.

## 7. Benefit Report
- **Always-On Availability:** Meets your requirement for constant readiness without the cost of full-scale idle resources.
- **79% Cost Reduction:** Massive savings by aligning resource power with actual business hours.
- **Modern Infrastructure:** Automatic storage scaling and faster HA failover compared to Cloud SQL.

---

# AlloyDB 迁移项目计划与收益报告 (中文)

## 1. 执行摘要
本报告针对现有 Cloud SQL for PostgreSQL 实例迁移至 AlloyDB for PostgreSQL 提供了详细的分析和项目计划。基于您的特定负载模式（每周 5 天，每天 8 小时忙碌）以及**“随时接受流量”**的待机需求，通过利用 **AlloyDB 的“低功耗待机”模式**，可以获得极高的成本优化空间。

## 2. 当前状态分析
### 源实例：Cloud SQL for PostgreSQL
- **规格**：96 vCPU / 768 GB 内存 / 12 TB SSD
- **工作负载模式**：每周忙碌约 40 小时（8h/天，5天/周）；其余时间闲置。
- **当前需求**：实例必须 24/7 保持在线并响应，随时能够立即处理流量，不接受完全停机。

### 关键指标观察 (过去 30 天)
- **CPU 利用率**：P99 为 41.9%，但 P50 仅为 1.18%。这表明在非工作时间，大量的计算资源被闲置且产生了高昂费用。
- **磁盘利用率**：仅使用 13.3%（约 1.6 TB）。在 Cloud SQL 中，您必须为预留的 12 TB 全额付费。

## 3. 成本节省分析：低功耗待机
### 成本估算 (区域：`europe-west-2`，货币：USD)

| 组件 | Cloud SQL (现状) | AlloyDB (固定 64vCPU) | AlloyDB (低功耗待机) |
| :--- | :--- | :--- | :--- |
| **计算 (vCPU/RAM)** | ~44,290 USD / 月 | ~36,908 USD / 月 | **~9,626 USD / 月** |
| **存储 (按实际 2TB)** | ~4,080 USD / 月 (预留) | ~480 USD / 月 | **~480 USD / 月** |
| **总计成本** | **~48,370 USD / 月** | **~37,388 USD / 月** | **~10,106 USD / 月** |
| **较现状节省** | - | **~10,982 USD / 月 (23%)** | **~38,264 USD / 月 (79%)** |

### “低功耗待机”如何运作 (垂直自动缩容)
1. **垂直规格调整 (Instance Resizing)**：不停止集群，而是动态调整实例规格。
    - **忙碌时段 (09:00 - 18:00)**：自动调优至 **64 vCPU / 512 GB RAM**。
    - **待机时段 (夜间及周末)**：自动降级至 **2 vCPU / 16 GB RAM**。
2. **随时响应**：实例保持 **Always-On** 状态。即使在凌晨 2 点，它也能立即处理查询，只是吞吐量较低。
3. **存储弹性**：您只需为实际使用的 ~1.6TB 空间付费。不再需要为闲置的 10TB SSD 空间买单。

### 年度财务影响
- **当前年支出**：约 580,440 USD / 年
- **待机模式下预计年支出**：约 121,272 USD / 年
- **预计年度节省**：**约 459,168 USD / 年 (降低约 79%)**

## 4. 迁移策略
### 推荐方案：Database Migration Service (DMS)
1. **准备阶段**：创建 AlloyDB 集群。
2. **规格调整自动化**：利用 **Cloud Scheduler + Cloud Function**，调用 AlloyDB API (`instances.patch`) 定时调整规格：
    - 工作日 08:45：调整至 64 vCPU。
    - 工作日 18:15：调整至 2 vCPU。
3. **数据同步**：通过 DMS 进行持续 CDC 同步。
4. **切换**：将应用连接指向 AlloyDB。

## 5. 技术注意事项
- **调整期间的瞬断**：AlloyDB 调整实例规格时，通常会有 **2-3 分钟** 的重启时间，期间连接会中断。虽然高可用（HA）次级实例可以加速恢复，但建议在 09:00 和 18:00 切换时，应用层具备重连机制。
- **待机性能**：在 2 vCPU 规格下，数据库可以处理背景任务和小流量查询，但应避免在此时段执行重型报表或批量导入。
- **读线程池**：读线程池节点在待机时段也可以缩减至零，以进一步节省费用。

## 6. 迁移项目计划
- **第一阶段 (第 1 周)**：审计扩展，确认 2vCPU 足以支撑待机期间的基础任务。
- **第二阶段 (第 2 周)**：测试 `instances.patch` API 自动化脚本，验证 2 分钟调整窗口内的应用重连逻辑。
- **第三阶段 (第 3 周)**：启动 DMS 同步，监控延迟。
- **第四阶段 (第 4 周)**：正式切换，并激活每日定时缩容调度。

## 7. 收益报告
- **全天候可用性**：满足您随时响应的需求，无需为闲置资源支付全额费用。
- **79% 成本降幅**：通过将资源规模与实际营业时间匹配，实现极致的性价比。
- **架构现代化**：相比 Cloud SQL 提供更快的故障转移和完全自动化的存储管理。

---
**报告生成人**：Trae 助手
**日期**：2026-03-13
