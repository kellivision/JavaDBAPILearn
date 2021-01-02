# JavaDBAPILearn
Learning automating Java connecting to MySQL DB, pulling data and pushing the data to an API

# Details
Create a MySQL DB. I created my DB by pulling a MySQL docker image:
[Pull from docker](https://hub.docker.com/_/mysql)

Start the container, then log in using Powershell (Windows):

`docker exec -it JavaDBSQL mysql -uroot -p`

Create table using code from course:

```
CREATE DATABASE Business;

use Business;

CREATE TABLE CustomerInfo

(CourseName varchar(50),

PurchasedDate date,

Amount int(50),

Location varchar(50));



INSERT INTO CustomerInfo values("selenium",CURRENT_DATE(),120,'Africa');

INSERT INTO CustomerInfo values("Protractor",CURRENT_DATE(),45,'Africa');

INSERT INTO CustomerInfo values("Appium",CURRENT_DATE(),99,'Asia');

INSERT INTO CustomerInfo values("WebServices",CURRENT_DATE(),21,'Asia');

INSERT INTO CustomerInfo values("Jmeter",CURRENT_DATE(),76,'Asia');

```
Check it's all working as expected:
` select * from CustomerInfo;`
