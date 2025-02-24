# 🌟 Stargram - Instagram Clone Project

> **Stargram은 인스타그램의 핵심 기능을 구현한 SNS 플랫폼입니다.**
> **사용자는 사진, 동영상과 텍스트를 공유하며, 댓글과 좋아요를 통해 소통할 수 있습니다.**



---

## 📷 **프로젝트 개요**
### 🔹 Stargram이란?
Stargram은 **Instagram의 핵심 기능을 모방한 SNS 플랫폼**으로, 사용자가 **사진과 동영상을 공유하고, 댓글과 좋아요로 소통하며, 팔로우 시스템을 통해 개인화된 피드를 받을 수 있도록 설계**되었습니다.

### 🔹 주요 목표 및 특징
✅ **객체 지향 원칙(SOLID) 적용**  
✅ **Spring의 트랜잭션 관리(@Transactional) 활용**  
✅ **테스트 코드(JUnit) 기반 안정성 확보**  
✅ **GitHub PR & Code Review 방식으로 코드 품질 개선**  
✅ **GitHub Projects를 활용한 애자일 개발 진행**  
✅ **RESTful API 설계 및 문서화**


📌 **[Stargram API LIST](https://docs.google.com/spreadsheets/d/1piFu00NngGIWQvQfoORgb7A-OuY4zVjEH804PHcYPtA/edit?gid=0#gid=0)**  
📌 **[Stargram 애자일 프로젝트](https://github.com/orgs/f-lab-edu/projects/331)**  
📌 **[코드 규칙 (Naver Convention)](https://naver.github.io/hackday-conventions-java/#_intellij)**  

---

## 🛠 **기술 스택**
| 기술 분야 | 사용한 기술 |
|-----------|------------|
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.4.0 |
| **Database** | MySQL |
| **Version Control** | Git & GitHub |
| **Testing** | JUnit, Mockito |
| **Development Tools** | IntelliJ IDEA, Gradle |
| **Infrastructure** | Redis (세션 관리) |

---

## ⚙️ **주요 기능**
### 📌 **1. 사용자 인증 및 관리**
- ✅ JWT 기반 로그인
- ✅ 쿠키를 활용한 사용자 상태 검증
- ✅ 사용자 프로필 조회 및 수정 (구현예정)

### 📌 **2. 피드 시스템**
- ✅ 이미지/동영상 업로드  (구현예정)
- ✅ 댓글 작성 및 삭제
- ✅ 좋아요 기능

### 📌 **3. 팔로우/팔로잉 시스템**
- ✅ 사용자가 다른 유저를 팔로우/언팔로우
- ✅ 팔로워 기반 개인화 피드 제공 (구현예정)

### 📌 **4. 트랜잭션 관리**
- ✅ **Spring @Transactional 적용** → DB 무결성 보장
- ✅ 여러 DB 연산을 하나의 트랜잭션으로 처리

### 📌 **5. 테스트 코드 작성**
- ✅ JUnit 기반의 단위 테스트(Unit Test) 및 통합 테스트(Integration Test) 작성
- ✅ Mocking을 활용한 서비스 테스트 수행

---

## 🏗 **프로젝트 구조**
```
Stargram
│── src
│   ├── main
│   │   ├── java/com/stargram
│   │   │   ├── config         # 프로젝트 설정 (예외 처리, 보안, 키 관리 등)
│   │   │   ├── controller     # REST API 컨트롤러
│   │   │   ├── domain         # 비즈니스 로직
│   │   │   ├── entity         # DB 엔티티 및 DTO
│   │   │   ├── repository     # 데이터 접근 계층
│   │   │   ├── service        # 서비스 계층 (비즈니스 로직 처리)
│   │   ├── resources
│   │   │   ├── application.properties  # 환경 설정 파일
│   ├── test                   # JUnit 기반 테스트 코드
│── build.gradle               # 프로젝트 의존성 관리
```

---
