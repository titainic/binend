# 此项目只是技术架构，无任何业务。学习研究。和积累沉淀所写
## 项目模块说明：
![image](https://github.com/titainic/note-image/blob/master/model/web.png)
### 使用技术:spring mvc,spring,JdbcTemplate,maven
### 使用数据库：mysql,mongo.
### 使用缓存：redis

## 缓存redis说明：
### 情况一：第一次查询是查询redis中是否有需要数据（dao层查询参数作为redis的key）如果redis存在这个key，直接返回。
### 情况二：如果redis没有查询参数组成的key，查询数据库。并把查询的结果放入redis。查询参数作为redis的key，然后返回数据给dao层

### 缓存的使用是系统性能优化的方式之一，并不适合所有全部场景。合适大量查询，不平凡更新的数据。对数据的精准性要求不是很高。有性能提升。减轻数据库压力

### 定时任务，每天晚上12点定时删除redis缓存中的所有数据

# 缓存逻辑图：
![image](https://github.com/titainic/note-image/blob/master/model/redis.png)

# 联系方式：
## mail: binend@live.cn

