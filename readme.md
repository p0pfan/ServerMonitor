### Server Monitor Web Application

---

#### 1. Introduction
This web Application can be used to monitor the CPU and Memory usage **Linux server**. It has a Connect page and a Monitor page.  

![Connect Page](http://7xrn7f.com1.z0.glb.clouddn.com/16-6-21/20821681.jpg)  
![Monitor Page](http://7xrn7f.com1.z0.glb.clouddn.com/16-6-21/70555586.jpg)  
![DataBase](http://7xrn7f.com1.z0.glb.clouddn.com/16-6-21/30188260.jpg)  

---

#### 2. Main Feature

- Login Page
    - Server Connection by using server IP, username, password;
    - If server is connected, it will jump to the monitor page or it will stay in this page. 


- Monitor Page
    - "Disconnect" button is used to disconnect the server.
    - "REFRESH" checkbox is used to refresh the server usage every 2sec.
    - Two charts show the usage change.

- Metric Collection job 
    - the web server collects the CPU and Memory usage of remote server from the front end every 30sec. Data is stored into MySQL.

---

#### 3. Technique Selection
- Environment
    - eclipse  Mars.2 Release (4.5.2)
    - Maven
    - JDK 1.8
    - tomcat 8.0
    - MySQL 5.7.12
    - springframework 4.1.1

- Back-end
    - springMVC 

- Front-end
    - Highcharts.js
    - jQuery
    - Ajax

- DataBase
    - MySQL

---

#### 4. Setting
`/servermonitor/src/main/resources/property.properties`

```
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?useSSL=false
jdbc.username=root
jdbc.password=1234
```