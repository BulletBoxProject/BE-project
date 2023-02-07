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
   6-1. [builder의 private화/정적 팩토리 메소드 활용](#6-1builder의-private화정적-팩토리-메소드-활용) <br>
   6-2. [FACADE 패턴](#6-2facade-패턴) <br>
   6-3. [계층 별 DTO 분리](#6-3계층-별-dto-분리) <br>
<br>

## 1.프로젝트 소개
### 항해 10기 A반 1조 [Bullet Box]
- 불렛저널을 통한 스케줄러 + 다이어리 서비스
- 단순히 할 일, 완료만 있는 TODOLIST가 아닌 다양한 속성을 가진 TODOLIST
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

![20230207_193822](https://user-images.githubusercontent.com/114788315/217222541-182a7204-20e7-4c14-9454-288be6d91209.png)

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
#### 6-1.builder의 private화/정적 팩토리 메소드 활용
<details>
<summary></summary>
<div markdown="1">       

builder , static

</div>
</details>

#### 6-2.FACADE 패턴
<details>
<summary></summary>
<div markdown="1">       

파사드 패턴


</div>
</details>

#### 6-3.계층 별 DTO 분리
<details>
<summary></summary>
<div markdown="1">       

계층 별 Dto 분리

</div>
</details>



## 👥팀원소개
### BackEnd
[김민호](https://github.com/minokim1080) <br>
[한교진](https://github.com/hangj97) <br>
[전재경](https://github.com/Jaekyeong1)
