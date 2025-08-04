- 캘린더 및 댓글 기능 구현 스프링 부트
- MySQL 사용
- 비밀번호는 사용자에게 보이지 않도록
- 이 부분은 gpt의 도움을 받음. 아직도 readme.md 의 작성법이 어색하고 잘 모르겠음. 더 공부해봐야겠다. 

#### 1-1. 일정 생성 (CREATE)
* **HTTP Method**: `POST`
* **Endpoint**: `/calendars`
* **Request Body (JSON)**:
    -   `title`: 일정 제목 (최대 30자 이내, 필수값)
    -   `content`: 일정 내용 (최대 200자 이내, 필수값)
    -   `name`: 작성자명 (필수값)
    -   `password`: 비밀번호 (필수값)

    ```json
    {
      "title": "Spring Boot 스터디 일정",
      "content": "JPA와 RESTful API에 대해 학습합니다.",
      "name": "홍길동",
      "password": "1234"
    }
    ```
* **Response Body (JSON)
    ```json
    {
      "id": 1,
      "title": "Spring Boot 스터디 일정",
      "content": "JPA와 RESTful API에 대해 학습합니다.",
      "name": "홍길동",
      "creationTime": "2025-08-04T10:00:00.123456",
      "modificationTime": "2025-08-04T10:00:00.123456"
    }
    ```
  
#### 1-2. 전체 일정 조회 (READ ALL)

* **HTTP Method**: `GET`
* **Endpoint**: `/calendars`
* **Request**:
    -   `Query Parameter` (선택 사항): `name` (특정 작성자명으로 필터링)
* **Response Body (JSON)**:
    ```json
    [
      {
        "id": 1,
        "title": "Spring Boot 스터디",
        "content": "...",
        "name": "홍길동",
        "creationTime": "...",
        "modificationTime": "..."
      },
      {
        "id": 2,
        "title": "여행 계획",
        "content": "...",
        "name": "김철수",
        "creationTime": "...",
        "modificationTime": "..."
      }
    ]
    ```

#### 1-3. 일정 단건 조회 (READ)
* **HTTP Method**: `GET`
* **Endpoint**: `/calendars/{calendarId}`
* **Request**:
    -   `Path Variable`: `calendarId` (조회할 일정의 ID)
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
        -   해당 일정과 그 일정에 속한 모든 댓글을 반환합니다.
    ```json
    {
      "id": 1,
      "title": "Spring Boot 스터디 일정",
      "content": "JPA와 RESTful API에 대해 학습합니다.",
      "name": "홍길동",
      "creationTime": "...",
      "modificationTime": "...",
      "comments": [
        {
          "id": 1,
          "content": "댓글 내용입니다.",
          "name": "댓글 작성자",
          "creationTime": "...",
          "modificationTime": "..."
        },
        {
          "id": 2,
          "content": "두 번째 댓글",
          "name": "다른 작성자",
          "creationTime": "...",
          "modificationTime": "..."
        }
      ]
    }
    ```
    -   **실패 (HTTP 404 Not Found)**
        -   해당 `calendarId`를 찾을 수 없는 경우
    ```json
    {
      "timestamp": "...",
      "status": 404,
      "error": "Not Found",
      "message": "해당 캘린더를 찾을 수 없습니다.",
      "path": "/calendars/999"
    }
    ```

#### 1-4. 일정 수정 (UPDATE)

* **API명**: 일정 수정
* **HTTP Method**: `PUT`
* **Endpoint**: `/calendars/{calendarId}`
* **Request Body (JSON)**:
    -   `title`: 변경할 일정 제목 (최대 30자 이내, 필수값)
    -   `content`: 변경할 일정 내용 (최대 200자 이내, 필수값)
    -   `name`: 작성자명 (필수값)
    -   `password`: 비밀번호 (필수값)
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "title": "수정된 일정 제목",
      "content": "수정된 일정 내용입니다.",
      "name": "홍길동",
      "creationTime": "...",
      "modificationTime": "..."
    }
    ```
    -   **실패 (HTTP 400 Bad Request)**
        -   비밀번호 불일치 시
    ```json
    {
      "timestamp": "...",
      "status": 400,
      "error": "Bad Request",
      "message": "비밀번호가 일치하지 않습니다.",
      "path": "/calendars/1"
    }
    ```

#### 1-5. 일정 삭제 (DELETE)
* **HTTP Method**: `DELETE`
* **Endpoint**: `/calendars/{calendarId}`
* **Request Body (JSON)**:
    -   `password`: 일정 삭제를 위한 비밀번호 (필수값)
    ```json
    {
      "password": "1234"
    }
    ```
* **Response Body**:
    -   **성공 (HTTP 204 No Content)**
        -   성공적으로 삭제되면 본문 없이 응답합니다.
    -   **실패 (HTTP 400 Bad Request)**
        -   비밀번호 불일치 시
    ```json
    {
      "timestamp": "...",
      "status": 400,
      "error": "Bad Request",
      "message": "비밀번호가 일치하지 않습니다.",
      "path": "/calendars/1"
    }
    ```


### 2. 댓글 기능
#### 2-1. 댓글 생성 (CREATE)
* **HTTP Method**: `POST`
* **Endpoint**: `/comments`
* **Request Body (JSON)**:
    -   `content`: 댓글 내용 (최대 100자 이내, 필수값)
    -   `name`: 작성자명 (필수값)
    -   `password`: 비밀번호 (필수값)
    -   `calendarId`: 댓글을 달 일정의 ID (필수값)
    ```json
    {
      "content": "좋은 내용 감사합니다!",
      "name": "박영희",
      "password": "5678",
      "calendarId": 1
    }
    ```
* **Response Body (JSON)**:
    -   **성공 (HTTP 200 OK)**
    ```json
    {
      "id": 1,
      "content": "좋은 내용 감사합니다!",
      "name": "박영희",
      "creationTime": "...",
      "modificationTime": "..."
    }
    ```
    -   **실패 (HTTP 400 Bad Request)**
        -   댓글 10개 제한 초과 시
    ```json
    {
      "timestamp": "...",
      "status": 400,
      "error": "Bad Request",
      "message": "하나의 일정에는 댓글을 10개까지만 작성할 수 있습니다.",
      "path": "/comments"
    }
    ```

#### 2-2. 댓글 삭제 (DELETE)

* **API명**: 댓글 삭제
* **HTTP Method**: `DELETE`
* **Endpoint**: `/comments/{commentId}`
* **Request Body (JSON)**:
    -   `password`: 댓글 삭제를 위한 비밀번호 (필수값)
    ```json
    {
      "password": "5678"
    }
    ```
* **Response Body**:
    -   **성공 (HTTP 204 No Content)**
        -   성공적으로 삭제되면 본문 없이 응답합니다.


### 3. ERD (Entity-Relationship Diagram)

프로젝트의 주요 데이터베이스 테이블과 그 관계를 나타낸 ERD

#### 테이블 목록 및 관계

**1. `calendar_entity`**
-   **용도**: 사용자 일정 정보를 저장합니다.
-   **속성**:
    -   `id`: `BIGINT` - 기본 키(PK), 자동 증가
    -   `title`: `VARCHAR(30)` - 일정 제목
    -   `content`: `VARCHAR(200)` - 일정 내용
    -   `name`: `VARCHAR(255)` - 작성자명
    -   `password`: `VARCHAR(255)` - 비밀번호
    -   `creation_time`: `DATETIME` - 생성 시간
    -   `modification_time`: `DATETIME` - 수정 시간

**2. `comment_entity`**
-   **용도**: 각 일정에 대한 댓글을 저장합니다.
-   **속성**:
    -   `id`: `BIGINT` - 기본 키(PK), 자동 증가
    -   `content`: `VARCHAR(255)` - 댓글 내용
    -   `name`: `VARCHAR(255)` - 작성자명
    -   `password`: `VARCHAR(255)` - 비밀번호
    -   `creation_time`: `DATETIME` - 생성 시간
    -   `modification_time`: `DATETIME` - 수정 시간
    -   `calendar_id`: `BIGINT` - 외래 키(FK), `calendar_entity`의 `id` 참조

#### 관계
-   **`calendar_entity` (1) : (N) `comment_entity`**
    -   하나의 일정(캘린더)은 여러 개의 댓글을 가질 수 있습니다.
    -   하나의 댓글은 반드시 하나의 일정에만 속합니다.
    -   `comment_entity`의 `calendar_id`가 `calendar_entity`의 `id`를 참조하는 `1:N` 관계입니다.
