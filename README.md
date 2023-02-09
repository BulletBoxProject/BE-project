# 📦 Bullet Box(불렛 박스)

### 내가 직접 의미를 부여하는 나만의 투두 리스트
![20230206_163404](https://user-images.githubusercontent.com/114788315/216911688-e54d1fdb-88aa-4340-addb-7a8a964e518e.png)

### [📦 Bullet Box 둘러보기](https://bullet-box.com)
##
### 목차
1. [프로젝트 소개](#1프로젝트-소개)
2. [프로젝트 아키텍처](#2프로젝트-아키텍처) <br>
   2-1. [서비스 아키텍처](#2-1서비스-아키텍처) <br>
   2-2. [패키지 구조](#2-2패키지-구조) <br>
3. [코드 컨벤션](#3코드-컨벤션)
4. [ERD](#4erd)
5. [API 명세](#5api-명세)
6. [기술적 의사결정](#6기술적-의사결정) <br>
   6-1. [builder의 private화/정적 팩토리 메소드 활용](#6-1) <br>
   6-2. [FACADE 패턴](#6-2) <br>
   6-3. [계층 별 DTO 분리](#6-3) <br>
<br>

## 1.프로젝트 소개
### 항해 10기 A반 1조 [Bullet Box]
- **불렛저널**을 통한 **스케줄러 + 다이어리** 서비스
- 단순히 할 일, 완료만 있는 TODOLIST가 아닌 **다양한 속성**을 가진 TODOLIST
- 서비스 환경: 웹/모바일 모두 가능합니다 <br>
<br>

<div align=center> 

<img src = "https://user-images.githubusercontent.com/114788315/217217590-dd472c9d-c5cf-44b8-85ff-0db4a4668590.png" width="45%" height="45%">
<img src = "https://user-images.githubusercontent.com/114788315/217217177-88925601-9557-40d3-9d6d-61231aff948b.png" width="45%" height="45%">
<img src = "https://user-images.githubusercontent.com/114788315/217217188-0db036c7-6a35-4086-9d08-944b8ee5b2aa.png" width="45%" height="45%">
<img src = "https://user-images.githubusercontent.com/114788315/217217191-f94ceedf-51bc-4801-953f-444c04cf854a.png" width="45%" height="45%">

</div>

## 2.프로젝트 아키텍처
### 🛠기술 스택

![Git](https://img.shields.io/badge/Git-F05032.svg?&style=for-the-badge&logo=Git&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-181717.svg?&style=for-the-badge&logo=GitHub&logoColor=white)
![GitHubActions](https://img.shields.io/badge/GitHubActions-2088FF.svg?&style=for-the-badge&logo=GitHubActions&logoColor=white)
![SpringBoot](https://img.shields.io/badge/Spring_Boot-6DB33F.svg?&style=for-the-badge&logo=SpringBoot&logoColor=white)
![AmazonAWS](https://img.shields.io/badge/AWS-232F3E.svg?&style=for-the-badge&logo=AmazonAWS&logoColor=white)
![AmazonS3](https://img.shields.io/badge/S3-569A31.svg?&style=for-the-badge&logo=AmazonS3&logoColor=white)
![AmazonRDS](https://img.shields.io/badge/RDS-527FFF.svg?&style=for-the-badge&logo=AmazonRDS&logoColor=white)
![AmazonEC2](https://img.shields.io/badge/EC2-FF9900.svg?&style=for-the-badge&logo=AmazonEC2&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?&style=for-the-badge&logo=Gradle&logoColor=white)
![SpringSecurity](https://img.shields.io/badge/SpringSecurity-6DB33F.svg?&style=for-the-badge&logo=SpringSecurity&logoColor=white)
![JSONWebTokens](https://img.shields.io/badge/JWT-000000.svg?&style=for-the-badge&logo=JSONWebTokens&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1.svg?&style=for-the-badge&logo=MySQL&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D.svg?&style=for-the-badge&logo=Redis&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED.svg?&style=for-the-badge&logo=Docker&logoColor=white)
![NGINX](https://img.shields.io/badge/NGINX-009639.svg?&style=for-the-badge&logo=NGINX&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D.svg?&style=for-the-badge&logo=Swagger&logoColor=white)
![Slack](https://img.shields.io/badge/Slack-4A154B.svg?&style=for-the-badge&logo=Slack&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000.svg?&style=for-the-badge&logo=Notion&logoColor=white)



### 2-1.서비스 아키텍처
![서비스 아키텍처](https://user-images.githubusercontent.com/114788315/216919764-69332d7f-2e5b-4e04-a0f0-98c091504831.png)

### 2-2.패키지 구조

<details>
<summary>패키지 구조</summary>
<div markdown="1">       

![패키지구조](https://user-images.githubusercontent.com/114788315/217496579-3f189ebe-42d7-475b-9fa8-eb6bdb24c4eb.png)

</div>
</details>

## 3.코드 컨벤션
### [🔍 NOTION 문서](https://www.notion.so/Back-end-Code-Convention-36a10590e9fe4614b6d429cf11c1188e)

## 4.ERD

![ERD](https://user-images.githubusercontent.com/114788315/217196568-b8182c69-4301-4cf7-81d3-73fdc273e137.png)

## 5.API 명세
<details>
<summary>API 명세서 </summary>
<div markdown="1">    
  
![20230206_204331](https://user-images.githubusercontent.com/114788315/216963712-6f20ee03-0738-49c3-98ca-a49a4d117136.png)

</div>
</details>

### [📜 API 명세](http://bulletbox.store:8080/swagger-ui/index.html#/)

## 6.기술적 의사결정
#### 6-1
<details>
<summary>builder의 private화/정적 팩토리 메소드 활용</summary>
<div markdown="1">       
<br>

본 프로젝트에선 객체 외부에서 객체를 생성할 때 **생성자** 대신 **static 메소드**를 사용합니다. <br>
**static 메소드에선 Builder를 사용하여 객체를 생성**합니다.

이때 Builder는 Lombok의 @Builder 어노테이션을 통해 구현하며, **생성자와 Builder는 모두 private으로 사용**합니다. <br>
그렇기에 **객체 외부에선 해당 객체의 생성자와 Builder를 모두 사용할 수 없고,** <br> 
**오로지 static 메소드를 통해서만 해당 객체를 생성**할 수 있습니다. <br>

![Untitled (1)](https://user-images.githubusercontent.com/114788315/217497571-19e8eb74-66db-4a72-bdec-9075fdb97225.png) <br>
Builder와 생성자의 private화 <br>
<br>
![Untitled (2)](https://user-images.githubusercontent.com/114788315/217497803-56fb59a7-1c36-437f-961a-1df1422beaff.png) <br>
static 메소드에서의 Builder 사용 <br>
<br>
![Untitled (3)](https://user-images.githubusercontent.com/114788315/217497853-6d53555a-1bd6-4dbf-8edf-9b97404da510.png) <br>
외부에서 static 메소드를 통해 객체를 생성하는 모습 <br>
<br>

**Builder는 매개 변수가 많아질 때 가독성이 높고, 작성하기가 쉽다는 장점**이 있습니다. <br>
하지만 생성자와 static 메소드처럼 객체의 형태를 강제할 수 없기에 **다양한 형태의 객체가 생성될 수 있다는 단점**이 있습니다. 

특히 Builder는 보통 public으로 만들어지기에 외부에서 이상한 형태의 객체가 무분별하게 생성될 수 있습니다. <br>
그럴 경우 Builder가 어디서 어떻게 사용되고 있는지 모르기에 디버깅하기가 더욱 어려워집니다.

하지만 **Builder를 private으로 한다면 Builder의 장점은 살리면서도 단점은 최소화할 수 있습니다.** <br>
혹여나 Builder로 인한 문제가 생기더라도 해당 객체만 디버깅하면 되기에 유지/보수가 쉬워집니다.

**static 메소드를 함께 사용하는 이유는 캡슐화 때문입니다.** <br>
static 메소드와 Builder를 함께 사용하면 내부 구현에 변화가 생겼을 때 그 파급 효과를 최소화 할 수 있습니다.

</div>
</details>

#### 6-2
<details>
<summary>FACADE 패턴</summary>
<div markdown="1">       
<br>

본 프로젝트에선 다음 그림과 같이 기존 Spring 웹 계층에서 Facade 계층을 추가하여 사용합니다.

![Untitled (5)](https://user-images.githubusercontent.com/114788315/217498356-e7a96a5d-b988-4790-a919-e0f95b53f86a.png) <br>
<br>
기존에는 Service 계층에서 비즈니스 로직과 데이터 접근이라는 두 가지 일을 한꺼번에 처리하였는데, <br>
Facade 계층을 도입하여 두 업무를 분리하였습니다. <br>
<br>

**Facade 계층**에서는 **비즈니스 로직을 처리**하고, **새로운 Service 계층**에서는 **데이터 접근과 관련된 업무만 진행**합니다.

**새로운 Service 계층만 Repository에 의존하며 Controller와 Facade 계층에서는 Repository에 의존하지 않고,** <br>
**Entity도 사용하지 않습니다.** 두 계층에서는 오로지 DTO 만 사용합니다. 

**바뀐 Service 계층에는 각 Entity와 Repository를 사용하기 위한 Service 클래스들이 존재**합니다. <br>
해당 클래스들의 이름은 해당 Entity 이름 + Service로 구성되어 있습니다. <br>

![Untitled (6)](https://user-images.githubusercontent.com/114788315/217498532-6085538f-fe41-46e5-a034-b791cb83d63e.png) <br>
Diary Entity를 사용하기 위한 DiaryService <br>
<br>

**Facade 계층을 추가함으로써 얻은 이점은 다음과 같습니다.** <br>
<br>

1. Entity 노출을 최소화하여 **Entity 사용으로 인해 발생할 수 있는 문제를 해결 및 예방**합니다. <br>
2. 여러 비즈니스 로직에서 **공통적으로 사용하는 메소드들을 새로운 Service 계층에서 관리**할 수 있습니다. <br>
3. 역할이 세분화되어 **코드의 재사용성이 높고, 기존보다 유지/보수가 수월**해집니다. <br>

</div>
</details>

#### 6-3
<details>
<summary>계층 별 DTO 분리</summary>
<div markdown="1">       
<br>
6-2에서 확인할 수 있듯이 저희의 웹 계층은 다음과 같이 이루어져 있습니다.<br>

![Untitled (5)](https://user-images.githubusercontent.com/114788315/217498978-2dd4be4d-c5fe-46ca-b59d-6c56b9b189db.png) <br>
본 프로젝트에선 Presentation Layer, Business Layer 에서 사용하는 DTO들을 크게 3종류로 분리하여 사용합니다. <br>
<br>

![Untitled (7)](https://user-images.githubusercontent.com/114788315/217499047-dfbb2fc0-cc1e-4483-ad89-2af675a76971.png) <br>
<br>

Controller에서 외부 요청을 받기 위한 **RequestDto**, 외부에 응답을 보내기 위한 **ResponseDto**, <br>
Business Layer에서 사용하기 위한 **EntityDto** 이렇게 3 종류로 사용합니다. <br>
<br>
**EntityDto는 특정 Entity를 대체하는 DTO**이며, Facade 계층에서 요청을 받거나 보낼 때는 주로 EntityDto를 사용합니다. <br>
<br>
**DTO를 분리해서 사용하여 얻는 이점은 다음과 같습니다.**<br>
<br>
<br>
1. Facade 계층에 있는 메소드들이 보편적인 DTO (EntityDto)를 매개변수로 받아 사용하므로 **메소드의 재사용성이 높아집니다.** <br>
또한 보편적인 DTO를 사용하기 때문에 다른 팀원이 작성한 코드의 흐름을 이해하기가 쉬워져 협업 능률이 올라갑니다. <br>
2. **DTO가 세분화되어 있기에 유지/보수가 수월해집니다.** <br>
예를 들어 Request 값이 변경되었을 때 해당 변경으로 인한 파급 효과는 보통 Controller 계층에만 미치게 됩니다.

</div>
</details>



## 👥팀원소개
### BackEnd
[김민호](https://github.com/minokim1080) <br>
[한교진](https://github.com/hangj97) <br>
[전재경](https://github.com/Jaekyeong1)
