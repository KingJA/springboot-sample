如果表数据中有createTime和updateTime，如果没指定数据，则存入数据库的是null

//用这个注解才能实现动态更新（update_time的更新）
@DynamicUpdate


不差入null值
修改的时候，先查到数据对象再修改，别直接new

save（）方法
除非直到显式调用flush（） 或commit（）方法，否则与save操作相关联的数据将不会刷新到DB。
saveAndFlush（）方法
在执行期间立即刷新数据。

注解方式：更新/删除必须要开启事务

注解方式的insert,select需要加入nativeQuery = true属性

如果返回值是int，则操作成功返回1，失败返回0