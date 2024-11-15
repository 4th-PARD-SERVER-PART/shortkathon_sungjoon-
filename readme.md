![image](https://github.com/user-attachments/assets/cd7ebe98-d2e1-44dc-944c-b2b9fe26c978)
# **Keeper**

## **프로젝트 소개**
서비스명 : Keeper

서비스 소개 : 익명성이 보장된 중학교 내 학교 폭력 신고 서비스

>학생 익명 신고 기능(텍스트 첨부 기능, 녹음)

### **주요 기능**
- **사용자 관리**
    - 새로운 사용자 생성.
    - 사용자가 작성한 게시물 목록 조회.

- **게시물 관리**
    - 게시물 생성(음성 파일 업로드 포함).
    - 게시물 상세 조회.
    - 게시물 삭제.

---

## **기술 스택**
- **백엔드**: Spring Boot, Java
- **데이터베이스**: JPA (Hibernate 사용)
- **문서화**: Swagger (OpenAPI)
- **파일 처리**: MultipartFile (Spring 내장 기능 사용)
- **빌드 도구**: Gradle 또는 Maven (설치 시 따라 선택)

---

## **설치 및 실행 방법**


## **API 문서**

### **사용자 관리**

#### **POST /user**
- **설명**: 새로운 사용자를 생성합니다.
- **요청 예시**:
```json
{
  "username": "홍길동"
}
```
- **응답**: 201 Created

#### **GET /user/{userId}/list**
- **설명**: 특정 사용자가 작성한 게시물 목록을 조회합니다.
- **응답 예시**:
```json
[
  {
    "title": "게시물 제목 1"
  },
  {
    "title": "게시물 제목 2"
  }
]
```

### **게시물 관리**

#### **POST /post/create**
- **설명**: 새로운 게시물을 생성합니다.
- **요청 파라미터**:
    - `title`: 게시물 제목 (필수)
    - `file`: 업로드할 음성 파일 (선택)
- **응답**: 201 Created

#### **GET /post/detail/{postId}**
- **설명**: 게시물 ID를 기준으로 상세 정보를 조회합니다.
- **응답 예시**:
```json
{
  "postId": 1,
  "title": "게시물 제목",
  "audioFilePath": "/uploads/audio.mp3"
}
```

#### **DELETE /post/delete/{postId}**
- **설명**: 게시물 ID를 기준으로 게시물을 삭제합니다.
- **응답**: 204 No Content

---

## **프로젝트 구조**
```plaintext
├── src
│   ├── main
│   │   ├── java/com/example/shortcarton
│   │   │   ├── post
│   │   │   │   ├── controller
│   │   │   │   ├── dto
│   │   │   │   ├── entity
│   │   │   │   ├── repository
│   │   │   │   └── service
│   │   │   ├── user
│   │   │   │   ├── controller
│   │   │   │   ├── dto
│   │   │   │   ├── entity
│   │   │   │   ├── repository
│   │   │   │   └── service
│   │   │   └── config
│   │   └── resources
│   │       └── application.properties
├── build.gradle
└── README.md
```

---

## **ERD (Entity-Relationship Diagram)**

### **다이어그램**
![shortcarton_erd (2)](https://github.com/user-attachments/assets/6ffb36f3-f394-434a-ac6b-6d0fdf83a0e4)

#### **User**
- `id` (Primary Key): 사용자 ID
- `username`: 사용자 이름

#### **Post**
- `id` (Primary Key): 게시물 ID
- `title`: 게시물 제목
- `audioFilePath`: 음성 파일 경로
- `user_id` (Foreign Key): 작성자 ID (User 테이블과 연결)

#### **관계**
- **User** ↔ **Post**: 1:N 관계 (사용자는 여러 게시물을 작성할 수 있음)

---

