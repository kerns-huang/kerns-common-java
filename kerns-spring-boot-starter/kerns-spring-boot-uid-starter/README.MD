### 主要作用

全局ID生成器，基于[雪花算法](https://github.com/twitter/snowflake)，目前来说市面上已经好几种成熟的工具了


### 类似的

#### [uid-generator](https://github.com/baidu/uid-generator)
 通过 BitsAllocator 生成一秒钟内的所有ID数据
 通过 RingBuffer 把一秒中内的数据缓存起来
 后续所有的数据通过 ringBuffer去读取，
 进一步提升性能问题，解决内存爆炸的问题
#### [epi-uid](https://github.com/linhuaichuan/ecp-uid)