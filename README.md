# binend
项目模块说明：
![image](https://github.com/titainic/note-image/blob/master/model/web.png)

## 缓存redis说明：
### 情况一：第一次查询是查询redis中是否有需要数据（dao层查询参数作为redis的key）如果redis存在这个key，直接返回。
### 情况二：如果redis没有查询参数组成的key，查询数据库。并把查询的结果放入redis。查询参数作为redis的key，然后返回数据给dao层

缓存逻辑图：
![image](https://github.com/titainic/note-image/blob/master/model/redis.png)
