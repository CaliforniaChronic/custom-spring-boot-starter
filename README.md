# Demo Custom Starter(a hello world starter)
一個簡單的自定義Spring Boot Starter demo 與應用說明
- 參考[mybatis-spring-boot-starter](https://github.com/mybatis/spring-boot-starter/tree/master/mybatis-spring-boot-starter) 的方式拆分為兩個部分
    - 啟動器 -> hsu-spring-boot-starter，負責處理依賴管理
    - 自動配置 -> hsu-spring-boot-autoconfigure，負責處理自動配置
- 引用啟動器

![image](https://github.com/CaliforniaChronic/pics/blob/main/cus-starter-pom-dependency.png)

- 配置提示

![image](https://github.com/CaliforniaChronic/pics/blob/main/metadata-description.png)

## 什麼是Spring Boot Starter
管理引用依賴是一個麻煩的工作，Spring Boot 官方提供了大量starters 用來集成常用組件(ex. AOP、JPA、Redis etc.)。starter 是一個「連接結合」用的組件，本身沒有太多程式，starter 定義了目標項目所需要的配置和引用，把我們的專案和目標項目結合起來。例如我們的專案要使用Redis，只需要引入spring-boot-starter-data-redis 就可以開始使用，添加配置類、配置文件與RedisTemplate 操作。簡化了開發者自行配置各種依賴的工作。Starters 讓相關的技術使用起來變得很簡單，隨插即用，開發者不用再去尋找各種的dependencies 來複製貼上，而轉變為專注取得對應Starter 即可。

![image](https://github.com/CaliforniaChronic/pics/blob/main/starters-01.png)

## 應用自定義starter 
透過自定義starter 用模組化的方式整合企業內部中間件，或是將開發的功能包裝好設定與依賴提供給別人引用，達到
- 簡化依賴管理和配置
- 降低開發人員學習成本，提高工作效率 
- 統一管理、可複用，內部開發規範與技術標準一致
- 依場景切換profile 搭配不同設定值，或是使用自定義starter 預先配上的設定值(ex. localhost)
![image](https://github.com/CaliforniaChronic/pics/blob/main/starters-02.png)

## Spring Boot Profile
透過Spring Boot Profile 切換的方式面對多環境開發不同對應的配置。例如正式區、測試區、開發區對應的資料庫和中間件設定都不相同，我們搭配以下配置文件
- applcation.yml -> 公用配置
- application-prod.yml -> 正式區配置
- application-test.yml -> 測試區配置
- application-dev.yml -> 開發區配置

使用啟動參數來切換profile 使用對應的配置文件，例如：
- java -jar xxxxx.jar --spring.profiles.active=dev
- Docker 可以在Dockerfile 的ENTRYPOINT 指定切換，或是用傳參的方式在Docker run 的時候賦值

application 文件加載的順序是如何呢? 以指定spring.profiles.active=dev 為例
1. applcation.yml 優先加載
2. 再來是application-dev.yml
    - 兩邊都有的屬性則application-dev.yml 中的值會覆蓋掉applcation.yml 裡的
    - 如果是application-dev.yml 裡面沒有的屬性，則會採用applcation.yml 的值生效
