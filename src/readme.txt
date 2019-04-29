mvn package -DskipTests
mvn package -e

geowave官方源码打包命令
mvn package -P hbase-container-singlejar -DskipTests


<repository>
            <id>nexus-aliyun</id>
            <name>Nexus aliyun</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public</url>
            <releases>
                <enabled>true</enabled>
            </releases>
            <snapshots>
                <enabled>true</enabled>
            </snapshots>
        </repository>