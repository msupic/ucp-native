# ucp-native

Contains a simple example which shows that usage of **com.oracle.database.jdbc:ucp:23.3.0.23.09** causes the following exception when running in a native image

```text
Exception in thread "main" java.lang.RuntimeException: java.lang.NoSuchMethodException: oracle.ucp.jdbc.proxy.oracle$1ucp$1jdbc$1proxy$1oracle$1ConnectionProxy$2oracle$1jdbc$1internal$1OracleConnection$$$Proxy.<init>(oracle.jdbc.internal.OracleConnection, java.lang.Object, oracle.ucp.proxy.ProxyFactory, java.util.Map)
	at oracle.ucp.proxy.ProxyFactory.prepareProxy(ProxyFactory.java:170)
	at oracle.ucp.proxy.ProxyFactory.createProxy(ProxyFactory.java:499)
	at oracle.ucp.proxy.ProxyFactory.proxyForCreate(ProxyFactory.java:274)
	at oracle.ucp.jdbc.proxy.oracle.OracleConnectionProxyFactory.proxyForCreate(OracleConnectionProxyFactory.java:60)
	at oracle.ucp.jdbc.proxy.oracle.OracleConnectionProxyFactory.proxyForConnection(OracleConnectionProxyFactory.java:66)
	at oracle.ucp.jdbc.PoolDataSourceImpl.getConnection(PoolDataSourceImpl.java:1930)
	at oracle.ucp.jdbc.PoolDataSourceImpl$3.build(PoolDataSourceImpl.java:4256)
	at oracle.ucp.jdbc.PoolDataSourceImpl.getConnection(PoolDataSourceImpl.java:1865)
	at oracle.ucp.jdbc.PoolDataSourceImpl.getConnection(PoolDataSourceImpl.java:1822)
	at oracle.ucp.jdbc.PoolDataSourceImpl.getConnection(PoolDataSourceImpl.java:1808)
	at org.example.UcpExample.main(UcpExample.java:17)
Caused by: java.lang.NoSuchMethodException: oracle.ucp.jdbc.proxy.oracle$1ucp$1jdbc$1proxy$1oracle$1ConnectionProxy$2oracle$1jdbc$1internal$1OracleConnection$$$Proxy.<init>(oracle.jdbc.internal.OracleConnection, java.lang.Object, oracle.ucp.proxy.ProxyFactory, java.util.Map)
	at java.base@17.0.10/java.lang.Class.checkMethod(DynamicHub.java:1038)
	at java.base@17.0.10/java.lang.Class.getConstructor0(DynamicHub.java:1204)
	at java.base@17.0.10/java.lang.Class.getConstructor(DynamicHub.java:2271)
	at oracle.ucp.proxy.ProxyFactory.prepareProxy(ProxyFactory.java:164)
	... 10 more
```

### Reproduce steps:
1. Start gvenzl/oracle-xe:slim-faststart container
    ```shell
    docker run -d --name oracle_ucp \
    -e ORACLE_PASSWORD=ucpsystem \
    -e APP_USER=ucptest \
    -e APP_USER_PASSWORD=ucptest \
    -p 1521:1521 \
    gvenzl/oracle-xe:slim-faststart
    ```
2. Wait the container to start
3. Run
    ```shell
    ./gradlew clean nativeRun
    ```
   
If **ucp** version is changed **23.3.0.23.09** from to **21.11.0.0** in build.gradle, **./gradlew clean nativeRun** is successfully executed.