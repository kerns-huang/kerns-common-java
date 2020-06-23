### 主要作用

对spring-boot-starter-data-mongodb 的增强
比如 对 BigDecimal 的支持，默认情况下，mongo不支持对BigDecimal的操作，需要转换成Double,
这么做的好处，在账户系统里面算法的操作基本上需要BigDecimal去操作，这样做可以在程序的内部实现类型的统一。
不用在BigDecimal和Double之间来回转。